package org.fergonco.music.mjargon.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.InstrumentNames;
import org.fergonco.music.midi.Note;
import org.fergonco.music.midi.Score;
import org.fergonco.music.midi.Track;
import org.fergonco.music.mjargon.parser.NoteSequenceExpression;
import org.fergonco.music.mjargon.parser.NoteSequenceExpressionVisitor;

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

	public void addSequenceToBarline(final int instrumentIndex, NoteSequenceExpression expression,
			final String rhythmId) throws SemanticException {
		expression.visit(new NoteSequenceExpressionVisitor() {

			@Override
			public void sequenceReference(String noteOrDrumsSequenceId, int noteIndex) throws SemanticException {
				if (noteSequences.containsKey(noteOrDrumsSequenceId)) {
					NoteSequence noteSequence = noteSequences.get(noteOrDrumsSequenceId);
					addNoteSequenceToBarline(instrumentIndex, rhythmId, noteSequence, noteIndex);
				} else {
					throw new SemanticException("No such sequence: " + noteOrDrumsSequenceId);
				}
			}

			@Override
			public void pitched(String[] notes) throws SemanticException {
				NoteSequence noteSequence = new PitchedNoteSequence(notes);
				addNoteSequenceToBarline(instrumentIndex, rhythmId, noteSequence, -1);
			}

			@Override
			public void chordBasedPitched(String[] notes, String chordProgressionId, int chordProgressionIndex) throws SemanticException {
				NoteSequence noteSequence = new PitchedNoteSequence(notes, getChord(chordProgressionId, chordProgressionIndex));
				addNoteSequenceToBarline(instrumentIndex, rhythmId, noteSequence, -1);
			}

			@Override
			public void drums(DrumNote[] notes) throws SemanticException {
				NoteSequence noteSequence = new DrumSequence(notes);
				addNoteSequenceToBarline(instrumentIndex, rhythmId, noteSequence, -1);
			}
		});
	}

	private void addNoteSequenceToBarline(int instrumentIndex, String rhythmId, NoteSequence noteSequence,
			int noteIndex) throws SemanticException {
		Rhythm rhythm = getRhythmOrFail(rhythmId);
		setInstrumentBar(instrumentIndex, new InstrumentBar(noteSequence, noteIndex, rhythm));
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
		Score score = new Score(output);
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
					Note[] notes = bars[i].getNotes(currentDynamics[i]);
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

	public void setTempo(int tempo) {
		songlines.add(new TempoBar(tempo));
	}

	public void setDynamics(ArrayList<Dynamic> dynamics) {
		songlines.add(new DynamicsLine(dynamics));
	}

}
