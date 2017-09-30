package org.fergonco.music.mjargon.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.Token;
import org.fergonco.music.mjargon.antlr.MJargonBaseVisitor;
import org.fergonco.music.mjargon.antlr.MJargonLexer;
import org.fergonco.music.mjargon.antlr.MJargonParser.DrumSequenceExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.ExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.NumericExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.PitchSequenceExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.ReferenceExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.RhythmExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.StringLiteralContext;
import org.fergonco.music.mjargon.model.DrumNote;
import org.fergonco.music.mjargon.model.DrumSequence;
import org.fergonco.music.mjargon.model.FractionValue;
import org.fergonco.music.mjargon.model.FunctionValue;
import org.fergonco.music.mjargon.model.Model;
import org.fergonco.music.mjargon.model.NoteSequenceElement;
import org.fergonco.music.mjargon.model.NoteSubsequence;
import org.fergonco.music.mjargon.model.NumberValue;
import org.fergonco.music.mjargon.model.PitchedNoteSequence;
import org.fergonco.music.mjargon.model.Rhythm;
import org.fergonco.music.mjargon.model.SemanticException;
import org.fergonco.music.mjargon.model.SequenceAndRhythm;
import org.fergonco.music.mjargon.model.StringValue;
import org.fergonco.music.mjargon.model.Value;

public class ExpressionVisitor extends MJargonBaseVisitor<Value> {
	private static Map<Integer, DrumNote> drumInstrumentCodes = new HashMap<>();
	static {
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
		return new StringValue(ctx.text.getText());
	}

	@Override
	public Value visitReferenceExpression(ReferenceExpressionContext ctx) {
		String id = ctx.id.getText();
		if (ctx.parameterValues.size() > 0) {
			try {
				return new FunctionValue(id, ScriptLineVisitor.getValues(model, ctx.parameterValues));
			} catch (SemanticException e) {
				throw new RuntimeException(e);
			}
		} else {
			return model.getReference(id);
		}
	}

	@Override
	public Value visitRhythmExpression(RhythmExpressionContext ctx) {
		return new Rhythm(trimDelimiters(ctx.value.getText()), new ExpressionVisitor(model).visit(ctx.timeSignature));
	}

	@Override
	public Value visitPitchSequenceExpression(PitchSequenceExpressionContext ctx) {
		List<Token> chordTokens = ctx.notes;
		String[] chords = new String[chordTokens.size()];
		for (int i = 0; i < chords.length; i++) {
			chords[i] = chordTokens.get(i).getText();
		}
		return new PitchedNoteSequence(chords);
	}

	@Override
	public Value visitDrumSequenceExpression(DrumSequenceExpressionContext ctx) {
		List<Token> drumTokens = ctx.instruments;
		DrumNote[] drumNotes = new DrumNote[drumTokens.size()];
		for (int i = 0; i < drumNotes.length; i++) {
			drumNotes[i] = drumInstrumentCodes.get(drumTokens.get(i).getType());
		}
		return new DrumSequence(drumNotes);
	}

	private String trimDelimiters(String text) {
		return text.substring(1, text.length() - 1);
	}
}
