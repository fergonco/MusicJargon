package org.fergonco.music.mjargon.model;

public abstract class AbstractValue implements Value{

	@Override
	public SequenceAndRhythm toAural() {
		throw new UnsupportedOperationException();
	}

	@Override
	public NoteSequence toNoteSequence() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Rhythm toRhythm() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TimeSignature toTimeSignature() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int toInt() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toStringLiteral() {
		throw new UnsupportedOperationException();
	}

}
