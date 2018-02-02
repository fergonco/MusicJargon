package org.fergonco.music.mjargon.model;

public class PitchedNoteSequence extends AbstractSingleSequence implements NoteSequence {

	public PitchedNoteSequence(PitchArray[] singleNotes) {
		pitches = singleNotes;
	}

	public PitchArray getChord(int chordProgressionIndex) {
		return pitches[chordProgressionIndex];
	}

	@Override
	public void validate() {
	}

}
