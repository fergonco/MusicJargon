package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.SemanticException;

public class FunctionExpression implements Expression {

	private String id;
	private Expression[] parameters;

	public FunctionExpression(String functionId, Expression[]parameters) {
		this.id = functionId;
		this.parameters = parameters;
	}

	@Override
	public void visit(ExpressionVisitor visitor) throws SemanticException {
		visitor.function(id, parameters);
	}

}
