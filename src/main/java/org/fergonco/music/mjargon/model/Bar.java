package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

import org.fergonco.music.midi.Chord;
import org.fergonco.music.midi.Duration;
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

		ArrayList<Note> ret = new ArrayList<>();
		RhythmComponent[] components = rhythm.getComponents();
		int pitchesIndex = 0;
		for (int i = 0; i < components.length; i++) {
			PitchArray pitch = pitches[pitchesIndex];
			int dynamic = components[i].getDynamic();
			Duration duration = components[i].getDuration();
			Note note = null;
			if (pitch.pitchCount() == 1) {
				note = new NoteImpl(pitch.getPitch(0), duration, dynamic);
			} else {
				note = new Chord(duration, dynamic, pitch.getPitches());
			}
			ret.add(note);
		}

		return ret.toArray(new Note[ret.size()]);
	}

}
