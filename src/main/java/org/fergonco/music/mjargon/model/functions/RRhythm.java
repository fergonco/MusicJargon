package org.fergonco.music.mjargon.model.functions;

import org.fergonco.music.mjargon.model.FractionValue;
import org.fergonco.music.mjargon.model.Rhythm;
import org.fergonco.music.mjargon.model.SemanticException;
import org.fergonco.music.mjargon.model.Value;

public class RRhythm extends AbstractFunction implements Function {

	private Rhythm rhythm = null;

	@Override
	public String getId() {
		return "rRhythm";
	}

	@Override
	public void validate() throws SemanticException {
		Value[] parameters = getParameters();
		if (parameters.length != 1) {
			throw new SemanticException("rrhythm takes a fraction");
		} else {
			if (parameters[0].getType() != ValueType.FRACTION) {
				throw new SemanticException("arpeggio takes a fraction as parameter");
			}
		}
		FractionValue fraction = getParameters()[0].toFraction();
		int count = fraction.getNumerator();
		StringBuilder expression = new StringBuilder();
		for (int i = 0; i < count; i++) {
			if (Math.random() < 0.5) {
				expression.append("x");
			} else {
				expression.append(".");
			}
		}
		System.out.println(expression);
		rhythm = new Rhythm(expression.toString(), fraction);
		rhythm.validate();
	}

	@Override
	public Rhythm toRhythm() {
		return rhythm;
	}

	@Override
	public ValueType getType() {
		return ValueType.RHYTHM;
	}

}
