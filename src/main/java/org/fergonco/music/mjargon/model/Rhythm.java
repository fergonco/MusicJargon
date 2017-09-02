package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

import org.fergonco.music.midi.Duration;

public class Rhythm {

	private ArrayList<RhythmComponent> components = new ArrayList<>();

	public Rhythm(String expression, TimeSignature timeSignature) {
		int length = expression.length();
		Duration subdivisionDuration = timeSignature.getSubdivisionDuration(length);
		RhythmComponent current = null;
		for (int i = 0; i < length; i++) {
			char symbol = expression.charAt(i);
			if (current == null) {
				current = new RhythmComponent(subdivisionDuration.getNumBeats(), symbol);
			} else {
				RhythmComponent next = current.process(symbol, subdivisionDuration.getNumBeats());
				if (next != null) {
					components.add(current);
					current = next;
				}
			}
		}
	}

	public RhythmComponent[] getComponents() {
		return components.toArray(new RhythmComponent[components.size()]);
	}

}
