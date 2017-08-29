package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

import org.fergonco.music.midi.Duration;
import org.fergonco.music.midi.Dynamic;

public class Rhythm {

	private ArrayList<RhythmComponent> components = new ArrayList<>();

	public Rhythm(String expression, TimeSignature timeSignature) {
		Duration subdivisionDuration = timeSignature.getSubdivisionDuration(expression.length());
		int currentSubdivisionCount = -1;
		int lastDynamic = -1;
		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			if (ch == 'x' || ch == 'X') {
				if (currentSubdivisionCount != -1) {
					addRhythmComponent(subdivisionDuration, currentSubdivisionCount, lastDynamic);
				}
				currentSubdivisionCount = 1;
				lastDynamic = ch == 'X' ? Dynamic.FF : Dynamic.MF;
			} else if (ch == '.') {
				// start rhythm with a silence
				if (currentSubdivisionCount == -1) {
					currentSubdivisionCount = 1;
					lastDynamic = 0;
				} else {
					currentSubdivisionCount++;
				}
			}
		}
		addRhythmComponent(subdivisionDuration, currentSubdivisionCount, lastDynamic);
	}

	private void addRhythmComponent(Duration subdivisionDuration, int currentSubdivisionCount, int lastDynamic) {
		Duration duration = new Duration(subdivisionDuration.getNumBeats() * currentSubdivisionCount);
		components.add(new RhythmComponent(duration, lastDynamic));
	}

	public RhythmComponent[] getComponents() {
		return components.toArray(new RhythmComponent[components.size()]);
	}

}
