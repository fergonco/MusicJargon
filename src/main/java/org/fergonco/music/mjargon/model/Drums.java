package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

import org.fergonco.music.midi.DrumNoteImpl;
import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.Note;

public class Drums implements Bar {

	private DrumSequence sequence;
	private Rhythm rhythm;

	public Drums(DrumSequence sequence, Rhythm rhythm) {
		this.sequence = sequence;
		this.rhythm = rhythm;
	}

	@Override
	public Note[] getNotes(Dynamic baseDynamics) {
		PitchArray[] notes = sequence.getNotes();
		ArrayList<Note> ret = new ArrayList<>();
		RhythmComponent[] components = rhythm.getComponents();
		int noteIndex = 0;
		for (RhythmComponent component : components) {
			PitchArray pitch = notes[noteIndex];
			noteIndex = (noteIndex + 1) % notes.length;

			Dynamic dynamic = baseDynamics;
			if (component.isAccent()) {
				dynamic = dynamic.louder().louder();
			}

			ret.add(new DrumNoteImpl(component.getDuration(), new int[] { dynamic.getLevel() },
					new int[] { pitch.getPitch(0) }));
		}
		return ret.toArray(new Note[ret.size()]);
	}

}
