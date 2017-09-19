package org.fergonco.music.mjargon.model;

public interface NoteSequence {

	PitchArray[] getAllNotes(int numNotes);

	PitchArray[] getAllNotes();

}
