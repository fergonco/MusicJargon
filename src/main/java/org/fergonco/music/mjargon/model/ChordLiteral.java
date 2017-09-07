package org.fergonco.music.mjargon.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChordLiteral {
	private static HashMap<Character, Integer> basePitches = new HashMap<>();
	static {
		basePitches.put('C', 0);
		basePitches.put('D', 2);
		basePitches.put('E', 4);
		basePitches.put('F', 5);
		basePitches.put('G', 7);
		basePitches.put('A', 9);
		basePitches.put('B', 11);
	}
	private static Pattern chordPattern = Pattern.compile("([A-Z])(\\d)?([♯|♭])?");

	private String chordString;
	private int currentOctave;

	public ChordLiteral(int defaultOctave, String chordString) {
		this.currentOctave = defaultOctave;
		this.chordString = chordString;
	}

	public PitchArray getPitchArray() {
		ArrayList<Integer> pitchList = new ArrayList<>();
		Matcher matcher = chordPattern.matcher(chordString);
		while (matcher.find()) {
			String noteString = matcher.group();
			int pitch = basePitches.get(noteString.charAt(0));
			int index = 1;
			while (index < noteString.length()) {
				char octaveOrAccidental = noteString.charAt(index);
				if (Character.isDigit(octaveOrAccidental)) {
					currentOctave = Integer.parseInt(String.valueOf(octaveOrAccidental));
				} else if (octaveOrAccidental == '♭') {
					pitch--;
				} else if (octaveOrAccidental == '♯') {
					pitch++;
				} else {
					throw new RuntimeException();
				}
				index++;
			}
			pitch += 12 * currentOctave;
			pitchList.add(pitch);
		}
		return new PitchArray(pitchList);
	}
	
	public int getCurrentOctave() {
		return currentOctave;
	}

	public static PitchArray[] parseSequence(String[] chordStrings) {
		PitchArray[] chords = new PitchArray[chordStrings.length];
		int lastOctave = 4;
		for (int i = 0; i < chordStrings.length; i++) {
			String chordString = chordStrings[i];
			ChordLiteral chordLiteral = new ChordLiteral(lastOctave, chordString);
			chords[i] = chordLiteral.getPitchArray();
			lastOctave = chordLiteral.getCurrentOctave();
		}
		return chords;
	}

}
