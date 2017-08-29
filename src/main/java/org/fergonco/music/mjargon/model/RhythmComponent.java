package org.fergonco.music.mjargon.model;

public class RhythmComponent {
	private int duration;
	private int dynamic;

	public RhythmComponent(int duration, int dynamic) {
		super();
		this.duration = duration;
		this.dynamic = dynamic;
	}

	public int getDuration() {
		return duration;
	}

	public int getDynamic() {
		return dynamic;
	}

}
