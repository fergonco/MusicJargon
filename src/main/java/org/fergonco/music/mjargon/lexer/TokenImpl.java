package org.fergonco.music.mjargon.lexer;

public class TokenImpl implements Token {

	private String text;
	private int type;
	private Token next = null;
	private int position;

	public TokenImpl(int position, String text, int type) {
		this.position = position;
		this.text = text;
		this.type = type;
	}

	@Override
	public String getText() {
		return text;
	}

	public void next(Token token) {
		this.next = token;
	}

	@Override
	public Token next() {
		return next;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public int getPosition() {
		return position;
	}
}
