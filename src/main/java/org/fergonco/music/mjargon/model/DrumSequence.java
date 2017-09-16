package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

public class DrumSequence implements NoteSequence {

	private PitchArray[] pitches;

	public DrumSequence(DrumNote[] drumNotes) {
		ArrayList<PitchArray> pitches = new ArrayList<>();
		for (DrumNote drumNote : drumNotes) {
			PitchArrayImpl pitch = new PitchArrayImpl();
			pitch.add(drumNote.getMIDICode());
			pitch.setDrums(true);
			pitches.add(pitch);
		}
		this.pitches = pitches.toArray(new PitchArray[pitches.size()]);
	}

	@Override
	public PitchArray getNote(int noteIndex) {
		return pitches[noteIndex];
	}

	@Override
	public PitchArray[] getAllNotes() {
		return pitches;
	}

}
