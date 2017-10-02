package org.fergonco.music.mjargon.model;

public abstract class AbstractSingleSequence extends AbstractNoteSequence implements NoteSequence {

	protected PitchArray[] pitches = null;

	@Override
	public PitchArray[] getAllNotes(int numNotes) {
		return pitches;
	}

	@Override
	public PitchArray[] getAllNotes() {
		return pitches;
	}

}
