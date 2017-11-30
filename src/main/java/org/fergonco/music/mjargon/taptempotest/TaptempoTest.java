package org.fergonco.music.mjargon.taptempotest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.JFrame;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.fergonco.music.midi.Score;
import org.fergonco.music.mjargon.antlr.MJargonLexer;
import org.fergonco.music.mjargon.antlr.MJargonParser;
import org.fergonco.music.mjargon.antlr.MJargonParser.ScriptContext;
import org.fergonco.music.mjargon.model.Model;
import org.fergonco.music.mjargon.parser.MJargonError;
import org.fergonco.music.mjargon.parser.ScriptLineVisitor;

public class TaptempoTest extends WindowAdapter implements KeyListener {
	private Sequencer sequencer;
	private long lastPress = -1;
	private File midi;
	private int currentBeat = -1;

	public static void main(String[] args) throws Exception {
		JFrame frm = new JFrame();
		frm.setSize(500, 500);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TaptempoTest instance = new TaptempoTest();
		frm.addWindowListener(instance);
		frm.addKeyListener(instance);
		frm.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		try {
			String scriptName = "drums1";
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
			midi = new File("src/test/resources/" + scriptName + ".midi");
			model.writeMidi(midi);
			// Timer timer = new Timer(true);
			// timer.schedule(new TimerTask() {
			//
			// @Override
			// public void run() {
			// beat();
			// }
			// }, 500, 500);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		beat(e);
	}

	private void beat(KeyEvent e) {
		if (currentBeat == -1) {
			try {
				byte[] bytes = IOUtils.toByteArray(new FileInputStream(midi));
				sequencer = MidiSystem.getSequencer();
				sequencer.setSequence(MidiSystem.getSequence(new ByteArrayInputStream(bytes)));
				sequencer.open();
			} catch (MidiUnavailableException | IOException | InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
			currentBeat++;
		} else {
			if (currentBeat == 0) {
				sequencer.start();
			}
			long now = e.getWhen();// new Date().getTime();
			if (lastPress != -1) {
				long elapsedMs = now - lastPress;
				// double bpm = (1000 * 60) / elapsedMs;
				// sequencer.setTempoInBPM((float) bpm);

				long currentPosition = sequencer.getTickPosition() - 60;
				long relativePosition = (currentBeat * Score.ticksPerQuarterNote) - currentPosition;
				long wantedTicks = relativePosition + Score.ticksPerQuarterNote;
				double wantedBeats = wantedTicks / (double) Score.ticksPerQuarterNote;
				float bpm = Math.max(10, (float) (60 * wantedBeats / (elapsedMs / 1000.0)));
				System.out.println("****************************************");
				System.out.println("currentPosition: " + currentPosition);
				System.out.println("beat: " + currentBeat);
				System.out.println("relativePosition: " + relativePosition);
				System.out.println("wantedTicks: " + wantedTicks);
				System.out.println("elapsedMs: " + elapsedMs);
				System.out.println("bpm: " + bpm);
				System.out.println("****************************************");
				float previousBPM = sequencer.getTempoInBPM();
				float difference = Math.abs(previousBPM - bpm);
				if (difference < 5) {
					bpm = previousBPM;
				} else if (difference < 10) {
					bpm = (bpm + previousBPM) / 2;
				} else if (difference < 15) {
					bpm = (bpm + 2 * previousBPM) / 3;
				}
				sequencer.setTempoInBPM(bpm);
			}
			// sequencer.setTickPosition(Math.max(position,
			// sequencer.getTickPosition()));
			lastPress = now;
			currentBeat++;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
