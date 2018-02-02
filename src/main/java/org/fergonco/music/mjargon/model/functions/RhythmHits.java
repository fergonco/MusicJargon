package org.fergonco.music.mjargon.model.functions;

import org.fergonco.music.mjargon.model.Rhythm;
import org.fergonco.music.mjargon.model.SemanticException;
import org.fergonco.music.mjargon.model.Value;

public class RhythmHits extends AbstractFunction implements Function {

	@Override
	public String getId() {
		return "rhythmHits";
	}

	@Override
	public void validate() throws SemanticException {
		Value[] parameters = getParameters();
		if (parameters.length != 1) {
			throw new SemanticException("rrhythm takes a fraction");
		} else {
			if (parameters[0].getType() != ValueType.RHYTHM) {
				throw new SemanticException("arpeggio takes a rhythm as parameter");
			}
		}
	}

	@Override
	public int toInt() {
		Rhythm rhythm = getParameters()[0].toRhythm();
		return rhythm.getComponents().length;
	}

	@Override
	public ValueType getType() {
		return ValueType.NUMBER;
	}

}
