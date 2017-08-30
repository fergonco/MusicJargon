package org.fergonco.music.mjargon.model;

public class Barline extends AbstractSongLine implements SongLine {

	private Bar[] bars;

	public Barline(int numInstruments) {
		bars = new Bar[numInstruments];
	}

	public void setInstrumentBar(int instrumentIndex, Bar bar) {
		bars[instrumentIndex] = bar;
	}
	
	@Override
	public boolean isBarline() {
		return true;
	}
	
	@Override
	public Bar[] getBars() {
		return bars;
	}

}
