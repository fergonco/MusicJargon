package org.fergonco.music.mjargon.model;

public class PitchedNoteSequence implements NoteSequence {

	private PitchArray[] chords;

	public PitchedNoteSequence(String[] noteIndices, PitchArray chord) {
		PitchArray[] singleNotes = new PitchArray[noteIndices.length];
		for (int i = 0; i < singleNotes.length; i++) {
			try {
				int pitch = chord.getPitch(Integer.parseInt(noteIndices[i]) - 1);
				PitchArrayImpl pitchArray = new PitchArrayImpl();
				pitchArray.add(pitch);
				singleNotes[i] = pitchArray;
			} catch (NumberFormatException e) {
				singleNotes[i] = new TiedPichArray();
			}
		}
	}

	public PitchedNoteSequence(String[] chordStrings) {
		chords = ChordLiteral.parseSequence(chordStrings);
	}

	public PitchArray getChord(int chordProgressionIndex) {
		return chords[chordProgressionIndex];
	}

	@Override
	public PitchArray getNote(int noteIndex) {
		return chords[noteIndex];
	}

	@Override
	public PitchArray[] getAllNotes() {
		return chords;
	}

}
