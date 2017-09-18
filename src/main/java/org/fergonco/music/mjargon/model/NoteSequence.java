package org.fergonco.music.mjargon.model;

public interface NoteSequence {

	PitchArray getNote(int noteIndex);

	PitchArray[] getAllNotes(int numNotes);

	PitchArray[] getAllNotes();

}
