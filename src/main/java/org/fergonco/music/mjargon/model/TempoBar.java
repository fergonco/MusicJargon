package org.fergonco.music.mjargon.model;

public class TempoBar extends AbstractSongLine implements SongLine {

	private double tempo;

	public TempoBar(double tempo) {
		this.tempo = tempo;
	}

	@Override
	public boolean isTempo() {
		return true;
	}

	@Override
	public double getTempo() {
		return tempo;
	}

	@Override
	public void validate(Model model) throws SemanticException {
	}
}
