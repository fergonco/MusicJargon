package org.fergonco.music.mjargon.parser;

public class MJargonError {

	private String msg;
	private int line;

	public MJargonError(String msg, int line) {
		this.msg = msg;
		this.line = line;
	}

	public int getLine() {
		return line;
	}
	
	public String getMessage() {
		return msg;
	}
}
