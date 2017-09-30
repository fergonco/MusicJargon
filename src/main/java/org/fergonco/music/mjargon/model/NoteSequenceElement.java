package org.fergonco.music.mjargon.model;

public class NoteSequenceElement extends AbstractSingleSequence implements NoteSequence {

	public NoteSequenceElement(Value noteSequence, int index) {
		pitches = new PitchArray[] { noteSequence.toNoteSequence().getAllNotes()[index] };
	}

}
