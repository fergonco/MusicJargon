package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

import org.fergonco.music.midi.Dynamic;

public class Rhythm {

	private ArrayList<RhythmComponent> components = new ArrayList<>();

	public Rhythm(String expression, TimeSignature timeSignature) {
		int characterLength = timeSignature.getSubdivisionLength(expression.length());
		int currentDuration = -1;
		int lastDynamic = -1;
		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			if (ch == 'x' || ch == 'X') {
				if (currentDuration != -1) {
					components.add(new RhythmComponent(currentDuration, lastDynamic));
				}
				currentDuration = characterLength;
				lastDynamic = ch == 'X' ? Dynamic.FF : Dynamic.MF;
			} else if (ch == '.') {
				if (currentDuration == -1) { // start rhythm with a silence
					currentDuration = characterLength;
					lastDynamic = 0;
				} else {
					currentDuration += characterLength;
				}
			}
		}
	}

	public RhythmComponent[] getComponents() {
		return components.toArray(new RhythmComponent[components.size()]);
	}

}
