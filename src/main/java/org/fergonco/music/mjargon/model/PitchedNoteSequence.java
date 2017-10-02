package org.fergonco.music.mjargon.model;

public class PitchedNoteSequence extends AbstractSingleSequence implements NoteSequence {

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
		pitches = singleNotes;
	}

	public PitchedNoteSequence(String[] chordStrings) {
		pitches = ChordLiteral.parseSequence(chordStrings);
	}

	public PitchArray getChord(int chordProgressionIndex) {
		return pitches[chordProgressionIndex];
	}
	
	@Override
	public void validate() {
	}

}
