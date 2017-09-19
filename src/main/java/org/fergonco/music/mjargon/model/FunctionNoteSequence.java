package org.fergonco.music.mjargon.model;

import java.util.HashMap;

import org.fergonco.music.mjargon.model.functions.Arpeggio;
import org.fergonco.music.mjargon.model.functions.Function;

public class FunctionNoteSequence implements NoteSequence {
	private static HashMap<String, Class<? extends Function>> functions = new HashMap<>();
	static {
		try {
			add(Arpeggio.class);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private static void add(Class<? extends Function> class1) throws InstantiationException, IllegalAccessException {
		Function instance = class1.newInstance();
		functions.put(instance.getId(), class1);
	}

	private Function function;

	public FunctionNoteSequence(String functionId, NoteSequence[] parameters) throws SemanticException {
		try {
			function = functions.get(functionId).newInstance();
			function.setParameters(parameters);
		} catch (InstantiationException | IllegalAccessException e) {
			/*
			 * Should never happen in normal conditions, it was already
			 * instantiated
			 */
			throw new RuntimeException(e);
		}
	}

	@Override
	public PitchArray[] getAllNotes(int numNotes) {
		return function.getNotes(numNotes);
	}

	@Override
	public PitchArray[] getAllNotes() {
		return function.getAllNotes();
	}

}
