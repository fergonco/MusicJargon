// Generated from MJargon.g4 by ANTLR 4.0

package org.fergonco.music.mjargon.antlr;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;

public class MJargonBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements MJargonVisitor<T> {
	@Override public T visitExpression(MJargonParser.ExpressionContext ctx) { return visitChildren(ctx); }

	@Override public T visitReferenceExpression(MJargonParser.ReferenceExpressionContext ctx) { return visitChildren(ctx); }

	@Override public T visitBarline(MJargonParser.BarlineContext ctx) { return visitChildren(ctx); }

	@Override public T visitLeftExpression(MJargonParser.LeftExpressionContext ctx) { return visitChildren(ctx); }

	@Override public T visitVoices(MJargonParser.VoicesContext ctx) { return visitChildren(ctx); }

	@Override public T visitTempo(MJargonParser.TempoContext ctx) { return visitChildren(ctx); }

	@Override public T visitDeclaration(MJargonParser.DeclarationContext ctx) { return visitChildren(ctx); }

	@Override public T visitNumericExpression(MJargonParser.NumericExpressionContext ctx) { return visitChildren(ctx); }

	@Override public T visitRhythmExpression(MJargonParser.RhythmExpressionContext ctx) { return visitChildren(ctx); }

	@Override public T visitScript(MJargonParser.ScriptContext ctx) { return visitChildren(ctx); }

	@Override public T visitVariableDeclaration(MJargonParser.VariableDeclarationContext ctx) { return visitChildren(ctx); }

	@Override public T visitScriptLine(MJargonParser.ScriptLineContext ctx) { return visitChildren(ctx); }

	@Override public T visitDynamics(MJargonParser.DynamicsContext ctx) { return visitChildren(ctx); }

	@Override public T visitPitchSequenceExpression(MJargonParser.PitchSequenceExpressionContext ctx) { return visitChildren(ctx); }

	@Override public T visitStringLiteral(MJargonParser.StringLiteralContext ctx) { return visitChildren(ctx); }

	@Override public T visitRepeat(MJargonParser.RepeatContext ctx) { return visitChildren(ctx); }

	@Override public T visitComment(MJargonParser.CommentContext ctx) { return visitChildren(ctx); }

	@Override public T visitLabelDeclaration(MJargonParser.LabelDeclarationContext ctx) { return visitChildren(ctx); }

	@Override public T visitDrumSequenceExpression(MJargonParser.DrumSequenceExpressionContext ctx) { return visitChildren(ctx); }
}