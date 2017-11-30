package org.fergonco.music.mjargon.taptempotest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.fergonco.music.midi.BeatIterator;
import org.fergonco.music.midi.BeatRelativeMidiEvents;
import org.fergonco.music.mjargon.antlr.MJargonLexer;
import org.fergonco.music.mjargon.antlr.MJargonParser;
import org.fergonco.music.mjargon.antlr.MJargonParser.ScriptContext;
import org.fergonco.music.mjargon.model.Model;
import org.fergonco.music.mjargon.parser.MJargonError;
import org.fergonco.music.mjargon.parser.ScriptLineVisitor;

public class MidiMessageTest implements WindowListener, KeyListener {

	public static void main(String[] args) throws Exception {
		JFrame frm = new JFrame();
		frm.setSize(500, 500);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MidiMessageTest instance = new MidiMessageTest();
		frm.addWindowListener(instance);
		frm.addKeyListener(instance);
		frm.setVisible(true);

	}

	private Synthesizer synthesizer;
	private Receiver receiver;
	private long lastPress = -1;
	private long latency;
	private int beat = -3;
	private MidiEventProvider eventProvider;

	public MidiMessageTest() throws MidiUnavailableException, IOException {
		String scriptName = "simple";
		InputStream is = new FileInputStream(new File("src/test/resources/" + scriptName + ".mjargon"));
		String script = IOUtils.toString(is, "utf-8");
		is.close();
		MJargonLexer lexer = new MJargonLexer(new ANTLRInputStream(script));
		MJargonParser parser = new MJargonParser(new CommonTokenStream(lexer));
		Model model = new Model();
		ScriptContext root = parser.script();
		new ScriptLineVisitor(model).visit(root);
		model.validate();
		List<MJargonError> errors = model.getErrors();
		if (errors.size() > 0) {
			for (MJargonError mJargonError : errors) {
				System.out.println(mJargonError);
			}
		}

		synthesizer = MidiSystem.getSynthesizer();
		latency = synthesizer.getLatency();
		synthesizer.open();
		receiver = synthesizer.getReceiver();
		eventProvider = new MockEventProvider(model.getScore());
		
		BeatIterator iterator = model.getScore().getBeatIterator(0);
		showBeat(iterator);
		showBeat(iterator);
		showBeat(iterator);
		showBeat(iterator);
		showBeat(iterator);
		
	}

	private void showBeat(BeatIterator iterator) {
		System.out.println("***** beat");
		ArrayList<BeatRelativeMidiEvents> beatNotes = iterator.nextBeat();
		for (BeatRelativeMidiEvents beatRelativeMidiEvents : beatNotes) {
			System.out.println(beatRelativeMidiEvents.getTickPositionInBeat());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		beat++;
		try {
			long currentPress = synthesizer.getMicrosecondPosition();
			if (beat >= -1) {
				long beatLength = currentPress - lastPress;
				long nextPress = currentPress + beatLength - latency;
				MidiEvent[] midiEvents = eventProvider.getBeatMidiEvents(beatLength);
				System.out.println("ask events for next beat");
				System.out.println("--");
				for (MidiEvent midiEvent : midiEvents) {
					midiEvent.send(nextPress, receiver);
				}
			}
			lastPress = currentPress;
		} catch (InvalidMidiDataException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}
