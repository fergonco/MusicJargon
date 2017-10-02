package org.fergonco.music.mjargon.model;

public class Barline extends AbstractSongLine implements SongLine {

	private Bar[] bars;

	public Barline(Bar[] bars) {
		this.bars = bars;
	}

	@Override
	public boolean isBarline() {
		return true;
	}
	
	@Override
	public Bar[] getBars() {
		return bars;
	}

	@Override
	public void validate(Model model) throws SemanticException {
		for (Bar bar : bars) {
			bar.validate();
		}
	}

}
