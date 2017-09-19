package org.fergonco.music.mjargon.model.functions;

import org.fergonco.music.mjargon.model.NoteSequence;
import org.fergonco.music.mjargon.model.SemanticException;

public abstract class AbstractFunction implements Function {

	protected NoteSequence[] parameters;

	@Override
	public void setParameters(NoteSequence[] parameters) throws SemanticException {
		this.parameters = parameters;
	}

}
