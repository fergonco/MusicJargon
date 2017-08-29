package org.fergonco.music.mjargon.model;

import java.util.Iterator;

import org.fergonco.music.midi.Note;
import org.fergonco.music.midi.NoteImpl;

public class Bar {

	private NoteSequence noteSequence;
	private int noteIndex;
	private Rhythm rhythm;

	public Bar(NoteSequence noteSequence, int noteIndex, Rhythm rhythm) {
		this.noteSequence = noteSequence;
		this.noteIndex = noteIndex;
		this.rhythm = rhythm;
	}

	public Note[] getNotes() {
		PitchArray[] pitches = null;
		if (noteIndex == -1) {
			pitches = noteSequence.getAllNotes();
		} else {
			pitches = new PitchArray[] { noteSequence.getNote(noteIndex) };
		}
		
		RhythmComponent[] components = rhythm.getComponents();
		int pitchesIndex = 0;
		for (int i = 0; i < components.length; i++) {
			Note note = null;
			PitchArray pitch = pitches[pitchesIndex];
			if (pitch.pitchCount()==1) {
				note = new NoteImpl(pitch.getPitch(0), components[i].getDuration(),components[i].getDynamic());
			}
		}
	}

}
