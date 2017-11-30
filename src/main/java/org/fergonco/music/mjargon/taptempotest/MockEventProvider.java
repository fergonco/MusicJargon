package org.fergonco.music.mjargon.taptempotest;

import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

import org.fergonco.music.midi.BeatIterator;
import org.fergonco.music.midi.BeatRelativeMidiEvents;
import org.fergonco.music.midi.Score;

public class MockEventProvider implements MidiEventProvider {

	private BeatIterator beatIterator;

	public MockEventProvider(Score score) {
		this.beatIterator = score.getBeatIterator(0);
	}

	@Override
	public MidiEvent[] getBeatMidiEvents(long beatLengthMicroSeconds) throws InvalidMidiDataException {
		ArrayList<MidiEvent> ret = new ArrayList<>();
		
		ArrayList<BeatRelativeMidiEvents> events = beatIterator.nextBeat();
		for (BeatRelativeMidiEvents midiEvents : events) {
			int tickPosition = midiEvents.getTickPositionInBeat();
			double microSecondsPerTick = beatLengthMicroSeconds / (double) Score.ticksPerQuarterNote;
			long microSecondsPosition = Math.round(tickPosition * microSecondsPerTick);
			ShortMessage message = new ShortMessage();
			message.setMessage(ShortMessage.NOTE_ON, 0, 60, 40);
			ret.add(new MidiEvent(microSecondsPosition, message));
		}
		
		return ret.toArray(new MidiEvent[ret.size()]);
	}

}
