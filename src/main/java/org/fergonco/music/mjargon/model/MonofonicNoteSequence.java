package org.fergonco.music.mjargon.model;

public class MonofonicNoteSequence implements NoteSequence {

	// In this case the PitchArrays contain only one pitch
	private PitchArray[] singleNotes;

	public MonofonicNoteSequence(Integer[] noteIndices, PitchArray chord) {
		singleNotes = new PitchArray[noteIndices.length];
		for (int i = 0; i < singleNotes.length; i++) {
			int pitch = chord.getPitch(noteIndices[i]);
			singleNotes[i] = new PitchArray(pitch);
		}
	}

	@Override
	public PitchArray getNote(int noteIndex) {
		return singleNotes[noteIndex];
	}

	@Override
	public PitchArray[] getAllNotes() {
		return singleNotes;
	}

}
