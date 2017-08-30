package org.fergonco.music.mjargon.model;

public abstract class AbstractSongLine implements SongLine{

	@Override
	public boolean isBarline() {
		return false;
	}

	@Override
	public Bar[] getBars() {
		return null;
	}

	@Override
	public boolean isTempo() {
		return false;
	}

	@Override
	public int getTempo() {
		return 0;
	}

	@Override
	public boolean isRepeat() {
		return false;
	}

	@Override
	public int getTarget() {
		return 0;
	}

}
