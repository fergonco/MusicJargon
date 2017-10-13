package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.Note;

public class InstrumentRepeatBar implements Bar {

	@Override
	public Note[] getNotes(Model model, int songlineIndex, int voiceIndex, Dynamic baseDynamics, Note lastNote) {
		Bar instrumentBar = model.getBar(songlineIndex, -1, voiceIndex);
		return instrumentBar.getNotes(model, songlineIndex - 1, voiceIndex, baseDynamics, lastNote);
	}

	@Override
	public void validate() throws SemanticException {
	}

}
