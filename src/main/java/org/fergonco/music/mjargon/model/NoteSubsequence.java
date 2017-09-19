package org.fergonco.music.mjargon.model;

public class NoteSubsequence extends AbstractSingleSequence implements NoteSequence {

	public NoteSubsequence(NoteSequence noteSequence, int subsequenceStart, int subsequenceEnd) {
		PitchArray[] allNotes = noteSequence.getAllNotes();
		if (subsequenceEnd == -1) {
			subsequenceEnd = allNotes.length - 1;
		}
		int numNotes = subsequenceEnd - subsequenceStart + 1;
		PitchArray[] subset = new PitchArray[numNotes];
		System.arraycopy(allNotes, subsequenceStart, subset, 0, numNotes);
		pitches = subset;
	}
}
