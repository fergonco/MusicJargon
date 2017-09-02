package org.fergonco.music.mjargon.model;

public class Repeat extends AbstractSongLine implements SongLine {

	private int songlineIndex;
	private int currentRepetition = 0;
	private int times;

	public Repeat(int songlineIndex, int times) {
		this.songlineIndex = songlineIndex;
		this.times = times;
	}

	@Override
	public boolean isRepeat() {
		return true;
	}

	@Override
	public int getTarget() {
		currentRepetition++;
		return currentRepetition < times ? songlineIndex : -1;
	}
}
