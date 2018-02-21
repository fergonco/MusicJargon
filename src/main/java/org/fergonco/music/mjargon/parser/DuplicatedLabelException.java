package org.fergonco.music.mjargon.parser;

public class DuplicatedLabelException extends ModelException {

	public DuplicatedLabelException(String label) {
		super("Duplicated label: " + label);
	}

}
