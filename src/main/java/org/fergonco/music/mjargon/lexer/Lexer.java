package org.fergonco.music.mjargon.lexer;

import java.util.ArrayList;

public class Lexer {

	public static final int PLAY = 1;
	public static final int ID = 2;
	public static final int COLON = 3;
	public static final int COMMENT = 4;
	public static final int NUMBER = 5;
	public static final int FORWARD_SLASH = 6;
	public static final int EQUALS = 7;
	public static final int COMA = 8;
	public static final int TIME = 9;
	public static final int SIGNATURE = 10;
	public static final int RHYTHM = 11;
	public static final int CHORD = 12;
	public static final int PROGRESSION = 13;
	public static final int SEQUENCE = 14;
	public static final int REPEAT = 15;
	public static final int RHYTHM_EXPRESSION = 16;
	public static final int VERTICAL_BAR = 17;
	public static final int LINE_BREAK = 18;
	public static final int OPEN_SQUARE_BRACKET = 19;
	public static final int CLOSE_SQUARE_BRACKET = 20;
	public static final int ON = 21;
	public static final int DOT = 22;
	public static final int CHORD_LITERAL = 24;
	private String script;
	private char[] chars;
	private int position;
	private ArrayList<Token> ret = new ArrayList<>();
	private String lastConsumed;

	public Lexer(String script) {
		this.script = script;
		this.chars = script.toCharArray();
	}

	public Token process() throws LexerException {
		position = 0;
		while (position < chars.length) {
			char character = chars[position];
			if (consume("play")) {
				ret.add(new TokenImpl(lastConsumed, PLAY));
			} else if (consume("time")) {
				ret.add(new TokenImpl(lastConsumed, TIME));
			} else if (consume("signature")) {
				ret.add(new TokenImpl(lastConsumed, SIGNATURE));
			} else if (consume("rythm")) {
				ret.add(new TokenImpl(lastConsumed, RHYTHM));
			} else if (consume("chord")) {
				ret.add(new TokenImpl(lastConsumed, CHORD));
			} else if (consume("progression")) {
				ret.add(new TokenImpl(lastConsumed, PROGRESSION));
			} else if (consume("on")) {
				ret.add(new TokenImpl(lastConsumed, ON));
			} else if (consume("repeat")) {
				ret.add(new TokenImpl(lastConsumed, SEQUENCE));
			} else if (consume("repeat")) {
				ret.add(new TokenImpl(lastConsumed, REPEAT));
			} else if (isRhythmItem(character)) {
				ret.add(createToken(RHYTHM_EXPRESSION, new TokenFilter() {

					@Override
					public boolean isToken() {
						return isRhythmItem(chars[position]);
					}
				}));
			} else if (Character.isLetter(character) && Character.isLowerCase(character)) {
				ret.add(createToken(ID, new TokenFilter() {

					@Override
					public boolean isToken() {
						return Character.isLetterOrDigit(chars[position]);
					}
				}));
			} else if (Character.isUpperCase(character)) {
				ret.add(createToken(CHORD_LITERAL, new TokenFilter() {

					@Override
					public boolean isToken() {
						char ch = chars[position];
						return Character.isUpperCase(ch) || Character.isDigit(ch) || ch == '♯' || ch == '♭';
					}
				}));
			} else if (character == ':') {
				ret.add(new TokenImpl(":", COLON));
			} else if (Character.isWhitespace(character)) {
				// noop
			} else if (character == '\'') {
				ret.add(createToken(COMMENT, new TokenFilter() {

					@Override
					public boolean isToken() {
						return !isLineBreak(chars[position]);
					}
				}));
			} else if (Character.isDigit(character)) {
				ret.add(createToken(NUMBER, new TokenFilter() {

					@Override
					public boolean isToken() {
						return Character.isDigit(chars[position]);
					}
				}));
			} else if (isLineBreak(character)) {
				ret.add(new TokenImpl(String.valueOf(character), LINE_BREAK));
			} else if (character == '/') {
				ret.add(new TokenImpl(String.valueOf(character), FORWARD_SLASH));
			} else if (character == ',') {
				ret.add(new TokenImpl(String.valueOf(character), COMA));
			} else if (character == '.') {
				ret.add(new TokenImpl(String.valueOf(character), DOT));
			} else if (character == '[') {
				ret.add(new TokenImpl(String.valueOf(character), OPEN_SQUARE_BRACKET));
			} else if (character == ']') {
				ret.add(new TokenImpl(String.valueOf(character), CLOSE_SQUARE_BRACKET));
			} else if (character == '|') {
				ret.add(new TokenImpl(String.valueOf(character), VERTICAL_BAR));
			} else if (character == '=') {
				ret.add(new TokenImpl(String.valueOf(character), EQUALS));
			} else {
				throw new LexerException("Invalid character: " + character);
			}
			position++;
		}
		linkTokens();
		return ret.get(0);
	}

	private Token createToken(int tokenType, TokenFilter tokenFilter) {
		int start = position;
		while (position < chars.length && tokenFilter.isToken()) {
			position++;
		}
		int end = position;
		position--; // move to the last position of token
		return new TokenImpl(script.substring(start, end), tokenType);
	}

	private boolean isLineBreak(char character) {
		return !String.valueOf(character).matches(".");
	}

	private boolean isRhythmItem(char character) {
		return character == 'x' || character == 'X' || character == '.';
	}

	private boolean consume(String str) {
		if (chars.length < position + str.length()) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (chars[position + i] != str.charAt(i)) {
				return false;
			}
		}
		position += str.length() - 1;
		lastConsumed = str;
		return true;
	}

	private void linkTokens() {
		for (int i = 1; i < ret.size(); i++) {
			((TokenImpl) ret.get(i - 1)).next(ret.get(i));
		}
	}

	private interface TokenFilter {

		boolean isToken();

	}
}
