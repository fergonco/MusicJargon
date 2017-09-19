package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.SemanticException;

public class PitchedLiteralExpression implements Expression {

	private String[] notes;

	public PitchedLiteralExpression(String[] notes) {
		this.notes = notes;
	}

	@Override
	public void visit(ExpressionVisitor visitor) throws SemanticException {
		visitor.pitched(notes);
	}

	public String[] getNotes() {
		return notes;
	}

}
