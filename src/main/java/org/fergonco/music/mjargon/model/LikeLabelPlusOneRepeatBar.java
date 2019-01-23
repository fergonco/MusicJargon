package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.Note;

public class LikeLabelPlusOneRepeatBar implements Bar {

	private String labelName = null;
	private int shift = Integer.MAX_VALUE;

	@Override
	public Note[] getNotes(Model model, int songlineIndex, int voiceIndex, Dynamic baseDynamics, Note lastNote) {
		LabelAndShift labelAndShift = getLastLabelReference(model, songlineIndex, voiceIndex);
		labelName = labelAndShift.label;
		shift = labelAndShift.shift;
		songlineIndex = model.getLabel(labelName);
		Bar instrumentBar = model.getBar(songlineIndex, shift, voiceIndex);
		return instrumentBar.getNotes(model, songlineIndex + shift, voiceIndex, baseDynamics, lastNote);
	}

	private LabelAndShift getLastLabelReference(Model model, int songlineIndex, int voiceIndex) {
		int offset = -1;
		int shift = 1;
		while (songlineIndex + offset >= 0) {
			Bar bar = model.getBar(songlineIndex, offset, voiceIndex);
			if (bar instanceof LikeLabelPlusOneRepeatBar) {
				shift++;
			} else if (bar instanceof LikeLabelRepeatBar) {
				LikeLabelRepeatBar repeatBar = (LikeLabelRepeatBar) bar;
				LabelAndShift ret = new LabelAndShift();
				ret.label = repeatBar.getLabelName();
				ret.shift = repeatBar.getShift() + shift;
				return ret;
			}
			offset--;
		}
		return null;
	}

	@Override
	public void validate(Model model, int songlineIndex, int voiceIndex) throws SemanticException {
		if (getLastLabelReference(model, songlineIndex, voiceIndex) == null) {
			throw new SemanticException("Cannot find a 'like' previous bar");
		}
	}

	private class LabelAndShift {
		private String label;
		private int shift;
	}

}
