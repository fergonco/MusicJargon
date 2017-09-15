package org.fergonco.music.mjargon.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.fergonco.music.midi.Note;

public class DrumSequence implements NoteSequence {

	private static Map<String, Integer> instrumentCodes = new HashMap<>();

	static {
		instrumentCodes.put("hihat", Note.DRUMS_Closed_Hi_Hat);
		instrumentCodes.put("hh", Note.DRUMS_Closed_Hi_Hat);
		instrumentCodes.put("hihatopen", Note.DRUMS_Open_HiHat);
		instrumentCodes.put("hho", Note.DRUMS_Open_HiHat);
		instrumentCodes.put("hihatpedal", Note.DRUMS_Pedal_HiHat);
		instrumentCodes.put("hhp", Note.DRUMS_Pedal_HiHat);
		instrumentCodes.put("bassdrum", Note.DRUMS_Acoustic_Bass_Drum);
		instrumentCodes.put("bd", Note.DRUMS_Acoustic_Bass_Drum);
		instrumentCodes.put("snare", Note.DRUMS_Acoustic_Snare);
		instrumentCodes.put("sn", Note.DRUMS_Acoustic_Snare);
		instrumentCodes.put("ride", Note.DRUMS_Ride_Cymbal_1);
		instrumentCodes.put("rd", Note.DRUMS_Ride_Cymbal_1);
		instrumentCodes.put("crash", Note.DRUMS_Crash_Cymbal_1);
		instrumentCodes.put("cr", Note.DRUMS_Crash_Cymbal_1);
		instrumentCodes.put("tom1", Note.DRUMS_High_Tom);
		instrumentCodes.put("t1", Note.DRUMS_High_Tom);
		instrumentCodes.put("tom2", Note.DRUMS_Hi_Mid_Tom);
		instrumentCodes.put("t2", Note.DRUMS_Hi_Mid_Tom);
		instrumentCodes.put("tom3", Note.DRUMS_Low_Mid_Tom);
		instrumentCodes.put("t3", Note.DRUMS_Low_Mid_Tom);
		instrumentCodes.put("tom4", Note.DRUMS_Low_Tom);
		instrumentCodes.put("t4", Note.DRUMS_Low_Tom);
		instrumentCodes.put("tom5", Note.DRUMS_High_Floor_Tom);
		instrumentCodes.put("t5", Note.DRUMS_High_Floor_Tom);
		instrumentCodes.put("tom6", Note.DRUMS_Low_Floor_Tom);
		instrumentCodes.put("t6", Note.DRUMS_Low_Floor_Tom);
	}

	private PitchArray[] pitches;

	public DrumSequence(String[] drumNotes) throws SemanticException {
		ArrayList<PitchArray> pitches = new ArrayList<>();
		for (String drumNote : drumNotes) {
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
