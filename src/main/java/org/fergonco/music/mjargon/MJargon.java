package org.fergonco.music.mjargon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.sound.midi.Sequencer;

import org.apache.commons.io.IOUtils;
import org.fergonco.music.midi.MidiPlayer;
import org.fergonco.music.mjargon.lexer.Lexer;
import org.fergonco.music.mjargon.lexer.Token;
import org.fergonco.music.mjargon.model.Model;
import org.fergonco.music.mjargon.parser.Parser;

public class MJargon {

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Usage:");
			System.out.println("MJargon <file>");
			System.exit(1);
		}

		System.out.println("Version: " + MJargon.class.getPackage().getImplementationVersion());

		InputStream is = new FileInputStream(new File(args[0]));
		String script = IOUtils.toString(is, "utf-8");
		is.close();
		Lexer lexer = new Lexer(script);
		Token token = lexer.process();
		Parser parser = new Parser();
		final Model model = parser.parse(token);
		final PipedOutputStream midiOutputStream = new PipedOutputStream();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					model.writeMidi(midiOutputStream);
				} catch (IOException e) {
					System.err.println("Cannot generate midi: " + e.getMessage());
					e.printStackTrace();
				} finally {
					try {
						midiOutputStream.close();
					} catch (IOException e) {
					}
				}
			}
		}).start();
		Sequencer sequencer = MidiPlayer.play(new PipedInputStream(midiOutputStream));
		while (sequencer.isRunning()) {
			synchronized (MJargon.class) {
				MJargon.class.wait(500);
			}
		}
		sequencer.stop();
		sequencer.close();
	}
}
