package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.SemanticException;

public class NoteSequenceCompositeExpression implements NoteSequenceExpression {

	private NoteSequenceExpression[] expressions;

	public NoteSequenceCompositeExpression(NoteSequenceExpression[] expressions) {
		this.expressions = expressions;
	}

	@Override
	public void visit(NoteSequenceExpressionVisitor visitor) throws SemanticException {
		visitor.composite(expressions);
	}

}
