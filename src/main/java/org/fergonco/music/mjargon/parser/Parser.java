package org.fergonco.music.mjargon.parser;

import static org.fergonco.music.mjargon.lexer.Lexer.CHORD;
import static org.fergonco.music.mjargon.lexer.Lexer.CHORD_LITERAL;
import static org.fergonco.music.mjargon.lexer.Lexer.CLOSE_PARENTHESIS;
import static org.fergonco.music.mjargon.lexer.Lexer.COLON;
import static org.fergonco.music.mjargon.lexer.Lexer.COMMENT;
import static org.fergonco.music.mjargon.lexer.Lexer.DRUMPATTERN;
import static org.fergonco.music.mjargon.lexer.Lexer.DYNAMICS;
import static org.fergonco.music.mjargon.lexer.Lexer.EQUALS;
import static org.fergonco.music.mjargon.lexer.Lexer.F;
import static org.fergonco.music.mjargon.lexer.Lexer.FF;
import static org.fergonco.music.mjargon.lexer.Lexer.FFF;
import static org.fergonco.music.mjargon.lexer.Lexer.FFFF;
import static org.fergonco.music.mjargon.lexer.Lexer.FORWARD_SLASH;
import static org.fergonco.music.mjargon.lexer.Lexer.ID;
import static org.fergonco.music.mjargon.lexer.Lexer.LINE_BREAK;
import static org.fergonco.music.mjargon.lexer.Lexer.MF;
import static org.fergonco.music.mjargon.lexer.Lexer.MINUS;
import static org.fergonco.music.mjargon.lexer.Lexer.MP;
import static org.fergonco.music.mjargon.lexer.Lexer.NUMBER;
import static org.fergonco.music.mjargon.lexer.Lexer.ON;
import static org.fergonco.music.mjargon.lexer.Lexer.OPEN_PARENTHESIS;
import static org.fergonco.music.mjargon.lexer.Lexer.P;
import static org.fergonco.music.mjargon.lexer.Lexer.PP;
import static org.fergonco.music.mjargon.lexer.Lexer.PPP;
import static org.fergonco.music.mjargon.lexer.Lexer.PPPP;
import static org.fergonco.music.mjargon.lexer.Lexer.PROGRESSION;
import static org.fergonco.music.mjargon.lexer.Lexer.REPEAT;
import static org.fergonco.music.mjargon.lexer.Lexer.RHYTHM;
import static org.fergonco.music.mjargon.lexer.Lexer.RHYTHM_EXPRESSION;
import static org.fergonco.music.mjargon.lexer.Lexer.SEQUENCE;
import static org.fergonco.music.mjargon.lexer.Lexer.SIGNATURE;
import static org.fergonco.music.mjargon.lexer.Lexer.TEMPO;
import static org.fergonco.music.mjargon.lexer.Lexer.TIME;
import static org.fergonco.music.mjargon.lexer.Lexer.VERTICAL_BAR;
import static org.fergonco.music.mjargon.lexer.Lexer.VOICES;
import static org.fergonco.music.mjargon.lexer.Lexer.WITH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.mjargon.lexer.Lexer;
import org.fergonco.music.mjargon.lexer.Token;
import org.fergonco.music.mjargon.model.Model;
import org.fergonco.music.mjargon.model.SemanticException;

public class Parser {
	private Token currentToken;
	private Token lastConsumed;

	private Model model;

	public Model parse(Token token) throws SyntaxException, SemanticException {
		model = new Model();
		this.currentToken = token;
		try {
			while (currentToken != null) {
				if (accept(VERTICAL_BAR)) {
					barline();
				} else if (accept(ID)) {
					id();
				} else if (accept(COMMENT)) {
					comment();
				} else if (accept(TEMPO)) {
					tempo();
				} else if (accept(VOICES)) {
					voices();
				} else if (accept(DYNAMICS)) {
					dynamics();
				} else if (accept(REPEAT)) {
					repeat();
				} else if (accept(LINE_BREAK)) {
					// no op
					currentToken = currentToken.next();
				} else {
					throw new SyntaxException(currentToken.getPosition(),
							"Unexpected token " + Lexer.getTokenName(currentToken.getType()));
				}
			}
		} catch (SyntaxException e) {
			int position = e.getPosition();
			if (position != -1) {
				int lineCount = 0;
				int lastStartLinePosition = 0;
				while (token.getPosition() <= position) {
					if (token.getType() == LINE_BREAK) {
						lastStartLinePosition = token.getPosition() + 1;
						lineCount++;
					}
					token = token.next();
				}
				throw new SyntaxException(
						"Error at line " + (lineCount + 1) + " position " + (position - lastStartLinePosition), e);
			} else {
				throw e;
			}
		}

		return model;
	}

	private void dynamics() throws SyntaxException {
		expect(DYNAMICS);
		ArrayList<Dynamic> dynamics = new ArrayList<>();
		while (true) {
			try {
				expect(VERTICAL_BAR);
			} catch (SyntaxException e) {
				break;
			}
			Dynamic dynamic;
			switch (currentToken.getType()) {
			case PPPP:
				expect(PPPP);
				dynamic = Dynamic.PPPP;
				break;
			case PPP:
				expect(PPP);
				dynamic = Dynamic.PPP;
				break;
			case PP:
				expect(PP);
				dynamic = Dynamic.PP;
				break;
			case P:
				expect(P);
				dynamic = Dynamic.P;
				break;
			case MP:
				expect(MP);
				dynamic = Dynamic.MP;
				break;
			case MF:
				expect(MF);
				dynamic = Dynamic.MF;
				break;
			case F:
				expect(F);
				dynamic = Dynamic.F;
				break;
			case FF:
				expect(FF);
				dynamic = Dynamic.FF;
				break;
			case FFF:
				expect(FFF);
				dynamic = Dynamic.FFF;
				break;
			case FFFF:
				expect(FFFF);
				dynamic = Dynamic.FFFF;
				break;
			case MINUS:
				expect(MINUS);
				dynamic = null;
				break;
			default:
				throw new SyntaxException(currentToken.getPosition(),
						"Dynamic expression expected (pppp, ppp, pp, p, mp, mf, f, ff, fff, ffff)");
			}
			dynamics.add(dynamic);
		}

		model.setDynamics(dynamics);
	}

	private void tempo() throws SyntaxException {
		expect(TEMPO);
		int tempo = Integer.parseInt(expect(NUMBER).getText());
		anyNumberOfVerticalBars();
		expect(LINE_BREAK);
		model.setTempo(tempo);
	}

	private void repeat() throws SyntaxException, SemanticException {
		expect(REPEAT);
		String label = expect(ID).getText();
		int times = Integer.parseInt(expect(NUMBER).getText());
		anyNumberOfVerticalBars();
		expect(LINE_BREAK);
		model.repeat(label, times);
	}

	private void comment() throws SyntaxException {
		expect(COMMENT);
	}

	private void id() throws SyntaxException, SemanticException {
		Token id = expect(ID);
		if (accept(EQUALS)) {
			variableDefinition(id);
		} else if (accept(COLON)) {
			label(id);
		} else {
			throw new SyntaxException(lastConsumed.getPosition(), "= or : expected");
		}
	}

	private void voices() throws SyntaxException {
		expect(VOICES);
		ArrayList<String> instruments = new ArrayList<>();
		try {
			while (true) {
				expect(VERTICAL_BAR);
				instruments.add(expect(ID).getText());
			}
		} catch (SyntaxException e) {
		}
		model.setInstruments(instruments.toArray(new String[instruments.size()]));
	}

	private void barline() throws SyntaxException, SemanticException {
		expect(VERTICAL_BAR);
		int instrumentIndex = 0;
		while (true) {
			if (accept(ID)) {
				Token id = expect(ID);
				String noteSequenceId = id.getText();
				int noteSequenceIndex = -1;
				try {
					expect(OPEN_PARENTHESIS);
					noteSequenceIndex = Integer.parseInt(expect(NUMBER).getText());
					expect(CLOSE_PARENTHESIS);
				} catch (SyntaxException e) {
				}
				try {
					expect(ON);
					String rhythmId = expect(ID).getText();
					model.addPitchedToBarline(instrumentIndex, noteSequenceId, noteSequenceIndex, rhythmId);
				} catch (SyntaxException e) {
					model.addDrumsToBarline(instrumentIndex, noteSequenceId);
				}
			} else if (accept(MINUS)) {
				expect(MINUS);
				model.addSilenceToBarline(instrumentIndex);
			}
			instrumentIndex++;
			try {
				expect(VERTICAL_BAR);
			} catch (SyntaxException e) {
				break;
			}
		}
		model.newBarline();
	}

	private void label(Token id) throws SyntaxException {
		expect(COLON);
		anyNumberOfVerticalBars();
		expect(LINE_BREAK);
		model.newLabel(id.getText());
	}

	private void anyNumberOfVerticalBars() {
		try {
			while (true) {
				expect(VERTICAL_BAR);
			}
		} catch (SyntaxException e) {
		}
	}

	private void variableDefinition(Token id) throws SyntaxException, SemanticException {
		expect(EQUALS);
		if (accept(TIME)) {
			timeSignature(id);
		} else if (accept(RHYTHM)) {
			rhythm(id);
		} else if (accept(CHORD)) {
			chordProgression(id);
		} else if (accept(SEQUENCE)) {
			noteSequence(id);
		} else if (accept(DRUMPATTERN)) {
			drums(id);
		}
	}

	private void drums(Token id) throws SyntaxException, SemanticException {
		expect(DRUMPATTERN);
		String expression = trimRhythmExpressionDelimiters(expect(RHYTHM_EXPRESSION).getText());
		Map<Character, String> mapping = getDrumsMapping();
		expect(ON);
		String timeSignatureId = expect(ID).getText();
		model.addDrums(id.getText(), expression, mapping, timeSignatureId);
	}

	private Map<Character, String> getDrumsMapping() throws SyntaxException {
		HashMap<Character, String> ret = new HashMap<>();
		if (accept(WITH)) {
			expect(WITH);
			while (accept(ID)) {
				String symbol = expect(ID).getText();
				if (symbol.length() > 1) {
					throw new SyntaxException(lastConsumed.getPosition(), "Symbol expected");
				}
				expect(EQUALS);
				String drumInstrument = expect(ID).getText();
				ret.put(symbol.charAt(0), drumInstrument);
			}
		}
		return ret;
	}

	private void noteSequence(Token id) throws SyntaxException, SemanticException {
		expect(SEQUENCE);
		if (accept(NUMBER)) {
			chordBasedNoteSequence(id);
		} else if (accept(CHORD_LITERAL)) {
			freeNoteSequence(id);
		} else {
			throw new SyntaxException(lastConsumed.getPosition(), "number or note expression expected");
		}
	}

	private void freeNoteSequence(Token id) throws SemanticException, SyntaxException {
		ArrayList<String> notes = new ArrayList<>();
		String note = expect(CHORD_LITERAL).getText();
		notes.add(note);
		try {
			while (true) {
				notes.add(expect(CHORD_LITERAL).getText());
			}
		} catch (SyntaxException e) {
			model.addMonofonicNoteSequence(id.getText(), notes.toArray(new String[notes.size()]));
		}
	}

	private void chordBasedNoteSequence(Token id) throws SyntaxException, SemanticException {
		ArrayList<Integer> notes = new ArrayList<>();
		notes.add(Integer.parseInt(expect(NUMBER).getText()) - 1);
		try {
			while (true) {
				notes.add(Integer.parseInt(expect(NUMBER).getText()) - 1);
			}
		} catch (SyntaxException e) {
			expect(ON);
			Token chordProgressionId = expect(ID);
			expect(OPEN_PARENTHESIS);
			int chordProgressionIndex = Integer.parseInt(expect(NUMBER).getText());
			expect(CLOSE_PARENTHESIS);
			model.addMonofonicNoteSequence(id.getText(), notes.toArray(new Integer[notes.size()]),
					chordProgressionId.getText(), chordProgressionIndex);
		}
	}

	private void chordProgression(Token id) throws SyntaxException {
		expect(CHORD);
		expect(PROGRESSION);
		ArrayList<String> chords = new ArrayList<>();
		chords.add(expect(CHORD_LITERAL).getText());
		try {
			while (true) {
				chords.add(expect(CHORD_LITERAL).getText());
			}
		} catch (SyntaxException e) {
		}
		model.addPolyphonicNoteSequence(id.getText(), chords.toArray(new String[chords.size()]));
	}

	private void rhythm(Token id) throws SyntaxException, SemanticException {
		expect(RHYTHM);
		Token expr = expect(RHYTHM_EXPRESSION);
		expect(ON);
		Token timeSignature = expect(ID);
		model.addRhythm(id.getText(), trimRhythmExpressionDelimiters(expr.getText()), timeSignature.getText());
	}

	private String trimRhythmExpressionDelimiters(String text) {
		return text.substring(1, text.length() - 1);
	}

	private void timeSignature(Token id) throws NumberFormatException, SyntaxException {
		expect(TIME);
		expect(SIGNATURE);
		int n1 = Integer.parseInt(expect(NUMBER).getText());
		expect(FORWARD_SLASH);
		int n2 = Integer.parseInt(expect(NUMBER).getText());
		model.addTimeSignature(id.getText(), n1, n2);
	}

	private boolean accept(int tokenType) {
		if (currentToken != null && currentToken.getType() == tokenType) {
			return true;
		} else {
			return false;
		}
	}

	private Token expect(int expectedTokenType) throws SyntaxException {
		if (currentToken != null && currentToken.getType() == expectedTokenType) {
			consumeToken();
			return lastConsumed;
		} else {
			int position = currentToken == null ? 0 : currentToken.getPosition();
			throw new SyntaxException(position, Lexer.getTokenName(expectedTokenType) + " expected");
		}
	}

	private void consumeToken() {
		lastConsumed = currentToken;
		currentToken = currentToken.next();
	}
}
