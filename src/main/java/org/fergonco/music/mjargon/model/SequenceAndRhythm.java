package org.fergonco.music.mjargon.model;

import org.fergonco.music.mjargon.model.functions.ValueType;

public class SequenceAndRhythm extends AbstractValue implements Value {

	private NoteSequence sequence;
	private Rhythm rhythm;

	public SequenceAndRhythm(Value sequence, Value rhythm) {
		this.sequence = sequence.toNoteSequence();
		this.rhythm = rhythm.toRhythm();
	}

	@Override
	public SequenceAndRhythm toAural() {
		return this;
	}

	@Override
	public ValueType getType() {
		return ValueType.AURAL;
	}

	public NoteSequence getSequence() {
		return sequence;
	}

	public Rhythm getRhythm() {
		return rhythm;
	}
}