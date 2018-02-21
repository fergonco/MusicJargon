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
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.fergonco.music.mjargon.antlr.MJargonLexer;
import org.fergonco.music.mjargon.antlr.MJargonParser;
import org.fergonco.music.mjargon.antlr.MJargonParser.ScriptContext;
import org.fergonco.music.mjargon.parser.MJargonError;
import org.fergonco.music.mjargon.parser.ModelException;
import org.fergonco.music.mjargon.parser.ScriptLineVisitor;
import org.junit.Test;

public class ModelTest {

	@Test
	public void testDuplicatedLabel() throws Exception {
		File testCase = new File("src/test/resources/testErrors/duplicatedLabel.mjargon");
		try {
			getModel(testCase);
			fail();
		} catch (ModelException e) {
		}
	}

	@Test
	public void testWriteMidi() throws Exception {
		File[] testCases = getTestCases();
		for (File testCase : testCases) {
			System.out.println(testCase);
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

	static void testWrite(File testCase) throws Exception {
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
				assertEquals(testCase.getName(), expectedResult[i], result[i]);
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

	private static Model getModel(File testCase) throws Exception {
		InputStream is = new FileInputStream(testCase);
		String script = IOUtils.toString(is, "utf-8");
		is.close();

		return getModel(script);
	}

	private static Model getModel(String script) throws Exception {
		MJargonLexer lexer = new MJargonLexer(new ANTLRInputStream(script));
		MJargonParser parser = new MJargonParser(new CommonTokenStream(lexer));
		Model model = new Model();
		ScriptContext root = parser.script();
		new ScriptLineVisitor(model).visit(root);
		model.validate();
		List<MJargonError> errors = model.getErrors();
		if (errors.size() > 0) {
			throw new RuntimeException(errors.get(0).toString());
		}
		return model;
	}

}