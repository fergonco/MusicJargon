package org.fergonco.music.mjargon.model.functions;

import org.fergonco.music.mjargon.model.DrumNote;
import org.fergonco.music.mjargon.model.DrumSequence;
import org.fergonco.music.mjargon.model.NoteSequence;
import org.fergonco.music.mjargon.model.SemanticException;
import org.fergonco.music.mjargon.model.Value;

public class RDrumSeq extends AbstractFunction implements Function {

	private DrumSequence sequence;

	@Override
	public String getId() {
		return "rDrumSeq";
	}

	@Override
	public void validate() throws SemanticException {
		Value[] parameters = getParameters();
		if (parameters.length != 1) {
			throw new SemanticException("rDrumSeq takes a parameter");
		} else {
			if (parameters[0].getType() != ValueType.NUMBER) {
				throw new SemanticException("rDrumSeq takes a number as parameter");
			}
		}
		DrumSequence drumSequence = new DrumSequence();
		int sequenceLength = getParameters()[0].toInt();
		for (int i = 0; i < sequenceLength; i++) {
			DrumNote note;
			if (Math.random() < 0.5) {
				note = DrumNote.BASSDRUM;
			} else {
				note = DrumNote.SNARE;
			}
			drumSequence.addDrumNote(note, false);
		}
		sequence = drumSequence;
		sequence.validate();
	}

	@Override
	public NoteSequence toNoteSequence() {
		return sequence;
	}

	@Override
	public ValueType getType() {
		return ValueType.SEQUENCE;
	}

}
