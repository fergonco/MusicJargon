package org.fergonco.music.mjargon.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.fergonco.music.mjargon.lexer.Lexer;
import org.fergonco.music.mjargon.lexer.LexerException;
import org.fergonco.music.mjargon.lexer.Token;
import org.fergonco.music.mjargon.parser.Parser;
import org.fergonco.music.mjargon.parser.SyntaxException;
import org.junit.Test;

public class ModelTest {

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
		File file = new File("/tmp/a.midi");
		model.writeMidi(file);
		byte[] result = readFile(file);
		File expectedResultFile = new File(testCase.getPath().replaceAll(".mjargon", ".midi"));
		if (!expectedResultFile.exists()) {
			fail("No result file for " + testCase.getPath());
		} else {
			byte[] expectedResult = readFile(expectedResultFile);
			assertEquals(testCase.getName(), expectedResult.length, result.length);
			for (int i = 0; i < expectedResult.length; i++) {
				assertEquals(expectedResult[i], result[i]);
			}
		}
	}

	private static byte[] readFile(File file) throws IOException, FileNotFoundException {
		InputStream stream = new BufferedInputStream(new FileInputStream(file));
		try {
			return IOUtils.toByteArray(stream);
		} finally {
			stream.close();
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

}