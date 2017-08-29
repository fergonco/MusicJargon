package org.fergonco.music.mjargon.parser;

import static org.fergonco.music.mjargon.lexer.Lexer.*;

import java.util.ArrayList;

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
		while (currentToken != null) {
			if (accept(ID)) {
				id();
			} else {
				comment();
			}
		}

		return model;
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
		} else if (accept(OPEN_SQUARE_BRACKET)) {
			barline(id);
		} else {
			instrumentHeader(id);
		}
	}

	private void instrumentHeader(Token id) throws SyntaxException {
		ArrayList<String> instruments = new ArrayList<>();
		instruments.add(id.getText());
		try {
			while (true) {
				expect(VERTICAL_BAR);
				instruments.add(expect(ID).getText());
			}
		} catch (SyntaxException e) {
			expect(LINE_BREAK);
		}
		model.setInstruments(instruments.toArray(new String[instruments.size()]));
	}

	private void barline(Token id) throws SyntaxException, SemanticException {
		int instrumentIndex = 0;
		while (!accept(LINE_BREAK)) {
			String noteSequenceId = id.getText();
			int noteSequenceIndex = -1;
			try {
				expect(OPEN_SQUARE_BRACKET);
				noteSequenceIndex = Integer.parseInt(expect(NUMBER).getText());
				expect(CLOSE_SQUARE_BRACKET);
			} catch (SyntaxException e) {
			}
			expect(ON);
			String rhythmId = expect(ID).getText();

			model.addToBarline(instrumentIndex, noteSequenceId, noteSequenceIndex, rhythmId);
			instrumentIndex++;
		}
		model.newBarline();
	}

	private void label(Token id) throws SyntaxException {
		expect(COLON);
		model.newLabel(id.getText());
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
		}
	}

	private void noteSequence(Token id) throws SyntaxException, SemanticException {
		expect(SEQUENCE);
		ArrayList<Integer> notes = new ArrayList<>();
		notes.add(Integer.parseInt(expect(NUMBER).getText()));
		try {
			while (true) {
				notes.add(Integer.parseInt(expect(NUMBER).getText()));
			}
		} catch (SyntaxException e) {
			expect(ON);
			Token chordProgressionId = expect(ID);
			expect(OPEN_SQUARE_BRACKET);
			int chordProgressionIndex = Integer.parseInt(expect(NUMBER).getText());
			expect(CLOSE_SQUARE_BRACKET);
			expect(LINE_BREAK);
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
				expect(COMA);
				chords.add(expect(CHORD_LITERAL).getText());
			}
		} catch (SyntaxException e) {
			expect(LINE_BREAK);
		}
		model.addPolyphonicNoteSequence(id.getText(), chords.toArray(new String[chords.size()]));
	}

	private void rhythm(Token id) throws SyntaxException, SemanticException {
		expect(RHYTHM);
		Token expr = expect(RHYTHM_EXPRESSION);
		expect(ON);
		Token timeSignature = expect(ID);
		model.addRhythm(id.getText(), expr.getText(), timeSignature.getText());
	}

	private void timeSignature(Token id) throws NumberFormatException, SyntaxException {
		expect(TIME);
		expect(SIGNATURE);
		int n1 = Integer.parseInt(expect(NUMBER).getText());
		expect(FORWARD_SLASH);
		int n2 = Integer.parseInt(expect(NUMBER).getText());
		expect(LINE_BREAK);
		model.addTimeSignature(id.getText(), n1, n2);
	}

	private boolean accept(int tokenType) {
		if (currentToken != null && currentToken.getType() == tokenType) {
			consumeToken();
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
			throw new SyntaxException(expectedTokenType + " expected");
		}
	}

	private void consumeToken() {
		lastConsumed = currentToken;
		currentToken = currentToken.next();
	}
}
