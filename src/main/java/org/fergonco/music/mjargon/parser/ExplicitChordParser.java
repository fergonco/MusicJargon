package org.fergonco.music.mjargon.parser;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fergonco.music.mjargon.model.PitchArrayImpl;

public class ExplicitChordParser extends ChordParser {
	private static HashMap<String, Integer> basePitches = new HashMap<>();
	static {
		basePitches.put("C", 0);
		basePitches.put("D", 2);
		basePitches.put("E", 4);
		basePitches.put("F", 5);
		basePitches.put("G", 7);
		basePitches.put("A", 9);
		basePitches.put("B", 11);
	}
	private static final Pattern p = Pattern.compile("([A-G])([#|b])?(\\d)?");

	public ExplicitChordParser(String text) {
		super(text);
	}

	public PitchArrayImpl getPitchArray() {
		if (pitchArray == null) {
			noteLiteralsToPitchArray();
		}

		return pitchArray;
	}

	private void noteLiteralsToPitchArray() {
		pitchArray = new PitchArrayImpl();
		Matcher matcher = p.matcher(text);
		while (matcher.find()) {
			Integer basePitch = getPitchAndUpdateOctave(matcher, 0);
			pitchArray.add(basePitch);
		}
	}
}
