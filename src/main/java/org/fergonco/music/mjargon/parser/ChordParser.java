package org.fergonco.music.mjargon.parser;

import java.util.HashMap;
import java.util.regex.Matcher;

import org.fergonco.music.mjargon.model.PitchArrayImpl;
import org.fergonco.music.mjargon.model.SemanticException;

public abstract class ChordParser {
	protected static HashMap<String, Integer> basePitches = new HashMap<>();
	static {
		basePitches.put("C", 0);
		basePitches.put("D", 2);
		basePitches.put("E", 4);
		basePitches.put("F", 5);
		basePitches.put("G", 7);
		basePitches.put("A", 9);
		basePitches.put("B", 11);
	}

	protected String text;
	protected int octave;
	protected PitchArrayImpl pitchArray;

	public ChordParser(String text) {
		this.text = text;
	}

	public void setDefaultOctave(int octave) {
		this.octave = octave;
	}

	public int getOctave() throws SemanticException {
		if (pitchArray == null) {
			getPitchArray();
		}
		return octave;
	}

	public abstract PitchArrayImpl getPitchArray() throws SemanticException ;

	protected Integer getPitchAndUpdateOctave(Matcher matcher, int groupOffset) {
		String noteName = matcher.group(groupOffset + 1);
		String accidental = matcher.group(groupOffset + 2);
		String octaveIndex = matcher.group(groupOffset + 3);
		Integer basePitch = basePitches.get(noteName);
		if (accidental != null) {
			if (accidental.equals("#")) {
				basePitch++;
			} else if (accidental.equals("b")) {
				basePitch--;
			}
		}
		int chordOctave = octave;
		if (octaveIndex != null) {
			chordOctave = Integer.parseInt(octaveIndex);
		}
		octave = chordOctave;
		basePitch += 12 * octave;
		return basePitch;
	}
}
