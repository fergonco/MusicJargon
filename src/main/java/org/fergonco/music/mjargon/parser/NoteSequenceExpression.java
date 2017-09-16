package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.SemanticException;

public interface NoteSequenceExpression {

	void visit(NoteSequenceExpressionVisitor visitor) throws SemanticException;
}
