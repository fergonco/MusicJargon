package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Dynamic;

public interface SongLine {

	boolean isBarline();

	Bar[] getBars();

	boolean isTempo();

	int getTempo();

	boolean isRepeat();

	int getTarget();

	boolean isDynamics();

	Dynamic[] getDynamics();

}
