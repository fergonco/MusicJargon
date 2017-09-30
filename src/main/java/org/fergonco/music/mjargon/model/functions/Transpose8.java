package org.fergonco.music.mjargon.model.functions;

import org.fergonco.music.mjargon.model.NoteSequence;
import org.fergonco.music.mjargon.model.PitchArray;
import org.fergonco.music.mjargon.model.PitchArrayImpl;
import org.fergonco.music.mjargon.model.SemanticException;
import org.fergonco.music.mjargon.model.Value;

public class Transpose8 extends AbstractFunction implements Function, NoteSequence {

	@Override
	public String getId() {
		return "transpose8";
	}

	@Override
	public void setParameters(Value[] parameters) throws SemanticException {
		if (parameters.length != 2) {
			throw new SemanticException("octave takes a sequence and a number as parameters");
		} else {
			if (parameters[0].getType() != ValueType.SEQUENCE) {
				throw new SemanticException("First argument must be a sequence");
			}
			if (parameters[1].getType() != ValueType.NUMBER) {
				throw new SemanticException("Second argument must be a number");
			}
		}
		super.setParameters(parameters);
	}

	@Override
	public PitchArray[] getAllNotes(int numNotes) {
		PitchArray[] notes = getParameters()[0].toNoteSequence().getAllNotes(numNotes);
		int numOctaves = getParameters()[1].toInt();
		PitchArrayImpl[] newNotes = new PitchArrayImpl[notes.length];
		for (int i = 0; i < notes.length; i++) {
			PitchArrayImpl pitchArray = new PitchArrayImpl();
			for (int j = 0; j < notes[i].pitchCount(); j++) {
				pitchArray.add(notes[i].getPitch(j) + numOctaves * 12);
			}
			newNotes[i] = pitchArray;
		}
		return newNotes;
	}

	@Override
	public PitchArray[] getAllNotes() {
		PitchArray chord = getParameters()[0].toNoteSequence().getAllNotes()[0];
		return getAllNotes(chord.pitchCount());
	}

	@Override
	public ValueType getType() {
		return ValueType.SEQUENCE;
	}

}
