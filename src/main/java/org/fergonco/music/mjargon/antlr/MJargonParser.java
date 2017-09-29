// Generated from MJargon.g4 by ANTLR 4.0

package org.fergonco.music.mjargon.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MJargonParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NUMBER=1, RHYTHMEXPRESSION=2, SLASH=3, VERTICAL_BAR=4, COLON=5, EQUALS=6, 
		OPEN_BRACE=7, CLOSE_BRACE=8, OPEN_PARENTHESIS=9, CLOSE_PARENTHESIS=10, 
		UNDERSCORE=11, COMMENT=12, ON=13, TEMPO=14, REPEAT=15, VOICES=16, DYNAMICS=17, 
		DYNAMIC=18, DRUM_INSTRUMENT=19, CHORD_LITERAL=20, STRING_LITERAL=21, ID=22, 
		EOL=23, TS=24;
	public static final String[] tokenNames = {
		"<INVALID>", "NUMBER", "RHYTHMEXPRESSION", "'/'", "'|'", "':'", "'='", 
		"OPEN_BRACE", "CLOSE_BRACE", "OPEN_PARENTHESIS", "CLOSE_PARENTHESIS", 
		"'_'", "COMMENT", "'on'", "'tempo'", "'repeat'", "'voices'", "'dynamics'", 
		"DYNAMIC", "DRUM_INSTRUMENT", "CHORD_LITERAL", "STRING_LITERAL", "ID", 
		"EOL", "TS"
	};
	public static final int
		RULE_script = 0, RULE_scriptLine = 1, RULE_comment = 2, RULE_declaration = 3, 
		RULE_variableDeclaration = 4, RULE_expression = 5, RULE_numericExpression = 6, 
		RULE_stringLiteral = 7, RULE_referenceExpression = 8, RULE_onRhythm = 9, 
		RULE_parameters = 10, RULE_rhythmExpression = 11, RULE_pitchSequenceExpression = 12, 
		RULE_drumSequenceExpression = 13, RULE_labelDeclaration = 14, RULE_barline = 15, 
		RULE_tempo = 16, RULE_voices = 17, RULE_dynamics = 18, RULE_repeat = 19;
	public static final String[] ruleNames = {
		"script", "scriptLine", "comment", "declaration", "variableDeclaration", 
		"expression", "numericExpression", "stringLiteral", "referenceExpression", 
		"onRhythm", "parameters", "rhythmExpression", "pitchSequenceExpression", 
		"drumSequenceExpression", "labelDeclaration", "barline", "tempo", "voices", 
		"dynamics", "repeat"
	};

	@Override
	public String getGrammarFileName() { return "MJargon.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public MJargonParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ScriptContext extends ParserRuleContext {
		public ScriptLineContext scriptLine;
		public List<ScriptLineContext> lines = new ArrayList<ScriptLineContext>();
		public List<ScriptLineContext> scriptLine() {
			return getRuleContexts(ScriptLineContext.class);
		}
		public TerminalNode EOF() { return getToken(MJargonParser.EOF, 0); }
		public ScriptLineContext scriptLine(int i) {
			return getRuleContext(ScriptLineContext.class,i);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitScript(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VERTICAL_BAR) | (1L << COMMENT) | (1L << TEMPO) | (1L << REPEAT) | (1L << VOICES) | (1L << DYNAMICS) | (1L << ID))) != 0)) {
				{
				{
				setState(40); ((ScriptContext)_localctx).scriptLine = scriptLine();
				((ScriptContext)_localctx).lines.add(((ScriptContext)_localctx).scriptLine);
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScriptLineContext extends ParserRuleContext {
		public DynamicsContext dynamics() {
			return getRuleContext(DynamicsContext.class,0);
		}
		public TerminalNode EOL() { return getToken(MJargonParser.EOL, 0); }
		public BarlineContext barline() {
			return getRuleContext(BarlineContext.class,0);
		}
		public RepeatContext repeat() {
			return getRuleContext(RepeatContext.class,0);
		}
		public VoicesContext voices() {
			return getRuleContext(VoicesContext.class,0);
		}
		public TempoContext tempo() {
			return getRuleContext(TempoContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public ScriptLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scriptLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterScriptLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitScriptLine(this);
		}
	}

	public final ScriptLineContext scriptLine() throws RecognitionException {
		ScriptLineContext _localctx = new ScriptLineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_scriptLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			switch (_input.LA(1)) {
			case COMMENT:
				{
				setState(48); comment();
				}
				break;
			case ID:
				{
				setState(49); declaration();
				}
				break;
			case VERTICAL_BAR:
				{
				setState(50); barline();
				}
				break;
			case TEMPO:
				{
				setState(51); tempo();
				}
				break;
			case VOICES:
				{
				setState(52); voices();
				}
				break;
			case DYNAMICS:
				{
				setState(53); dynamics();
				}
				break;
			case REPEAT:
				{
				setState(54); repeat();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(57); match(EOL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(MJargonParser.COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitComment(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59); match(COMMENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public Token id;
		public TerminalNode ID() { return getToken(MJargonParser.ID, 0); }
		public LabelDeclarationContext labelDeclaration() {
			return getRuleContext(LabelDeclarationContext.class,0);
		}
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); ((DeclarationContext)_localctx).id = match(ID);
			setState(64);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(62); variableDeclaration();
				}
				break;
			case COLON:
				{
				setState(63); labelDeclaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public ExpressionContext value;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(MJargonParser.EQUALS, 0); }
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitVariableDeclaration(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); match(EQUALS);
			setState(67); ((VariableDeclarationContext)_localctx).value = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public PitchSequenceExpressionContext pitchSequenceExpression() {
			return getRuleContext(PitchSequenceExpressionContext.class,0);
		}
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public ReferenceExpressionContext referenceExpression() {
			return getRuleContext(ReferenceExpressionContext.class,0);
		}
		public NumericExpressionContext numericExpression() {
			return getRuleContext(NumericExpressionContext.class,0);
		}
		public RhythmExpressionContext rhythmExpression() {
			return getRuleContext(RhythmExpressionContext.class,0);
		}
		public DrumSequenceExpressionContext drumSequenceExpression() {
			return getRuleContext(DrumSequenceExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				setState(69); numericExpression();
				}
				break;
			case STRING_LITERAL:
				{
				setState(70); stringLiteral();
				}
				break;
			case ID:
				{
				setState(71); referenceExpression();
				}
				break;
			case RHYTHMEXPRESSION:
				{
				setState(72); rhythmExpression();
				}
				break;
			case UNDERSCORE:
			case CHORD_LITERAL:
				{
				setState(73); pitchSequenceExpression();
				}
				break;
			case DRUM_INSTRUMENT:
				{
				setState(74); drumSequenceExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericExpressionContext extends ParserRuleContext {
		public Token numerator;
		public Token denominator;
		public List<TerminalNode> NUMBER() { return getTokens(MJargonParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(MJargonParser.NUMBER, i);
		}
		public TerminalNode SLASH() { return getToken(MJargonParser.SLASH, 0); }
		public NumericExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterNumericExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitNumericExpression(this);
		}
	}

	public final NumericExpressionContext numericExpression() throws RecognitionException {
		NumericExpressionContext _localctx = new NumericExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_numericExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77); ((NumericExpressionContext)_localctx).numerator = match(NUMBER);
			setState(80);
			_la = _input.LA(1);
			if (_la==SLASH) {
				{
				setState(78); match(SLASH);
				setState(79); ((NumericExpressionContext)_localctx).denominator = match(NUMBER);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringLiteralContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(MJargonParser.STRING_LITERAL, 0); }
		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitStringLiteral(this);
		}
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); match(STRING_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReferenceExpressionContext extends ParserRuleContext {
		public Token id;
		public OnRhythmContext onRhythm() {
			return getRuleContext(OnRhythmContext.class,0);
		}
		public TerminalNode ID() { return getToken(MJargonParser.ID, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public ReferenceExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_referenceExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterReferenceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitReferenceExpression(this);
		}
	}

	public final ReferenceExpressionContext referenceExpression() throws RecognitionException {
		ReferenceExpressionContext _localctx = new ReferenceExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_referenceExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); ((ReferenceExpressionContext)_localctx).id = match(ID);
			setState(88);
			switch (_input.LA(1)) {
			case ON:
				{
				setState(85); onRhythm();
				}
				break;
			case OPEN_PARENTHESIS:
				{
				setState(86); parameters();
				}
				break;
			case VERTICAL_BAR:
			case CLOSE_PARENTHESIS:
			case EOL:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OnRhythmContext extends ParserRuleContext {
		public ExpressionContext rhythm;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ON() { return getToken(MJargonParser.ON, 0); }
		public OnRhythmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onRhythm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterOnRhythm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitOnRhythm(this);
		}
	}

	public final OnRhythmContext onRhythm() throws RecognitionException {
		OnRhythmContext _localctx = new OnRhythmContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_onRhythm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90); match(ON);
			setState(91); ((OnRhythmContext)_localctx).rhythm = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParametersContext extends ParserRuleContext {
		public ExpressionContext expression;
		public List<ExpressionContext> parameterValues = new ArrayList<ExpressionContext>();
		public TerminalNode OPEN_PARENTHESIS() { return getToken(MJargonParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(MJargonParser.CLOSE_PARENTHESIS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitParameters(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_parameters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93); match(OPEN_PARENTHESIS);
			setState(94); ((ParametersContext)_localctx).expression = expression();
			((ParametersContext)_localctx).parameterValues.add(((ParametersContext)_localctx).expression);
			setState(95); match(CLOSE_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RhythmExpressionContext extends ParserRuleContext {
		public Token value;
		public ExpressionContext timeSignature;
		public TerminalNode RHYTHMEXPRESSION() { return getToken(MJargonParser.RHYTHMEXPRESSION, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ON() { return getToken(MJargonParser.ON, 0); }
		public RhythmExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rhythmExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterRhythmExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitRhythmExpression(this);
		}
	}

	public final RhythmExpressionContext rhythmExpression() throws RecognitionException {
		RhythmExpressionContext _localctx = new RhythmExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_rhythmExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97); ((RhythmExpressionContext)_localctx).value = match(RHYTHMEXPRESSION);
			setState(98); match(ON);
			setState(99); ((RhythmExpressionContext)_localctx).timeSignature = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PitchSequenceExpressionContext extends ParserRuleContext {
		public Token CHORD_LITERAL;
		public List<Token> notes = new ArrayList<Token>();
		public Token UNDERSCORE;
		public List<TerminalNode> UNDERSCORE() { return getTokens(MJargonParser.UNDERSCORE); }
		public TerminalNode UNDERSCORE(int i) {
			return getToken(MJargonParser.UNDERSCORE, i);
		}
		public TerminalNode CHORD_LITERAL(int i) {
			return getToken(MJargonParser.CHORD_LITERAL, i);
		}
		public List<TerminalNode> CHORD_LITERAL() { return getTokens(MJargonParser.CHORD_LITERAL); }
		public PitchSequenceExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pitchSequenceExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterPitchSequenceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitPitchSequenceExpression(this);
		}
	}

	public final PitchSequenceExpressionContext pitchSequenceExpression() throws RecognitionException {
		PitchSequenceExpressionContext _localctx = new PitchSequenceExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pitchSequenceExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(103);
				switch (_input.LA(1)) {
				case CHORD_LITERAL:
					{
					setState(101); ((PitchSequenceExpressionContext)_localctx).CHORD_LITERAL = match(CHORD_LITERAL);
					((PitchSequenceExpressionContext)_localctx).notes.add(((PitchSequenceExpressionContext)_localctx).CHORD_LITERAL);
					}
					break;
				case UNDERSCORE:
					{
					setState(102); ((PitchSequenceExpressionContext)_localctx).UNDERSCORE = match(UNDERSCORE);
					((PitchSequenceExpressionContext)_localctx).notes.add(((PitchSequenceExpressionContext)_localctx).UNDERSCORE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(105); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==UNDERSCORE || _la==CHORD_LITERAL );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DrumSequenceExpressionContext extends ParserRuleContext {
		public Token DRUM_INSTRUMENT;
		public List<Token> instruments = new ArrayList<Token>();
		public TerminalNode DRUM_INSTRUMENT(int i) {
			return getToken(MJargonParser.DRUM_INSTRUMENT, i);
		}
		public List<TerminalNode> DRUM_INSTRUMENT() { return getTokens(MJargonParser.DRUM_INSTRUMENT); }
		public DrumSequenceExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drumSequenceExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterDrumSequenceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitDrumSequenceExpression(this);
		}
	}

	public final DrumSequenceExpressionContext drumSequenceExpression() throws RecognitionException {
		DrumSequenceExpressionContext _localctx = new DrumSequenceExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_drumSequenceExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(107); ((DrumSequenceExpressionContext)_localctx).DRUM_INSTRUMENT = match(DRUM_INSTRUMENT);
				((DrumSequenceExpressionContext)_localctx).instruments.add(((DrumSequenceExpressionContext)_localctx).DRUM_INSTRUMENT);
				}
				}
				setState(110); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DRUM_INSTRUMENT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelDeclarationContext extends ParserRuleContext {
		public TerminalNode VERTICAL_BAR(int i) {
			return getToken(MJargonParser.VERTICAL_BAR, i);
		}
		public List<TerminalNode> VERTICAL_BAR() { return getTokens(MJargonParser.VERTICAL_BAR); }
		public TerminalNode COLON() { return getToken(MJargonParser.COLON, 0); }
		public LabelDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterLabelDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitLabelDeclaration(this);
		}
	}

	public final LabelDeclarationContext labelDeclaration() throws RecognitionException {
		LabelDeclarationContext _localctx = new LabelDeclarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_labelDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); match(COLON);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VERTICAL_BAR) {
				{
				{
				setState(113); match(VERTICAL_BAR);
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BarlineContext extends ParserRuleContext {
		public ExpressionContext expression;
		public List<ExpressionContext> expressions = new ArrayList<ExpressionContext>();
		public TerminalNode VERTICAL_BAR(int i) {
			return getToken(MJargonParser.VERTICAL_BAR, i);
		}
		public List<TerminalNode> VERTICAL_BAR() { return getTokens(MJargonParser.VERTICAL_BAR); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BarlineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_barline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterBarline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitBarline(this);
		}
	}

	public final BarlineContext barline() throws RecognitionException {
		BarlineContext _localctx = new BarlineContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_barline);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(121); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(119); match(VERTICAL_BAR);
					setState(120); ((BarlineContext)_localctx).expression = expression();
					((BarlineContext)_localctx).expressions.add(((BarlineContext)_localctx).expression);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(123); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			setState(126);
			_la = _input.LA(1);
			if (_la==VERTICAL_BAR) {
				{
				setState(125); match(VERTICAL_BAR);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TempoContext extends ParserRuleContext {
		public Token bpmOrNumerator;
		public Token denominator;
		public Token bpm;
		public List<TerminalNode> NUMBER() { return getTokens(MJargonParser.NUMBER); }
		public TerminalNode EQUALS() { return getToken(MJargonParser.EQUALS, 0); }
		public TerminalNode NUMBER(int i) {
			return getToken(MJargonParser.NUMBER, i);
		}
		public TerminalNode SLASH() { return getToken(MJargonParser.SLASH, 0); }
		public TerminalNode TEMPO() { return getToken(MJargonParser.TEMPO, 0); }
		public TempoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tempo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterTempo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitTempo(this);
		}
	}

	public final TempoContext tempo() throws RecognitionException {
		TempoContext _localctx = new TempoContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_tempo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); match(TEMPO);
			setState(129); ((TempoContext)_localctx).bpmOrNumerator = match(NUMBER);
			setState(134);
			_la = _input.LA(1);
			if (_la==SLASH) {
				{
				setState(130); match(SLASH);
				setState(131); ((TempoContext)_localctx).denominator = match(NUMBER);
				setState(132); match(EQUALS);
				setState(133); ((TempoContext)_localctx).bpm = match(NUMBER);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VoicesContext extends ParserRuleContext {
		public Token ID;
		public List<Token> instrumentNames = new ArrayList<Token>();
		public TerminalNode VERTICAL_BAR(int i) {
			return getToken(MJargonParser.VERTICAL_BAR, i);
		}
		public List<TerminalNode> VERTICAL_BAR() { return getTokens(MJargonParser.VERTICAL_BAR); }
		public List<TerminalNode> STRING_LITERAL() { return getTokens(MJargonParser.STRING_LITERAL); }
		public List<TerminalNode> ID() { return getTokens(MJargonParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(MJargonParser.ID, i);
		}
		public TerminalNode STRING_LITERAL(int i) {
			return getToken(MJargonParser.STRING_LITERAL, i);
		}
		public TerminalNode VOICES() { return getToken(MJargonParser.VOICES, 0); }
		public VoicesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_voices; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterVoices(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitVoices(this);
		}
	}

	public final VoicesContext voices() throws RecognitionException {
		VoicesContext _localctx = new VoicesContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_voices);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(136); match(VOICES);
			setState(142); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(137); match(VERTICAL_BAR);
					setState(138); ((VoicesContext)_localctx).ID = match(ID);
					((VoicesContext)_localctx).instrumentNames.add(((VoicesContext)_localctx).ID);
					setState(140);
					_la = _input.LA(1);
					if (_la==STRING_LITERAL) {
						{
						setState(139); match(STRING_LITERAL);
						}
					}

					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(144); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			setState(147);
			_la = _input.LA(1);
			if (_la==VERTICAL_BAR) {
				{
				setState(146); match(VERTICAL_BAR);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DynamicsContext extends ParserRuleContext {
		public Token DYNAMIC;
		public List<Token> dynamicCodes = new ArrayList<Token>();
		public TerminalNode VERTICAL_BAR() { return getToken(MJargonParser.VERTICAL_BAR, 0); }
		public TerminalNode DYNAMICS() { return getToken(MJargonParser.DYNAMICS, 0); }
		public TerminalNode DYNAMIC() { return getToken(MJargonParser.DYNAMIC, 0); }
		public DynamicsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dynamics; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterDynamics(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitDynamics(this);
		}
	}

	public final DynamicsContext dynamics() throws RecognitionException {
		DynamicsContext _localctx = new DynamicsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_dynamics);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149); match(DYNAMICS);
			{
			setState(150); match(VERTICAL_BAR);
			setState(152);
			_la = _input.LA(1);
			if (_la==DYNAMIC) {
				{
				setState(151); ((DynamicsContext)_localctx).DYNAMIC = match(DYNAMIC);
				((DynamicsContext)_localctx).dynamicCodes.add(((DynamicsContext)_localctx).DYNAMIC);
				}
			}

			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepeatContext extends ParserRuleContext {
		public Token labelId;
		public Token times;
		public TerminalNode NUMBER() { return getToken(MJargonParser.NUMBER, 0); }
		public TerminalNode VERTICAL_BAR(int i) {
			return getToken(MJargonParser.VERTICAL_BAR, i);
		}
		public List<TerminalNode> VERTICAL_BAR() { return getTokens(MJargonParser.VERTICAL_BAR); }
		public TerminalNode REPEAT() { return getToken(MJargonParser.REPEAT, 0); }
		public TerminalNode ID() { return getToken(MJargonParser.ID, 0); }
		public RepeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterRepeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitRepeat(this);
		}
	}

	public final RepeatContext repeat() throws RecognitionException {
		RepeatContext _localctx = new RepeatContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_repeat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); match(REPEAT);
			setState(155); ((RepeatContext)_localctx).labelId = match(ID);
			setState(156); ((RepeatContext)_localctx).times = match(NUMBER);
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VERTICAL_BAR) {
				{
				{
				setState(157); match(VERTICAL_BAR);
				}
				}
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3\32\u00a6\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t"+
		"\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\7\2,\n\2\f\2"+
		"\16\2/\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3:\n\3\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\5\5\5C\n\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7N\n\7\3"+
		"\b\3\b\3\b\5\bS\n\b\3\t\3\t\3\n\3\n\3\n\3\n\5\n[\n\n\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\6\16j\n\16\r\16\16\16k\3\17\6"+
		"\17o\n\17\r\17\16\17p\3\20\3\20\7\20u\n\20\f\20\16\20x\13\20\3\21\3\21"+
		"\6\21|\n\21\r\21\16\21}\3\21\5\21\u0081\n\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\5\22\u0089\n\22\3\23\3\23\3\23\3\23\5\23\u008f\n\23\6\23\u0091\n"+
		"\23\r\23\16\23\u0092\3\23\5\23\u0096\n\23\3\24\3\24\3\24\5\24\u009b\n"+
		"\24\3\25\3\25\3\25\3\25\7\25\u00a1\n\25\f\25\16\25\u00a4\13\25\3\25\2"+
		"\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\2\u00ad\2-\3\2\2\2"+
		"\49\3\2\2\2\6=\3\2\2\2\b?\3\2\2\2\nD\3\2\2\2\fM\3\2\2\2\16O\3\2\2\2\20"+
		"T\3\2\2\2\22V\3\2\2\2\24\\\3\2\2\2\26_\3\2\2\2\30c\3\2\2\2\32i\3\2\2\2"+
		"\34n\3\2\2\2\36r\3\2\2\2 {\3\2\2\2\"\u0082\3\2\2\2$\u008a\3\2\2\2&\u0097"+
		"\3\2\2\2(\u009c\3\2\2\2*,\5\4\3\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2"+
		"\2\2.\60\3\2\2\2/-\3\2\2\2\60\61\7\1\2\2\61\3\3\2\2\2\62:\5\6\4\2\63:"+
		"\5\b\5\2\64:\5 \21\2\65:\5\"\22\2\66:\5$\23\2\67:\5&\24\28:\5(\25\29\62"+
		"\3\2\2\29\63\3\2\2\29\64\3\2\2\29\65\3\2\2\29\66\3\2\2\29\67\3\2\2\29"+
		"8\3\2\2\2:;\3\2\2\2;<\7\31\2\2<\5\3\2\2\2=>\7\16\2\2>\7\3\2\2\2?B\7\30"+
		"\2\2@C\5\n\6\2AC\5\36\20\2B@\3\2\2\2BA\3\2\2\2C\t\3\2\2\2DE\7\b\2\2EF"+
		"\5\f\7\2F\13\3\2\2\2GN\5\16\b\2HN\5\20\t\2IN\5\22\n\2JN\5\30\r\2KN\5\32"+
		"\16\2LN\5\34\17\2MG\3\2\2\2MH\3\2\2\2MI\3\2\2\2MJ\3\2\2\2MK\3\2\2\2ML"+
		"\3\2\2\2N\r\3\2\2\2OR\7\3\2\2PQ\7\5\2\2QS\7\3\2\2RP\3\2\2\2RS\3\2\2\2"+
		"S\17\3\2\2\2TU\7\27\2\2U\21\3\2\2\2VZ\7\30\2\2W[\5\24\13\2X[\5\26\f\2"+
		"Y[\3\2\2\2ZW\3\2\2\2ZX\3\2\2\2ZY\3\2\2\2[\23\3\2\2\2\\]\7\17\2\2]^\5\f"+
		"\7\2^\25\3\2\2\2_`\7\13\2\2`a\5\f\7\2ab\7\f\2\2b\27\3\2\2\2cd\7\4\2\2"+
		"de\7\17\2\2ef\5\f\7\2f\31\3\2\2\2gj\7\26\2\2hj\7\r\2\2ig\3\2\2\2ih\3\2"+
		"\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2l\33\3\2\2\2mo\7\25\2\2nm\3\2\2\2op"+
		"\3\2\2\2pn\3\2\2\2pq\3\2\2\2q\35\3\2\2\2rv\7\7\2\2su\7\6\2\2ts\3\2\2\2"+
		"ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\37\3\2\2\2xv\3\2\2\2yz\7\6\2\2z|\5\f\7"+
		"\2{y\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u0080\3\2\2\2\177\u0081\7"+
		"\6\2\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081!\3\2\2\2\u0082\u0083"+
		"\7\20\2\2\u0083\u0088\7\3\2\2\u0084\u0085\7\5\2\2\u0085\u0086\7\3\2\2"+
		"\u0086\u0087\7\b\2\2\u0087\u0089\7\3\2\2\u0088\u0084\3\2\2\2\u0088\u0089"+
		"\3\2\2\2\u0089#\3\2\2\2\u008a\u0090\7\22\2\2\u008b\u008c\7\6\2\2\u008c"+
		"\u008e\7\30\2\2\u008d\u008f\7\27\2\2\u008e\u008d\3\2\2\2\u008e\u008f\3"+
		"\2\2\2\u008f\u0091\3\2\2\2\u0090\u008b\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0096\7\6"+
		"\2\2\u0095\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096%\3\2\2\2\u0097\u0098"+
		"\7\23\2\2\u0098\u009a\7\6\2\2\u0099\u009b\7\24\2\2\u009a\u0099\3\2\2\2"+
		"\u009a\u009b\3\2\2\2\u009b\'\3\2\2\2\u009c\u009d\7\21\2\2\u009d\u009e"+
		"\7\30\2\2\u009e\u00a2\7\3\2\2\u009f\u00a1\7\6\2\2\u00a0\u009f\3\2\2\2"+
		"\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3)\3"+
		"\2\2\2\u00a4\u00a2\3\2\2\2\24-9BMRZikpv}\u0080\u0088\u008e\u0092\u0095"+
		"\u009a\u00a2";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}