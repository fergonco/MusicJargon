package org.fergonco.music.mjargon.model;

public abstract class AbstractSingleSequence extends AbstractNoteSequence implements NoteSequence {

	protected PitchArray[] pitches;

	@Override
	public PitchArray[] getAllNotes(int numNotes) {
		PitchArray[] ret = new PitchArray[numNotes];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = pitches[i % pitches.length];
		}
		return ret;
	}

	@Override
	public PitchArray[] getAllNotes() {
		return pitches;
	}

}