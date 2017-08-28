package org.fergonco.music.mjargon.parser;

import static org.fergonco.music.mjargon.lexer.Lexer.CHORD;
import static org.fergonco.music.mjargon.lexer.Lexer.CLOSE_SQUARE_BRACKET;
import static org.fergonco.music.mjargon.lexer.Lexer.COLON;
import static org.fergonco.music.mjargon.lexer.Lexer.COMMENT;
import static org.fergonco.music.mjargon.lexer.Lexer.DOT;
import static org.fergonco.music.mjargon.lexer.Lexer.EQUALS;
import static org.fergonco.music.mjargon.lexer.Lexer.FORWARD_SLASH;
import static org.fergonco.music.mjargon.lexer.Lexer.ID;
import static org.fergonco.music.mjargon.lexer.Lexer.LINE_BREAK;
import static org.fergonco.music.mjargon.lexer.Lexer.NUMBER;
import static org.fergonco.music.mjargon.lexer.Lexer.ON;
import static org.fergonco.music.mjargon.lexer.Lexer.OPEN_SQUARE_BRACKET;
import static org.fergonco.music.mjargon.lexer.Lexer.PROGRESSION;
import static org.fergonco.music.mjargon.lexer.Lexer.RHYTHM;
import static org.fergonco.music.mjargon.lexer.Lexer.RHYTHM_EXPRESSION;
import static org.fergonco.music.mjargon.lexer.Lexer.SEQUENCE;
import static org.fergonco.music.mjargon.lexer.Lexer.SIGNATURE;
import static org.fergonco.music.mjargon.lexer.Lexer.TIME;
import static org.fergonco.music.mjargon.lexer.Lexer.VERTICAL_BAR;

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
		if (accept(ID)) {
			id();
		} else {
			comment();
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
			musicalExpressions(id);
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

	private void musicalExpressions(Token id) throws SyntaxException {
		int index = 0;
		while (!accept(LINE_BREAK)) {
			String noteSequenceId = id.getText();
			int noteSequenceIndex = -1;
			try {
				expect(OPEN_SQUARE_BRACKET);
				noteSequenceIndex = Integer.parseInt(expect(NUMBER).getText());
				expect(CLOSE_SQUARE_BRACKET);
			} catch (SyntaxException e) {
			}
			int polyphonicNoteIndex = -1;
			try {
				expect(DOT);
				polyphonicNoteIndex = Integer.parseInt(expect(ID).getText());
			} catch (SyntaxException e) {
			}

			model.addMusicalExpression(index, noteSequenceId, noteSequenceIndex, polyphonicNoteIndex);
			index++;
		}
		model.newMusicalExpression();
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

	private void noteSequence(Token id) throws SyntaxException {
		expect(SEQUENCE);
		ArrayList<Integer> notes = new ArrayList<>();
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
		try {
			while (true) {
				chords.add(expect(ID).getText());
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
