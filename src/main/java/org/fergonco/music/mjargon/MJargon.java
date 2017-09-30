package org.fergonco.music.mjargon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.sound.midi.Sequencer;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.fergonco.music.midi.MidiPlayer;
import org.fergonco.music.mjargon.antlr.MJargonLexer;
import org.fergonco.music.mjargon.antlr.MJargonParser;
import org.fergonco.music.mjargon.antlr.MJargonParser.ScriptContext;
import org.fergonco.music.mjargon.model.Model;
import org.fergonco.music.mjargon.parser.ScriptLineVisitor;

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
		MJargonLexer lexer = new MJargonLexer(new ANTLRInputStream(script));
		MJargonParser parser = new MJargonParser(new CommonTokenStream(lexer));
		final Model model = new Model();
		ScriptContext root = parser.script();
		new ScriptLineVisitor(model).visit(root);
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
