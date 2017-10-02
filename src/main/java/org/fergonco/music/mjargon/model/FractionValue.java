package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Duration;
import org.fergonco.music.mjargon.model.functions.ValueType;

public class FractionValue extends AbstractValue implements Value {

	private int numerator;
	private int denominator;

	public FractionValue(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	@Override
	public FractionValue toFraction() {
		return this;
	}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	public Duration getSubdivisionDuration(int length) {
		double totalLength = numerator / (double) denominator;
		double lengthPerSubdivision = totalLength / length;
		return new Duration(lengthPerSubdivision);
	}

	@Override
	public ValueType getType() {
		return ValueType.FRACTION;
	}
}
