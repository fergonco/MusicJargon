package org.fergonco.music.mjargon.lexer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class Lexer {

	public static final int TEMPO = 1;
	public static final int ID = 2;
	public static final int COLON = 3;
	public static final int COMMENT = 4;
	public static final int NUMBER = 5;
	public static final int FORWARD_SLASH = 6;
	public static final int EQUALS = 7;
	public static final int TIME = 9;
	public static final int SIGNATURE = 10;
	public static final int RHYTHM = 11;
	public static final int SEQUENCE = 14;
	public static final int REPEAT = 15;
	public static final int RHYTHM_EXPRESSION = 16;
	public static final int VERTICAL_BAR = 17;
	public static final int LINE_BREAK = 18;
	public static final int ON = 21;
	public static final int DOT = 22;
	public static final int CHORD_LITERAL = 24;
	public static final int DYNAMICS = 25;
	public static final int PPPP = 26;
	public static final int PPP = 27;
	public static final int PP = 28;
	public static final int P = 29;
	public static final int MP = 30;
	public static final int MF = 31;
	public static final int F = 32;
	public static final int FF = 33;
	public static final int FFF = 34;
	public static final int FFFF = 35;
	public static final int DRUM = 36;
	public static final int WITH = 37;
	public static final int VOICES = 38;
	public static final int OPEN_BRACE = 39;
	public static final int CLOSE_BRACE = 40;
	public static final int STRING_LITERAL = 42;
	public static final int UNDERSCORE = 43;
	public static final int HIHAT = 44;
	public static final int HH = 45;
	public static final int HIHATOPEN = 46;
	public static final int HHO = 47;
	public static final int HIHATPEDAL = 48;
	public static final int HHP = 49;
	public static final int BASSDRUM = 50;
	public static final int BD = 51;
	public static final int SNARE = 52;
	public static final int SN = 53;
	public static final int RIDE = 54;
	public static final int RD = 55;
	public static final int CRASH = 56;
	public static final int CR = 57;
	public static final int TOM1 = 58;
	public static final int T1 = 59;
	public static final int TOM2 = 60;
	public static final int T2 = 61;
	public static final int TOM3 = 62;
	public static final int T3 = 63;
	public static final int TOM4 = 64;
	public static final int T4 = 65;
	public static final int TOM5 = 66;
	public static final int T5 = 67;
	public static final int TOM6 = 68;
	public static final int T6 = 69;
	public static final int OF = 70;
	public static final int PLUS = 71;
	public static final int OPEN_PARENTHESIS = 72;
	public static final int CLOSE_PARENTHESIS = 73;
	public static final int COMA = 74;
	public static final int NOTE = 75;
	public static final int[] DRUM_INSTRUMENTS = new int[] { HIHAT, HH, HIHATOPEN, HHO, HIHATPEDAL, HHP, BASSDRUM, BD,
			SNARE, SN, RIDE, RD, CRASH, CR, TOM1, T1, TOM2, T2, TOM3, T3, TOM4, T4, TOM5, T5, TOM6, T6 };

	private static HashMap<Integer, String> TOKEN_NAMES = new HashMap<>();

	private static int[] KEYWORDS = new int[] { TEMPO, TIME, SIGNATURE, RHYTHM, SEQUENCE, REPEAT, ON, DYNAMICS, PPPP,
			PPP, PP, P, MP, MF, F, FF, FFF, FFFF, DRUM, WITH, VOICES, OF, NOTE };
	static {

		int[] allKeywords = new int[DRUM_INSTRUMENTS.length + KEYWORDS.length];
		System.arraycopy(DRUM_INSTRUMENTS, 0, allKeywords, 0, DRUM_INSTRUMENTS.length);
		System.arraycopy(KEYWORDS, 0, allKeywords, DRUM_INSTRUMENTS.length, KEYWORDS.length);
		KEYWORDS = allKeywords;

		Field[] fields = Lexer.class.getDeclaredFields();
		for (Field field : fields) {
			if (!Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			if (field.getName().equals("DRUM_INSTRUMENTS") || field.getName().equals("KEYWORDS")
					|| field.getName().equals("TOKEN_NAMES")) {
				continue;
			}
			try {
				TOKEN_NAMES.put(field.getInt(null), field.getName());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(field.getName(), e);
			}
		}
	}

	public static String getTokenName(int code) {
		return TOKEN_NAMES.get(code);
	}

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
			int tokenPosition = position;
			boolean keywordMatched = false;
			for (int keywordCode : KEYWORDS) {
				String keyword = TOKEN_NAMES.get(keywordCode).toLowerCase();
				if (consumeWord(keyword)) {
					ret.add(new TokenImpl(tokenPosition, lastConsumed, keywordCode));
					keywordMatched = true;
					break;
				}
			}
			if (!keywordMatched) {
				if (character == '[') {
					ret.add(createDelimitedToken(RHYTHM_EXPRESSION, new DelimitedTokenFilter() {

						@Override
						public boolean isToken() {
							return isRhythmItem(chars[position]);
						}

						@Override
						public char getClosingChar() {
							return ']';
						}
					}));
				} else if (character == '\"') {
					ret.add(createDelimitedToken(STRING_LITERAL, new DelimitedTokenFilter() {

						@Override
						public boolean isToken() {
							return chars[position] != getClosingChar();
						}

						@Override
						public char getClosingChar() {
							return '\"';
						}
					}));
				} else if (Character.isLetter(character) && Character.isLowerCase(character)) {
					ret.add(createToken(ID, new TokenFilter() {

						@Override
						public boolean isToken() {
							char ch = chars[position];
							return Character.isLetterOrDigit(ch) || ch == '_';
						}
					}));
				} else if ("ABCDEFG-".indexOf(character) != -1) {
					ret.add(createToken(CHORD_LITERAL, new TokenFilter() {

						@Override
						public boolean isToken() {
							char ch = chars[position];
							return "ABCDEFG-".indexOf(ch) != -1 || Character.isDigit(ch) || ch == '♯' || ch == '♭';
						}
					}));
				} else if (character == ':') {
					ret.add(new TokenImpl(tokenPosition, ":", COLON));
				} else if (Character.isWhitespace(character)) {
					if (String.valueOf(character).matches(".")) {
						// noop
					} else {
						ret.add(new TokenImpl(tokenPosition, null, LINE_BREAK));
					}
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
					ret.add(new TokenImpl(tokenPosition, String.valueOf(character), LINE_BREAK));
				} else if (character == '/') {
					ret.add(new TokenImpl(tokenPosition, String.valueOf(character), FORWARD_SLASH));
				} else if (character == ',') {
					ret.add(new TokenImpl(tokenPosition, String.valueOf(character), COMA));
				} else if (character == '.') {
					ret.add(new TokenImpl(tokenPosition, String.valueOf(character), DOT));
				} else if (character == '(') {
					ret.add(new TokenImpl(tokenPosition, String.valueOf(character), OPEN_PARENTHESIS));
				} else if (character == ')') {
					ret.add(new TokenImpl(tokenPosition, String.valueOf(character), CLOSE_PARENTHESIS));
				} else if (character == '{') {
					ret.add(new TokenImpl(tokenPosition, String.valueOf(character), OPEN_BRACE));
				} else if (character == '}') {
					ret.add(new TokenImpl(tokenPosition, String.valueOf(character), CLOSE_BRACE));
				} else if (character == '|') {
					ret.add(new TokenImpl(tokenPosition, String.valueOf(character), VERTICAL_BAR));
				} else if (character == '_') {
					ret.add(new TokenImpl(tokenPosition, String.valueOf(character), UNDERSCORE));
				} else if (character == '=') {
					ret.add(new TokenImpl(tokenPosition, String.valueOf(character), EQUALS));
				} else if (character == '+') {
					ret.add(new TokenImpl(tokenPosition, String.valueOf(character), PLUS));
				} else {
					throw new LexerException("Invalid character: " + character);
				}
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
		return new TokenImpl(start, script.substring(start, end), tokenType);
	}

	private Token createDelimitedToken(int tokenType, DelimitedTokenFilter tokenFilter) throws LexerException {
		int start = position;
		position++;
		while (position < chars.length && tokenFilter.isToken()) {
			position++;
		}
		if (chars[position] != tokenFilter.getClosingChar()) {
			throw new LexerException(tokenFilter.getClosingChar() + " expected");
		}
		int end = position;
		return new TokenImpl(start, script.substring(start, end + 1), tokenType);
	}

	private boolean isLineBreak(char character) {
		return !String.valueOf(character).matches(".");
	}

	private boolean isRhythmItem(char character) {
		return character == 'o' || character == 'O' || character == 'x' || character == 'X' || character == '.';
	}

	private boolean consumeWord(String str) {
		if (chars.length < position + str.length()) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (chars[position + i] != str.charAt(i)) {
				return false;
			}
		}
		if (Character.isLetterOrDigit(chars[position + str.length()])) {
			return false;
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

	private interface DelimitedTokenFilter extends TokenFilter {

		char getClosingChar();

	}
}
