package org.fergonco.music.mjargon.model;

import java.io.File;

public class ManualTest {
	public static void main(String[] args) throws Exception {
		ModelTest.testWrite(new File("src/test/resources/pfrhythm.mjargon"));
		ModelTest.playMidi();
	}
}
