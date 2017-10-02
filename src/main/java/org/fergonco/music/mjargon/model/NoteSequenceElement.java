package org.fergonco.music.mjargon.model;

import org.fergonco.music.mjargon.model.functions.ValueType;

public class NoteSequenceElement extends AbstractSingleSequence implements NoteSequence {

	private Value noteSequence;
	private int index;

	public NoteSequenceElement(Value noteSequence, int index) {
		this.noteSequence = noteSequence;
		this.index = index;
	}

	@Override
	public void validate() throws SemanticException {
		if (pitches == null) {
			if (noteSequence.getType() != ValueType.SEQUENCE) {
				throw new SemanticException("Trying to access a note from a non sequence expression");
			}
			pitches = new PitchArray[] { noteSequence.toNoteSequence().getAllNotes()[index] };
		}
	}

}
