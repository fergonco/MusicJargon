package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.Note;

public interface Bar {
	Note[] getNotes(Model model, int songlineIndex, int voiceIndex, Dynamic baseDynamics, Note lastNote);

	void validate(Model model, int songlineIndex, int voiceIndex) throws SemanticException;
}
