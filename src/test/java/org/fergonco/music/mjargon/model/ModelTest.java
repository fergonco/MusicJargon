package org.fergonco.music.mjargon.model;

import static junit.framework.Assert.fail;

import java.io.File;
import java.io.InputStream;

import javax.sound.midi.Sequencer;

import org.apache.commons.io.IOUtils;
import org.fergonco.music.midi.MidiPlayer;
import org.fergonco.music.mjargon.lexer.Lexer;
import org.fergonco.music.mjargon.lexer.Token;
import org.fergonco.music.mjargon.parser.Parser;
import org.junit.Test;

public class ModelTest {

	@Test
	public void testWriteMidi() throws Exception {
		InputStream is = this.getClass().getResourceAsStream("/script.mjargon");
		String script = IOUtils.toString(is, "utf-8");
		is.close();

		Lexer lexer = new Lexer(script);
		Token token = lexer.process();
		Parser parser = new Parser();
		Model model = parser.parse(token);
		File file = new File("/tmp/a.mid");
		model.writeMidi(file);
		Sequencer sequencer = MidiPlayer.play(file);
		while (sequencer.isRunning()) {
			synchronized (this) {
				wait(500);
			}
		}
		fail();
	}
}
