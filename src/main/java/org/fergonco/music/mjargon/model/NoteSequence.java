package org.fergonco.music.mjargon.model;

public interface NoteSequence extends Value {

	PitchArray[] getAllNotes(int numNotes);

	PitchArray[] getAllNotes();

}
