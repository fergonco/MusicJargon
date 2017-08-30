package org.fergonco.music.mjargon.model;

public interface SongLine {

	boolean isBarline();

	Bar[] getBars();

	boolean isTempo();

	int getTempo();

	boolean isRepeat();

	int getTarget();

}
