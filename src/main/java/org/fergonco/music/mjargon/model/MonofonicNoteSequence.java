package org.fergonco.music.mjargon.model;

public class MonofonicNoteSequence implements NoteSequence {

	// In this case the PitchArrays contain only one pitch
	private PitchArray[] singleNotes;

	public MonofonicNoteSequence(String[] noteIndices, PitchArray chord) {
		singleNotes = new PitchArray[noteIndices.length];
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

	public MonofonicNoteSequence(String[] notes) throws SemanticException {
		singleNotes = ChordLiteral.parseSequence(notes);
		for (PitchArray pitchArray : singleNotes) {
			if (!pitchArray.isTie() && pitchArray.pitchCount() > 1) {
				throw new SemanticException("Only single notes allowed in note sequences");
			}
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
