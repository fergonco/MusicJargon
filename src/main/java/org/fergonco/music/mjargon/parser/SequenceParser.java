package org.fergonco.music.mjargon.parser;

import java.util.List;

import org.fergonco.music.mjargon.antlr.MJargonParser.ChordLiteralContext;
import org.fergonco.music.mjargon.model.PitchArray;
import org.fergonco.music.mjargon.model.PitchArrayImpl;
import org.fergonco.music.mjargon.model.TiedPitchArray;

public class SequenceParser {

	private int octave = 4;

	public void addPitches(ChordLiteralContext context, List<PitchArray> array) {
		int n = 1;
		if (context.times != null) {
			n = Integer.parseInt(context.times.getText());
		}
		for (int i = 0; i < n; i++) {
			PitchArray pitchArray;
			if (context.underscore != null) {
				pitchArray = new TiedPitchArray();
			} else if (context.silence != null) {
				PitchArrayImpl pitchArrayImpl = new PitchArrayImpl();
				pitchArrayImpl.setSilence();
				pitchArray = pitchArrayImpl;
			} else {
				String chordText = context.getText();
				ChordParser chordParser = new ChordParser(chordText);
				chordParser.setDefaultOctave(octave);
				octave = chordParser.getOctave();
				pitchArray = chordParser.getPitchArray();
			}
			array.add(pitchArray);
		}
	}
}
