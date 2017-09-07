package org.fergonco.music.mjargon.model;

public class TiedPichArray implements PitchArray {

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

}
