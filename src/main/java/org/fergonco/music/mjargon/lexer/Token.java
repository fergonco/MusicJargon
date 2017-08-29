package org.fergonco.music.mjargon.lexer;

public interface Token {

	String getText();
	
	Token next();
	
	int getType();

	int getPosition();
}
