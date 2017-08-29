package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Duration;

public class RhythmComponent {
	private Duration duration;
	private int dynamic;

	public RhythmComponent(Duration duration, int dynamic) {
		super();
		this.duration = duration;
		this.dynamic = dynamic;
	}

	public Duration getDuration() {
		return duration;
	}

	public int getDynamic() {
		return dynamic;
	}

}
