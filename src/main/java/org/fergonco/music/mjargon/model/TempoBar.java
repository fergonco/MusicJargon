package org.fergonco.music.mjargon.model;

public class TempoBar extends AbstractSongLine implements SongLine {

	private int tempo;

	public TempoBar(int tempo) {
		this.tempo = tempo;
	}

	@Override
	public boolean isTempo() {
		return true;
	}

	@Override
	public int getTempo() {
		return tempo;
	}
}
