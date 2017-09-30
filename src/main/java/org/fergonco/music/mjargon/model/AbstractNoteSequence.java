package org.fergonco.music.mjargon.model;

import org.fergonco.music.mjargon.model.functions.ValueType;

public abstract class AbstractNoteSequence extends AbstractValue implements NoteSequence {

	@Override
	public NoteSequence toNoteSequence() {
		return this;
	}

	@Override
	public int toInt() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toStringLiteral() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ValueType getType() {
		return ValueType.SEQUENCE;
	}
}
