package org.fergonco.music.mjargon.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.fergonco.music.midi.DrumNoteImpl;
import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.midi.Note;

public class Drums implements Bar {

	private static Map<String, Integer> instrumentCodes = new HashMap<>();

	static {
		instrumentCodes.put("hihat", Note.DRUMS_Closed_Hi_Hat);
		instrumentCodes.put("hihatopen", Note.DRUMS_Open_HiHat);
		instrumentCodes.put("hihatpedal", Note.DRUMS_Pedal_HiHat);
		instrumentCodes.put("bassdrum", Note.DRUMS_Acoustic_Bass_Drum);
		instrumentCodes.put("snare", Note.DRUMS_Acoustic_Snare);
		instrumentCodes.put("ride", Note.DRUMS_Ride_Cymbal_1);
		instrumentCodes.put("crash", Note.DRUMS_Crash_Cymbal_1);
		instrumentCodes.put("tom1", Note.DRUMS_High_Tom);
		instrumentCodes.put("tom2", Note.DRUMS_Hi_Mid_Tom);
		instrumentCodes.put("tom3", Note.DRUMS_Low_Mid_Tom);
		instrumentCodes.put("tom4", Note.DRUMS_Low_Tom);
		instrumentCodes.put("tom5", Note.DRUMS_High_Floor_Tom);
		instrumentCodes.put("tom6", Note.DRUMS_Low_Floor_Tom);
	}

	private String expression;
	private Map<Character, String> mapping;
	private TimeSignature timeSignature;

	public Drums(String expression, Map<Character, String> mapping, TimeSignature timeSignature) {
		super();
		this.expression = expression;
		this.mapping = mapping;
		this.timeSignature = timeSignature;
	}

	@Override
	public Note[] getNotes(Dynamic baseDynamics) {
		ArrayList<Note> ret = new ArrayList<>();
		RhythmComponent[] components = new Rhythm(expression, timeSignature).getComponents();
		for (RhythmComponent component : components) {
			Dynamic dynamic = baseDynamics;
			if (component.isAccent()) {
				dynamic = dynamic.louder().louder();
			}

			char beatSymbol = component.getBeatSymbol();
			String instrumentName = mapping.get(Character.toLowerCase(beatSymbol));
			int pitch = instrumentName != null ? instrumentCodes.get(instrumentName) : 0;
			ret.add(new DrumNoteImpl(component.getDuration(), new int[] { dynamic.getLevel() }, new int[] { pitch }));
		}
		return ret.toArray(new Note[ret.size()]);
	}

}
