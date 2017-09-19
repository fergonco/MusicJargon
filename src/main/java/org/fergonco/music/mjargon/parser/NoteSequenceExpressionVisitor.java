package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.DrumNote;
import org.fergonco.music.mjargon.model.SemanticException;

public interface NoteSequenceExpressionVisitor {

	void sequenceReference(String noteOrDrumsSequenceId, SequenceAccesor sequenceAccesor) throws SemanticException;

	void pitched(String[] notes) throws SemanticException;

	void drums(DrumNote[] notes) throws SemanticException;

	void chordBasedPitched(String[] notes, String chordProgressionId, int chordProgressionIndex)
			throws SemanticException;

	void composite(NoteSequenceExpression[] expressions) throws SemanticException;

	void function(String id, NoteSequenceExpression[] parameters) throws SemanticException;

}
