package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

import org.fergonco.music.midi.Duration;
import org.fergonco.music.midi.Dynamic;

public class Rhythm {

	private ArrayList<RhythmComponent> components = new ArrayList<>();

	public Rhythm(String expression, TimeSignature timeSignature) {
		Duration subdivisionDuration = timeSignature.getSubdivisionDuration(expression.length());
		int currentSubdivisionCount = -1;
		int lastDynamicIncrease = 0;
		boolean silence = false;
		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			if (ch == 'x' || ch == 'X') {
				if (currentSubdivisionCount != -1) {
					addRhythmComponent(subdivisionDuration, currentSubdivisionCount, lastDynamicIncrease, silence);
				}
				currentSubdivisionCount = 1;
				lastDynamicIncrease = ch == 'X' ? 1 : 0;
				silence = false;
			} else if (ch == '.') {
				// start rhythm with a silence
				if (currentSubdivisionCount == -1) {
					currentSubdivisionCount = 1;
					silence = true;
				} else {
					currentSubdivisionCount++;
				}
			}
		}
		addRhythmComponent(subdivisionDuration, currentSubdivisionCount, lastDynamicIncrease, silence);
	}

	private void addRhythmComponent(Duration subdivisionDuration, int currentSubdivisionCount, int lastDynamicIncrease,
			boolean silence) {
		Duration duration = new Duration(subdivisionDuration.getNumBeats() * currentSubdivisionCount);
		RhythmComponent rhythmComponent;
		if (silence) {
			rhythmComponent =new RhythmComponent(duration, silence); 
		} else {
			rhythmComponent =new RhythmComponent(duration, lastDynamicIncrease); 
		}
		components.add(rhythmComponent);
	}

	public RhythmComponent[] getComponents() {
		return components.toArray(new RhythmComponent[components.size()]);
	}

}
