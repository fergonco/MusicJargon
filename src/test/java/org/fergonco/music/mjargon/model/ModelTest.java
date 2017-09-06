package org.fergonco.music.mjargon.model;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

import org.apache.commons.io.IOUtils;
import org.fergonco.music.midi.MidiPlayer;
import org.fergonco.music.mjargon.lexer.Lexer;
import org.fergonco.music.mjargon.lexer.LexerException;
import org.fergonco.music.mjargon.lexer.Token;
import org.fergonco.music.mjargon.parser.Parser;
import org.fergonco.music.mjargon.parser.SyntaxException;
import org.junit.Test;

public class ModelTest {

	@Test
	public void testWriteAndPlayMidi() throws Exception {
		File[] testCases = getTestCases();
		for (File testCase : testCases) {
			try {
				testWriteAndPlayMidi(testCase);
			} catch (Exception e) {
				throw new RuntimeException(testCase.getName(), e);
			}
		}
	}

	@Test
	public void testParse() throws Exception {
		File[] testCases = getTestCases();
		for (File testCase : testCases) {
			try {
				assertNotNull(getModel(testCase));
			} catch (Exception e) {
				throw new RuntimeException(testCase.getName(), e);
			}
		}
	}

	private File[] getTestCases() {
		File folder = new File("src/test/resources/");
		File[] testCases = folder.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".mjargon");
			}
		});
		return testCases;
	}

	@Test
	public void manualTest() throws Exception {
		testWriteAndPlayMidi(new File("src/test/resources/script.mjargon"));
	}

	private void testWriteAndPlayMidi(File testCase)
			throws FileNotFoundException, IOException, LexerException, SyntaxException, SemanticException,
			MidiUnavailableException, InvalidMidiDataException, InterruptedException {
		Model model = getModel(testCase);
		File file = new File("/tmp/a.mid");
		model.writeMidi(file);
		Sequencer sequencer = MidiPlayer.play(file);
		while (sequencer.isRunning()) {
			synchronized (this) {
				wait(500);
			}
		}
	}

	private Model getModel(File testCase)
			throws FileNotFoundException, IOException, LexerException, SyntaxException, SemanticException {
		InputStream is = new FileInputStream(testCase);
		String script = IOUtils.toString(is, "utf-8");
		is.close();

		Lexer lexer = new Lexer(script);
		Token token = lexer.process();
		Parser parser = new Parser();
		Model model = parser.parse(token);
		return model;
	}
}
