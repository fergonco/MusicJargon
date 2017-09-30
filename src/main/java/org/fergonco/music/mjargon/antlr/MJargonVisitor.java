// Generated from MJargon.g4 by ANTLR 4.0

package org.fergonco.music.mjargon.antlr;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface MJargonVisitor<T> extends ParseTreeVisitor<T> {
	T visitExpression(MJargonParser.ExpressionContext ctx);

	T visitReferenceExpression(MJargonParser.ReferenceExpressionContext ctx);

	T visitBarline(MJargonParser.BarlineContext ctx);

	T visitLeftExpression(MJargonParser.LeftExpressionContext ctx);

	T visitVoices(MJargonParser.VoicesContext ctx);

	T visitTempo(MJargonParser.TempoContext ctx);

	T visitDeclaration(MJargonParser.DeclarationContext ctx);

	T visitNumericExpression(MJargonParser.NumericExpressionContext ctx);

	T visitRhythmExpression(MJargonParser.RhythmExpressionContext ctx);

	T visitScript(MJargonParser.ScriptContext ctx);

	T visitVariableDeclaration(MJargonParser.VariableDeclarationContext ctx);

	T visitScriptLine(MJargonParser.ScriptLineContext ctx);

	T visitDynamics(MJargonParser.DynamicsContext ctx);

	T visitPitchSequenceExpression(MJargonParser.PitchSequenceExpressionContext ctx);

	T visitStringLiteral(MJargonParser.StringLiteralContext ctx);

	T visitRepeat(MJargonParser.RepeatContext ctx);

	T visitComment(MJargonParser.CommentContext ctx);

	T visitLabelDeclaration(MJargonParser.LabelDeclarationContext ctx);

	T visitDrumSequenceExpression(MJargonParser.DrumSequenceExpressionContext ctx);
}