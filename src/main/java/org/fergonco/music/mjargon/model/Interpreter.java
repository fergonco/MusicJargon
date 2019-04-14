package org.fergonco.music.mjargon.model;

public interface Interpreter {

	boolean hasNext();

	int next();

	void jumpTo(int index);

}
