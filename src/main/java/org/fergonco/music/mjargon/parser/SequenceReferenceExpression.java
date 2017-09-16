package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.SemanticException;

public class SequenceReferenceExpression implements NoteSequenceExpression {

	private String sequenceId;
	private int noteSequenceIndex;

	public SequenceReferenceExpression(String sequenceId, int noteSequenceIndex) {
		this.sequenceId = sequenceId;
		this.noteSequenceIndex = noteSequenceIndex;
	}

	@Override
	public void visit(NoteSequenceExpressionVisitor visitor) throws SemanticException {
		visitor.sequenceReference(sequenceId, noteSequenceIndex);
	}

}
