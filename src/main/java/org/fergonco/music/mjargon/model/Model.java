package org.fergonco.music.mjargon.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.fergonco.music.midi.InstrumentNames;
import org.fergonco.music.midi.Note;
import org.fergonco.music.midi.Score;
import org.fergonco.music.midi.Track;

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

	public void addMonofonicNoteSequence(String id, Integer[] notes, String chordProgressionId,
			int chordProgressionIndex) throws SemanticException {
		NoteSequence chordProgression = noteSequences.get(chordProgressionId);
		if (chordProgression instanceof PolyphonicNoteSequence) {
			noteSequences.put(id, new MonofonicNoteSequence(notes,
					((PolyphonicNoteSequence) chordProgression).getChord(chordProgressionIndex)));
		} else {
			throw new SemanticException(chordProgressionId + " is not a chord progression");
		}
	}

	public void setInstruments(String[] instruments) {
		this.instruments = instruments;
	}

	public void addToBarline(int instrumentIndex, String noteSequenceId, int noteIndex, String rhythmId)
			throws SemanticException {
		if (currentBarline == null) {
			currentBarline = new Barline(instruments.length);
		}
		NoteSequence noteSequence = noteSequences.get(noteSequenceId);
		if (noteSequenceId == null) {
			throw new SemanticException("No such note sequence: " + noteSequenceId);
		}
		Rhythm rhythm = rhythms.get(rhythmId);
		if (rhythm == null) {
			throw new SemanticException("No such rhythm: " + rhythmId);
		}

		currentBarline.setInstrumentBar(instrumentIndex, new Bar(noteSequence, noteIndex, rhythm));
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
		for (int songlineIndex = 0; songlineIndex < songlines.size(); songlineIndex++) {
			SongLine songline = songlines.get(songlineIndex);
			if (songline.isBarline()) {
				Bar[] bars = songline.getBars();
				for (int i = 0; i < bars.length; i++) {
					Note[] notes = bars[i].getNotes();
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
			}else {
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

}
