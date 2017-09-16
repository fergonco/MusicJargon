package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.model.SemanticException;

public class ChordBasedPitchedLiteralExpression implements NoteSequenceExpression {

	private String[] notes;
	private String chordProgressionId;
	private int chordProgressionIndex;

	public ChordBasedPitchedLiteralExpression(String[] notes, String chordProgressionId, int chordProgressionIndex) {
		this.notes = notes;
		this.chordProgressionId = chordProgressionId;
		this.chordProgressionIndex = chordProgressionIndex;
	}

	@Override
	public void visit(NoteSequenceExpressionVisitor visitor) throws SemanticException {
		visitor.chordBasedPitched(notes, chordProgressionId, chordProgressionIndex);
	}

	public String getChordProgressionId() {
		return chordProgressionId;
	}

	public int getChordProgressionIndex() {
		return chordProgressionIndex;
	}

	public String[] getNotes() {
		return notes;
	}
}
