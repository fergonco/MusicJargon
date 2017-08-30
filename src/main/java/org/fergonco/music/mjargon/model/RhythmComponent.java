package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Duration;

public class RhythmComponent {
	private Duration duration;
	private int dynamic;
	private boolean silence;

	public RhythmComponent(Duration duration, int dynamic) {
		super();
		this.duration = duration;
		this.dynamic = dynamic;
		this.silence = false;
	}

	public RhythmComponent(Duration duration, boolean silence) {
		this.duration = duration;
		this.dynamic = 0;
		this.silence = silence;
	}

	public Duration getDuration() {
		return duration;
	}

	public int getDynamicIncrease() {
		return dynamic;
	}
	
	public boolean isSilence() {
		return silence;
	}

}
