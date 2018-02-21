package org.fergonco.music.mjargon.parser;

public class ModelException extends RuntimeException {

	public ModelException(String msg) {
		super(msg);
	}

	public ModelException() {
		super();
	}

	public ModelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ModelException(String message, Throwable cause) {
		super(message, cause);
	}

	public ModelException(Throwable cause) {
		super(cause);
	}

}
