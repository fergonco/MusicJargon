package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.SemanticException;

public class NumberExpression implements Expression {

	private int number;

	public NumberExpression(int number) {
		this.number = number;
	}

	@Override
	public void visit(ExpressionVisitor visitor) throws SemanticException {
		visitor.number(number);
	}

}
