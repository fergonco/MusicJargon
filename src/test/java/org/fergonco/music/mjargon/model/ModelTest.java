package org.fergonco.music.mjargon.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

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
import org.junit.Ignore;
import org.junit.Test;

public class ModelTest {

	@Ignore
	@Test
	public void testWriteAndPlayMidi() throws Exception {
		File[] testCases = getTestCases();
		for (File testCase : testCases) {
			try {
				testWrite(testCase);
				playMidi();
			} catch (Exception e) {
				throw new RuntimeException(testCase.getName(), e);
			}
		}
	}

	@Test
	public void testWriteMidi() throws Exception {
		File[] testCases = getTestCases();
		for (File testCase : testCases) {
			try {
				testWrite(testCase);
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

	@Test
	public void manualTest() throws Exception {
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

	static void testWrite(File testCase)
			throws FileNotFoundException, IOException, LexerException, SyntaxException, SemanticException {
		Model model = getModel(testCase);
		File file = new File("/tmp/a.mid");
		model.writeMidi(file);
	}

	static void playMidi()
			throws MidiUnavailableException, IOException, InvalidMidiDataException, InterruptedException {
		Sequencer sequencer = MidiPlayer.play(new File("/tmp/a.mid"));
		while (sequencer.isRunning()) {
			synchronized (ModelTest.class) {
				ModelTest.class.wait(500);
			}
		}
	}

	private static Model getModel(File testCase)
			throws FileNotFoundException, IOException, LexerException, SyntaxException, SemanticException {
		InputStream is = new FileInputStream(testCase);
		String script = IOUtils.toString(is, "utf-8");
		is.close();

		return getModel(script);
	}

	private static Model getModel(String script) throws LexerException, SyntaxException, SemanticException {
		Lexer lexer = new Lexer(script);
		Token token = lexer.process();
		Parser parser = new Parser();
		Model model = parser.parse(token);
		return model;
	}

	@Test
	public void chordTiesNotAllowed() throws LexerException, SyntaxException, SemanticException {
		try {
			getModel("c = chord progression CEG _ DFA");
			fail();
		} catch (Exception e) {
			getModel("c = chord progression CEG CEG DFA");
		}
	}
}
