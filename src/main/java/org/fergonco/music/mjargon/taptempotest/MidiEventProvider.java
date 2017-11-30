package org.fergonco.music.mjargon.taptempotest;

import javax.sound.midi.InvalidMidiDataException;

public interface MidiEventProvider {
	
	MidiEvent[] getBeatMidiEvents(long beatLengthMicroSeconds) throws InvalidMidiDataException;

}
