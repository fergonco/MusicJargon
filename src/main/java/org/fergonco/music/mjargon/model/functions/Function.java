package org.fergonco.music.mjargon.model.functions;

import org.fergonco.music.mjargon.model.PitchArray;
import org.fergonco.music.mjargon.model.SemanticException;
import org.fergonco.music.mjargon.model.Value;

public interface Function {

	String getId();

	void setParameters(Value[] values) throws SemanticException;

	PitchArray[] getNotes(int numNotes);

	PitchArray[] getAllNotes();

}
