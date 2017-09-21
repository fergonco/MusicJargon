package org.fergonco.music.mjargon.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.InstrumentNames;
import org.fergonco.music.midi.Note;
import org.fergonco.music.midi.Score;
import org.fergonco.music.midi.Track;
import org.fergonco.music.mjargon.parser.Expression;
import org.fergonco.music.mjargon.parser.ExpressionVisitor;
import org.fergonco.music.mjargon.parser.SequenceAccesor;

public class Model {

	private HashMap<String, TimeSignature> timeSignatures = new HashMap<>();
	private HashMap<String, Rhythm> rhythms = new HashMap<>();
	private HashMap<String, NoteSequence> noteSequences = new HashMap<>();
	private String[] instruments;
	private ArrayList<SongLine> songlines = new ArrayList<>();
	private Barline currentBarline = null;
	private HashMap<String, Integer> labels = new HashMap<>();

	public void addTimeSignature(String id, int n1, int n2) {
		timeSignatures.put(id, new TimeSignature(n1, n2));
	}

	public void addDrums(String id, DrumNote[] drumNotes) throws SemanticException {
		noteSequences.put(id, new DrumSequence(drumNotes));
	}

	public void addRhythmWithNoteLength(String id, String expression, int noteLength) {
		rhythms.put(id, new Rhythm(expression, new TimeSignature(expression.length(), noteLength)));
	}

	public void addRhythmWithTimeSignatureLiteral(String id, String expression, int[] timeSignature)
			throws SemanticException {
		rhythms.put(id, new Rhythm(expression, new TimeSignature(timeSignature[0], timeSignature[1])));
	}

	public void addRhythmWithTimeSignatureId(String id, String expression, String timeSignatureId)
			throws SemanticException {
		TimeSignature timeSignature = getTimeSignatureOrFail(timeSignatureId);
		rhythms.put(id, new Rhythm(expression, timeSignature));
	}

	private TimeSignature getTimeSignatureOrFail(String timeSignatureId) throws SemanticException {
		TimeSignature timeSignature = timeSignatures.get(timeSignatureId);
		if (timeSignature == null) {
			throw new SemanticException("Time signature not found: " + timeSignatureId);
		}
		return timeSignature;
	}

	public void addPitchedNoteSequence(String id, String[] notes) {
		noteSequences.put(id, new PitchedNoteSequence(notes));
	}

	public void addPitchedNoteSequence(String id, String[] noteIndices, String chordProgressionId,
			int chordProgressionIndex) throws SemanticException {
		PitchArray chord = getChord(chordProgressionId, chordProgressionIndex);
		noteSequences.put(id, new PitchedNoteSequence(noteIndices, chord));
	}

	private PitchArray getChord(String chordProgressionId, int chordProgressionIndex) throws SemanticException {
		NoteSequence chordProgression = noteSequences.get(chordProgressionId);
		PitchArray chord;
		if (chordProgression instanceof PitchedNoteSequence) {
			chord = ((PitchedNoteSequence) chordProgression).getChord(chordProgressionIndex);
		} else {
			throw new SemanticException(chordProgressionId + " is not a chord progression");
		}
		return chord;
	}

	public void setInstruments(String[] instruments) {
		this.instruments = instruments;
	}

	public void addSequenceToBarline(final int instrumentIndex, Expression expression, final String rhythmId)
			throws SemanticException {
		ValueGetter getter = new ValueGetter();
		expression.visit(getter);
		NoteSequence noteSequence = getter.getValue().toNoteSequence();
		addNoteSequenceToBarline(instrumentIndex, rhythmId, noteSequence);
	}

	private void addNoteSequenceToBarline(int instrumentIndex, String rhythmId, NoteSequence noteSequence)
			throws SemanticException {
		Rhythm rhythm = getRhythmOrFail(rhythmId);
		setInstrumentBar(instrumentIndex, new InstrumentBar(noteSequence, rhythm));
	}

	private Rhythm getRhythmOrFail(String rhythmId) throws SemanticException {
		Rhythm rhythm = rhythms.get(rhythmId);
		if (rhythm == null) {
			throw new SemanticException("No such rhythm: " + rhythmId);
		}
		return rhythm;
	}

	public void addSilenceToBarline(int instrumentIndex) {
		setInstrumentBar(instrumentIndex, new SilenceBar());
	}

	private void setInstrumentBar(int instrumentIndex, Bar bar) {
		if (currentBarline == null) {
			currentBarline = new Barline(instruments.length);
		}
		currentBarline.setInstrumentBar(instrumentIndex, bar);
	}

	public void newBarline() {
		songlines.add(currentBarline);
		currentBarline = null;
	}

	public void newLabel(String text) {
		labels.put(text, songlines.size());
	}

	public void writeMidi(File output) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(output));
		writeMidi(bos);
		bos.close();
	}

	public void writeMidi(OutputStream stream) throws IOException {
		Score score = new Score(stream);
		Track[] tracks = new Track[instruments.length];
		for (int i = 0; i < tracks.length; i++) {
			tracks[i] = new Track();
			tracks[i].setInstrument(InstrumentNames.getInstrument(instruments[i]));
		}
		Dynamic[] currentDynamics = new Dynamic[tracks.length];
		for (int i = 0; i < currentDynamics.length; i++) {
			currentDynamics[i] = Dynamic.F;
		}
		for (int songlineIndex = 0; songlineIndex < songlines.size(); songlineIndex++) {
			SongLine songline = songlines.get(songlineIndex);
			if (songline.isBarline()) {
				Bar[] bars = songline.getBars();
				for (int i = 0; i < bars.length; i++) {
					Note[] notes = bars[i].getNotes(currentDynamics[i], tracks[i].getLastNote());
					for (Note note : notes) {
						tracks[i].addNote(note);
					}
				}
			} else if (songline.isTempo()) {
				for (Track track : tracks) {
					track.setTempo(songline.getTempo());
				}
			} else if (songline.isRepeat()) {
				int index = songline.getTarget();
				if (index != -1) {
					songlineIndex = index - 1;
				}
			} else if (songline.isDynamics()) {
				Dynamic[] nextDynamics = songline.getDynamics();
				for (int i = 0; i < nextDynamics.length; i++) {
					if (nextDynamics[i] != null) {
						currentDynamics[i] = nextDynamics[i];
					}
				}
			} else {
				throw new RuntimeException();
			}
		}

		score.addTracks(tracks);
		score.write();
	}

	public void repeat(String label, int times) throws SemanticException {
		Integer songlineIndex = labels.get(label);
		if (songlineIndex == null) {
			throw new SemanticException("No such label: " + label);
		}
		songlines.add(new Repeat(songlineIndex, times));
	}

	public void setTempo(int[] length, int bpm) {
		double total = bpm * length[0] / (double) length[1];
		songlines.add(new TempoBar(total * 4));
	}

	public void setDynamics(ArrayList<Dynamic> dynamics) {
		songlines.add(new DynamicsLine(dynamics));
	}

	private class ValueGetter implements ExpressionVisitor {

		private Value value;

		@Override
		public void sequenceReference(String noteOrDrumsSequenceId, SequenceAccesor sequenceAccesor)
				throws SemanticException {
			NoteSequence sequence;
			if (noteSequences.containsKey(noteOrDrumsSequenceId)) {
				sequence = noteSequences.get(noteOrDrumsSequenceId);
			} else {
				throw new SemanticException("No such sequence: " + noteOrDrumsSequenceId);
			}
			if (sequenceAccesor != null) {
				if (sequenceAccesor.singleNote()) {
					sequence = new NoteSequenceElement(sequence, sequenceAccesor.getIndex());
				} else {
					sequence = new NoteSubsequence(sequence, sequenceAccesor.getIndex(), sequenceAccesor.getEndIndex());
				}
			}
			value = sequence;
		}

		@Override
		public void pitched(String[] notes) throws SemanticException {
			value = new PitchedNoteSequence(notes);
		}

		@Override
		public void chordBasedPitched(String[] notes, String chordProgressionId, int chordProgressionIndex)
				throws SemanticException {
			value = new PitchedNoteSequence(notes, getChord(chordProgressionId, chordProgressionIndex));
		}

		@Override
		public void drums(DrumNote[] notes) throws SemanticException {
			value = new DrumSequence(notes);
		}

		@Override
		public void composite(Expression[] expressions) throws SemanticException {
			ArrayList<NoteSequence> sequences = new ArrayList<>();
			for (Expression expression : expressions) {
				ValueGetter visitor = new ValueGetter();
				expression.visit(visitor);
				sequences.add(visitor.getValue().toNoteSequence());
			}
			value = new NoteSequenceComposite(sequences.toArray(new NoteSequence[sequences.size()]));
		}

		public Value getValue() {
			return value;
		}

		@Override
		public void function(String id, Expression[] parameters) throws SemanticException {
			ArrayList<Value> values = new ArrayList<>();
			for (Expression expression : parameters) {
				ValueGetter visitor = new ValueGetter();
				expression.visit(visitor);
				values.add(visitor.getValue());
			}
			value = new FunctionNoteSequence(id, values.toArray(new Value[values.size()]));
		}

		@Override
		public void number(int number) {
			value = new NumberValue(number);
		}

		@Override
		public void string(String string) {
			value = new StringValue(string);
		}

	}
}
