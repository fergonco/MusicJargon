package org.fergonco.music.mjargon.parser;

import static org.fergonco.music.mjargon.lexer.Lexer.BASSDRUM;
import static org.fergonco.music.mjargon.lexer.Lexer.BD;
import static org.fergonco.music.mjargon.lexer.Lexer.CHORD;
import static org.fergonco.music.mjargon.lexer.Lexer.CHORD_LITERAL;
import static org.fergonco.music.mjargon.lexer.Lexer.CLOSE_PARENTHESIS;
import static org.fergonco.music.mjargon.lexer.Lexer.COLON;
import static org.fergonco.music.mjargon.lexer.Lexer.COMMENT;
import static org.fergonco.music.mjargon.lexer.Lexer.CR;
import static org.fergonco.music.mjargon.lexer.Lexer.CRASH;
import static org.fergonco.music.mjargon.lexer.Lexer.DRUM;
import static org.fergonco.music.mjargon.lexer.Lexer.DRUM_INSTRUMENTS;
import static org.fergonco.music.mjargon.lexer.Lexer.DYNAMICS;
import static org.fergonco.music.mjargon.lexer.Lexer.EQUALS;
import static org.fergonco.music.mjargon.lexer.Lexer.F;
import static org.fergonco.music.mjargon.lexer.Lexer.FF;
import static org.fergonco.music.mjargon.lexer.Lexer.FFF;
import static org.fergonco.music.mjargon.lexer.Lexer.FFFF;
import static org.fergonco.music.mjargon.lexer.Lexer.FORWARD_SLASH;
import static org.fergonco.music.mjargon.lexer.Lexer.HH;
import static org.fergonco.music.mjargon.lexer.Lexer.HHO;
import static org.fergonco.music.mjargon.lexer.Lexer.HHP;
import static org.fergonco.music.mjargon.lexer.Lexer.HIHAT;
import static org.fergonco.music.mjargon.lexer.Lexer.HIHATOPEN;
import static org.fergonco.music.mjargon.lexer.Lexer.HIHATPEDAL;
import static org.fergonco.music.mjargon.lexer.Lexer.ID;
import static org.fergonco.music.mjargon.lexer.Lexer.LINE_BREAK;
import static org.fergonco.music.mjargon.lexer.Lexer.MF;
import static org.fergonco.music.mjargon.lexer.Lexer.MP;
import static org.fergonco.music.mjargon.lexer.Lexer.NUMBER;
import static org.fergonco.music.mjargon.lexer.Lexer.ON;
import static org.fergonco.music.mjargon.lexer.Lexer.OF;
import static org.fergonco.music.mjargon.lexer.Lexer.OPEN_PARENTHESIS;
import static org.fergonco.music.mjargon.lexer.Lexer.P;
import static org.fergonco.music.mjargon.lexer.Lexer.PP;
import static org.fergonco.music.mjargon.lexer.Lexer.PPP;
import static org.fergonco.music.mjargon.lexer.Lexer.PPPP;
import static org.fergonco.music.mjargon.lexer.Lexer.PROGRESSION;
import static org.fergonco.music.mjargon.lexer.Lexer.RD;
import static org.fergonco.music.mjargon.lexer.Lexer.REPEAT;
import static org.fergonco.music.mjargon.lexer.Lexer.RHYTHM;
import static org.fergonco.music.mjargon.lexer.Lexer.RHYTHM_EXPRESSION;
import static org.fergonco.music.mjargon.lexer.Lexer.RIDE;
import static org.fergonco.music.mjargon.lexer.Lexer.SEQUENCE;
import static org.fergonco.music.mjargon.lexer.Lexer.SIGNATURE;
import static org.fergonco.music.mjargon.lexer.Lexer.SN;
import static org.fergonco.music.mjargon.lexer.Lexer.SNARE;
import static org.fergonco.music.mjargon.lexer.Lexer.STRING_LITERAL;
import static org.fergonco.music.mjargon.lexer.Lexer.T1;
import static org.fergonco.music.mjargon.lexer.Lexer.T2;
import static org.fergonco.music.mjargon.lexer.Lexer.T3;
import static org.fergonco.music.mjargon.lexer.Lexer.T4;
import static org.fergonco.music.mjargon.lexer.Lexer.T5;
import static org.fergonco.music.mjargon.lexer.Lexer.T6;
import static org.fergonco.music.mjargon.lexer.Lexer.TEMPO;
import static org.fergonco.music.mjargon.lexer.Lexer.TIME;
import static org.fergonco.music.mjargon.lexer.Lexer.TOM1;
import static org.fergonco.music.mjargon.lexer.Lexer.TOM2;
import static org.fergonco.music.mjargon.lexer.Lexer.TOM3;
import static org.fergonco.music.mjargon.lexer.Lexer.TOM4;
import static org.fergonco.music.mjargon.lexer.Lexer.TOM5;
import static org.fergonco.music.mjargon.lexer.Lexer.TOM6;
import static org.fergonco.music.mjargon.lexer.Lexer.UNDERSCORE;
import static org.fergonco.music.mjargon.lexer.Lexer.VERTICAL_BAR;
import static org.fergonco.music.mjargon.lexer.Lexer.VOICES;
import static org.fergonco.music.mjargon.lexer.Lexer.WITH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.mjargon.lexer.Lexer;
import org.fergonco.music.mjargon.lexer.Token;
import org.fergonco.music.mjargon.model.DrumNote;
import org.fergonco.music.mjargon.model.Model;
import org.fergonco.music.mjargon.model.SemanticException;

public class Parser {

	private static Map<Integer, DrumNote> drumInstrumentCodes = new HashMap<>();

	static {
		drumInstrumentCodes.put(HIHAT, DrumNote.HIHAT);
		drumInstrumentCodes.put(HH, DrumNote.HIHAT);
		drumInstrumentCodes.put(HIHATOPEN, DrumNote.HIHATOPEN);
		drumInstrumentCodes.put(HHO, DrumNote.HIHATOPEN);
		drumInstrumentCodes.put(HIHATPEDAL, DrumNote.HIHATPEDAL);
		drumInstrumentCodes.put(HHP, DrumNote.HIHATPEDAL);
		drumInstrumentCodes.put(BASSDRUM, DrumNote.BASSDRUM);
		drumInstrumentCodes.put(BD, DrumNote.BASSDRUM);
		drumInstrumentCodes.put(SNARE, DrumNote.SNARE);
		drumInstrumentCodes.put(SN, DrumNote.SNARE);
		drumInstrumentCodes.put(RIDE, DrumNote.RIDE);
		drumInstrumentCodes.put(RD, DrumNote.RIDE);
		drumInstrumentCodes.put(CRASH, DrumNote.CRASH);
		drumInstrumentCodes.put(CR, DrumNote.CRASH);
		drumInstrumentCodes.put(TOM1, DrumNote.TOM1);
		drumInstrumentCodes.put(T1, DrumNote.TOM1);
		drumInstrumentCodes.put(TOM2, DrumNote.TOM2);
		drumInstrumentCodes.put(T2, DrumNote.TOM2);
		drumInstrumentCodes.put(TOM3, DrumNote.TOM3);
		drumInstrumentCodes.put(T3, DrumNote.TOM3);
		drumInstrumentCodes.put(TOM4, DrumNote.TOM4);
		drumInstrumentCodes.put(T4, DrumNote.TOM4);
		drumInstrumentCodes.put(TOM5, DrumNote.TOM5);
		drumInstrumentCodes.put(T5, DrumNote.TOM5);
		drumInstrumentCodes.put(TOM6, DrumNote.TOM6);
		drumInstrumentCodes.put(T6, DrumNote.TOM6);
	}

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
			default:
				dynamic = null;
			}
			dynamics.add(dynamic);
		}

		model.setDynamics(dynamics);
	}

	private void tempo() throws SyntaxException {
		expect(TEMPO);
		int[] length = new int[] { 1, 4 };
		if (currentToken.next().getType() == FORWARD_SLASH) {
			length = getFractionExpression();
			expect(EQUALS);
		}

		int tempo = Integer.parseInt(expect(NUMBER).getText());
		anyNumberOfVerticalBars();
		expect(LINE_BREAK);
		model.setTempo(length, tempo);
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
				if (accept(STRING_LITERAL)) {
					expect(STRING_LITERAL);
				}
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
			NoteSequenceExpression expression = noteSequenceExpression();
			if (expression != null) {
				expect(ON);
				String rhythmId = expect(ID).getText();
				model.addSequenceToBarline(instrumentIndex, expression, rhythmId);
			} else {
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

	private NoteSequenceExpression noteSequenceExpression() throws SyntaxException {
		if (accept(ID)) {
			return sequenceReferenceExpression();
		} else if (accept(UNDERSCORE, CHORD_LITERAL)) {
			return pitchedLiteralExpression();
		} else if (accept(NUMBER)) {
			return chordBasedPitchedLiteralExpression();
		} else if (accept(DRUM_INSTRUMENTS)) {
			return drumLiteralExpression();
		} else {
			return null;
		}
	}

	private DrumLiteralExpression drumLiteralExpression() throws SyntaxException {
		ArrayList<DrumNote> drumNotes = new ArrayList<>();
		while (accept(DRUM_INSTRUMENTS)) {
			int drumTokenType = expect(DRUM_INSTRUMENTS).getType();
			if (!drumInstrumentCodes.containsKey(drumTokenType)) {
				throw new RuntimeException("Unrecognized drum instrument: " + drumTokenType);
			}

			drumNotes.add(drumInstrumentCodes.get(drumTokenType));
		}
		return new DrumLiteralExpression(drumNotes.toArray(new DrumNote[drumNotes.size()]));
	}

	private PitchedLiteralExpression pitchedLiteralExpression() throws SyntaxException {
		ArrayList<String> notes = new ArrayList<>();
		while (accept(CHORD_LITERAL, UNDERSCORE)) {
			notes.add(expect(CHORD_LITERAL, UNDERSCORE).getText());
		}
		return new PitchedLiteralExpression(notes.toArray(new String[notes.size()]));
	}

	private ChordBasedPitchedLiteralExpression chordBasedPitchedLiteralExpression() throws SyntaxException {
		ArrayList<String> notes = new ArrayList<>();
		while (accept(NUMBER, UNDERSCORE)) {
			notes.add(expect(NUMBER, UNDERSCORE).getText());
		}
		expect(OF);
		Token chordProgressionId = expect(ID);
		expect(OPEN_PARENTHESIS);
		int chordProgressionIndex = Integer.parseInt(expect(NUMBER).getText());
		expect(CLOSE_PARENTHESIS);
		return new ChordBasedPitchedLiteralExpression(notes.toArray(new String[notes.size()]),
				chordProgressionId.getText(), chordProgressionIndex);
	}

	private NoteSequenceExpression sequenceReferenceExpression() throws SyntaxException {
		Token id = expect(ID);
		String noteOrDrumSequenceId = id.getText();
		int noteSequenceIndex = -1;
		try {
			expect(OPEN_PARENTHESIS);
			noteSequenceIndex = Integer.parseInt(expect(NUMBER).getText());
			expect(CLOSE_PARENTHESIS);
		} catch (SyntaxException e) {
		}
		return new SequenceReferenceExpression(noteOrDrumSequenceId, noteSequenceIndex);
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
		} else if (accept(DRUM)) {
			drumSequence(id);
		}
	}

	private void drumSequence(Token id) throws SyntaxException, SemanticException {
		expect(DRUM);
		expect(SEQUENCE);
		model.addDrums(id.getText(), drumLiteralExpression().getNotes());
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

	private void freeNoteSequence(Token id) throws SyntaxException {
		PitchedLiteralExpression noteSequence = pitchedLiteralExpression();
		model.addPitchedNoteSequence(id.getText(), noteSequence.getNotes());
	}

	private void chordBasedNoteSequence(Token id) throws SyntaxException, SemanticException {
		ChordBasedPitchedLiteralExpression noteSequence = chordBasedPitchedLiteralExpression();
		model.addPitchedNoteSequence(id.getText(), noteSequence.getNotes(), noteSequence.getChordProgressionId(),
				noteSequence.getChordProgressionIndex());
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
		model.addPitchedNoteSequence(id.getText(), chords.toArray(new String[chords.size()]));
	}

	private void rhythm(Token id) throws SyntaxException, SemanticException {
		expect(RHYTHM);
		Token expr = expect(RHYTHM_EXPRESSION);
		if (accept(ON)) {
			expect(ON);
			if (accept(ID)) {
				Token timeSignature = expect(ID);
				model.addRhythmWithTimeSignatureId(id.getText(), trimRhythmExpressionDelimiters(expr.getText()),
						timeSignature.getText());
			} else if (accept(NUMBER)) {
				int[] timeSignature = getFractionExpression();
				model.addRhythmWithTimeSignatureLiteral(id.getText(), trimRhythmExpressionDelimiters(expr.getText()),
						timeSignature);
			} else {
				throw new SyntaxException(lastConsumed.getPosition(), "Time signature variable or literal expected");
			}
		} else if (accept(WITH)) {
			expect(WITH);
			int[] noteLength = getFractionExpression();
			model.addRhythmWithNoteLength(id.getText(), trimRhythmExpressionDelimiters(expr.getText()), noteLength[1]);
		} else {
			throw new SyntaxException(lastConsumed.getPosition(), "WITH or OR expected");
		}
	}

	private String trimRhythmExpressionDelimiters(String text) {
		return text.substring(1, text.length() - 1);
	}

	private void timeSignature(Token id) throws NumberFormatException, SyntaxException {
		expect(TIME);
		expect(SIGNATURE);
		int[] timeSignature = getFractionExpression();
		model.addTimeSignature(id.getText(), timeSignature[0], timeSignature[1]);
	}

	private int[] getFractionExpression() throws NumberFormatException, SyntaxException {
		int n1 = Integer.parseInt(expect(NUMBER).getText());
		expect(FORWARD_SLASH);
		int n2 = Integer.parseInt(expect(NUMBER).getText());
		return new int[] { n1, n2 };
	}

	private boolean accept(int... tokenTypes) {
		if (currentToken != null && expectedType(currentToken.getType(), tokenTypes)) {
			return true;
		} else {
			return false;
		}
	}

	private Token expect(int... expectedTokenTypes) throws SyntaxException {
		if (currentToken != null && expectedType(currentToken.getType(), expectedTokenTypes)) {
			consumeToken();
			return lastConsumed;
		} else {
			int position = currentToken == null ? 0 : currentToken.getPosition();
			throw new SyntaxException(position, "Expected: " + getTokenNameList(expectedTokenTypes));
		}
	}

	private String getTokenNameList(int[] expectedTokenTypes) {
		StringBuilder ret = new StringBuilder();
		for (int tokenType : expectedTokenTypes) {
			ret.append(Lexer.getTokenName(tokenType)).append(", ");
		}
		ret.setLength(ret.length() - 2);
		return ret.toString();
	}

	private boolean expectedType(int type, int[] expectedTokenTypes) {
		for (int expectedTokenType : expectedTokenTypes) {
			if (expectedTokenType == type) {
				return true;
			}
		}
		return false;
	}

	private void consumeToken() {
		lastConsumed = currentToken;
		currentToken = currentToken.next();
	}
}
