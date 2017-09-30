// Generated from MJargon.g4 by ANTLR 4.0

package org.fergonco.music.mjargon.antlr;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface MJargonListener extends ParseTreeListener {
	void enterExpression(MJargonParser.ExpressionContext ctx);
	void exitExpression(MJargonParser.ExpressionContext ctx);

	void enterReferenceExpression(MJargonParser.ReferenceExpressionContext ctx);
	void exitReferenceExpression(MJargonParser.ReferenceExpressionContext ctx);

	void enterBarline(MJargonParser.BarlineContext ctx);
	void exitBarline(MJargonParser.BarlineContext ctx);

	void enterLeftExpression(MJargonParser.LeftExpressionContext ctx);
	void exitLeftExpression(MJargonParser.LeftExpressionContext ctx);

	void enterVoices(MJargonParser.VoicesContext ctx);
	void exitVoices(MJargonParser.VoicesContext ctx);

	void enterTempo(MJargonParser.TempoContext ctx);
	void exitTempo(MJargonParser.TempoContext ctx);

	void enterDeclaration(MJargonParser.DeclarationContext ctx);
	void exitDeclaration(MJargonParser.DeclarationContext ctx);

	void enterNumericExpression(MJargonParser.NumericExpressionContext ctx);
	void exitNumericExpression(MJargonParser.NumericExpressionContext ctx);

	void enterRhythmExpression(MJargonParser.RhythmExpressionContext ctx);
	void exitRhythmExpression(MJargonParser.RhythmExpressionContext ctx);

	void enterScript(MJargonParser.ScriptContext ctx);
	void exitScript(MJargonParser.ScriptContext ctx);

	void enterVariableDeclaration(MJargonParser.VariableDeclarationContext ctx);
	void exitVariableDeclaration(MJargonParser.VariableDeclarationContext ctx);

	void enterScriptLine(MJargonParser.ScriptLineContext ctx);
	void exitScriptLine(MJargonParser.ScriptLineContext ctx);

	void enterDynamics(MJargonParser.DynamicsContext ctx);
	void exitDynamics(MJargonParser.DynamicsContext ctx);

	void enterPitchSequenceExpression(MJargonParser.PitchSequenceExpressionContext ctx);
	void exitPitchSequenceExpression(MJargonParser.PitchSequenceExpressionContext ctx);

	void enterStringLiteral(MJargonParser.StringLiteralContext ctx);
	void exitStringLiteral(MJargonParser.StringLiteralContext ctx);

	void enterRepeat(MJargonParser.RepeatContext ctx);
	void exitRepeat(MJargonParser.RepeatContext ctx);

	void enterComment(MJargonParser.CommentContext ctx);
	void exitComment(MJargonParser.CommentContext ctx);

	void enterLabelDeclaration(MJargonParser.LabelDeclarationContext ctx);
	void exitLabelDeclaration(MJargonParser.LabelDeclarationContext ctx);

	void enterDrumSequenceExpression(MJargonParser.DrumSequenceExpressionContext ctx);
	void exitDrumSequenceExpression(MJargonParser.DrumSequenceExpressionContext ctx);
}