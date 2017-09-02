package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.Note;

public interface Bar {
	Note[] getNotes(Dynamic baseDynamics);
}
