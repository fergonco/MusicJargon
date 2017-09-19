package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.SemanticException;

public interface Expression {

	void visit(ExpressionVisitor visitor) throws SemanticException;
}
