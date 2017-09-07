package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Duration;

public class RhythmComponent {
	private Duration duration;
	private char beatSymbol;

	public RhythmComponent(Duration duration, char symbol) {
		this.duration = duration.cloneDuration();
		this.beatSymbol = symbol;
	}

	public Duration getDuration() {
		return duration.cloneDuration();
	}

	public boolean isAccent() {
		return Character.isUpperCase(beatSymbol);
	}

	public boolean isSilence() {
		return beatSymbol == '.';
	}

	public RhythmComponent process(char symbol, Duration subdivisionDuration) {
		if (symbol != '.') {
			return new RhythmComponent(subdivisionDuration, symbol);
		} else {
			duration.add(subdivisionDuration);
			return null;
		}
	}

	public char getBeatSymbol() {
		return beatSymbol;
	}

}
