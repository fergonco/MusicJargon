package org.fergonco.music.mjargon.model;

import java.util.HashMap;

import org.fergonco.music.mjargon.model.functions.Arpeggio;
import org.fergonco.music.mjargon.model.functions.Function;
import org.fergonco.music.mjargon.model.functions.Transpose8;
import org.fergonco.music.mjargon.model.functions.ValueType;
import org.fergonco.music.mjargon.model.functions.chordNotes;

public class FunctionValue extends AbstractValue implements Value {

	private static HashMap<String, Class<? extends Function>> functions = new HashMap<>();
	static {
		try {
			add(Arpeggio.class);
			add(Transpose8.class);
			add(chordNotes.class);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private static void add(Class<? extends Function> class1) throws InstantiationException, IllegalAccessException {
		Function instance = class1.newInstance();
		functions.put(instance.getId(), class1);
	}

	private Function function;

	public FunctionValue(String functionId, Value[] values) throws SemanticException {
		try {
			function = functions.get(functionId).newInstance();
			function.setParameters(values);
		} catch (InstantiationException | IllegalAccessException e) {
			/*
			 * Should never happen in normal conditions, it was already
			 * instantiated
			 */
			throw new RuntimeException(e);
		}
	}

	@Override
	public ValueType getType() {
		return function.getType();
	}

	public SequenceAndRhythm toAural() {
		return function.toAural();
	}

	public NoteSequence toNoteSequence() {
		return function.toNoteSequence();
	}

	public Rhythm toRhythm() {
		return function.toRhythm();
	}

	public TimeSignature toTimeSignature() {
		return function.toTimeSignature();
	}

	public int toInt() {
		return function.toInt();
	}

	public String toStringLiteral() {
		return function.toStringLiteral();
	}

}