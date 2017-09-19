package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

public class NoteSequenceComposite extends AbstractNoteSequence implements NoteSequence {

	private PitchArray[] notes;
	private int repeatingIndex;

	public NoteSequenceComposite(NoteSequence... sequences) {
		ArrayList<PitchArray> allSequenceNotes = new ArrayList<>();
		int repeatingIndex = 0;
		for (NoteSequence noteSequence : sequences) {
			PitchArray[] notes = noteSequence.getAllNotes();
			repeatingIndex = allSequenceNotes.size();
			for (PitchArray note : notes) {
				allSequenceNotes.add(note);
			}
		}

		this.notes = allSequenceNotes.toArray(new PitchArray[allSequenceNotes.size()]);
		this.repeatingIndex = repeatingIndex;
	}

	@Override
	public PitchArray[] getAllNotes(int numNotes) {
		PitchArray[] ret = new PitchArray[numNotes];
		int noteIndex = 0;
		for (int i = 0; i < ret.length; i++) {
			ret[i] = notes[noteIndex];
			noteIndex++;
			if (noteIndex == notes.length) {
				noteIndex = repeatingIndex;
			}
		}
		return ret;
	}

	@Override
	public PitchArray[] getAllNotes() {
		return notes;
	}

}
