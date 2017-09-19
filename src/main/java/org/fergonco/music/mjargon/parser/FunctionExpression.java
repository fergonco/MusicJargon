package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.SemanticException;

public class FunctionExpression implements NoteSequenceExpression {

	private String id;
	private NoteSequenceExpression[] parameters;

	public FunctionExpression(String functionId, NoteSequenceExpression[]parameters) {
		this.id = functionId;
		this.parameters = parameters;
	}

	@Override
	public void visit(NoteSequenceExpressionVisitor visitor) throws SemanticException {
		visitor.function(id, parameters);
	}

}
