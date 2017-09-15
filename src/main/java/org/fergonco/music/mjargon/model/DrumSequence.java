package org.fergonco.music.mjargon.model;

import static org.fergonco.music.mjargon.lexer.Lexer.*; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.fergonco.music.midi.Note;

public class DrumSequence implements NoteSequence {

	private static Map<Integer, Integer> instrumentCodes = new HashMap<>();

	static {
		instrumentCodes.put(HIHAT, Note.DRUMS_Closed_Hi_Hat);
		instrumentCodes.put(HH, Note.DRUMS_Closed_Hi_Hat);
		instrumentCodes.put(HIHATOPEN, Note.DRUMS_Open_HiHat);
		instrumentCodes.put(HHO, Note.DRUMS_Open_HiHat);
		instrumentCodes.put(HIHATPEDAL, Note.DRUMS_Pedal_HiHat);
		instrumentCodes.put(HHP, Note.DRUMS_Pedal_HiHat);
		instrumentCodes.put(BASSDRUM, Note.DRUMS_Acoustic_Bass_Drum);
		instrumentCodes.put(BD, Note.DRUMS_Acoustic_Bass_Drum);
		instrumentCodes.put(SNARE, Note.DRUMS_Acoustic_Snare);
		instrumentCodes.put(SN, Note.DRUMS_Acoustic_Snare);
		instrumentCodes.put(RIDE, Note.DRUMS_Ride_Cymbal_1);
		instrumentCodes.put(RD, Note.DRUMS_Ride_Cymbal_1);
		instrumentCodes.put(CRASH, Note.DRUMS_Crash_Cymbal_1);
		instrumentCodes.put(CR, Note.DRUMS_Crash_Cymbal_1);
		instrumentCodes.put(TOM1, Note.DRUMS_High_Tom);
		instrumentCodes.put(T1, Note.DRUMS_High_Tom);
		instrumentCodes.put(TOM2, Note.DRUMS_Hi_Mid_Tom);
		instrumentCodes.put(T2, Note.DRUMS_Hi_Mid_Tom);
		instrumentCodes.put(TOM3, Note.DRUMS_Low_Mid_Tom);
		instrumentCodes.put(T3, Note.DRUMS_Low_Mid_Tom);
		instrumentCodes.put(TOM4, Note.DRUMS_Low_Tom);
		instrumentCodes.put(T4, Note.DRUMS_Low_Tom);
		instrumentCodes.put(TOM5, Note.DRUMS_High_Floor_Tom);
		instrumentCodes.put(T5, Note.DRUMS_High_Floor_Tom);
		instrumentCodes.put(TOM6, Note.DRUMS_Low_Floor_Tom);
		instrumentCodes.put(T6, Note.DRUMS_Low_Floor_Tom);
	}

	private PitchArray[] pitches;

	public DrumSequence(Integer[] drumNotes) throws SemanticException {
		ArrayList<PitchArray> pitches = new ArrayList<>();
		for (Integer drumNote : drumNotes) {
			if (!instrumentCodes.containsKey(drumNote)) {
				throw new SemanticException("Unrecognized drum instrument: " + drumNote);
			}
			PitchArrayImpl pitch = new PitchArrayImpl();
			pitch.add(instrumentCodes.get(drumNote));
			pitch.setDrums(true);
			pitches.add(pitch);
		}
		this.pitches = pitches.toArray(new PitchArray[pitches.size()]);
	}

	@Override
	public PitchArray getNote(int noteIndex) {
		return pitches[noteIndex];
	}

	@Override
	public PitchArray[] getAllNotes() {
		return pitches;
	}

}
