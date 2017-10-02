package org.fergonco.music.mjargon.model.functions;

import java.util.ArrayList;

import org.fergonco.music.mjargon.model.NoteSequence;
import org.fergonco.music.mjargon.model.PitchArray;
import org.fergonco.music.mjargon.model.SemanticException;
import org.fergonco.music.mjargon.model.Value;

public class Concat extends AbstractFunction implements Function, NoteSequence {

	private int repeatingIndex;
	private PitchArray[] notes = null;

	@Override
	public String getId() {
		return "concat";
	}

	@Override
	public void setParameters(Value[] parameters) throws SemanticException {
		if (parameters.length < 2) {
			throw new SemanticException(getId() + " takes at least two sequences");
		} else {
			for (int i = 1; i < parameters.length; i++) {
				if (parameters[i].getType() != ValueType.SEQUENCE) {
					throw new SemanticException(getId() + " expect sequences as parameters");
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
		concatenate();

		PitchArray[] ret = new PitchArray[numNotes];
		int noteIndex = 0;
		for (int i = 0; i < ret.length; i++) {
			ret[i] = notes[noteIndex];
			noteIndex++;
			if (noteIndex == notes.length) {
				noteIndex = repeatingIndex;
			}
		}
		return ret;
	}

	private void concatenate() {
		if (notes == null) {
			ArrayList<PitchArray> allSequenceNotes = new ArrayList<>();
			repeatingIndex = 0;
			for (Value value : getParameters()) {
				PitchArray[] notes = value.toNoteSequence().getAllNotes();
				repeatingIndex = allSequenceNotes.size();
				for (PitchArray note : notes) {
					allSequenceNotes.add(note);
				}
			}

			this.notes = allSequenceNotes.toArray(new PitchArray[allSequenceNotes.size()]);
		}
	}

	@Override
	public PitchArray[] getAllNotes() {
		concatenate();
		return notes;
	}

	@Override
	public ValueType getType() {
		return ValueType.SEQUENCE;
	}

}
