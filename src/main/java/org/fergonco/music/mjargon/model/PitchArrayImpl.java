package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

public class PitchArrayImpl implements PitchArray {

	private int[] pitch;
	private boolean drums;

	public PitchArrayImpl(int... pitch) {
		super();
		this.pitch = pitch;
	}

	public PitchArrayImpl(ArrayList<Integer> pitchList) {
		int[] intPitches = new int[pitchList.size()];
		for (int i = 0; i < pitchList.size(); i++) {
			intPitches[i] = pitchList.get(i);
		}
		this.pitch = intPitches;
	}

	public void setDrums(boolean drums) {
		this.drums = drums;
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

	@Override
	public boolean isTie() {
		return false;
	}

	@Override
	public boolean isDrums() {
		return drums;
	}
}