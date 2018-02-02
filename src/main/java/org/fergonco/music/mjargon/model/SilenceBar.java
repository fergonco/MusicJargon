package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Duration;
import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.Note;
import org.fergonco.music.midi.NoteImpl;

public class SilenceBar implements Bar {

	@Override
	public Note[] getNotes(Model model, int songlineIndex, int voiceIndex, Dynamic baseDynamics, Note lastNote) {
		return new Note[] { new NoteImpl(0, new Duration(1), Dynamic.MUTE.getLevel()) };
	}

	@Override
	public void validate(Model model, int songlineIndex, int voiceIndex) {
	}

}
