package org.fergonco.music.mjargon.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fergonco.music.mjargon.model.PitchArrayImpl;
import org.fergonco.music.mjargon.model.SemanticException;

public class NamedChordParser extends ChordParser {
	private static final Pattern p = Pattern.compile("([A-G])([#|b])?(\\d)?(maj|min|aug|dim)(?:\\/([A-G])([#|b])?(\\d)?)?");

	private Integer bassNote = null;

	public NamedChordParser(String text) {
		super(text);
	}

	public PitchArrayImpl getPitchArray() throws SemanticException {
		if (pitchArray == null) {
			noteLiteralsToPitchArray();
			readModifiers();
			inverseChord();
		}

		return pitchArray;
	}

	private void inverseChord() throws SemanticException {
		if (bassNote != null) {
			int iterations = 0;
			while (pitchArray.getPitch(0) != bassNote) {
				pitchArray.transposeFirst();
				iterations ++;
				if (iterations > pitchArray.pitchCount()) {
					throw new SemanticException("Chord inversion not found: " + text);
				}
			}
		}
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
	}

	private void noteLiteralsToPitchArray() {
		pitchArray = new PitchArrayImpl();
		Matcher matcher = p.matcher(text);
		if (matcher.find()) {
			Integer basePitch = getPitchAndUpdateOctave(matcher, 0);
			pitchArray.add(basePitch);

			if (text.contains("/")) {
				bassNote = getPitchAndUpdateOctave(matcher, 4);
			}
		}
	}

}