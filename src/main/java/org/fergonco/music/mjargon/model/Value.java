package org.fergonco.music.mjargon.model;

import org.fergonco.music.mjargon.model.functions.ValueType;

public interface Value {

	SequenceAndRhythm toAural();
	
	NoteSequence toNoteSequence();
	
	Rhythm toRhythm();
	
	FractionValue toFraction();
	
	int toInt();
	
	String toStringLiteral();

	ValueType getType();

	void validate() throws SemanticException;

}
