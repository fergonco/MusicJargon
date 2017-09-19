package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.SemanticException;

public class SequenceReferenceExpression implements Expression {

	private String sequenceId;
	private SequenceAccesor sequenceAccesor;

	public SequenceReferenceExpression(String sequenceId, SequenceAccesor sequenceAccesor) {
		this.sequenceId = sequenceId;
		this.sequenceAccesor= sequenceAccesor;
	}

	@Override
	public void visit(ExpressionVisitor visitor) throws SemanticException {
		visitor.sequenceReference(sequenceId, sequenceAccesor);
	}

}
