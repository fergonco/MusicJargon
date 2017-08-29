package org.fergonco.music.mjargon.parser;

public class SyntaxException extends Exception {
	private static final long serialVersionUID = 1L;

	private int position = -1;

	public SyntaxException(int position, String message) {
		super(message);
		this.position = position;
	}

	public SyntaxException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getPosition() {
		return position;
	}

}
