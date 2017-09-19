package org.fergonco.music.mjargon.model;

import org.fergonco.music.mjargon.model.functions.ValueType;

public class NumberValue implements Value {

	private int number;

	public NumberValue(int number) {
		this.number = number;
	}

	@Override
	public NoteSequence toNoteSequence() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int toInt() {
		return number;
	}

	@Override
	public String toStringLiteral() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ValueType getType() {
		return ValueType.NUMBER;
	}

}
