package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.Note;

public class InstrumentRepeatBar implements Bar {

	private String labelName;
	private int shift;
	private boolean lastLabelReference;

	@Override
	public Note[] getNotes(Model model, int songlineIndex, int voiceIndex, Dynamic baseDynamics, Note lastNote) {
		if (lastLabelReference) {
			LabelAndShift labelAndShift = getLastLabelReference(model, songlineIndex, voiceIndex);
			labelName = labelAndShift.label;
			shift = labelAndShift.shift;
		}
		if (labelName != null) {
			songlineIndex = model.getLabel(labelName);
		}
		Bar instrumentBar = model.getBar(songlineIndex, shift, voiceIndex);
		return instrumentBar.getNotes(model, songlineIndex - 1, voiceIndex, baseDynamics, lastNote);
	}

	private LabelAndShift getLastLabelReference(Model model, int songlineIndex, int voiceIndex) {
		int offset = -1;
		int shift = 1;
		while (songlineIndex + offset >= 0) {
			Bar bar = model.getBar(songlineIndex, offset, voiceIndex);
			if (bar instanceof InstrumentRepeatBar) {
				InstrumentRepeatBar repeatBar = (InstrumentRepeatBar) bar;
				if (!repeatBar.lastLabelReference && repeatBar.labelName != null) {
					LabelAndShift ret = new LabelAndShift();
					ret.label = repeatBar.labelName;
					ret.shift = shift;
					return ret;
				}
				if (repeatBar.lastLabelReference) {
					shift++;
				}
			}
			offset--;
		}
		return null;
	}

	@Override
	public void validate(Model model, int songlineIndex, int voiceIndex) throws SemanticException {
		if (labelName != null) {
			if (model.getLabel(labelName) == null) {
				throw new SemanticException("Label not found: " + labelName);
			}
		} else if (lastLabelReference) {
			if (getLastLabelReference(model, songlineIndex, voiceIndex) == null) {
				throw new SemanticException("Cannot find a 'like' previous bar");
			}
		}
	}

	public static InstrumentRepeatBar lastOne() {
		InstrumentRepeatBar ret = new InstrumentRepeatBar();
		ret.labelName = null;
		ret.shift = -1;
		ret.lastLabelReference = false;
		return ret;
	}

	public static InstrumentRepeatBar plusOne() {
		InstrumentRepeatBar ret = new InstrumentRepeatBar();
		ret.labelName = null;
		ret.shift = Integer.MAX_VALUE; // It will be calculated later
		ret.lastLabelReference = true;
		return ret;
	}

	public static Bar labelReference(String labelName, int shift) {
		InstrumentRepeatBar ret = new InstrumentRepeatBar();
		ret.labelName = labelName;
		ret.shift = shift;
		ret.lastLabelReference = false;
		return ret;
	}

	private class LabelAndShift {
		private String label;
		private int shift;
	}

}
