package org.fergonco.music.mjargon.model;

import org.fergonco.music.mjargon.model.functions.ValueType;

public class Repeat extends AbstractSongLine implements SongLine {

	private String label;
	private Integer labelIndex;
	private int currentRepetition = 0;
	private Value times;

	public Repeat(String label, Value times) {
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
		if (currentRepetition < times.toInt()) {
			return labelIndex;
		} else {
			currentRepetition = 0;
			return -1;
		}
	}

	@Override
	public void validate(Model model, int songlineIndex) throws SemanticException {
		Integer labelIndex = model.getLabel(label);
		if (labelIndex == null) {
			throw new SemanticException("Unknown label: " + label);
		}
		if (times.getType() != ValueType.NUMBER) {
			throw new SemanticException("Repeat expression should evaluate to a number");
		}
		this.labelIndex = labelIndex;
	}
}
