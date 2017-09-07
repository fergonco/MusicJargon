package org.fergonco.music.mjargon.model;

public class PolyphonicNoteSequence implements NoteSequence {

	private PitchArray[] chords;

	public PolyphonicNoteSequence(String[] chordStrings) {
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
