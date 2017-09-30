package org.fergonco.music.mjargon.model.functions;

import org.fergonco.music.mjargon.model.SemanticException;
import org.fergonco.music.mjargon.model.Value;

public interface Function extends Value {

	String getId();

	void setParameters(Value[] values) throws SemanticException;

}
