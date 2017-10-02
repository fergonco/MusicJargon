package org.fergonco.music.mjargon.model;

import org.fergonco.music.mjargon.model.functions.ValueType;

public class StringValue extends AbstractValue implements Value {

	private String string;

	public StringValue(String string) {
		this.string = string;
	}

	@Override
	public NoteSequence toNoteSequence() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int toInt() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toStringLiteral() {
		return string;
	}

	@Override
	public ValueType getType() {
		return ValueType.STRING;
	}

	@Override
	public void validate() throws SemanticException {
	}

}
