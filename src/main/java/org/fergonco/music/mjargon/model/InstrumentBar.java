package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

import org.fergonco.music.midi.Chord;
import org.fergonco.music.midi.DrumNoteImpl;
import org.fergonco.music.midi.Duration;
import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.Note;
import org.fergonco.music.midi.NoteImpl;

public class InstrumentBar implements Bar {

	private NoteSequence noteSequence;
	private int noteIndex;
	private Rhythm rhythm;

	public InstrumentBar(NoteSequence noteSequence, int noteIndex, Rhythm rhythm) {
		this.noteSequence = noteSequence;
		this.noteIndex = noteIndex;
		this.rhythm = rhythm;
	}

	public Note[] getNotes(Dynamic baseDynamics) {
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
			pitchesIndex = (pitchesIndex + 1) % pitches.length;
			Dynamic dynamic = components[i].isSilence() ? Dynamic.MUTE : baseDynamics;
			if (components[i].isAccent()) {
				dynamic = dynamic.louder().louder();
			}
			Duration duration = components[i].getDuration();
			Note note = null;
			if (pitch.isTie()) {
				Note lastNote = ret.get(ret.size() - 1);
				lastNote.addDuration(duration);
			} else {
				if (pitch.isDrums()) {
					note = new DrumNoteImpl(duration, new int[] { dynamic.getLevel() },
							new int[] { pitch.getPitch(0) });
				} else if (pitch.pitchCount() == 1) {
					note = new NoteImpl(pitch.getPitch(0), duration, dynamic.getLevel());
				} else {
					note = new Chord(duration, dynamic.getLevel(), pitch.getPitches());
				}
				ret.add(note);
			}
		}

		return ret.toArray(new Note[ret.size()]);
	}

}
