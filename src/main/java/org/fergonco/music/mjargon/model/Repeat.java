package org.fergonco.music.mjargon.model;

public class Repeat extends AbstractSongLine implements SongLine {

	private String label;
	private Integer labelIndex;
	private int currentRepetition = 0;
	private int times;

	public Repeat(String label, int times) {
		this.label = label;
		this.times = times;
	}

	@Override
	public boolean isRepeat() {
		return true;
	}

	@Override
	public int getTarget() {
		currentRepetition++;
		return currentRepetition < times ? labelIndex : -1;
	}

	@Override
	public void validate(Model model) throws SemanticException {
		Integer labelIndex = model.getLabel(label);
		if (labelIndex == null) {
			throw new SemanticException("Unknown label: " + label);
		}
		this.labelIndex = labelIndex;
	}
}
