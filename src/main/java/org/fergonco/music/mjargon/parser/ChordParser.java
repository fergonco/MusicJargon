package org.fergonco.music.mjargon.parser;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fergonco.music.mjargon.model.PitchArrayImpl;

public class ChordParser {
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

	private String text;
	private int octave;
	private PitchArrayImpl pitchArray;

	public ChordParser(String text) {
		this.text = text;
	}

	public void setDefaultOctave(int octave) {
		this.octave = octave;
	}

	public int getOctave() {
		if (pitchArray == null) {
			getPitchArray();
		}
		return octave;
	}

	public PitchArrayImpl getPitchArray() {
		if (pitchArray == null) {
			readExplicit();
			readModifiers();
		}

		return pitchArray;
	}

	private void readModifiers() {
		int basePitch = pitchArray.getPitch(0);
		if (text.contains("maj")) {
			pitchArray.add(basePitch + 4);
			pitchArray.add(basePitch + 7);
		} else if (text.contains("min")) {
			pitchArray.add(basePitch + 3);
			pitchArray.add(basePitch + 7);
		} else if (text.contains("aug")) {
			pitchArray.add(basePitch + 4);
			pitchArray.add(basePitch + 8);
		} else if (text.contains("dim")) {
			pitchArray.add(basePitch + 3);
			pitchArray.add(basePitch + 6);
		}

		if (text.endsWith("!")) {
			pitchArray.setAccented(true);
		}
	}

	private void readExplicit() {
		pitchArray = new PitchArrayImpl();
		Matcher matcher = p.matcher(text);
		while (matcher.find()) {
			String noteName = matcher.group(1);
			String accidental = matcher.group(2);
			String octaveIndex = matcher.group(3);
			Integer basePitch = basePitches.get(noteName);
			if (accidental != null) {
				if (accidental.equals("#")) {
					basePitch++;
				} else if (accidental.equals("b")) {
					basePitch--;
				}
			}
			if (octaveIndex != null) {
				octave = Integer.parseInt(octaveIndex);
			}
			basePitch += 12 * octave;
			pitchArray.add(basePitch);
		}
	}
}
