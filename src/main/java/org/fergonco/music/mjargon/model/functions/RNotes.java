package org.fergonco.music.mjargon.model.functions;

import java.util.ArrayList;

import org.fergonco.music.mjargon.model.NoteSequence;
import org.fergonco.music.mjargon.model.PitchArray;
import org.fergonco.music.mjargon.model.PitchArrayImpl;
import org.fergonco.music.mjargon.model.SemanticException;
import org.fergonco.music.mjargon.model.Value;

public class RNotes extends AbstractFunction implements Function, NoteSequence {

	@Override
	public String getId() {
		return "rNotes";
	}

	@Override
	public void validate() throws SemanticException {
		Value[] parameters = getParameters();
		if (parameters.length != 2) {
			throw new SemanticException(getId() + " takes a chord and a number");
		} else {
			if (parameters[0].getType() != ValueType.SEQUENCE) {
				throw new SemanticException(getId() + " takes a sequence parameter");
			}
			if (parameters[1].getType() != ValueType.NUMBER) {
				throw new SemanticException(getId() + " expects a number as second parameter");
			}
		}
	}

	@Override
	public NoteSequence toNoteSequence() {
		return this;
	}

	@Override
	public PitchArray[] getAllNotes(int numNotes) {
		PitchArray chord = getParameters()[0].toNoteSequence().getAllNotes()[0];
		ArrayList<Integer> pitches = new ArrayList<Integer>();
		int[] chordPitches = chord.getPitches();
		for (int octave = 0; octave <= 0; octave += 12) {
			for (int chordPitch : chordPitches) {
				pitches.add(chordPitch + octave);
			}
		}
		ArrayList<PitchArray> ret = new ArrayList<>();
		for (int i = 0; i < numNotes; i++) {
			int index = (int) (Math.random() * pitches.size());
			PitchArrayImpl pitch = new PitchArrayImpl();
			pitch.add(pitches.get(index));
			ret.add(pitch);
		}
		return ret.toArray(new PitchArray[ret.size()]);
	}

	@Override
	public PitchArray[] getAllNotes() {
		return getAllNotes(getParameters()[1].toInt());
	}

	@Override
	public ValueType getType() {
		return ValueType.SEQUENCE;
	}

}
