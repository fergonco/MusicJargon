package org.fergonco.music.mjargon.model;

public class NoteSequenceElement extends AbstractSingleSequence implements NoteSequence {

	public NoteSequenceElement(NoteSequence noteSequence, int index) {
		pitches = new PitchArray[] { noteSequence.getAllNotes()[index] };
	}

}
