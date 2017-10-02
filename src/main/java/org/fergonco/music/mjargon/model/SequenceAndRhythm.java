package org.fergonco.music.mjargon.model;

import org.fergonco.music.mjargon.model.functions.ValueType;

public class SequenceAndRhythm extends AbstractValue implements Value {

	private Value sequence;
	private Value rhythm;

	public SequenceAndRhythm(Value sequence, Value rhythm) {
		this.sequence = sequence;
		this.rhythm = rhythm;
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
		return sequence.toNoteSequence();
	}

	public Rhythm getRhythm() {
		return rhythm.toRhythm();
	}

	@Override
	public void validate() throws SemanticException {
		sequence.validate();
		if (sequence.getType() != ValueType.SEQUENCE) {
			throw new SemanticException("Sequence expression expected");
		}
		rhythm.validate();
		if (rhythm.getType() != ValueType.RHYTHM) {
			throw new SemanticException("Rhythm expression expected");
		}
	}
}