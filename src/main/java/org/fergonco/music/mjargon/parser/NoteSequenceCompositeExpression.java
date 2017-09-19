package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.SemanticException;

public class NoteSequenceCompositeExpression implements Expression {

	private Expression[] expressions;

	public NoteSequenceCompositeExpression(Expression[] expressions) {
		this.expressions = expressions;
	}

	@Override
	public void visit(ExpressionVisitor visitor) throws SemanticException {
		visitor.composite(expressions);
	}

}
