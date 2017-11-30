package org.fergonco.music.mjargon.taptempotest;

import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class MidiEvent {

	private long beatBasedMicrosecondPosition;

	private ShortMessage midiEvent;

	public MidiEvent(long beatBasedMicrosecondPosition, ShortMessage midiEvent) {
		super();
		this.beatBasedMicrosecondPosition = beatBasedMicrosecondPosition;
		this.midiEvent = midiEvent;
	}

	public long getBeatBasedMicrosecondPosition() {
		return beatBasedMicrosecondPosition;
	}

	public void setBeatBasedMicrosecondPosition(long beatBasedMicrosecondPosition) {
		this.beatBasedMicrosecondPosition = beatBasedMicrosecondPosition;
	}

	public ShortMessage getMidiEvent() {
		return midiEvent;
	}

	public void setMidiEvent(ShortMessage midiEvent) {
		this.midiEvent = midiEvent;
	}

	public void send(long basePosition, Receiver receiver) {
		System.out.println("Sending event");
		System.out.println("  " + (basePosition + beatBasedMicrosecondPosition));
		receiver.send(midiEvent, basePosition + beatBasedMicrosecondPosition);
	}

}
