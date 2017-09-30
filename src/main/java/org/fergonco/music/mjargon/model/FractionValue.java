package org.fergonco.music.mjargon.model;

import org.fergonco.music.mjargon.model.functions.ValueType;

public class FractionValue extends AbstractValue implements Value {

	private int numerator;
	private int denominator;

	public FractionValue(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	@Override
	public TimeSignature toTimeSignature() {
		return new TimeSignature(numerator, denominator);
	}
	
	@Override
	public ValueType getType() {
		return ValueType.FRACTION;
	}
}
