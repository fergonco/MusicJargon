package org.fergonco.music.mjargon.model.functions;

import org.fergonco.music.mjargon.model.NoteSequence;
import org.fergonco.music.mjargon.model.PitchArray;
import org.fergonco.music.mjargon.model.SemanticException;

public interface Function {

	String getId();

	void setParameters(NoteSequence[] parameters) throws SemanticException;

	PitchArray[] getNotes(int numNotes);

	PitchArray[] getAllNotes();

}
