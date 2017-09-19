package org.fergonco.music.mjargon.model.functions;

import java.util.ArrayList;

import org.fergonco.music.mjargon.model.PitchArray;
import org.fergonco.music.mjargon.model.PitchArrayImpl;
import org.fergonco.music.mjargon.model.SemanticException;
import org.fergonco.music.mjargon.model.Value;

public class Arpeggio extends AbstractFunction implements Function {

	@Override
	public String getId() {
		return "arpeggio";
	}

	@Override
	public void setParameters(Value[] parameters) throws SemanticException {
		if (parameters.length != 1 && parameters.length != 2) {
			throw new SemanticException("arpeggio takes a sequence an an optional string as parameters");
		} else {
			if (parameters[0].getType() != ValueType.SEQUENCE) {
				throw new SemanticException("arpeggio takes a sequence parameter");
			}
			if (parameters.length == 2 && parameters[1].getType() != ValueType.STRING) {
				throw new SemanticException("arpeggio second parameter must be a string");
			}
		}
		super.setParameters(parameters);
	}

	@Override
	public PitchArray[] getNotes(int numNotes) {
		PitchArray chord = getParameters()[0].toNoteSequence().getAllNotes()[0];
		int[] chordNoteIndices = getChordNoteIndices(chord, numNotes);
		ArrayList<PitchArray> ret = new ArrayList<>();
		for (int i = 0; i < numNotes; i++) {
			PitchArrayImpl note = new PitchArrayImpl();
			note.add(chord.getPitch(chordNoteIndices[i]));
			ret.add(note);
		}
		return ret.toArray(new PitchArray[ret.size()]);
	}

	private int[] getChordNoteIndices(PitchArray chord, int numNotes) {
		if (getParameters().length == 2) {
			String indicesString = getParameters()[1].toStringLiteral();
			int[] indices = new int[numNotes];
			for (int i = 0; i < numNotes; i++) {
				int stringIndex = i % indicesString.length();
				indices[i] = Integer.parseInt(indicesString.substring(stringIndex, stringIndex + 1)) - 1;
			}
			return indices;
		} else {
			int[] indices = new int[numNotes];
			for (int i = 0; i < indices.length; i++) {
				indices[i] = i % chord.pitchCount();
			}
			return indices;
		}
	}

	@Override
	public PitchArray[] getAllNotes() {
		PitchArray chord = getParameters()[0].toNoteSequence().getAllNotes()[0];
		return getNotes(chord.pitchCount());
	}
}
