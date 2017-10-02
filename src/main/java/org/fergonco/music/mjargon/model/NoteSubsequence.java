package org.fergonco.music.mjargon.model;

public class NoteSubsequence extends AbstractSingleSequence implements NoteSequence {

	private Value sequence;
	private int subsequenceStart;
	private int subsequenceEnd;

	public NoteSubsequence(Value sequence, int subsequenceStart, int subsequenceEnd) {
		this.sequence = sequence;
		this.subsequenceStart = subsequenceStart;
		this.subsequenceEnd = subsequenceEnd;
	}

	@Override
	public void validate() throws SemanticException {
		if (pitches == null) {
			PitchArray[] allNotes = sequence.toNoteSequence().getAllNotes();
			if (subsequenceEnd == -1) {
				subsequenceEnd = allNotes.length - 1;
			}
			int numNotes = subsequenceEnd - subsequenceStart + 1;
			PitchArray[] subset = new PitchArray[numNotes];
			System.arraycopy(allNotes, subsequenceStart, subset, 0, numNotes);
			pitches = subset;
		}
	}
}
