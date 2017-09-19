package org.fergonco.music.mjargon.model;

import org.fergonco.music.mjargon.model.functions.ValueType;

public interface Value {

	NoteSequence toNoteSequence();
	
	int toInt();
	
	String toStringLiteral();

	ValueType getType();

}
