package org.fergonco.music.mjargon.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Model {

	private HashMap<String, TimeSignature> timeSignatures = new HashMap<>();
	private HashMap<String, Rhythm> rhythms = new HashMap<>();
	private HashMap<String, NoteSequence> noteSequences = new HashMap<>();
	private String[] instruments;
	private ArrayList<MusicalExpression[]> musicalExpressions = new ArrayList<>();
	private MusicalExpression[] currentMusicalExpression = null;
	private HashMap<String, Integer> labels = new HashMap<>();

	public void addTimeSignature(String id, int n1, int n2) {
		timeSignatures.put(id, new TimeSignature(n1, n2));
	}

	public void addRhythm(String id, String expression, String timeSignatureId) throws SemanticException {
		TimeSignature timeSignature = timeSignatures.get(timeSignatureId);
		if (timeSignature == null) {
			throw new SemanticException("Time signature not found: " + timeSignatureId);
		}
		rhythms.put(id, new Rhythm(expression, timeSignature));
	}

	public void addPolyphonicNoteSequence(String id, String[] chords) {
		noteSequences.put(id, new PolyphonicNoteSequence(chords));
	}

	public void addMonofonicNoteSequence(String id, Integer[] notes, String chordProgressionId, int chordProgressionIndex) {
		noteSequences.put(id, new MonofonicNoteSequence(notes, chordProgressionId, chordProgressionIndex));
	}

	public void setInstruments(String[] instruments) {
		this.instruments = instruments;
	}

	public void addMusicalExpression(int instrumentIndex, String noteSequenceId, int noteIndex, int chordNoteIndex) {
		if (currentMusicalExpression == null) {
			currentMusicalExpression = new MusicalExpression[instruments.length];
		}
		NoteSequence noteSequence = noteSequences.get(noteSequenceId);
		currentMusicalExpression[instrumentIndex] = new MusicalExpression(noteSequence, noteIndex, chordNoteIndex);
	}

	public void newMusicalExpression() {
		musicalExpressions.add(currentMusicalExpression);
		currentMusicalExpression = null;
	}

	public void newLabel(String text) {
		labels.put(text, musicalExpressions.size());
	}

}
