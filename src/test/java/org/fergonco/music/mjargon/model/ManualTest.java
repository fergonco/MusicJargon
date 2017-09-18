package org.fergonco.music.mjargon.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.fergonco.music.mjargon.lexer.Lexer;
import org.fergonco.music.mjargon.lexer.Token;
import org.fergonco.music.mjargon.parser.Parser;

public class ManualTest {
	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream(new File("src/test/resources/addingSequences.mjargon"));
		String script = IOUtils.toString(is, "utf-8");
		is.close();
		Lexer lexer = new Lexer(script);
		Token token = lexer.process();
		Parser parser = new Parser();
		Model model = parser.parse(token);
		model.writeMidi(new File("/tmp/a.midi"));
		ModelTest.playMidi();
	}
}
