package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

public class PitchArray {

	private int[] pitch;

	public PitchArray(int... pitch) {
		super();
		this.pitch = pitch;
	}

	public PitchArray(ArrayList<Integer> pitchList) {
		int[] intPitches = new int[pitchList.size()];
		for (int i = 0; i < pitchList.size(); i++) {
			intPitches[i] = pitchList.get(i);
		}
		this.pitch = intPitches;
	}

	public int getPitch(int index) {
		return pitch[index];
	}

	public int pitchCount() {
		return pitch.length;
	}

	public int[] getPitches() {
		return pitch;
	}

}
