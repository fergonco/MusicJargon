package org.fergonco.music.mjargon.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.midi.Sequencer;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.fergonco.music.midi.MidiPlayer;
import org.fergonco.music.mjargon.antlr.MJargonLexer;
import org.fergonco.music.mjargon.antlr.MJargonParser;
import org.fergonco.music.mjargon.antlr.MJargonParser.ScriptContext;
import org.fergonco.music.mjargon.parser.ScriptLineVisitor;

public class ManualTest {
	public static void main(String[] args) throws Exception {
		String scriptName = "subsequences";
		InputStream is = new FileInputStream(new File("src/test/resources/" + scriptName + ".mjargon"));
		String script = IOUtils.toString(is, "utf-8");
		is.close();
		MJargonLexer lexer = new MJargonLexer(new ANTLRInputStream(script));
		MJargonParser parser = new MJargonParser(new CommonTokenStream(lexer));
		Model model = new Model();
		ScriptContext root = parser.script();
		new ScriptLineVisitor(model).visit(root);
		File midi = new File("src/test/resources/" + scriptName + ".midi");
		model.writeMidi(midi);
		Sequencer sequencer = MidiPlayer.play(midi);
		while (sequencer.isRunning()) {
			synchronized (ModelTest.class) {
				ModelTest.class.wait(500);
			}
		}
		sequencer.stop();
		sequencer.close();
	}
}
