package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Duration;

public class RhythmComponent {
	private Duration duration;
	private char[] beatSymbols;

	public RhythmComponent(Duration duration, char[] beatSymbols) {
		this.duration = duration;
		this.beatSymbols = beatSymbols;
	}

	public Duration getDuration() {
		return duration;
	}

	public boolean isAccent(int voiceIndex) {
		return Character.isUpperCase(beatSymbols[voiceIndex]);
	}

	public boolean isSilence() {
		for (char symbol : beatSymbols) {
			if (symbol != '.') {
				return false;
			}
		}
		return true;
	}

	public char getBeatSymbol(int voiceIndex) {
		return beatSymbols[voiceIndex];
	}

	public RhythmComponent process(char[] symbols, Duration symbolDuration) {
		boolean anyBeat = false;
		for (char symbol : symbols) {
			if (symbol != '.') {
				anyBeat = true;
			}
		}
		if (anyBeat) {
			return new RhythmComponent(symbolDuration, symbols);
		} else {
			duration.add(symbolDuration);
			return null;
		}
	}

	public int getVoiceCount() {
		return beatSymbols.length;
	}
}
