// Generated from C:/Users/Baxi/Desktop/Proyecto Compi\MonkeyParser.g4 by ANTLR 4.8
package generated;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MonkeyParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		L_PAREN=1, R_PAREN=2, L_BRACE=3, R_BRACE=4, L_BRACK=5, R_BRACK=6, PYCOMMA=7, 
		COMMA=8, COLON=9, LE=10, GE=11, EQUAL=12, GT=13, LT=14, ASSIGN=15, ADD=16, 
		SUB=17, MUL=18, DIV=19, LET=20, RETURN=21, FN=22, IF=23, ELSE=24, PUTS=25, 
		LEN=26, FIRST=27, LAST=28, REST=29, PUSH=30, TRUE=31, FALSE=32, INTEGER=33, 
		STRING=34, IDENT=35, WS=36, BLOCK_COMMENT=37, SINGLE_COMMENT=38;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_expression = 2, RULE_comparison = 3, 
		RULE_additionExpression = 4, RULE_multiplicationExpression = 5, RULE_elementExpression = 6, 
		RULE_primitiveExpression = 7, RULE_arrayFunctions = 8, RULE_expressionList = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "expression", "comparison", "additionExpression", 
			"multiplicationExpression", "elementExpression", "primitiveExpression", 
			"arrayFunctions", "expressionList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", "','", "':'", 
			"'<='", "'>='", "'=='", "'>'", "'<'", "'='", "'+'", "'-'", "'*'", "'/'", 
			"'let'", "'return'", "'fn'", "'if'", "'else'", "'puts'", "'len'", "'first'", 
			"'last'", "'rest'", "'push'", "'true'", "'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "L_PAREN", "R_PAREN", "L_BRACE", "R_BRACE", "L_BRACK", "R_BRACK", 
			"PYCOMMA", "COMMA", "COLON", "LE", "GE", "EQUAL", "GT", "LT", "ASSIGN", 
			"ADD", "SUB", "MUL", "DIV", "LET", "RETURN", "FN", "IF", "ELSE", "PUTS", 
			"LEN", "FIRST", "LAST", "REST", "PUSH", "TRUE", "FALSE", "INTEGER", "STRING", 
			"IDENT", "WS", "BLOCK_COMMENT", "SINGLE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MonkeyParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MonkeyParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	 
		public ProgramContext() { }
		public void copyFrom(ProgramContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ProgramASTContext extends ProgramContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramASTContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitProgramAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			_localctx = new ProgramASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PAREN) | (1L << L_BRACE) | (1L << L_BRACK) | (1L << LET) | (1L << RETURN) | (1L << FN) | (1L << IF) | (1L << PUTS) | (1L << LEN) | (1L << FIRST) | (1L << LAST) | (1L << REST) | (1L << PUSH) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << IDENT))) != 0)) {
				{
				{
				setState(20);
				statement();
				}
				}
				setState(25);
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

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CallExpressionStatementASTContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PYCOMMA() { return getToken(MonkeyParser.PYCOMMA, 0); }
		public CallExpressionStatementASTContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitCallExpressionStatementAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Statement_LetASTContext extends StatementContext {
		public TerminalNode LET() { return getToken(MonkeyParser.LET, 0); }
		public TerminalNode IDENT() { return getToken(MonkeyParser.IDENT, 0); }
		public TerminalNode ASSIGN() { return getToken(MonkeyParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PYCOMMA() { return getToken(MonkeyParser.PYCOMMA, 0); }
		public Statement_LetASTContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitStatement_LetAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Statement_returnASTContext extends StatementContext {
		public TerminalNode RETURN() { return getToken(MonkeyParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PYCOMMA() { return getToken(MonkeyParser.PYCOMMA, 0); }
		public Statement_returnASTContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitStatement_returnAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
				_localctx = new Statement_LetASTContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				match(LET);
				setState(27);
				match(IDENT);
				setState(28);
				match(ASSIGN);
				setState(29);
				expression();
				setState(32);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PYCOMMA:
					{
					setState(30);
					match(PYCOMMA);
					}
					break;
				case EOF:
				case L_PAREN:
				case L_BRACE:
				case R_BRACE:
				case L_BRACK:
				case LET:
				case RETURN:
				case FN:
				case IF:
				case PUTS:
				case LEN:
				case FIRST:
				case LAST:
				case REST:
				case PUSH:
				case TRUE:
				case FALSE:
				case INTEGER:
				case STRING:
				case IDENT:
					{
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case RETURN:
				_localctx = new Statement_returnASTContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				match(RETURN);
				setState(35);
				expression();
				setState(38);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PYCOMMA:
					{
					setState(36);
					match(PYCOMMA);
					}
					break;
				case EOF:
				case L_PAREN:
				case L_BRACE:
				case R_BRACE:
				case L_BRACK:
				case LET:
				case RETURN:
				case FN:
				case IF:
				case PUTS:
				case LEN:
				case FIRST:
				case LAST:
				case REST:
				case PUSH:
				case TRUE:
				case FALSE:
				case INTEGER:
				case STRING:
				case IDENT:
					{
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case L_PAREN:
			case L_BRACE:
			case L_BRACK:
			case FN:
			case IF:
			case PUTS:
			case LEN:
			case FIRST:
			case LAST:
			case REST:
			case PUSH:
			case TRUE:
			case FALSE:
			case INTEGER:
			case STRING:
			case IDENT:
				_localctx = new CallExpressionStatementASTContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				expression();
				setState(43);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PYCOMMA:
					{
					setState(41);
					match(PYCOMMA);
					}
					break;
				case EOF:
				case L_PAREN:
				case L_BRACE:
				case R_BRACE:
				case L_BRACK:
				case LET:
				case RETURN:
				case FN:
				case IF:
				case PUTS:
				case LEN:
				case FIRST:
				case LAST:
				case REST:
				case PUSH:
				case TRUE:
				case FALSE:
				case INTEGER:
				case STRING:
				case IDENT:
					{
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExpressionASTContext extends ExpressionContext {
		public AdditionExpressionContext additionExpression() {
			return getRuleContext(AdditionExpressionContext.class,0);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public ExpressionASTContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitExpressionAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expression);
		try {
			_localctx = new ExpressionASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			additionExpression();
			setState(48);
			comparison();
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

	public static class ComparisonContext extends ParserRuleContext {
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
	 
		public ComparisonContext() { }
		public void copyFrom(ComparisonContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ComparisonASTContext extends ComparisonContext {
		public List<AdditionExpressionContext> additionExpression() {
			return getRuleContexts(AdditionExpressionContext.class);
		}
		public AdditionExpressionContext additionExpression(int i) {
			return getRuleContext(AdditionExpressionContext.class,i);
		}
		public List<TerminalNode> LT() { return getTokens(MonkeyParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(MonkeyParser.LT, i);
		}
		public List<TerminalNode> GT() { return getTokens(MonkeyParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(MonkeyParser.GT, i);
		}
		public List<TerminalNode> LE() { return getTokens(MonkeyParser.LE); }
		public TerminalNode LE(int i) {
			return getToken(MonkeyParser.LE, i);
		}
		public List<TerminalNode> GE() { return getTokens(MonkeyParser.GE); }
		public TerminalNode GE(int i) {
			return getToken(MonkeyParser.GE, i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(MonkeyParser.EQUAL); }
		public TerminalNode EQUAL(int i) {
			return getToken(MonkeyParser.EQUAL, i);
		}
		public ComparisonASTContext(ComparisonContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitComparisonAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_comparison);
		int _la;
		try {
			int _alt;
			_localctx = new ComparisonASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(50);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LE) | (1L << GE) | (1L << EQUAL) | (1L << GT) | (1L << LT))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(51);
					additionExpression();
					}
					} 
				}
				setState(56);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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

	public static class AdditionExpressionContext extends ParserRuleContext {
		public AdditionExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additionExpression; }
	 
		public AdditionExpressionContext() { }
		public void copyFrom(AdditionExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AdditionExpressionASTContext extends AdditionExpressionContext {
		public List<MultiplicationExpressionContext> multiplicationExpression() {
			return getRuleContexts(MultiplicationExpressionContext.class);
		}
		public MultiplicationExpressionContext multiplicationExpression(int i) {
			return getRuleContext(MultiplicationExpressionContext.class,i);
		}
		public List<TerminalNode> ADD() { return getTokens(MonkeyParser.ADD); }
		public TerminalNode ADD(int i) {
			return getToken(MonkeyParser.ADD, i);
		}
		public List<TerminalNode> SUB() { return getTokens(MonkeyParser.SUB); }
		public TerminalNode SUB(int i) {
			return getToken(MonkeyParser.SUB, i);
		}
		public AdditionExpressionASTContext(AdditionExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitAdditionExpressionAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditionExpressionContext additionExpression() throws RecognitionException {
		AdditionExpressionContext _localctx = new AdditionExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_additionExpression);
		int _la;
		try {
			int _alt;
			_localctx = new AdditionExpressionASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			multiplicationExpression();
			setState(62);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(58);
					_la = _input.LA(1);
					if ( !(_la==ADD || _la==SUB) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(59);
					multiplicationExpression();
					}
					} 
				}
				setState(64);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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

	public static class MultiplicationExpressionContext extends ParserRuleContext {
		public MultiplicationExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicationExpression; }
	 
		public MultiplicationExpressionContext() { }
		public void copyFrom(MultiplicationExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultiplicationExpressionASTContext extends MultiplicationExpressionContext {
		public List<ElementExpressionContext> elementExpression() {
			return getRuleContexts(ElementExpressionContext.class);
		}
		public ElementExpressionContext elementExpression(int i) {
			return getRuleContext(ElementExpressionContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(MonkeyParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(MonkeyParser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(MonkeyParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(MonkeyParser.DIV, i);
		}
		public MultiplicationExpressionASTContext(MultiplicationExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitMultiplicationExpressionAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicationExpressionContext multiplicationExpression() throws RecognitionException {
		MultiplicationExpressionContext _localctx = new MultiplicationExpressionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_multiplicationExpression);
		int _la;
		try {
			int _alt;
			_localctx = new MultiplicationExpressionASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			elementExpression();
			setState(70);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(66);
					_la = _input.LA(1);
					if ( !(_la==MUL || _la==DIV) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(67);
					elementExpression();
					}
					} 
				}
				setState(72);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public static class ElementExpressionContext extends ParserRuleContext {
		public ElementExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementExpression; }
	 
		public ElementExpressionContext() { }
		public void copyFrom(ElementExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ElementExpressionASTContext extends ElementExpressionContext {
		public PrimitiveExpressionContext primitiveExpression() {
			return getRuleContext(PrimitiveExpressionContext.class,0);
		}
		public TerminalNode L_BRACK() { return getToken(MonkeyParser.L_BRACK, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode R_BRACK() { return getToken(MonkeyParser.R_BRACK, 0); }
		public TerminalNode L_PAREN() { return getToken(MonkeyParser.L_PAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MonkeyParser.R_PAREN, 0); }
		public ElementExpressionASTContext(ElementExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitElementExpressionAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementExpressionContext elementExpression() throws RecognitionException {
		ElementExpressionContext _localctx = new ElementExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_elementExpression);
		try {
			_localctx = new ElementExpressionASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			primitiveExpression();
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(74);
				match(L_BRACK);
				setState(75);
				expression();
				setState(76);
				match(R_BRACK);
				}
				break;
			case 2:
				{
				setState(78);
				match(L_PAREN);
				setState(79);
				expressionList();
				setState(80);
				match(R_PAREN);
				}
				break;
			case 3:
				{
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

	public static class PrimitiveExpressionContext extends ParserRuleContext {
		public PrimitiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveExpression; }
	 
		public PrimitiveExpressionContext() { }
		public void copyFrom(PrimitiveExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PrimitiveExpression_literalASTContext extends PrimitiveExpressionContext {
		public TerminalNode L_BRACK() { return getToken(MonkeyParser.L_BRACK, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode R_BRACK() { return getToken(MonkeyParser.R_BRACK, 0); }
		public PrimitiveExpression_literalASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_literalAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_identASTContext extends PrimitiveExpressionContext {
		public TerminalNode IDENT() { return getToken(MonkeyParser.IDENT, 0); }
		public PrimitiveExpression_identASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_identAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_trueASTContext extends PrimitiveExpressionContext {
		public TerminalNode TRUE() { return getToken(MonkeyParser.TRUE, 0); }
		public PrimitiveExpression_trueASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_trueAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_numberASTContext extends PrimitiveExpressionContext {
		public TerminalNode INTEGER() { return getToken(MonkeyParser.INTEGER, 0); }
		public PrimitiveExpression_numberASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_numberAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_ArrayFunctionsASTContext extends PrimitiveExpressionContext {
		public ArrayFunctionsContext arrayFunctions() {
			return getRuleContext(ArrayFunctionsContext.class,0);
		}
		public TerminalNode L_PAREN() { return getToken(MonkeyParser.L_PAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MonkeyParser.R_PAREN, 0); }
		public PrimitiveExpression_ArrayFunctionsASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_ArrayFunctionsAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_FunctionLiteral_ASTContext extends PrimitiveExpressionContext {
		public TerminalNode FN() { return getToken(MonkeyParser.FN, 0); }
		public TerminalNode L_PAREN() { return getToken(MonkeyParser.L_PAREN, 0); }
		public List<TerminalNode> IDENT() { return getTokens(MonkeyParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(MonkeyParser.IDENT, i);
		}
		public TerminalNode R_PAREN() { return getToken(MonkeyParser.R_PAREN, 0); }
		public TerminalNode L_BRACE() { return getToken(MonkeyParser.L_BRACE, 0); }
		public TerminalNode R_BRACE() { return getToken(MonkeyParser.R_BRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MonkeyParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MonkeyParser.COMMA, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public PrimitiveExpression_FunctionLiteral_ASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_FunctionLiteral_AST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_IfExpressionContext extends PrimitiveExpressionContext {
		public TerminalNode IF() { return getToken(MonkeyParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> L_BRACE() { return getTokens(MonkeyParser.L_BRACE); }
		public TerminalNode L_BRACE(int i) {
			return getToken(MonkeyParser.L_BRACE, i);
		}
		public List<TerminalNode> R_BRACE() { return getTokens(MonkeyParser.R_BRACE); }
		public TerminalNode R_BRACE(int i) {
			return getToken(MonkeyParser.R_BRACE, i);
		}
		public TerminalNode ELSE() { return getToken(MonkeyParser.ELSE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public PrimitiveExpression_IfExpressionContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_IfExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_stringASTContext extends PrimitiveExpressionContext {
		public TerminalNode STRING() { return getToken(MonkeyParser.STRING, 0); }
		public PrimitiveExpression_stringASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_stringAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_falseASTContext extends PrimitiveExpressionContext {
		public TerminalNode FALSE() { return getToken(MonkeyParser.FALSE, 0); }
		public PrimitiveExpression_falseASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_falseAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_expressionASTContext extends PrimitiveExpressionContext {
		public TerminalNode L_PAREN() { return getToken(MonkeyParser.L_PAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MonkeyParser.R_PAREN, 0); }
		public PrimitiveExpression_expressionASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_expressionAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_HashLiteral_ASTContext extends PrimitiveExpressionContext {
		public TerminalNode L_BRACE() { return getToken(MonkeyParser.L_BRACE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(MonkeyParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(MonkeyParser.COLON, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MonkeyParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MonkeyParser.COMMA, i);
		}
		public PrimitiveExpression_HashLiteral_ASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_HashLiteral_AST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_PrintExpressionASTContext extends PrimitiveExpressionContext {
		public TerminalNode PUTS() { return getToken(MonkeyParser.PUTS, 0); }
		public TerminalNode L_PAREN() { return getToken(MonkeyParser.L_PAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MonkeyParser.R_PAREN, 0); }
		public PrimitiveExpression_PrintExpressionASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_PrintExpressionAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveExpressionContext primitiveExpression() throws RecognitionException {
		PrimitiveExpressionContext _localctx = new PrimitiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_primitiveExpression);
		int _la;
		try {
			int _alt;
			setState(163);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				_localctx = new PrimitiveExpression_numberASTContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				match(INTEGER);
				}
				break;
			case STRING:
				_localctx = new PrimitiveExpression_stringASTContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				match(STRING);
				}
				break;
			case IDENT:
				_localctx = new PrimitiveExpression_identASTContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(87);
				match(IDENT);
				}
				break;
			case TRUE:
				_localctx = new PrimitiveExpression_trueASTContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(88);
				match(TRUE);
				}
				break;
			case FALSE:
				_localctx = new PrimitiveExpression_falseASTContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(89);
				match(FALSE);
				}
				break;
			case L_PAREN:
				_localctx = new PrimitiveExpression_expressionASTContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(90);
				match(L_PAREN);
				setState(91);
				expression();
				setState(92);
				match(R_PAREN);
				}
				break;
			case L_BRACK:
				_localctx = new PrimitiveExpression_literalASTContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(94);
				match(L_BRACK);
				setState(95);
				expressionList();
				setState(96);
				match(R_BRACK);
				}
				break;
			case LEN:
			case FIRST:
			case LAST:
			case REST:
			case PUSH:
				_localctx = new PrimitiveExpression_ArrayFunctionsASTContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(98);
				arrayFunctions();
				setState(99);
				match(L_PAREN);
				setState(100);
				expressionList();
				setState(101);
				match(R_PAREN);
				}
				break;
			case FN:
				_localctx = new PrimitiveExpression_FunctionLiteral_ASTContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(103);
				match(FN);
				setState(104);
				match(L_PAREN);
				setState(105);
				match(IDENT);
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(106);
					match(COMMA);
					setState(107);
					match(IDENT);
					}
					}
					setState(112);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(113);
				match(R_PAREN);
				setState(114);
				match(L_BRACE);
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PAREN) | (1L << L_BRACE) | (1L << L_BRACK) | (1L << LET) | (1L << RETURN) | (1L << FN) | (1L << IF) | (1L << PUTS) | (1L << LEN) | (1L << FIRST) | (1L << LAST) | (1L << REST) | (1L << PUSH) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << IDENT))) != 0)) {
					{
					{
					setState(115);
					statement();
					}
					}
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(121);
				match(R_BRACE);
				}
				break;
			case L_BRACE:
				_localctx = new PrimitiveExpression_HashLiteral_ASTContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(122);
				match(L_BRACE);
				setState(123);
				expression();
				setState(124);
				match(COLON);
				setState(125);
				expression();
				setState(133);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(126);
						match(COMMA);
						setState(127);
						expression();
						setState(128);
						match(COLON);
						setState(129);
						expression();
						}
						} 
					}
					setState(135);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				}
				}
				break;
			case PUTS:
				_localctx = new PrimitiveExpression_PrintExpressionASTContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(136);
				match(PUTS);
				setState(137);
				match(L_PAREN);
				setState(138);
				expression();
				setState(139);
				match(R_PAREN);
				}
				break;
			case IF:
				_localctx = new PrimitiveExpression_IfExpressionContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(141);
				match(IF);
				setState(142);
				expression();
				setState(143);
				match(L_BRACE);
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PAREN) | (1L << L_BRACE) | (1L << L_BRACK) | (1L << LET) | (1L << RETURN) | (1L << FN) | (1L << IF) | (1L << PUTS) | (1L << LEN) | (1L << FIRST) | (1L << LAST) | (1L << REST) | (1L << PUSH) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << IDENT))) != 0)) {
					{
					{
					setState(144);
					statement();
					}
					}
					setState(149);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(150);
				match(R_BRACE);
				setState(161);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ELSE:
					{
					setState(151);
					match(ELSE);
					setState(152);
					match(L_BRACE);
					setState(156);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PAREN) | (1L << L_BRACE) | (1L << L_BRACK) | (1L << LET) | (1L << RETURN) | (1L << FN) | (1L << IF) | (1L << PUTS) | (1L << LEN) | (1L << FIRST) | (1L << LAST) | (1L << REST) | (1L << PUSH) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << IDENT))) != 0)) {
						{
						{
						setState(153);
						statement();
						}
						}
						setState(158);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(159);
					match(R_BRACE);
					}
					break;
				case EOF:
				case L_PAREN:
				case R_PAREN:
				case L_BRACE:
				case R_BRACE:
				case L_BRACK:
				case R_BRACK:
				case PYCOMMA:
				case COMMA:
				case COLON:
				case LE:
				case GE:
				case EQUAL:
				case GT:
				case LT:
				case ADD:
				case SUB:
				case MUL:
				case DIV:
				case LET:
				case RETURN:
				case FN:
				case IF:
				case PUTS:
				case LEN:
				case FIRST:
				case LAST:
				case REST:
				case PUSH:
				case TRUE:
				case FALSE:
				case INTEGER:
				case STRING:
				case IDENT:
					{
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ArrayFunctionsContext extends ParserRuleContext {
		public ArrayFunctionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayFunctions; }
	 
		public ArrayFunctionsContext() { }
		public void copyFrom(ArrayFunctionsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayFunctions_lenASTContext extends ArrayFunctionsContext {
		public TerminalNode LEN() { return getToken(MonkeyParser.LEN, 0); }
		public ArrayFunctions_lenASTContext(ArrayFunctionsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitArrayFunctions_lenAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayFunctions_restASTContext extends ArrayFunctionsContext {
		public TerminalNode REST() { return getToken(MonkeyParser.REST, 0); }
		public ArrayFunctions_restASTContext(ArrayFunctionsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitArrayFunctions_restAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayFunctions_pushASTContext extends ArrayFunctionsContext {
		public TerminalNode PUSH() { return getToken(MonkeyParser.PUSH, 0); }
		public ArrayFunctions_pushASTContext(ArrayFunctionsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitArrayFunctions_pushAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayFunctions_lastASTContext extends ArrayFunctionsContext {
		public TerminalNode LAST() { return getToken(MonkeyParser.LAST, 0); }
		public ArrayFunctions_lastASTContext(ArrayFunctionsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitArrayFunctions_lastAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayFunctions_firstASTContext extends ArrayFunctionsContext {
		public TerminalNode FIRST() { return getToken(MonkeyParser.FIRST, 0); }
		public ArrayFunctions_firstASTContext(ArrayFunctionsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitArrayFunctions_firstAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayFunctionsContext arrayFunctions() throws RecognitionException {
		ArrayFunctionsContext _localctx = new ArrayFunctionsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_arrayFunctions);
		try {
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LEN:
				_localctx = new ArrayFunctions_lenASTContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				match(LEN);
				}
				break;
			case FIRST:
				_localctx = new ArrayFunctions_firstASTContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				match(FIRST);
				}
				break;
			case LAST:
				_localctx = new ArrayFunctions_lastASTContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(167);
				match(LAST);
				}
				break;
			case REST:
				_localctx = new ArrayFunctions_restASTContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(168);
				match(REST);
				}
				break;
			case PUSH:
				_localctx = new ArrayFunctions_pushASTContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(169);
				match(PUSH);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ExpressionListContext extends ParserRuleContext {
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
	 
		public ExpressionListContext() { }
		public void copyFrom(ExpressionListContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExpressionList_expressionASTContext extends ExpressionListContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MonkeyParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MonkeyParser.COMMA, i);
		}
		public ExpressionList_expressionASTContext(ExpressionListContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitExpressionList_expressionAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionList_nullASTContext extends ExpressionListContext {
		public ExpressionList_nullASTContext(ExpressionListContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitExpressionList_nullAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expressionList);
		int _la;
		try {
			setState(181);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case L_PAREN:
			case L_BRACE:
			case L_BRACK:
			case FN:
			case IF:
			case PUTS:
			case LEN:
			case FIRST:
			case LAST:
			case REST:
			case PUSH:
			case TRUE:
			case FALSE:
			case INTEGER:
			case STRING:
			case IDENT:
				_localctx = new ExpressionList_expressionASTContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(172);
				expression();
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(173);
					match(COMMA);
					setState(174);
					expression();
					}
					}
					setState(179);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case R_PAREN:
			case R_BRACK:
				_localctx = new ExpressionList_nullASTContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\u00ba\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\7\2\30\n\2\f\2\16\2\33\13\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3#\n\3\3"+
		"\3\3\3\3\3\3\3\5\3)\n\3\3\3\3\3\3\3\5\3.\n\3\5\3\60\n\3\3\4\3\4\3\4\3"+
		"\5\3\5\7\5\67\n\5\f\5\16\5:\13\5\3\6\3\6\3\6\7\6?\n\6\f\6\16\6B\13\6\3"+
		"\7\3\7\3\7\7\7G\n\7\f\7\16\7J\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\5\bV\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\to\n\t\f\t\16\tr\13\t\3\t\3\t\3"+
		"\t\7\tw\n\t\f\t\16\tz\13\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t"+
		"\u0086\n\t\f\t\16\t\u0089\13\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t"+
		"\u0094\n\t\f\t\16\t\u0097\13\t\3\t\3\t\3\t\3\t\7\t\u009d\n\t\f\t\16\t"+
		"\u00a0\13\t\3\t\3\t\5\t\u00a4\n\t\5\t\u00a6\n\t\3\n\3\n\3\n\3\n\3\n\5"+
		"\n\u00ad\n\n\3\13\3\13\3\13\7\13\u00b2\n\13\f\13\16\13\u00b5\13\13\3\13"+
		"\5\13\u00b8\n\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\5\3\2\f\20\3\2\22"+
		"\23\3\2\24\25\2\u00d1\2\31\3\2\2\2\4/\3\2\2\2\6\61\3\2\2\2\b8\3\2\2\2"+
		"\n;\3\2\2\2\fC\3\2\2\2\16K\3\2\2\2\20\u00a5\3\2\2\2\22\u00ac\3\2\2\2\24"+
		"\u00b7\3\2\2\2\26\30\5\4\3\2\27\26\3\2\2\2\30\33\3\2\2\2\31\27\3\2\2\2"+
		"\31\32\3\2\2\2\32\3\3\2\2\2\33\31\3\2\2\2\34\35\7\26\2\2\35\36\7%\2\2"+
		"\36\37\7\21\2\2\37\"\5\6\4\2 #\7\t\2\2!#\3\2\2\2\" \3\2\2\2\"!\3\2\2\2"+
		"#\60\3\2\2\2$%\7\27\2\2%(\5\6\4\2&)\7\t\2\2\')\3\2\2\2(&\3\2\2\2(\'\3"+
		"\2\2\2)\60\3\2\2\2*-\5\6\4\2+.\7\t\2\2,.\3\2\2\2-+\3\2\2\2-,\3\2\2\2."+
		"\60\3\2\2\2/\34\3\2\2\2/$\3\2\2\2/*\3\2\2\2\60\5\3\2\2\2\61\62\5\n\6\2"+
		"\62\63\5\b\5\2\63\7\3\2\2\2\64\65\t\2\2\2\65\67\5\n\6\2\66\64\3\2\2\2"+
		"\67:\3\2\2\28\66\3\2\2\289\3\2\2\29\t\3\2\2\2:8\3\2\2\2;@\5\f\7\2<=\t"+
		"\3\2\2=?\5\f\7\2><\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2A\13\3\2\2\2B"+
		"@\3\2\2\2CH\5\16\b\2DE\t\4\2\2EG\5\16\b\2FD\3\2\2\2GJ\3\2\2\2HF\3\2\2"+
		"\2HI\3\2\2\2I\r\3\2\2\2JH\3\2\2\2KU\5\20\t\2LM\7\7\2\2MN\5\6\4\2NO\7\b"+
		"\2\2OV\3\2\2\2PQ\7\3\2\2QR\5\24\13\2RS\7\4\2\2SV\3\2\2\2TV\3\2\2\2UL\3"+
		"\2\2\2UP\3\2\2\2UT\3\2\2\2V\17\3\2\2\2W\u00a6\7#\2\2X\u00a6\7$\2\2Y\u00a6"+
		"\7%\2\2Z\u00a6\7!\2\2[\u00a6\7\"\2\2\\]\7\3\2\2]^\5\6\4\2^_\7\4\2\2_\u00a6"+
		"\3\2\2\2`a\7\7\2\2ab\5\24\13\2bc\7\b\2\2c\u00a6\3\2\2\2de\5\22\n\2ef\7"+
		"\3\2\2fg\5\24\13\2gh\7\4\2\2h\u00a6\3\2\2\2ij\7\30\2\2jk\7\3\2\2kp\7%"+
		"\2\2lm\7\n\2\2mo\7%\2\2nl\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2qs\3\2"+
		"\2\2rp\3\2\2\2st\7\4\2\2tx\7\5\2\2uw\5\4\3\2vu\3\2\2\2wz\3\2\2\2xv\3\2"+
		"\2\2xy\3\2\2\2y{\3\2\2\2zx\3\2\2\2{\u00a6\7\6\2\2|}\7\5\2\2}~\5\6\4\2"+
		"~\177\7\13\2\2\177\u0087\5\6\4\2\u0080\u0081\7\n\2\2\u0081\u0082\5\6\4"+
		"\2\u0082\u0083\7\13\2\2\u0083\u0084\5\6\4\2\u0084\u0086\3\2\2\2\u0085"+
		"\u0080\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2"+
		"\2\2\u0088\u00a6\3\2\2\2\u0089\u0087\3\2\2\2\u008a\u008b\7\33\2\2\u008b"+
		"\u008c\7\3\2\2\u008c\u008d\5\6\4\2\u008d\u008e\7\4\2\2\u008e\u00a6\3\2"+
		"\2\2\u008f\u0090\7\31\2\2\u0090\u0091\5\6\4\2\u0091\u0095\7\5\2\2\u0092"+
		"\u0094\5\4\3\2\u0093\u0092\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2"+
		"\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098"+
		"\u00a3\7\6\2\2\u0099\u009a\7\32\2\2\u009a\u009e\7\5\2\2\u009b\u009d\5"+
		"\4\3\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e"+
		"\u009f\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a4\7\6"+
		"\2\2\u00a2\u00a4\3\2\2\2\u00a3\u0099\3\2\2\2\u00a3\u00a2\3\2\2\2\u00a4"+
		"\u00a6\3\2\2\2\u00a5W\3\2\2\2\u00a5X\3\2\2\2\u00a5Y\3\2\2\2\u00a5Z\3\2"+
		"\2\2\u00a5[\3\2\2\2\u00a5\\\3\2\2\2\u00a5`\3\2\2\2\u00a5d\3\2\2\2\u00a5"+
		"i\3\2\2\2\u00a5|\3\2\2\2\u00a5\u008a\3\2\2\2\u00a5\u008f\3\2\2\2\u00a6"+
		"\21\3\2\2\2\u00a7\u00ad\7\34\2\2\u00a8\u00ad\7\35\2\2\u00a9\u00ad\7\36"+
		"\2\2\u00aa\u00ad\7\37\2\2\u00ab\u00ad\7 \2\2\u00ac\u00a7\3\2\2\2\u00ac"+
		"\u00a8\3\2\2\2\u00ac\u00a9\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ab\3\2"+
		"\2\2\u00ad\23\3\2\2\2\u00ae\u00b3\5\6\4\2\u00af\u00b0\7\n\2\2\u00b0\u00b2"+
		"\5\6\4\2\u00b1\u00af\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\u00b8\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b8\3\2"+
		"\2\2\u00b7\u00ae\3\2\2\2\u00b7\u00b6\3\2\2\2\u00b8\25\3\2\2\2\25\31\""+
		"(-/8@HUpx\u0087\u0095\u009e\u00a3\u00a5\u00ac\u00b3\u00b7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}