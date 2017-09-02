package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Duration;

public class RhythmComponent {
	private Duration duration;
	private char beatSymbol;

	public RhythmComponent(double multiplier, char symbol) {
		this.duration = new Duration(multiplier);
		this.beatSymbol = symbol;
	}

	public Duration getDuration() {
		return duration;
	}

	public boolean isAccent() {
		return Character.isUpperCase(beatSymbol);
	}

	public boolean isSilence() {
		return beatSymbol == '.';
	}

	public RhythmComponent process(char symbol, double multiplier) {
		if (symbol != '.') {
			return new RhythmComponent(multiplier, symbol);
		} else {
			duration.add(multiplier);
			return null;
		}
	}

	public char getBeatSymbol() {
		return beatSymbol;
	}

}
