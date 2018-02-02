package org.fergonco.music.mjargon.model;

public class TiedPitchArray implements PitchArray {

	@Override
	public int getPitch(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int pitchCount() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int[] getPitches() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isTie() {
		return true;
	}

	@Override
	public boolean isDrums() {
		return false;
	}

	@Override
	public boolean isSilence() {
		return false;
	}

}
