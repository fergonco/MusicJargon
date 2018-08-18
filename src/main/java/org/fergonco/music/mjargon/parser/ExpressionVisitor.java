package org.fergonco.music.mjargon.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fergonco.music.mjargon.antlr.MJargonBaseVisitor;
import org.fergonco.music.mjargon.antlr.MJargonLexer;
import org.fergonco.music.mjargon.antlr.MJargonParser.AuralDrumElementContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.AuralExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.AuralPitchContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.ChordLiteralContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.DrumSequenceExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.ExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.InstrumentContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.NumericExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.OnTimeSignatureContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.PitchSequenceExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.ReferenceExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.RhythmExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.StringLiteralContext;
import org.fergonco.music.mjargon.model.DrumNote;
import org.fergonco.music.mjargon.model.DrumSequence;
import org.fergonco.music.mjargon.model.FractionValue;
import org.fergonco.music.mjargon.model.FunctionValue;
import org.fergonco.music.mjargon.model.Model;
import org.fergonco.music.mjargon.model.NoteSequence;
import org.fergonco.music.mjargon.model.NoteSequenceElement;
import org.fergonco.music.mjargon.model.NoteSubsequence;
import org.fergonco.music.mjargon.model.NumberValue;
import org.fergonco.music.mjargon.model.PitchArray;
import org.fergonco.music.mjargon.model.PitchedNoteSequence;
import org.fergonco.music.mjargon.model.Rhythm;
import org.fergonco.music.mjargon.model.SequenceAndRhythm;
import org.fergonco.music.mjargon.model.StringValue;
import org.fergonco.music.mjargon.model.Value;

public class ExpressionVisitor extends MJargonBaseVisitor<Value> {
	private static HashMap<String, Integer> basePitches = new HashMap<>();
	private static Map<Integer, DrumNote> drumInstrumentCodes = new HashMap<>();

	static {
		basePitches.put("C", 0);
		basePitches.put("D", 2);
		basePitches.put("E", 4);
		basePitches.put("F", 5);
		basePitches.put("G", 7);
		basePitches.put("A", 9);
		basePitches.put("B", 11);

		drumInstrumentCodes.put(MJargonLexer.HIHAT, DrumNote.HIHAT);
		drumInstrumentCodes.put(MJargonLexer.HH, DrumNote.HIHAT);
		drumInstrumentCodes.put(MJargonLexer.HIHATOPEN, DrumNote.HIHATOPEN);
		drumInstrumentCodes.put(MJargonLexer.HHO, DrumNote.HIHATOPEN);
		drumInstrumentCodes.put(MJargonLexer.HIHATPEDAL, DrumNote.HIHATPEDAL);
		drumInstrumentCodes.put(MJargonLexer.HHP, DrumNote.HIHATPEDAL);
		drumInstrumentCodes.put(MJargonLexer.BASSDRUM, DrumNote.BASSDRUM);
		drumInstrumentCodes.put(MJargonLexer.BD, DrumNote.BASSDRUM);
		drumInstrumentCodes.put(MJargonLexer.SNARE, DrumNote.SNARE);
		drumInstrumentCodes.put(MJargonLexer.SN, DrumNote.SNARE);
		drumInstrumentCodes.put(MJargonLexer.RIDE, DrumNote.RIDE);
		drumInstrumentCodes.put(MJargonLexer.RD, DrumNote.RIDE);
		drumInstrumentCodes.put(MJargonLexer.RIDEBELL, DrumNote.RIDE_BELL);
		drumInstrumentCodes.put(MJargonLexer.RDB, DrumNote.RIDE_BELL);
		drumInstrumentCodes.put(MJargonLexer.CRASH, DrumNote.CRASH);
		drumInstrumentCodes.put(MJargonLexer.CR, DrumNote.CRASH);
		drumInstrumentCodes.put(MJargonLexer.TOM1, DrumNote.TOM1);
		drumInstrumentCodes.put(MJargonLexer.T1, DrumNote.TOM1);
		drumInstrumentCodes.put(MJargonLexer.TOM2, DrumNote.TOM2);
		drumInstrumentCodes.put(MJargonLexer.T2, DrumNote.TOM2);
		drumInstrumentCodes.put(MJargonLexer.TOM3, DrumNote.TOM3);
		drumInstrumentCodes.put(MJargonLexer.T3, DrumNote.TOM3);
		drumInstrumentCodes.put(MJargonLexer.TOM4, DrumNote.TOM4);
		drumInstrumentCodes.put(MJargonLexer.T4, DrumNote.TOM4);
		drumInstrumentCodes.put(MJargonLexer.TOM5, DrumNote.TOM5);
		drumInstrumentCodes.put(MJargonLexer.T5, DrumNote.TOM5);
		drumInstrumentCodes.put(MJargonLexer.TOM6, DrumNote.TOM6);
		drumInstrumentCodes.put(MJargonLexer.T6, DrumNote.TOM6);

	}

	private Model model;

	public ExpressionVisitor(Model model) {
		this.model = model;
	}

	@Override
	public Value visitNumericExpression(NumericExpressionContext ctx) {
		int numerator = Integer.parseInt(ctx.numerator.getText());
		if (ctx.minus != null) {
			numerator = -numerator;
		}
		if (ctx.denominator != null) {
			int denominator = Integer.parseInt(ctx.denominator.getText());
			return new FractionValue(numerator, denominator);
		} else {
			return new NumberValue(numerator);
		}
	}

	@Override
	public Value visitExpression(ExpressionContext ctx) {
		Value value = super.visitLeftExpression(ctx.left);
		SequenceAccessor sequenceAccessor = null;
		if (ctx.index != null) {
			sequenceAccessor = new SequenceAccessor(Integer.parseInt(ctx.index.getText()));
		}
		if (ctx.toIndex != null) {
			sequenceAccessor.setEndIndex(Integer.parseInt(ctx.toIndex.getText()));
		} else if (ctx.colon != null) {
			sequenceAccessor.setUnbounded();
		}
		if (sequenceAccessor != null) {
			if (sequenceAccessor.singleNote()) {
				value = new NoteSequenceElement(value, sequenceAccessor.getIndex());
			} else {
				value = new NoteSubsequence(value, sequenceAccessor.getIndex(), sequenceAccessor.getEndIndex());
			}
		}
		if (ctx.rhythm != null) {
			value = new SequenceAndRhythm(value, new ExpressionVisitor(model).visit(ctx.rhythm));
		}

		return value;
	}

	@Override
	public Value visitStringLiteral(StringLiteralContext ctx) {
		return new StringValue(trimDelimiters(ctx.text.getText()));
	}

	@Override
	public Value visitReferenceExpression(ReferenceExpressionContext ctx) {
		String id = ctx.id.getText();
		if (ctx.parameterValues.size() > 0) {
			return new FunctionValue(id, ScriptLineVisitor.getValues(model, ctx.parameterValues));
		} else {
			return model.getReference(id);
		}
	}

	@Override
	public Value visitRhythmExpression(RhythmExpressionContext ctx) {
		if (ctx.value != null) {
			String rhythmExpression = trimDelimiters(ctx.value.getText());
			Value timeSignature = null;
			OnTimeSignatureContext onTimeSignatureContext = ctx.timeDefinition;
			if (onTimeSignatureContext != null) {
				timeSignature = getTimeSignature(onTimeSignatureContext, rhythmExpression.length());
			} else {
				timeSignature = model.getDefaultTimeSignature();
			}
			return new Rhythm(rhythmExpression, timeSignature);
		} else {
			Value timeSignature = new ExpressionVisitor(model).visit(ctx.timeSignature);
			int numerator = timeSignature.toFraction().getNumerator();
			StringBuffer rhythmExpression = new StringBuffer();
			for (int i = 0; i < numerator; i++) {
				rhythmExpression.append("x");
			}
			return new Rhythm(rhythmExpression.toString(), timeSignature);
		}
	}

	private Value getTimeSignature(OnTimeSignatureContext onTimeSignatureContext, int rhythmLength) {
		Value timeSignature = null;
		if (onTimeSignatureContext.timeSignature != null) {
			timeSignature = new ExpressionVisitor(model).visit(onTimeSignatureContext);
		} else { // beatDuration != null
			int noteDenominator = new ExpressionVisitor(model).visit(onTimeSignatureContext.beatDuration).toFraction()
					.getDenominator();
			timeSignature = new FractionValue(rhythmLength, noteDenominator);
		}
		return timeSignature;
	}

	@Override
	public Value visitAuralExpression(AuralExpressionContext ctx) {
		StringBuilder rhythmExpression = new StringBuilder();
		NoteSequence sequence;
		if (!ctx.pitches.isEmpty()) {
			List<AuralPitchContext> noteLiterals = ctx.pitches;
			ArrayList<PitchArray> notes = new ArrayList<>();
			SequenceParser sequenceParser = new SequenceParser();
			for (AuralPitchContext noteLiteral : noteLiterals) {
				sequenceParser.addPitches(noteLiteral.pitch, notes);
				if (noteLiteral.accent != null) {
					rhythmExpression.append("X");
				} else {
					rhythmExpression.append("x");
				}
			}
			sequence = new PitchedNoteSequence(notes.toArray(new PitchArray[notes.size()]));
		} else {
			List<AuralDrumElementContext> drumLiterals = ctx.drumElements;
			ArrayList<DrumNote> drumNotes = new ArrayList<>();
			for (AuralDrumElementContext noteLiteral : drumLiterals) {
				drumNotes.add(drumInstrumentCodes.get(noteLiteral.drumElement.code.getType()));
				if (noteLiteral.accent != null) {
					rhythmExpression.append("X");
				} else {
					rhythmExpression.append("x");
				}
			}
			sequence = new DrumSequence(drumNotes.toArray(new DrumNote[drumNotes.size()]));

		}
		Value rhythm = new Rhythm(rhythmExpression.toString(),
				getTimeSignature(ctx.timeSignature, rhythmExpression.length()));
		return new SequenceAndRhythm(sequence, rhythm);
	}

	@Override
	public Value visitPitchSequenceExpression(PitchSequenceExpressionContext ctx) {
		List<ChordLiteralContext> noteLiterals = ctx.literals;
		ArrayList<PitchArray> notes = new ArrayList<>();
		SequenceParser sequenceParser = new SequenceParser();
		for (ChordLiteralContext context : noteLiterals) {
			sequenceParser.addPitches(context, notes);
		}
		return new PitchedNoteSequence(notes.toArray(new PitchArray[notes.size()]));
	}

	@Override
	public Value visitDrumSequenceExpression(DrumSequenceExpressionContext ctx) {
		List<InstrumentContext> drumInstruments = ctx.instruments;
		ArrayList<DrumNote> drumNotes = new ArrayList<>();
		for (InstrumentContext instrumentContext : drumInstruments) {
			int n = 1;
			if (instrumentContext.times != null) {
				n = Integer.parseInt(instrumentContext.times.getText());
			}
			for (int j = 0; j < n; j++) {
				drumNotes.add(drumInstrumentCodes.get(instrumentContext.code.getType()));
			}
		}
		return new DrumSequence(drumNotes.toArray(new DrumNote[drumNotes.size()]));
	}

	private String trimDelimiters(String text) {
		return text.substring(1, text.length() - 1);
	}

}
