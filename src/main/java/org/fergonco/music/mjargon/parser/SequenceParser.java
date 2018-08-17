package org.fergonco.music.mjargon.parser;

import org.fergonco.music.mjargon.antlr.MJargonParser.ChordLiteralContext;
import org.fergonco.music.mjargon.model.PitchArray;
import org.fergonco.music.mjargon.model.PitchArrayImpl;
import org.fergonco.music.mjargon.model.TiedPitchArray;

public class SequenceParser {

	private int octave = 4;

	public PitchArray getPitch(ChordLiteralContext context) {
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
		
		return pitchArray;
	}
}
