package org.fergonco.music.mjargon.model.functions;

import org.fergonco.music.mjargon.model.AbstractValue;
import org.fergonco.music.mjargon.model.Value;

public abstract class AbstractFunction extends AbstractValue implements Function {

	private Value[] parameters;

	@Override
	public void setParameters(Value[] parameters) {
		this.parameters = parameters;
	}
	
	public Value[] getParameters() {
		return parameters;
	}
}
