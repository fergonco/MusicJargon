package org.fergonco.music.mjargon.antlr;

import static org.fergonco.music.mjargon.lexer.Lexer.BASSDRUM;
import static org.fergonco.music.mjargon.lexer.Lexer.BD;
import static org.fergonco.music.mjargon.lexer.Lexer.CR;
import static org.fergonco.music.mjargon.lexer.Lexer.CRASH;
import static org.fergonco.music.mjargon.lexer.Lexer.HH;
import static org.fergonco.music.mjargon.lexer.Lexer.HHO;
import static org.fergonco.music.mjargon.lexer.Lexer.HHP;
import static org.fergonco.music.mjargon.lexer.Lexer.HIHAT;
import static org.fergonco.music.mjargon.lexer.Lexer.HIHATOPEN;
import static org.fergonco.music.mjargon.lexer.Lexer.HIHATPEDAL;
import static org.fergonco.music.mjargon.lexer.Lexer.RD;
import static org.fergonco.music.mjargon.lexer.Lexer.RIDE;
import static org.fergonco.music.mjargon.lexer.Lexer.SN;
import static org.fergonco.music.mjargon.lexer.Lexer.SNARE;
import static org.fergonco.music.mjargon.lexer.Lexer.T1;
import static org.fergonco.music.mjargon.lexer.Lexer.T2;
import static org.fergonco.music.mjargon.lexer.Lexer.T3;
import static org.fergonco.music.mjargon.lexer.Lexer.T4;
import static org.fergonco.music.mjargon.lexer.Lexer.T5;
import static org.fergonco.music.mjargon.lexer.Lexer.T6;
import static org.fergonco.music.mjargon.lexer.Lexer.TOM1;
import static org.fergonco.music.mjargon.lexer.Lexer.TOM2;
import static org.fergonco.music.mjargon.lexer.Lexer.TOM3;
import static org.fergonco.music.mjargon.lexer.Lexer.TOM4;
import static org.fergonco.music.mjargon.lexer.Lexer.TOM5;
import static org.fergonco.music.mjargon.lexer.Lexer.TOM6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.fergonco.music.midi.Dynamic;
import org.fergonco.music.mjargon.antlr.MJargonParser.DeclarationContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.DynamicsContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.LabelDeclarationContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.RepeatContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.TempoContext;
import org.fergonco.music.mjargon.antlr.MJargonParser.VariableDeclarationContext;
import org.fergonco.music.mjargon.model.DrumNote;
import org.fergonco.music.mjargon.model.Model;
import org.fergonco.music.mjargon.model.SemanticException;

public class AntlrTest {

	private static Map<Integer, DrumNote> drumInstrumentCodes = new HashMap<>();
	private static Map<String, Dynamic> dynamics = new HashMap<>();

	static {
		drumInstrumentCodes.put(HIHAT, DrumNote.HIHAT);
		drumInstrumentCodes.put(HH, DrumNote.HIHAT);
		drumInstrumentCodes.put(HIHATOPEN, DrumNote.HIHATOPEN);
		drumInstrumentCodes.put(HHO, DrumNote.HIHATOPEN);
		drumInstrumentCodes.put(HIHATPEDAL, DrumNote.HIHATPEDAL);
		drumInstrumentCodes.put(HHP, DrumNote.HIHATPEDAL);
		drumInstrumentCodes.put(BASSDRUM, DrumNote.BASSDRUM);
		drumInstrumentCodes.put(BD, DrumNote.BASSDRUM);
		drumInstrumentCodes.put(SNARE, DrumNote.SNARE);
		drumInstrumentCodes.put(SN, DrumNote.SNARE);
		drumInstrumentCodes.put(RIDE, DrumNote.RIDE);
		drumInstrumentCodes.put(RD, DrumNote.RIDE);
		drumInstrumentCodes.put(CRASH, DrumNote.CRASH);
		drumInstrumentCodes.put(CR, DrumNote.CRASH);
		drumInstrumentCodes.put(TOM1, DrumNote.TOM1);
		drumInstrumentCodes.put(T1, DrumNote.TOM1);
		drumInstrumentCodes.put(TOM2, DrumNote.TOM2);
		drumInstrumentCodes.put(T2, DrumNote.TOM2);
		drumInstrumentCodes.put(TOM3, DrumNote.TOM3);
		drumInstrumentCodes.put(T3, DrumNote.TOM3);
		drumInstrumentCodes.put(TOM4, DrumNote.TOM4);
		drumInstrumentCodes.put(T4, DrumNote.TOM4);
		drumInstrumentCodes.put(TOM5, DrumNote.TOM5);
		drumInstrumentCodes.put(T5, DrumNote.TOM5);
		drumInstrumentCodes.put(TOM6, DrumNote.TOM6);
		drumInstrumentCodes.put(T6, DrumNote.TOM6);

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

	public Model compile(String script) {
		MJargonLexer lexer = new MJargonLexer(new ANTLRInputStream(script));
		MJargonParser parser = new MJargonParser(new CommonTokenStream(lexer));
		parser.addParseListener(new MJargonBaseListener() {
			private String declarationId;

			@Override
			public void exitDynamics(DynamicsContext ctx) {
				ArrayList<Dynamic> dynamicList = new ArrayList<>();
				List<Token> dynamicTokens = ctx.dynamicCodes;
				boolean expectDynamic = false;
				for (Token dynamicToken : dynamicTokens) {
					if (expectDynamic) {
						if (dynamicToken.getType() == MJargonLexer.VERTICAL_BAR) {
							dynamicList.add(null);
							expectDynamic = true;
						} else {
							dynamicList.add(dynamics.get(dynamicToken.getText()));
							expectDynamic = false;
						}
					} else {
						expectDynamic = true;
					}
				}
				model.setDynamics(dynamicList);
			}

			@Override
			public void exitTempo(TempoContext ctx) {
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
			}

			@Override
			public void exitRepeat(RepeatContext ctx) {
				String label = ctx.labelId.getText();
				int times = Integer.parseInt(ctx.times.getText());
				try {
					model.repeat(label, times);
				} catch (SemanticException e) {
					registerError(e, ctx.getStart().getLine());
				}
			}

			@Override
			public void enterDeclaration(DeclarationContext ctx) {
				declarationId = ctx.id.getText();
			}

			@Override
			public void exitVariableDeclaration(VariableDeclarationContext ctx) {
				model.addVariable(declarationId, ctx.value);
			}

			@Override
			public void exitLabelDeclaration(LabelDeclarationContext ctx) {
				model.newLabel(declarationId);
			}
		});
		model = new Model();
		parser.script();
		return model;
	}

	private void registerError(SemanticException e, int line) {
		System.out.println("Error at line: " + line);
		e.printStackTrace();
	}

}