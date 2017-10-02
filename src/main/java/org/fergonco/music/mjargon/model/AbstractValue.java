package org.fergonco.music.mjargon.model;

public abstract class AbstractValue implements Value {

	private UnsupportedOperationException UOE() {
		return new UnsupportedOperationException(this.getClass().getCanonicalName());
	}
	
	@Override
	public SequenceAndRhythm toAural() {
		throw UOE();
	}

	@Override
	public NoteSequence toNoteSequence() {
		throw UOE();
	}

	@Override
	public Rhythm toRhythm() {
		throw UOE();
	}

	@Override
	public FractionValue toFraction() {
		throw UOE();
	}

	@Override
	public int toInt() {
		throw UOE();
	}

	@Override
	public String toStringLiteral() {
		throw UOE();
	}

}
