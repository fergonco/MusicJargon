package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

public class DrumSequence extends AbstractSingleSequence implements NoteSequence {

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
	public void validate() throws SemanticException {
	}

}
