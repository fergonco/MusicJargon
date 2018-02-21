package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

public class DrumSequence extends AbstractNoteSequence implements NoteSequence {

	private ArrayList<PitchArray> pitches = new ArrayList<>();

	public void addDrumNote(DrumNote drumNote, boolean higherDynamics) {
		PitchArrayImpl pitch = new PitchArrayImpl();
		pitch.add(drumNote.getMIDICode());
		pitch.setDrums(true);
		pitch.setAccented(higherDynamics);
		pitches.add(pitch);
	}

	@Override
	public void validate() throws SemanticException {
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
