package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.DrumNote;
import org.fergonco.music.mjargon.model.SemanticException;

public class DrumLiteralExpression implements NoteSequenceExpression {

	private DrumNote[] notes;

	public DrumLiteralExpression(DrumNote[] notes) {
		this.notes = notes;
	}

	@Override
	public void visit(NoteSequenceExpressionVisitor visitor) throws SemanticException {
		visitor.drums(notes);
	}

	public DrumNote[] getNotes() {
		return notes;
	}

}