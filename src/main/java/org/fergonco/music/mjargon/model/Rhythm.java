package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

import org.fergonco.music.midi.Duration;
import org.fergonco.music.mjargon.model.functions.ValueType;

public class Rhythm extends AbstractValue implements Value {

	private ArrayList<RhythmComponent> components = new ArrayList<>();

	public Rhythm(String expression, Value timeSignature) {
		int length = expression.length();
		Duration subdivisionDuration = timeSignature.toFraction().getSubdivisionDuration(length);
		RhythmComponent current = null;
		for (int i = 0; i < length; i++) {
			char symbol = expression.charAt(i);
			if (current == null) {
				current = new RhythmComponent(subdivisionDuration, symbol);
			} else {
				RhythmComponent next = current.process(symbol, subdivisionDuration);
				if (next != null) {
					components.add(current);
					current = next;
				}
			}
		}
		components.add(current);
	}

	public RhythmComponent[] getComponents() {
		return components.toArray(new RhythmComponent[components.size()]);
	}

	@Override
	public Rhythm toRhythm() {
		return this;
	}
	
	@Override
	public ValueType getType() {
		return ValueType.RHYTHM;
	}
}
