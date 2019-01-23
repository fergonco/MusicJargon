package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.Note;

public class LikeLabelRepeatBar implements Bar {

	private String labelName;
	private int shift;

	public LikeLabelRepeatBar(String labelName, int shift) {
		this.labelName = labelName;
		this.shift = shift;
	}

	@Override
	public Note[] getNotes(Model model, int songlineIndex, int voiceIndex, Dynamic baseDynamics, Note lastNote) {
		if (labelName != null) {
			songlineIndex = model.getLabel(labelName);
		}
		Bar instrumentBar = model.getBar(songlineIndex, shift, voiceIndex);
		return instrumentBar.getNotes(model, songlineIndex, voiceIndex, baseDynamics, lastNote);
	}

	@Override
	public void validate(Model model, int songlineIndex, int voiceIndex) throws SemanticException {
		if (model.getLabel(labelName) == null) {
			throw new SemanticException("Label not found: " + labelName);
		}
	}

	public String getLabelName() {
		return labelName;
	}
	
	public int getShift() {
		return shift;
	}

}
