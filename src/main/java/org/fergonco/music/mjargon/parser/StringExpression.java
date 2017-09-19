package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.SemanticException;

public class StringExpression implements Expression {

	private String string;

	public StringExpression(String string) {
		this.string = string;
	}

	@Override
	public void visit(ExpressionVisitor visitor) throws SemanticException {
		visitor.string(string);
	}

}
