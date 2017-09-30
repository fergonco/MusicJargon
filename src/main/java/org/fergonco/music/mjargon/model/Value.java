package org.fergonco.music.mjargon.model;

import org.fergonco.music.mjargon.model.functions.ValueType;

public interface Value {

	SequenceAndRhythm toAural();
	
	NoteSequence toNoteSequence();
	
	Rhythm toRhythm();
	
	TimeSignature toTimeSignature();
	
	int toInt();
	
	String toStringLiteral();

	ValueType getType();

}
