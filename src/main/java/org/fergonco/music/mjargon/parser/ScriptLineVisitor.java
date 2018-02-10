package org.fergonco.music.mjargon.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.Token;
import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.mjargon.antlr.MJargonBaseVisitor;
import org.fergonco.music.mjargon.antlr.MJargonLexer;
import org.fergonco.music.mjargon.antlr.MJargonParser.BarlineContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.DeclarationContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.DefaultDeclarationContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.DynamicsContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.ExpressionContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.ExpressionOrReferenceContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.LabelDeclarationContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.RepeatContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.TempoContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.VariableDeclarationContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.VoicesContext;
import org.fergonco.music.mjargon.model.Bar;
import org.fergonco.music.mjargon.model.InstrumentBar;
import org.fergonco.music.mjargon.model.InstrumentRepeatBar;
import org.fergonco.music.mjargon.model.Model;
import org.fergonco.music.mjargon.model.SilenceBar;
import org.fergonco.music.mjargon.model.Value;

public class ScriptLineVisitor extends MJargonBaseVisitor<Object> {

	private static Map<String, Dynamic> dynamics = new HashMap<>();

	static {
		dynamics.put("pppp", Dynamic.PPPP);
		dynamics.put("ppp", Dynamic.PPP);
		dynamics.put("pp", Dynamic.PP);
		dynamics.put("p", Dynamic.P);
		dynamics.put("mp", Dynamic.MP);
		dynamics.put("mf", Dynamic.MF);
		dynamics.put("f", Dynamic.F);
		dynamics.put("ff", Dynamic.FF);
		dynamics.put("fff", Dynamic.FFF);
		dynamics.put("ffff", Dynamic.FFFF);
	}

	private Model model;
	private String declarationId;

	public ScriptLineVisitor(Model model) {
		this.model = model;
	}

	@Override
	public Object visitDynamics(DynamicsContext ctx) {
		ArrayList<Dynamic> dynamicList = new ArrayList<>();
		List<Token> instrumentTokens = getPerInstrument(ctx.dynamicCodes);
		for (Token token : instrumentTokens) {
			dynamicList.add(token == null ? null : dynamics.get(token.getText()));
		}
		model.setDynamics(dynamicList);

		return super.visitDynamics(ctx);
	}

	@Override
	public Object visitTempo(TempoContext ctx) {
		int[] length = new int[] { 1, 4 };
		int bpm;
		if (ctx.bpm != null) {
			length = new int[] { Integer.parseInt(ctx.bpmOrNumerator.getText()),
					Integer.parseInt(ctx.denominator.getText()) };
			bpm = Integer.parseInt(ctx.bpm.getText());
		} else {
			bpm = Integer.parseInt(ctx.bpmOrNumerator.getText());
		}
		model.setTempo(length, bpm);
		return super.visitTempo(ctx);
	}

	@Override
	public Object visitRepeat(RepeatContext ctx) {
		String label = ctx.labelId.getText();
		int times = Integer.parseInt(ctx.times.getText());
		model.repeat(label, times);
		return super.visitRepeat(ctx);
	}

	@Override
	public Object visitDefaultDeclaration(DefaultDeclarationContext ctx) {
		Value defaultTimeSignature = new ExpressionVisitor(model).visit(ctx.defaultTimeSignature);
		model.setDefaultTimeSignature(defaultTimeSignature.toFraction());
		return super.visitDefaultDeclaration(ctx);
	}

	@Override
	public Object visitDeclaration(DeclarationContext ctx) {
		declarationId = ctx.id.getText();
		return super.visitDeclaration(ctx);
	}

	@Override
	public Object visitVariableDeclaration(VariableDeclarationContext ctx) {
		ExpressionVisitor visitor = new ExpressionVisitor(model);
		Value value = visitor.visit(ctx.expression());
		model.addVariable(declarationId, value);
		return null;
	}

	@Override
	public Object visitLabelDeclaration(LabelDeclarationContext ctx) {
		model.newLabel(declarationId);
		return super.visitLabelDeclaration(ctx);
	}

	@Override
	public Object visitVoices(VoicesContext ctx) {
		List<Token> tokens = ctx.instrumentNames;
		String[] instruments = new String[tokens.size()];
		for (int i = 0; i < instruments.length; i++) {
			instruments[i] = tokens.get(i).getText();
		}
		model.setInstruments(instruments);
		return super.visitVoices(ctx);
	}

	@Override
	public Object visitBarline(BarlineContext ctx) {
		List<ExpressionOrReferenceContext> barExpressions = ctx.expressions;
		List<ExpressionOrReferenceContext> instrumentExpressions = getPerInstrument(barExpressions);

		List<Bar> bars = new ArrayList<>();
		for (ExpressionOrReferenceContext expressionContext : instrumentExpressions) {
			if (expressionContext != null) {
				if (expressionContext.same != null) {
					bars.add(InstrumentRepeatBar.lastOne());
				} else if (expressionContext.label != null) {
					int shift = 0;
					if (expressionContext.shiftAmount != null) {
						shift = Integer.parseInt(expressionContext.shiftAmount.getText());
						if (expressionContext.shiftSign.getType() == MJargonLexer.SILENCE) {
							shift = -shift;
						}
					}
					bars.add(InstrumentRepeatBar.labelReference(expressionContext.label.getText(), shift));
				} else if (expressionContext.plus != null) {
					bars.add(InstrumentRepeatBar.plusOne());
				} else {
					Value value = new ExpressionVisitor(model).visit(expressionContext.expr);
					bars.add(new InstrumentBar(value));
				}
			} else {
				bars.add(new SilenceBar());
			}
		}
		model.addBarline(bars.toArray(new Bar[bars.size()]));
		return null;
	}

	private static <T> List<T> getPerInstrument(List<T> barExpressions) {
		List<T> instrumentExpressions = new ArrayList<>();
		boolean expect = false;
		for (T expressionContext : barExpressions) {
			if (expect) {
				instrumentExpressions.add(expressionContext);
				if (expressionContext == null) {
					expect = true;
				} else {
					expect = false;
				}
			} else {
				expect = true;
			}

		}
		return instrumentExpressions;
	}

	public static Value[] getValues(Model model, List<ExpressionContext> expressions) {
		Value[] values = new Value[expressions.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = new ExpressionVisitor(model).visit(expressions.get(i));
		}
		return values;
	}

}
