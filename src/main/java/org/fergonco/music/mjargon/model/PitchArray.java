package org.fergonco.music.mjargon.model;

public interface PitchArray {

	int getPitch(int index);

	int pitchCount();

	int[] getPitches();
	
	boolean isTie();

	boolean isDrums();

	boolean isSilence();
	
	boolean isAccented();
}