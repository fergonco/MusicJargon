package org.fergonco.music.mjargon;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;
import java.util.Set;

import javax.sound.midi.Sequencer;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.fergonco.music.midi.InstrumentNames;
import org.fergonco.music.midi.MidiPlayer;
import org.fergonco.music.mjargon.antlr.MJargonLexer;
import org.fergonco.music.mjargon.antlr.MJargonParser;
import org.fergonco.music.mjargon.antlr.MJargonParser.ScriptContext;
import org.fergonco.music.mjargon.model.Model;
import org.fergonco.music.mjargon.parser.MJargonError;
import org.fergonco.music.mjargon.parser.ScriptLineVisitor;

public class MJargon {

	public static void main(String[] args) throws Exception {
		System.out.println("Version: " + MJargon.class.getPackage().getImplementationVersion());
		String inputFile = null;
		String outputFile = null;
		String status = null;
		String label = null;
		boolean showInstrumentNames = false;
		for (String arg : args) {
			if ("-o".equals(status)) {
				outputFile = arg;
				status = null;
			} else if ("-l".equals(status)) {
				label = arg;
				status = null;
			} else if (arg.equals("-li")) {
				showInstrumentNames = true;
			} else if (arg.equals("-o") || arg.equals("-l")) {
				status = arg;
			} else {
				inputFile = arg;
			}
		}

		if (inputFile != null) {
			InputStream is = new FileInputStream(inputFile);
			String script = IOUtils.toString(is, "utf-8");
			is.close();
			MJargonLexer lexer = new MJargonLexer(new ANTLRInputStream(script));
			MJargonParser parser = new MJargonParser(new CommonTokenStream(lexer));
			final Model model = new Model();
			ScriptContext root = parser.script();
			new ScriptLineVisitor(model).visit(root);
			model.validate();
			if (label != null) {
				model.startInLabel(label);
			}
			List<MJargonError> errors = model.getErrors();
			for (MJargonError mJargonError : errors) {
				System.err.println(mJargonError);
			}
			OutputStream outputStream;
			if (outputFile != null) {
				outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
			} else {
				outputStream = new PipedOutputStream();
			}
			final OutputStream midiOutputStream = outputStream;
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
			if (outputFile == null) {
				Sequencer sequencer = MidiPlayer.play(new PipedInputStream((PipedOutputStream) midiOutputStream));
				while (sequencer.isRunning()) {
					synchronized (MJargon.class) {
						MJargon.class.wait(500);
					}
				}
				sequencer.stop();
				sequencer.close();
			}
		} else if (showInstrumentNames) {
			Set<String> instrumentNames = InstrumentNames.getInstrumentNames();
			for (String instrumentName : instrumentNames) {
				System.out.println(instrumentName.toLowerCase());
			}
		} else {
			System.out.println("Usage:");
			System.out.println("MJargon [-o <output_midi>] [-l <label>] <file>");
			System.out.println("MJargon -li");
			System.exit(1);
		}
	}
}
