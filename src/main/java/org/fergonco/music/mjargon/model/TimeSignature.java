package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Duration;

public class TimeSignature {

	private int n1;
	private int n2;

	public TimeSignature(int n1, int n2) {
		this.n1 = n1;
		this.n2 = n2;
	}

	public Duration getSubdivisionDuration(int length) {
		double subdivisionsPerBeat = length / (double) n1;
		return new Duration(1 / subdivisionsPerBeat);
	}

}
