package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

public class PitchedNoteSequence extends AbstractNoteSequence implements NoteSequence {

	private ArrayList<PitchArray> pitches = new ArrayList<>();
	
	@Override
	public void validate() {
	}

	public void addNote(PitchArray pitchArray) {
		pitches.add(pitchArray);
	}

	@Override
	public PitchArray[] getAllNotes(int numNotes) {
		return getAllNotes();
	}

	@Override
	public PitchArray[] getAllNotes() {
		return pitches.toArray(new PitchArray[pitches.size()]);
	}

}
