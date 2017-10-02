package org.fergonco.music.mjargon.parser;

public class MJargonError {

	private String message;
	private int line;

	public MJargonError(String msg, int line) {
		this.message = msg;
		this.line = line;
	}

	public int getLine() {
		return line;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "Error at line " + line + ": " + message;
	}
}
