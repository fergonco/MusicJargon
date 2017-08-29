package org.fergonco.music.mjargon.lexer;

import static junit.framework.Assert.*;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class LexerTest {

	@Test
	public void testScript() throws Exception {
		InputStream is = this.getClass().getResourceAsStream("/script.mjargon");
		String script = IOUtils.toString(is, "utf-8");
		is.close();

		Lexer lexer = new Lexer(script);
		Token token = lexer.process();

		assertNotNull(token);
		assertNotNull(token.next());
		int tokenCount = 0;
		while (token != null) {
			String text = token.getText();
			assertTrue(text.startsWith("#") || text.indexOf(" ") == -1);
			tokenCount++;
			token = token.next();
		}
		assertTrue(tokenCount > 50);
	}
}
