package org.fergonco.music.mjargon.model.functions;

import java.util.ArrayList;

import org.fergonco.music.mjargon.model.NoteSequence;
import org.fergonco.music.mjargon.model.PitchArray;
import org.fergonco.music.mjargon.model.PitchArrayImpl;
import org.fergonco.music.mjargon.model.SemanticException;

public class Arpeggio extends AbstractFunction implements Function {

	@Override
	public String getId() {
		return "arpeggio";
	}

	@Override
	public void setParameters(NoteSequence[] parameters) throws SemanticException {
		if (parameters.length != 1) {
			throw new SemanticException("arpeggio only takes a sequence as parameter");
		}
		super.setParameters(parameters);
	}

	@Override
	public PitchArray[] getNotes(int numNotes) {
		PitchArray chord = parameters[0].getAllNotes()[0];
		ArrayList<PitchArray> ret = new ArrayList<>();
		for (int i = 0; i < numNotes; i++) {
			PitchArrayImpl note = new PitchArrayImpl();
			note.add(chord.getPitch(i % chord.pitchCount()));
			ret.add(note);
		}
		return ret.toArray(new PitchArray[ret.size()]);
	}

	@Override
	public PitchArray[] getAllNotes() {
		PitchArray chord = parameters[0].getAllNotes()[0];
		return getNotes(chord.pitchCount());
	}
}
