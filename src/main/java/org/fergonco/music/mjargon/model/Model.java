package org.fergonco.music.mjargon.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.InstrumentNames;
import org.fergonco.music.midi.Note;
import org.fergonco.music.midi.Score;
import org.fergonco.music.midi.Track;
import org.fergonco.music.mjargon.parser.DuplicatedLabelException;
import org.fergonco.music.mjargon.parser.InvalidReferenceException;
import org.fergonco.music.mjargon.parser.MJargonError;
import org.fergonco.music.mjargon.parser.ModelException;

public class Model {

	private ArrayList<MJargonError> errors = new ArrayList<>();
	private HashMap<String, Value> variables = new HashMap<>();
	private String[] instruments;
	private ArrayList<SongLine> songlines = new ArrayList<>();
	private HashMap<String, Integer> labels = new HashMap<>();
	private String startingLabel = null;
	private FractionValue defaultTimeSignature = new FractionValue(4, 4);

	public void addVariable(String id, Value value) {
		variables.put(id, value);
	}

	public Value getReference(String id) throws InvalidReferenceException {
		Value value = variables.get(id);
		if (value == null) {
			throw new InvalidReferenceException("Reference not found: " + id);
		}
		return value;
	}

	public void setInstruments(String[] instruments) {
		this.instruments = instruments;
	}

	public void addBarline(Bar[] bars) {
		songlines.add(new Barline(bars));
	}

	public void newLabel(String text) throws DuplicatedLabelException {
		if (labels.containsKey(text)) {
			throw new DuplicatedLabelException(text);
		}
		labels.put(text, songlines.size());
	}

	public void writeMidi(File output) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(output));
		writeMidi(bos);
		bos.close();
	}

	public Bar getBar(int songlineIndex, int barOffset, int voiceIndex) {
		SongLine songLine = songlines.get(songlineIndex);
		int direction = (int) Math.signum(barOffset);
		int instrumentBarlines = 0;
		while (instrumentBarlines < Math.abs(barOffset)) {
			do {
				songlineIndex += direction;
				songLine = songlines.get(songlineIndex);
			} while (!songLine.isBarline());
			instrumentBarlines++;
		}
		return songLine.getBars()[voiceIndex];
	}

	public void writeMidi(OutputStream stream) throws IOException {
		if (instruments == null || instruments.length == 0) {
			throw new ModelException("No voices defined");
		}
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
				if (startingLabel != null) {
					songlineIndex = labels.get(startingLabel) - 1;
					startingLabel = null;
				} else {
					Bar[] bars = songline.getBars();
					for (int i = 0; i < bars.length; i++) {
						Note[] notes = bars[i].getNotes(this, songlineIndex, i, currentDynamics[i],
								tracks[i].getLastNote());
						for (Note note : notes) {
							tracks[i].addNote(note);
						}
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

	public void repeat(String label, Value times) {
		songlines.add(new Repeat(label, times));
	}

	public void setTempo(int[] length, int bpm) {
		double total = bpm * length[0] / (double) length[1];
		songlines.add(new TempoBar(total * 4));
	}

	public void setDynamics(ArrayList<Dynamic> dynamics) {
		songlines.add(new DynamicsLine(dynamics));
	}

	public void validate() {
		for (int i = 0; i < songlines.size(); i++) {
			try {
				songlines.get(i).validate(this, i);
			} catch (SemanticException e) {
				registerError(e, -1);
			}
		}
	}

	private void registerError(SemanticException e, int line) {
		errors.add(new MJargonError(e.getMessage(), line));
	}

	public List<MJargonError> getErrors() {
		return errors;
	}

	public Integer getLabel(String label) {
		return labels.get(label);
	}

	public void startInLabel(String label) {
		if (!labels.containsKey(label)) {
			throw new IllegalArgumentException("No such label: " + label);
		}
		this.startingLabel = label;
	}

	public void setDefaultTimeSignature(FractionValue fraction) {
		this.defaultTimeSignature = fraction;
	}

	public FractionValue getDefaultTimeSignature() {
		return defaultTimeSignature;
	}
}
