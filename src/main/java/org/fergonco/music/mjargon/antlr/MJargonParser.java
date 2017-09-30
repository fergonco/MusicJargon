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
		NUMBER=1, RHYTHMEXPRESSION=2, SLASH=3, VERTICAL_BAR=4, COLON=5, COMA=6, 
		EQUALS=7, OPEN_BRACE=8, CLOSE_BRACE=9, OPEN_PARENTHESIS=10, CLOSE_PARENTHESIS=11, 
		UNDERSCORE=12, COMMENT=13, ON=14, TEMPO=15, REPEAT=16, VOICES=17, DYNAMICS=18, 
		DYNAMIC=19, HIHAT=20, HH=21, HIHATOPEN=22, HHO=23, HIHATPEDAL=24, HHP=25, 
		BASSDRUM=26, BD=27, SNARE=28, SN=29, RIDE=30, RD=31, CRASH=32, CR=33, 
		TOM1=34, T1=35, TOM2=36, T2=37, TOM3=38, T3=39, TOM4=40, T4=41, TOM5=42, 
		T5=43, TOM6=44, T6=45, DRUM_INSTRUMENT=46, CHORD_LITERAL=47, STRING_LITERAL=48, 
		ID=49, EOL=50, TS=51;
	public static final String[] tokenNames = {
		"<INVALID>", "NUMBER", "RHYTHMEXPRESSION", "'/'", "'|'", "':'", "','", 
		"'='", "'{'", "'}'", "'('", "')'", "'_'", "COMMENT", "'on'", "'tempo'", 
		"'repeat'", "'voices'", "'dynamics'", "DYNAMIC", "'hihat'", "'hh'", "'hihatopen'", 
		"'hho'", "'hihatpedal'", "'hhp'", "'bassdrum'", "'bd'", "'snare'", "'sn'", 
		"'ride'", "'rd'", "'crash'", "'cr'", "'tom1'", "'t1'", "'tom2'", "'t2'", 
		"'tom3'", "'t3'", "'tom4'", "'t4'", "'tom5'", "'t5'", "'tom6'", "'t6'", 
		"DRUM_INSTRUMENT", "CHORD_LITERAL", "STRING_LITERAL", "ID", "EOL", "TS"
	};
	public static final int
		RULE_script = 0, RULE_scriptLine = 1, RULE_comment = 2, RULE_declaration = 3, 
		RULE_variableDeclaration = 4, RULE_expression = 5, RULE_leftExpression = 6, 
		RULE_numericExpression = 7, RULE_stringLiteral = 8, RULE_referenceExpression = 9, 
		RULE_rhythmExpression = 10, RULE_pitchSequenceExpression = 11, RULE_drumSequenceExpression = 12, 
		RULE_labelDeclaration = 13, RULE_barline = 14, RULE_tempo = 15, RULE_voices = 16, 
		RULE_dynamics = 17, RULE_repeat = 18;
	public static final String[] ruleNames = {
		"script", "scriptLine", "comment", "declaration", "variableDeclaration", 
		"expression", "leftExpression", "numericExpression", "stringLiteral", 
		"referenceExpression", "rhythmExpression", "pitchSequenceExpression", 
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitScript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VERTICAL_BAR) | (1L << COMMENT) | (1L << TEMPO) | (1L << REPEAT) | (1L << VOICES) | (1L << DYNAMICS) | (1L << ID) | (1L << EOL))) != 0)) {
				{
				{
				setState(38); ((ScriptContext)_localctx).scriptLine = scriptLine();
				((ScriptContext)_localctx).lines.add(((ScriptContext)_localctx).scriptLine);
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(44); match(EOF);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitScriptLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptLineContext scriptLine() throws RecognitionException {
		ScriptLineContext _localctx = new ScriptLineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_scriptLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			switch (_input.LA(1)) {
			case COMMENT:
				{
				setState(46); comment();
				}
				break;
			case ID:
				{
				setState(47); declaration();
				}
				break;
			case VERTICAL_BAR:
				{
				setState(48); barline();
				}
				break;
			case TEMPO:
				{
				setState(49); tempo();
				}
				break;
			case VOICES:
				{
				setState(50); voices();
				}
				break;
			case DYNAMICS:
				{
				setState(51); dynamics();
				}
				break;
			case REPEAT:
				{
				setState(52); repeat();
				}
				break;
			case EOL:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(56); match(EOL);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58); match(COMMENT);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); ((DeclarationContext)_localctx).id = match(ID);
			setState(63);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(61); variableDeclaration();
				}
				break;
			case COLON:
				{
				setState(62); labelDeclaration();
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65); match(EQUALS);
			setState(66); ((VariableDeclarationContext)_localctx).value = expression();
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
		public LeftExpressionContext left;
		public Token index;
		public Token colon;
		public Token toIndex;
		public ExpressionContext rhythm;
		public List<TerminalNode> NUMBER() { return getTokens(MJargonParser.NUMBER); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NUMBER(int i) {
			return getToken(MJargonParser.NUMBER, i);
		}
		public TerminalNode OPEN_BRACE() { return getToken(MJargonParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(MJargonParser.CLOSE_BRACE, 0); }
		public LeftExpressionContext leftExpression() {
			return getRuleContext(LeftExpressionContext.class,0);
		}
		public TerminalNode COLON() { return getToken(MJargonParser.COLON, 0); }
		public TerminalNode ON() { return getToken(MJargonParser.ON, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68); ((ExpressionContext)_localctx).left = leftExpression();
			setState(78);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(69); match(OPEN_BRACE);
				setState(70); ((ExpressionContext)_localctx).index = match(NUMBER);
				setState(75);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(71); ((ExpressionContext)_localctx).colon = match(COLON);
					setState(73);
					_la = _input.LA(1);
					if (_la==NUMBER) {
						{
						setState(72); ((ExpressionContext)_localctx).toIndex = match(NUMBER);
						}
					}

					}
				}

				setState(77); match(CLOSE_BRACE);
				}
				break;
			}
			setState(82);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(80); match(ON);
				setState(81); ((ExpressionContext)_localctx).rhythm = expression();
				}
				break;
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

	public static class LeftExpressionContext extends ParserRuleContext {
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
		public LeftExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).enterLeftExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MJargonListener ) ((MJargonListener)listener).exitLeftExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitLeftExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftExpressionContext leftExpression() throws RecognitionException {
		LeftExpressionContext _localctx = new LeftExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_leftExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				setState(84); numericExpression();
				}
				break;
			case STRING_LITERAL:
				{
				setState(85); stringLiteral();
				}
				break;
			case ID:
				{
				setState(86); referenceExpression();
				}
				break;
			case RHYTHMEXPRESSION:
				{
				setState(87); rhythmExpression();
				}
				break;
			case UNDERSCORE:
			case CHORD_LITERAL:
				{
				setState(88); pitchSequenceExpression();
				}
				break;
			case DRUM_INSTRUMENT:
				{
				setState(89); drumSequenceExpression();
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitNumericExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericExpressionContext numericExpression() throws RecognitionException {
		NumericExpressionContext _localctx = new NumericExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_numericExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); ((NumericExpressionContext)_localctx).numerator = match(NUMBER);
			setState(95);
			_la = _input.LA(1);
			if (_la==SLASH) {
				{
				setState(93); match(SLASH);
				setState(94); ((NumericExpressionContext)_localctx).denominator = match(NUMBER);
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
		public Token text;
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97); ((StringLiteralContext)_localctx).text = match(STRING_LITERAL);
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
		public ExpressionContext expression;
		public List<ExpressionContext> parameterValues = new ArrayList<ExpressionContext>();
		public TerminalNode OPEN_PARENTHESIS() { return getToken(MJargonParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(MJargonParser.CLOSE_PARENTHESIS, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ID() { return getToken(MJargonParser.ID, 0); }
		public TerminalNode COMA(int i) {
			return getToken(MJargonParser.COMA, i);
		}
		public List<TerminalNode> COMA() { return getTokens(MJargonParser.COMA); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitReferenceExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReferenceExpressionContext referenceExpression() throws RecognitionException {
		ReferenceExpressionContext _localctx = new ReferenceExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_referenceExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99); ((ReferenceExpressionContext)_localctx).id = match(ID);
			setState(111);
			_la = _input.LA(1);
			if (_la==OPEN_PARENTHESIS) {
				{
				setState(100); match(OPEN_PARENTHESIS);
				setState(101); ((ReferenceExpressionContext)_localctx).expression = expression();
				((ReferenceExpressionContext)_localctx).parameterValues.add(((ReferenceExpressionContext)_localctx).expression);
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMA) {
					{
					{
					setState(102); match(COMA);
					setState(103); ((ReferenceExpressionContext)_localctx).expression = expression();
					((ReferenceExpressionContext)_localctx).parameterValues.add(((ReferenceExpressionContext)_localctx).expression);
					}
					}
					setState(108);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(109); match(CLOSE_PARENTHESIS);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitRhythmExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RhythmExpressionContext rhythmExpression() throws RecognitionException {
		RhythmExpressionContext _localctx = new RhythmExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_rhythmExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113); ((RhythmExpressionContext)_localctx).value = match(RHYTHMEXPRESSION);
			setState(114); match(ON);
			setState(115); ((RhythmExpressionContext)_localctx).timeSignature = expression();
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitPitchSequenceExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PitchSequenceExpressionContext pitchSequenceExpression() throws RecognitionException {
		PitchSequenceExpressionContext _localctx = new PitchSequenceExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pitchSequenceExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(119);
				switch (_input.LA(1)) {
				case CHORD_LITERAL:
					{
					setState(117); ((PitchSequenceExpressionContext)_localctx).CHORD_LITERAL = match(CHORD_LITERAL);
					((PitchSequenceExpressionContext)_localctx).notes.add(((PitchSequenceExpressionContext)_localctx).CHORD_LITERAL);
					}
					break;
				case UNDERSCORE:
					{
					setState(118); ((PitchSequenceExpressionContext)_localctx).UNDERSCORE = match(UNDERSCORE);
					((PitchSequenceExpressionContext)_localctx).notes.add(((PitchSequenceExpressionContext)_localctx).UNDERSCORE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(121); 
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitDrumSequenceExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DrumSequenceExpressionContext drumSequenceExpression() throws RecognitionException {
		DrumSequenceExpressionContext _localctx = new DrumSequenceExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_drumSequenceExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(123); ((DrumSequenceExpressionContext)_localctx).DRUM_INSTRUMENT = match(DRUM_INSTRUMENT);
				((DrumSequenceExpressionContext)_localctx).instruments.add(((DrumSequenceExpressionContext)_localctx).DRUM_INSTRUMENT);
				}
				}
				setState(126); 
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitLabelDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelDeclarationContext labelDeclaration() throws RecognitionException {
		LabelDeclarationContext _localctx = new LabelDeclarationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_labelDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); match(COLON);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VERTICAL_BAR) {
				{
				{
				setState(129); match(VERTICAL_BAR);
				}
				}
				setState(134);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitBarline(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BarlineContext barline() throws RecognitionException {
		BarlineContext _localctx = new BarlineContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_barline);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(140); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(135); match(VERTICAL_BAR);
					((BarlineContext)_localctx).expressions.add(null);
					setState(138);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER) | (1L << RHYTHMEXPRESSION) | (1L << UNDERSCORE) | (1L << DRUM_INSTRUMENT) | (1L << CHORD_LITERAL) | (1L << STRING_LITERAL) | (1L << ID))) != 0)) {
						{
						setState(137); ((BarlineContext)_localctx).expression = expression();
						((BarlineContext)_localctx).expressions.add(((BarlineContext)_localctx).expression);
						}
					}

					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(142); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			setState(145);
			_la = _input.LA(1);
			if (_la==VERTICAL_BAR) {
				{
				setState(144); match(VERTICAL_BAR);
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
		public TerminalNode VERTICAL_BAR(int i) {
			return getToken(MJargonParser.VERTICAL_BAR, i);
		}
		public List<TerminalNode> VERTICAL_BAR() { return getTokens(MJargonParser.VERTICAL_BAR); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitTempo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TempoContext tempo() throws RecognitionException {
		TempoContext _localctx = new TempoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_tempo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147); match(TEMPO);
			setState(148); ((TempoContext)_localctx).bpmOrNumerator = match(NUMBER);
			setState(153);
			_la = _input.LA(1);
			if (_la==SLASH) {
				{
				setState(149); match(SLASH);
				setState(150); ((TempoContext)_localctx).denominator = match(NUMBER);
				setState(151); match(EQUALS);
				setState(152); ((TempoContext)_localctx).bpm = match(NUMBER);
				}
			}

			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VERTICAL_BAR) {
				{
				{
				setState(155); match(VERTICAL_BAR);
				}
				}
				setState(160);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitVoices(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VoicesContext voices() throws RecognitionException {
		VoicesContext _localctx = new VoicesContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_voices);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(161); match(VOICES);
			setState(167); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(162); match(VERTICAL_BAR);
					setState(163); ((VoicesContext)_localctx).ID = match(ID);
					((VoicesContext)_localctx).instrumentNames.add(((VoicesContext)_localctx).ID);
					setState(165);
					_la = _input.LA(1);
					if (_la==STRING_LITERAL) {
						{
						setState(164); match(STRING_LITERAL);
						}
					}

					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(169); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			setState(172);
			_la = _input.LA(1);
			if (_la==VERTICAL_BAR) {
				{
				setState(171); match(VERTICAL_BAR);
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
		public TerminalNode VERTICAL_BAR(int i) {
			return getToken(MJargonParser.VERTICAL_BAR, i);
		}
		public List<TerminalNode> VERTICAL_BAR() { return getTokens(MJargonParser.VERTICAL_BAR); }
		public TerminalNode DYNAMICS() { return getToken(MJargonParser.DYNAMICS, 0); }
		public TerminalNode DYNAMIC(int i) {
			return getToken(MJargonParser.DYNAMIC, i);
		}
		public List<TerminalNode> DYNAMIC() { return getTokens(MJargonParser.DYNAMIC); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitDynamics(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DynamicsContext dynamics() throws RecognitionException {
		DynamicsContext _localctx = new DynamicsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_dynamics);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174); match(DYNAMICS);
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VERTICAL_BAR) {
				{
				{
				setState(175); match(VERTICAL_BAR);
				((DynamicsContext)_localctx).dynamicCodes.add(null);
				setState(178);
				_la = _input.LA(1);
				if (_la==DYNAMIC) {
					{
					setState(177); ((DynamicsContext)_localctx).DYNAMIC = match(DYNAMIC);
					((DynamicsContext)_localctx).dynamicCodes.add(((DynamicsContext)_localctx).DYNAMIC);
					}
				}

				}
				}
				setState(184);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MJargonVisitor ) return ((MJargonVisitor<? extends T>)visitor).visitRepeat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepeatContext repeat() throws RecognitionException {
		RepeatContext _localctx = new RepeatContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_repeat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185); match(REPEAT);
			setState(186); ((RepeatContext)_localctx).labelId = match(ID);
			setState(187); ((RepeatContext)_localctx).times = match(NUMBER);
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VERTICAL_BAR) {
				{
				{
				setState(188); match(VERTICAL_BAR);
				}
				}
				setState(193);
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
		"\2\3\65\u00c5\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t"+
		"\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\3\2\7\2*\n\2\f\2\16\2-\13"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\39\n\3\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\5\5\5B\n\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\5\7L\n\7\5\7N\n\7"+
		"\3\7\5\7Q\n\7\3\7\3\7\5\7U\n\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b]\n\b\3\t\3"+
		"\t\3\t\5\tb\n\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\7\13k\n\13\f\13\16\13"+
		"n\13\13\3\13\3\13\5\13r\n\13\3\f\3\f\3\f\3\f\3\r\3\r\6\rz\n\r\r\r\16\r"+
		"{\3\16\6\16\177\n\16\r\16\16\16\u0080\3\17\3\17\7\17\u0085\n\17\f\17\16"+
		"\17\u0088\13\17\3\20\3\20\3\20\5\20\u008d\n\20\6\20\u008f\n\20\r\20\16"+
		"\20\u0090\3\20\5\20\u0094\n\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u009c"+
		"\n\21\3\21\7\21\u009f\n\21\f\21\16\21\u00a2\13\21\3\22\3\22\3\22\3\22"+
		"\5\22\u00a8\n\22\6\22\u00aa\n\22\r\22\16\22\u00ab\3\22\5\22\u00af\n\22"+
		"\3\23\3\23\3\23\3\23\5\23\u00b5\n\23\7\23\u00b7\n\23\f\23\16\23\u00ba"+
		"\13\23\3\24\3\24\3\24\3\24\7\24\u00c0\n\24\f\24\16\24\u00c3\13\24\3\24"+
		"\2\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&\2\2\u00d5\2+\3\2\2\2"+
		"\48\3\2\2\2\6<\3\2\2\2\b>\3\2\2\2\nC\3\2\2\2\fF\3\2\2\2\16\\\3\2\2\2\20"+
		"^\3\2\2\2\22c\3\2\2\2\24e\3\2\2\2\26s\3\2\2\2\30y\3\2\2\2\32~\3\2\2\2"+
		"\34\u0082\3\2\2\2\36\u008e\3\2\2\2 \u0095\3\2\2\2\"\u00a3\3\2\2\2$\u00b0"+
		"\3\2\2\2&\u00bb\3\2\2\2(*\5\4\3\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2"+
		"\2\2,.\3\2\2\2-+\3\2\2\2./\7\1\2\2/\3\3\2\2\2\609\5\6\4\2\619\5\b\5\2"+
		"\629\5\36\20\2\639\5 \21\2\649\5\"\22\2\659\5$\23\2\669\5&\24\2\679\3"+
		"\2\2\28\60\3\2\2\28\61\3\2\2\28\62\3\2\2\28\63\3\2\2\28\64\3\2\2\28\65"+
		"\3\2\2\28\66\3\2\2\28\67\3\2\2\29:\3\2\2\2:;\7\64\2\2;\5\3\2\2\2<=\7\17"+
		"\2\2=\7\3\2\2\2>A\7\63\2\2?B\5\n\6\2@B\5\34\17\2A?\3\2\2\2A@\3\2\2\2B"+
		"\t\3\2\2\2CD\7\t\2\2DE\5\f\7\2E\13\3\2\2\2FP\5\16\b\2GH\7\n\2\2HM\7\3"+
		"\2\2IK\7\7\2\2JL\7\3\2\2KJ\3\2\2\2KL\3\2\2\2LN\3\2\2\2MI\3\2\2\2MN\3\2"+
		"\2\2NO\3\2\2\2OQ\7\13\2\2PG\3\2\2\2PQ\3\2\2\2QT\3\2\2\2RS\7\20\2\2SU\5"+
		"\f\7\2TR\3\2\2\2TU\3\2\2\2U\r\3\2\2\2V]\5\20\t\2W]\5\22\n\2X]\5\24\13"+
		"\2Y]\5\26\f\2Z]\5\30\r\2[]\5\32\16\2\\V\3\2\2\2\\W\3\2\2\2\\X\3\2\2\2"+
		"\\Y\3\2\2\2\\Z\3\2\2\2\\[\3\2\2\2]\17\3\2\2\2^a\7\3\2\2_`\7\5\2\2`b\7"+
		"\3\2\2a_\3\2\2\2ab\3\2\2\2b\21\3\2\2\2cd\7\62\2\2d\23\3\2\2\2eq\7\63\2"+
		"\2fg\7\f\2\2gl\5\f\7\2hi\7\b\2\2ik\5\f\7\2jh\3\2\2\2kn\3\2\2\2lj\3\2\2"+
		"\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2\2op\7\r\2\2pr\3\2\2\2qf\3\2\2\2qr\3\2\2"+
		"\2r\25\3\2\2\2st\7\4\2\2tu\7\20\2\2uv\5\f\7\2v\27\3\2\2\2wz\7\61\2\2x"+
		"z\7\16\2\2yw\3\2\2\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|\31\3\2\2"+
		"\2}\177\7\60\2\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081"+
		"\3\2\2\2\u0081\33\3\2\2\2\u0082\u0086\7\7\2\2\u0083\u0085\7\6\2\2\u0084"+
		"\u0083\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2"+
		"\2\2\u0087\35\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008a\7\6\2\2\u008a\u008c"+
		"\b\20\1\2\u008b\u008d\5\f\7\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2"+
		"\u008d\u008f\3\2\2\2\u008e\u0089\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u008e"+
		"\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092\u0094\7\6\2\2\u0093"+
		"\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094\37\3\2\2\2\u0095\u0096\7\21\2"+
		"\2\u0096\u009b\7\3\2\2\u0097\u0098\7\5\2\2\u0098\u0099\7\3\2\2\u0099\u009a"+
		"\7\t\2\2\u009a\u009c\7\3\2\2\u009b\u0097\3\2\2\2\u009b\u009c\3\2\2\2\u009c"+
		"\u00a0\3\2\2\2\u009d\u009f\7\6\2\2\u009e\u009d\3\2\2\2\u009f\u00a2\3\2"+
		"\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1!\3\2\2\2\u00a2\u00a0"+
		"\3\2\2\2\u00a3\u00a9\7\23\2\2\u00a4\u00a5\7\6\2\2\u00a5\u00a7\7\63\2\2"+
		"\u00a6\u00a8\7\62\2\2\u00a7\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa"+
		"\3\2\2\2\u00a9\u00a4\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab"+
		"\u00ac\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00af\7\6\2\2\u00ae\u00ad\3\2"+
		"\2\2\u00ae\u00af\3\2\2\2\u00af#\3\2\2\2\u00b0\u00b8\7\24\2\2\u00b1\u00b2"+
		"\7\6\2\2\u00b2\u00b4\b\23\1\2\u00b3\u00b5\7\25\2\2\u00b4\u00b3\3\2\2\2"+
		"\u00b4\u00b5\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b1\3\2\2\2\u00b7\u00ba"+
		"\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9%\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00bb\u00bc\7\22\2\2\u00bc\u00bd\7\63\2\2\u00bd\u00c1\7"+
		"\3\2\2\u00be\u00c0\7\6\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1"+
		"\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\'\3\2\2\2\u00c3\u00c1\3\2\2\2"+
		"\34+8AKMPT\\alqy{\u0080\u0086\u008c\u0090\u0093\u009b\u00a0\u00a7\u00ab"+
		"\u00ae\u00b4\u00b8\u00c1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}