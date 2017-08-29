package org.fergonco.music.mjargon.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolyphonicNoteSequence implements NoteSequence {
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

	private PitchArray[] chords;

	public PolyphonicNoteSequence(String[] chordStrings) {
		chords = new PitchArray[chordStrings.length];
		for (int i = 0; i < chordStrings.length; i++) {
			String chordString = chordStrings[i];
			ArrayList<Integer> pitchList = new ArrayList<>();
			Matcher matcher = chordPattern.matcher(chordString);
			while (matcher.find()) {
				String noteString = matcher.group();
				int pitch = basePitches.get(noteString.charAt(0));
				int index = 1;
				while (index < noteString.length()) {
					char octaveOrAccidental = noteString.charAt(index);
					if (Character.isDigit(octaveOrAccidental)) {
						int octave = Integer.parseInt(String.valueOf(octaveOrAccidental));
						pitch += 12 * octave;
					} else if (octaveOrAccidental == '♭') {
						pitch--;
					} else if (octaveOrAccidental == '♯') {
						pitch++;
					} else {
						throw new RuntimeException();
					}
					index++;
				}
				pitchList.add(pitch);
			}
			chords[i] = new PitchArray(pitchList);
		}
	}

	public PitchArray getChord(int chordProgressionIndex) {
		return chords[chordProgressionIndex];
	}

	@Override
	public PitchArray getNote(int noteIndex) {
		return chords[noteIndex];
	}

	@Override
	public PitchArray[] getAllNotes() {
		return chords;
	}

}
