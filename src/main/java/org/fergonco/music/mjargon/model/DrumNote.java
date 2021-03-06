package org.fergonco.music.mjargon.model;

import org.fergonco.music.midi.Note;

public enum DrumNote {

	HIHAT(Note.DRUMS_Closed_Hi_Hat), //
	HIHATOPEN(Note.DRUMS_Open_HiHat), //
	HIHATPEDAL(Note.DRUMS_Pedal_HiHat), //
	BASSDRUM(Note.DRUMS_Bass_Drum_1), //
	SNARE(Note.DRUMS_Acoustic_Snare), //
	RIDE(Note.DRUMS_Ride_Cymbal_1), //
	RIDE_BELL(Note.DRUMS_Ride_Bell), //
	CRASH(Note.DRUMS_Crash_Cymbal_1), //
	TOM1(Note.DRUMS_High_Tom), //
	TOM2(Note.DRUMS_Hi_Mid_Tom), //
	TOM3(Note.DRUMS_Low_Mid_Tom), //
	TOM4(Note.DRUMS_Low_Tom), //
	TOM5(Note.DRUMS_High_Floor_Tom), //
	TOM6(Note.DRUMS_Low_Floor_Tom);

	private int midiCode;

	DrumNote(int code) {
		this.midiCode = code;
	}

	private int getMIDICode() {
		return midiCode;
	}

	public PitchArray toPitchArray() {
		PitchArrayImpl pitch = new PitchArrayImpl();
		pitch.add(this.getMIDICode());
		pitch.setDrums(true);
		return pitch;
	}

}
