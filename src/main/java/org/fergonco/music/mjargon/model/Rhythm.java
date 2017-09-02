package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

import org.fergonco.music.midi.Duration;

public class Rhythm {

	private ArrayList<RhythmComponent> components = new ArrayList<>();

	public Rhythm(String[] expressions, TimeSignature timeSignature) {
		int length = expressions[0].length();
		Duration subdivisionDuration = timeSignature.getSubdivisionDuration(length);
		RhythmComponent current = null;
		for (int i = 0; i < length; i++) {
			char[] symbols = getSymbols(expressions, i);
			if (current == null) {
				current = new RhythmComponent(subdivisionDuration, symbols);
			} else {
				RhythmComponent next = current.process(symbols, subdivisionDuration);
				if (next != null) {
					components.add(current);
					current = next;
				}
			}
		}
	}

	private char[] getSymbols(String[] expressions, int charIndex) {
		char[] ret = new char[expressions.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = expressions[i].charAt(charIndex);
		}
		return ret;
	}

	public RhythmComponent[] getComponents() {
		return components.toArray(new RhythmComponent[components.size()]);
	}

}
