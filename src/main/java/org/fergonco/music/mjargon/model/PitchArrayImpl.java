package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

public class PitchArrayImpl implements PitchArray {

	private ArrayList<Integer> pitch = new ArrayList<>();
	private boolean drums;
	private boolean silence;
	private boolean accented;

	public void setDrums(boolean drums) {
		this.drums = drums;
	}

	public int getPitch(int index) {
		return pitch.get(index);
	}

	public int pitchCount() {
		return pitch.size();
	}

	public int[] getPitches() {
		int[] intPitches = new int[pitch.size()];
		for (int i = 0; i < pitch.size(); i++) {
			intPitches[i] = pitch.get(i);
		}
		return intPitches;
	}

	@Override
	public boolean isTie() {
		return false;
	}

	@Override
	public boolean isDrums() {
		return drums;
	}

	public boolean isSilence() {
		return silence;
	}

	public void add(int newPitch) {
		this.pitch.add(newPitch);
	}

	public void setSilence() {
		this.silence = true;
	}

	public void setAccented(boolean accented) {
		this.accented = accented;
	}

	@Override
	public boolean isAccented() {
		return this.accented;
	}
}
