package org.fergonco.music.mjargon.model;

import static junit.framework.Assert.fail;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.fergonco.music.midi.MidiPlayer;
import org.fergonco.music.mjargon.lexer.Lexer;
import org.fergonco.music.mjargon.lexer.Token;
import org.fergonco.music.mjargon.parser.Parser;
import org.junit.Test;

public class ModelTest {

	@Test
	public void testWriteMidi() throws Exception {
		InputStream is = this.getClass().getResourceAsStream("/oneInstrument.mjargon");
		String script = IOUtils.toString(is, "utf-8");
		is.close();

		Lexer lexer = new Lexer(script);
		Token token = lexer.process();
		Parser parser = new Parser();
		Model model = parser.parse(token);
		File file = new File("/tmp/a.mid");
		model.writeMidi(file);
		MidiPlayer.play(file);
		fail();
	}
}
