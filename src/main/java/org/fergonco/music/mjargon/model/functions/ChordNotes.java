package org.fergonco.music.mjargon.model.functions;

import java.util.ArrayList;

import org.fergonco.music.mjargon.model.NoteSequence;
import org.fergonco.music.mjargon.model.PitchArray;
import org.fergonco.music.mjargon.model.PitchArrayImpl;
import org.fergonco.music.mjargon.model.SemanticException;
import org.fergonco.music.mjargon.model.Value;

public class ChordNotes extends AbstractFunction implements Function, NoteSequence {

	@Override
	public String getId() {
		return "chordNotes";
	}

	@Override
	public void setParameters(Value[] parameters) throws SemanticException {
		if (parameters.length < 2) {
			throw new SemanticException(getId() + " takes a chord and a variable number of índices in the chord");
		} else {
			if (parameters[0].getType() != ValueType.SEQUENCE) {
				throw new SemanticException(getId() + " takes a sequence parameter");
			}
			for (int i = 1; i < parameters.length; i++) {
				if (parameters[i].getType() != ValueType.NUMBER) {
					throw new SemanticException(getId() + " expect indices from second parameter on");
				}
			}
		}
		super.setParameters(parameters);
	}

	@Override
	public NoteSequence toNoteSequence() {
		return this;
	}

	@Override
	public PitchArray[] getAllNotes(int numNotes) {
		PitchArray chord = getParameters()[0].toNoteSequence().getAllNotes()[0];
		ArrayList<PitchArray> ret = new ArrayList<>();
		for (int i = 1; i < getParameters().length; i++) {
			int index = getParameters()[i].toInt() - 1;
			PitchArrayImpl note = new PitchArrayImpl();
			note.add(chord.getPitch(index));
			ret.add(note);
		}
		return ret.toArray(new PitchArray[ret.size()]);
	}

	@Override
	public PitchArray[] getAllNotes() {
		return getAllNotes(getParameters().length - 1);
	}

	@Override
	public ValueType getType() {
		return ValueType.SEQUENCE;
	}

}
