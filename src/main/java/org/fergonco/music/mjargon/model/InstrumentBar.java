package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

import org.fergonco.music.midi.Chord;
import org.fergonco.music.midi.DrumNoteImpl;
import org.fergonco.music.midi.Duration;
import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.Note;
import org.fergonco.music.midi.NoteImpl;
import org.fergonco.music.mjargon.model.functions.ValueType;

public class InstrumentBar implements Bar {

	private Value value;

	public InstrumentBar(Value value) {
		this.value = value;
	}

	@Override
	public void validate(Model model, int songlineIndex, int voiceIndex) throws SemanticException {
		value.validate();
		if (value.getType() != ValueType.AURAL) {
			throw new SemanticException("Instrument bars require aural expressions");
		}
	}

	public Note[] getNotes(Model model, int songlineIndex, int voiceIndex, Dynamic baseDynamics, Note lastNote) {
		RhythmComponent[] components = value.toAural().getRhythm().getComponents();

		PitchArray[] pitches = value.toAural().getSequence().getAllNotes(components.length);

		ArrayList<Note> ret = new ArrayList<>();
		int pitchesIndex = 0;
		for (int i = 0; i < components.length; i++) {
			PitchArray pitch = pitches[pitchesIndex % pitches.length];
			if (!components[i].isPlaceholder()) {
				pitchesIndex++;
			}

			Dynamic dynamic = components[i].isSilence() ? Dynamic.MUTE : baseDynamics;
			if (components[i].isAccent()) {
				dynamic = dynamic.louder().louder();
			}
			Duration duration = components[i].getDuration();
			Note note = null;
			if (pitch.isTie()) {
				lastNote.addDuration(duration);
			} else {
				if (pitch.isDrums()) {
					note = new DrumNoteImpl(duration, new int[] { dynamic.getLevel() },
							new int[] { pitch.getPitch(0) });
				} else if (pitch.isSilence()) {
					note = new NoteImpl(0, duration, Dynamic.MUTE.getLevel());
				} else if (pitch.pitchCount() == 1) {
					note = new NoteImpl(pitch.getPitch(0), duration, dynamic.getLevel());
				} else {
					note = new Chord(duration, dynamic.getLevel(), pitch.getPitches());
				}
				ret.add(note);
				lastNote = note;
			}
		}

		return ret.toArray(new Note[ret.size()]);
	}

}
