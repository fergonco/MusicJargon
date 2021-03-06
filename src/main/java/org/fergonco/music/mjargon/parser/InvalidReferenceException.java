package org.fergonco.music.mjargon.parser;

public class InvalidReferenceException extends ModelException {

	private int position = -1;

	public InvalidReferenceException(int position, String message) {
		super(message);
		this.position = position;
	}

	public InvalidReferenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidReferenceException(String message) {
		super(message);
	}

	public int getPosition() {
		return position;
	}

}
