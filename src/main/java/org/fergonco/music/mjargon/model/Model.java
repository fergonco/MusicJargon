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
	private HashMap<String, Section> labels = new HashMap<>();
	private String startingLabel = null;
	private FractionValue defaultTimeSignature = new FractionValue(4, 4);
	private List<Section> play = new ArrayList<>();

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
		labels.put(text, new Section(songlines.size()));
	}

	public void setSectionEnd(String text) throws ModelException {
		Section section = labels.get(text);
		if (section == null) {
			throw new ModelException("Ending a section '" + text + "' that was not declared before");
		}
		section.setEnd(songlines.size() - 1);
	}

	public void writeMidi(File output) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(output));
		writeMidi(bos);
		bos.close();
	}

	/**
	 * Get the index of the next Barline in the specified direction
	 *
	 * @param index
	 * @return
	 */
	public int getInstrumentalSongLineIndex(int index, int direction) {
		while (!(songlines.get(index) instanceof Barline)) {
			index += direction;
		}
		return index;
	}

	public Bar getBar(int songlineIndex, int barOffset, int voiceIndex) {
		int direction = (int) Math.signum(barOffset);
		direction = direction == 0 ? 1 : direction;
		songlineIndex = getInstrumentalSongLineIndex(songlineIndex, direction);
		SongLine songLine = songlines.get(songlineIndex);
		int instrumentBarlines = 0;
		while (instrumentBarlines < Math.abs(barOffset)) {
			do {
				songlineIndex += direction;
				songlineIndex = getInstrumentalSongLineIndex(songlineIndex, direction);
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
		Interpreter interpreter = getInterpreter();
		while (interpreter.hasNext()) {
			final int songlineIndex = interpreter.next();
			SongLine songline = songlines.get(songlineIndex);
			if (songline.isBarline()) {
				Bar[] bars = songline.getBars();
				for (int i = 0; i < bars.length; i++) {
					Note[] notes = bars[i].getNotes(this, songlineIndex, i, currentDynamics[i],
							tracks[i].getLastNote());
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
					interpreter.jumpTo(index);
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

	private Interpreter getInterpreter() {
		if (play.size() == 0) {
			return new SequentialInterpreter();
		} else {
			return new PlayInterpreter();
		}
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

	public Integer getLabel(String label) throws ModelException {
		Section section = labels.get(label);
		if (section == null) {
			throw new ModelException("Section not found: " + label);
		}
		return section.getStart();
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

	public void addPlaySection(String text) {
		Section section = labels.get(text);
		if (section == null) {
			throw new ModelException("Play section refers to missing section: " + text);
		} else if (!section.isComplete()) {
			throw new ModelException("Play section refers to section without end: " + text);
		} else {
			play.add(section);
		}
	}

	private class PlayInterpreter implements Interpreter {

		int currentPart = 0;
		int currentLine = 0;

		@Override
		public boolean hasNext() {
			return currentPart < play.size();
		}

		@Override
		public int next() {
			int ret = currentLine;
			Section section = play.get(currentPart);
			if (currentLine == section.getEnd()) {
				currentPart++;
				if (currentPart < play.size()) {
					currentLine = play.get(currentPart).getStart();
				}
			} else {
				currentLine++;
			}
			return ret;
		}

		@Override
		public void jumpTo(int index) {
			// Let's ignore jumps in play mode
		}
	}

	private class SequentialInterpreter implements Interpreter {

		private int currentLine = 0;

		@Override
		public boolean hasNext() {
			return currentLine < songlines.size();
		}

		@Override
		public int next() {
			// In case we start in command line with the -l parameter
			if (songlines.get(currentLine).isBarline() && startingLabel != null) {
				currentLine = labels.get(startingLabel).getStart();
				startingLabel = null;
			}
			int ret = currentLine;
			currentLine++;
			return ret;
		}

		@Override
		public void jumpTo(int index) {
			currentLine = index;
		}

	}
}
