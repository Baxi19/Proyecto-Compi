// Generated from C:/Users/Baxi/Desktop/Proyecto Compi\MonkeyParser.g4 by ANTLR 4.8
package generated;

    import contextualAnalysis.TablaSimbolos;
    import contextualAnalysis.SymbolTable;

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
		STRING=34, IDENT=35, WS=36, SINGLE_COMMENT=37, BLOCK_COMMENT=38;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_letStatement = 2, RULE_returnStatement = 3, 
		RULE_expressionStatement = 4, RULE_expression = 5, RULE_comparison = 6, 
		RULE_additionExpression = 7, RULE_additionFactor = 8, RULE_multiplicationExpression = 9, 
		RULE_multiplicationFactor = 10, RULE_elementExpression = 11, RULE_elementAccess = 12, 
		RULE_callExpression = 13, RULE_primitiveExpression = 14, RULE_arrayFunctions = 15, 
		RULE_arrayLiteral = 16, RULE_functionLiteral = 17, RULE_functionParameters = 18, 
		RULE_hashLiteral = 19, RULE_hashContent = 20, RULE_expressionList = 21, 
		RULE_printExpression = 22, RULE_ifExpression = 23, RULE_blockStatement = 24, 
		RULE_ident = 25;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "letStatement", "returnStatement", "expressionStatement", 
			"expression", "comparison", "additionExpression", "additionFactor", "multiplicationExpression", 
			"multiplicationFactor", "elementExpression", "elementAccess", "callExpression", 
			"primitiveExpression", "arrayFunctions", "arrayLiteral", "functionLiteral", 
			"functionParameters", "hashLiteral", "hashContent", "expressionList", 
			"printExpression", "ifExpression", "blockStatement", "ident"
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
			"IDENT", "WS", "SINGLE_COMMENT", "BLOCK_COMMENT"
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


	    private contextualAnalysis.TablaSimbolos symbols = new contextualAnalysis.TablaSimbolos();
	    private contextualAnalysis.SymbolTable symbols2 = new contextualAnalysis.SymbolTable();

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
			symbols.openScope();
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PAREN) | (1L << L_BRACE) | (1L << L_BRACK) | (1L << LET) | (1L << RETURN) | (1L << FN) | (1L << IF) | (1L << PUTS) | (1L << LEN) | (1L << FIRST) | (1L << LAST) | (1L << REST) | (1L << PUSH) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << IDENT))) != 0)) {
				{
				{
				setState(53);
				statement();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			symbols.imprimir(); symbols.closeScope();
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
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public CallExpressionStatementASTContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitCallExpressionStatementAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Statement_LetASTContext extends StatementContext {
		public TerminalNode LET() { return getToken(MonkeyParser.LET, 0); }
		public LetStatementContext letStatement() {
			return getRuleContext(LetStatementContext.class,0);
		}
		public Statement_LetASTContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitStatement_LetAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Statement_returnASTContext extends StatementContext {
		public TerminalNode RETURN() { return getToken(MonkeyParser.RETURN, 0); }
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
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
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
				_localctx = new Statement_LetASTContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				match(LET);
				setState(62);
				letStatement();
				}
				break;
			case RETURN:
				_localctx = new Statement_returnASTContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				match(RETURN);
				setState(64);
				returnStatement();
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
				setState(65);
				expressionStatement();
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

	public static class LetStatementContext extends ParserRuleContext {
		public LetStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letStatement; }
	 
		public LetStatementContext() { }
		public void copyFrom(LetStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LetStatementASTContext extends LetStatementContext {
		public Token IDENT;
		public TerminalNode IDENT() { return getToken(MonkeyParser.IDENT, 0); }
		public TerminalNode ASSIGN() { return getToken(MonkeyParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PYCOMMA() { return getToken(MonkeyParser.PYCOMMA, 0); }
		public LetStatementASTContext(LetStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitLetStatementAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetStatementContext letStatement() throws RecognitionException {
		LetStatementContext _localctx = new LetStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_letStatement);
		try {
			_localctx = new LetStatementASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			((LetStatementASTContext)_localctx).IDENT = match(IDENT);
			setState(69);
			match(ASSIGN);
			setState(70);
			expression();
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PYCOMMA:
				{
				setState(71);
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
			symbols.insertar(((LetStatementASTContext)_localctx).IDENT,0,_localctx);
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

	public static class ReturnStatementContext extends ParserRuleContext {
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
	 
		public ReturnStatementContext() { }
		public void copyFrom(ReturnStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReturnStatementASTContext extends ReturnStatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PYCOMMA() { return getToken(MonkeyParser.PYCOMMA, 0); }
		public ReturnStatementASTContext(ReturnStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitReturnStatementAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_returnStatement);
		try {
			_localctx = new ReturnStatementASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			expression();
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PYCOMMA:
				{
				setState(78);
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

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
	 
		public ExpressionStatementContext() { }
		public void copyFrom(ExpressionStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExpressionStatementASTContext extends ExpressionStatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PYCOMMA() { return getToken(MonkeyParser.PYCOMMA, 0); }
		public ExpressionStatementASTContext(ExpressionStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitExpressionStatementAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_expressionStatement);
		try {
			_localctx = new ExpressionStatementASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			expression();
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PYCOMMA:
				{
				setState(83);
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
		enterRule(_localctx, 10, RULE_expression);
		try {
			_localctx = new ExpressionASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			additionExpression();
			setState(88);
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
		enterRule(_localctx, 12, RULE_comparison);
		int _la;
		try {
			_localctx = new ComparisonASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LE) | (1L << GE) | (1L << EQUAL) | (1L << GT) | (1L << LT))) != 0)) {
				{
				{
				setState(90);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LE) | (1L << GE) | (1L << EQUAL) | (1L << GT) | (1L << LT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(91);
				additionExpression();
				}
				}
				setState(96);
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
		public MultiplicationExpressionContext multiplicationExpression() {
			return getRuleContext(MultiplicationExpressionContext.class,0);
		}
		public AdditionFactorContext additionFactor() {
			return getRuleContext(AdditionFactorContext.class,0);
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
		enterRule(_localctx, 14, RULE_additionExpression);
		try {
			_localctx = new AdditionExpressionASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			multiplicationExpression();
			setState(98);
			additionFactor();
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

	public static class AdditionFactorContext extends ParserRuleContext {
		public AdditionFactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additionFactor; }
	 
		public AdditionFactorContext() { }
		public void copyFrom(AdditionFactorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AdditionFactorASTContext extends AdditionFactorContext {
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
		public AdditionFactorASTContext(AdditionFactorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitAdditionFactorAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditionFactorContext additionFactor() throws RecognitionException {
		AdditionFactorContext _localctx = new AdditionFactorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_additionFactor);
		int _la;
		try {
			_localctx = new AdditionFactorASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD || _la==SUB) {
				{
				{
				setState(100);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUB) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(101);
				multiplicationExpression();
				}
				}
				setState(106);
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
		public ElementExpressionContext elementExpression() {
			return getRuleContext(ElementExpressionContext.class,0);
		}
		public MultiplicationFactorContext multiplicationFactor() {
			return getRuleContext(MultiplicationFactorContext.class,0);
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
		enterRule(_localctx, 18, RULE_multiplicationExpression);
		try {
			_localctx = new MultiplicationExpressionASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			elementExpression();
			setState(108);
			multiplicationFactor();
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

	public static class MultiplicationFactorContext extends ParserRuleContext {
		public MultiplicationFactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicationFactor; }
	 
		public MultiplicationFactorContext() { }
		public void copyFrom(MultiplicationFactorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultiplicationFactorASTContext extends MultiplicationFactorContext {
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
		public MultiplicationFactorASTContext(MultiplicationFactorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitMultiplicationFactorAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicationFactorContext multiplicationFactor() throws RecognitionException {
		MultiplicationFactorContext _localctx = new MultiplicationFactorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_multiplicationFactor);
		int _la;
		try {
			_localctx = new MultiplicationFactorASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MUL || _la==DIV) {
				{
				{
				setState(110);
				_la = _input.LA(1);
				if ( !(_la==MUL || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(111);
				elementExpression();
				}
				}
				setState(116);
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
		public ElementAccessContext elementAccess() {
			return getRuleContext(ElementAccessContext.class,0);
		}
		public CallExpressionContext callExpression() {
			return getRuleContext(CallExpressionContext.class,0);
		}
		public ElementExpressionASTContext(ElementExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitElementExpressionAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementExpressionContext elementExpression() throws RecognitionException {
		ElementExpressionContext _localctx = new ElementExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_elementExpression);
		try {
			_localctx = new ElementExpressionASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			primitiveExpression();
			setState(121);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(118);
				elementAccess();
				}
				break;
			case 2:
				{
				setState(119);
				callExpression();
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

	public static class ElementAccessContext extends ParserRuleContext {
		public ElementAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementAccess; }
	 
		public ElementAccessContext() { }
		public void copyFrom(ElementAccessContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ElementAccessASTContext extends ElementAccessContext {
		public TerminalNode L_BRACK() { return getToken(MonkeyParser.L_BRACK, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode R_BRACK() { return getToken(MonkeyParser.R_BRACK, 0); }
		public ElementAccessASTContext(ElementAccessContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitElementAccessAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementAccessContext elementAccess() throws RecognitionException {
		ElementAccessContext _localctx = new ElementAccessContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_elementAccess);
		try {
			_localctx = new ElementAccessASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(L_BRACK);
			setState(124);
			expression();
			setState(125);
			match(R_BRACK);
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

	public static class CallExpressionContext extends ParserRuleContext {
		public CallExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callExpression; }
	 
		public CallExpressionContext() { }
		public void copyFrom(CallExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CallExpressionASTContext extends CallExpressionContext {
		public TerminalNode L_PAREN() { return getToken(MonkeyParser.L_PAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MonkeyParser.R_PAREN, 0); }
		public CallExpressionASTContext(CallExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitCallExpressionAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallExpressionContext callExpression() throws RecognitionException {
		CallExpressionContext _localctx = new CallExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_callExpression);
		try {
			_localctx = new CallExpressionASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(L_PAREN);
			setState(128);
			expressionList();
			setState(129);
			match(R_PAREN);
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
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public PrimitiveExpression_literalASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_literalAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_HashLiteralASTContext extends PrimitiveExpressionContext {
		public HashLiteralContext hashLiteral() {
			return getRuleContext(HashLiteralContext.class,0);
		}
		public PrimitiveExpression_HashLiteralASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_HashLiteralAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_FunctionLiteralASTContext extends PrimitiveExpressionContext {
		public FunctionLiteralContext functionLiteral() {
			return getRuleContext(FunctionLiteralContext.class,0);
		}
		public PrimitiveExpression_FunctionLiteralASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_FunctionLiteralAST(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveExpression_identASTContext extends PrimitiveExpressionContext {
		public Token IDENT;
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
	public static class PrimitiveExpression_IfExpressionASTContext extends PrimitiveExpressionContext {
		public IfExpressionContext ifExpression() {
			return getRuleContext(IfExpressionContext.class,0);
		}
		public PrimitiveExpression_IfExpressionASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_IfExpressionAST(this);
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
	public static class PrimitiveExpression_PrintExpressionASTContext extends PrimitiveExpressionContext {
		public PrintExpressionContext printExpression() {
			return getRuleContext(PrintExpressionContext.class,0);
		}
		public PrimitiveExpression_PrintExpressionASTContext(PrimitiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrimitiveExpression_PrintExpressionAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveExpressionContext primitiveExpression() throws RecognitionException {
		PrimitiveExpressionContext _localctx = new PrimitiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_primitiveExpression);
		try {
			setState(151);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				_localctx = new PrimitiveExpression_numberASTContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				match(INTEGER);
				}
				break;
			case STRING:
				_localctx = new PrimitiveExpression_stringASTContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				match(STRING);
				}
				break;
			case IDENT:
				_localctx = new PrimitiveExpression_identASTContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(133);
				((PrimitiveExpression_identASTContext)_localctx).IDENT = match(IDENT);
				if (symbols.buscar((((PrimitiveExpression_identASTContext)_localctx).IDENT!=null?((PrimitiveExpression_identASTContext)_localctx).IDENT.getText():null)) == null) {
				                                    System.err.println("undefined variable: "+(((PrimitiveExpression_identASTContext)_localctx).IDENT!=null?((PrimitiveExpression_identASTContext)_localctx).IDENT.getText():null)+ " in : ["+(((PrimitiveExpression_identASTContext)_localctx).IDENT!=null?((PrimitiveExpression_identASTContext)_localctx).IDENT.getLine():0)+","+(((PrimitiveExpression_identASTContext)_localctx).IDENT!=null?((PrimitiveExpression_identASTContext)_localctx).IDENT.getCharPositionInLine():0) + "]");
				                                }
				}
				break;
			case TRUE:
				_localctx = new PrimitiveExpression_trueASTContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(135);
				match(TRUE);
				}
				break;
			case FALSE:
				_localctx = new PrimitiveExpression_falseASTContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(136);
				match(FALSE);
				}
				break;
			case L_PAREN:
				_localctx = new PrimitiveExpression_expressionASTContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(137);
				match(L_PAREN);
				setState(138);
				expression();
				setState(139);
				match(R_PAREN);
				}
				break;
			case L_BRACK:
				_localctx = new PrimitiveExpression_literalASTContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(141);
				arrayLiteral();
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
				setState(142);
				arrayFunctions();
				setState(143);
				match(L_PAREN);
				setState(144);
				expressionList();
				setState(145);
				match(R_PAREN);
				}
				break;
			case FN:
				_localctx = new PrimitiveExpression_FunctionLiteralASTContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(147);
				functionLiteral();
				}
				break;
			case L_BRACE:
				_localctx = new PrimitiveExpression_HashLiteralASTContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(148);
				hashLiteral();
				}
				break;
			case PUTS:
				_localctx = new PrimitiveExpression_PrintExpressionASTContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(149);
				printExpression();
				}
				break;
			case IF:
				_localctx = new PrimitiveExpression_IfExpressionASTContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(150);
				ifExpression();
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
		enterRule(_localctx, 30, RULE_arrayFunctions);
		try {
			setState(158);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LEN:
				_localctx = new ArrayFunctions_lenASTContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				match(LEN);
				}
				break;
			case FIRST:
				_localctx = new ArrayFunctions_firstASTContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				match(FIRST);
				}
				break;
			case LAST:
				_localctx = new ArrayFunctions_lastASTContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(155);
				match(LAST);
				}
				break;
			case REST:
				_localctx = new ArrayFunctions_restASTContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(156);
				match(REST);
				}
				break;
			case PUSH:
				_localctx = new ArrayFunctions_pushASTContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(157);
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

	public static class ArrayLiteralContext extends ParserRuleContext {
		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral; }
	 
		public ArrayLiteralContext() { }
		public void copyFrom(ArrayLiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayLiteralASTContext extends ArrayLiteralContext {
		public TerminalNode L_BRACK() { return getToken(MonkeyParser.L_BRACK, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode R_BRACK() { return getToken(MonkeyParser.R_BRACK, 0); }
		public ArrayLiteralASTContext(ArrayLiteralContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitArrayLiteralAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arrayLiteral);
		try {
			_localctx = new ArrayLiteralASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(L_BRACK);
			setState(161);
			expressionList();
			setState(162);
			match(R_BRACK);
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

	public static class FunctionLiteralContext extends ParserRuleContext {
		public FunctionLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionLiteral; }
	 
		public FunctionLiteralContext() { }
		public void copyFrom(FunctionLiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FunctionLiteralASTContext extends FunctionLiteralContext {
		public TerminalNode FN() { return getToken(MonkeyParser.FN, 0); }
		public TerminalNode L_PAREN() { return getToken(MonkeyParser.L_PAREN, 0); }
		public FunctionParametersContext functionParameters() {
			return getRuleContext(FunctionParametersContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MonkeyParser.R_PAREN, 0); }
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public FunctionLiteralASTContext(FunctionLiteralContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitFunctionLiteralAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionLiteralContext functionLiteral() throws RecognitionException {
		FunctionLiteralContext _localctx = new FunctionLiteralContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_functionLiteral);
		try {
			_localctx = new FunctionLiteralASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(FN);
			setState(165);
			match(L_PAREN);
			setState(166);
			functionParameters();
			setState(167);
			match(R_PAREN);
			setState(168);
			blockStatement();
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

	public static class FunctionParametersContext extends ParserRuleContext {
		public FunctionParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionParameters; }
	 
		public FunctionParametersContext() { }
		public void copyFrom(FunctionParametersContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FunctionParametersASTContext extends FunctionParametersContext {
		public List<TerminalNode> IDENT() { return getTokens(MonkeyParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(MonkeyParser.IDENT, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MonkeyParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MonkeyParser.COMMA, i);
		}
		public FunctionParametersASTContext(FunctionParametersContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitFunctionParametersAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionParametersContext functionParameters() throws RecognitionException {
		FunctionParametersContext _localctx = new FunctionParametersContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_functionParameters);
		int _la;
		try {
			_localctx = new FunctionParametersASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(IDENT);
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(171);
				match(COMMA);
				setState(172);
				match(IDENT);
				}
				}
				setState(177);
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

	public static class HashLiteralContext extends ParserRuleContext {
		public HashLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hashLiteral; }
	 
		public HashLiteralContext() { }
		public void copyFrom(HashLiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class HashLiteralASTContext extends HashLiteralContext {
		public TerminalNode L_BRACE() { return getToken(MonkeyParser.L_BRACE, 0); }
		public List<HashContentContext> hashContent() {
			return getRuleContexts(HashContentContext.class);
		}
		public HashContentContext hashContent(int i) {
			return getRuleContext(HashContentContext.class,i);
		}
		public TerminalNode R_BRACE() { return getToken(MonkeyParser.R_BRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MonkeyParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MonkeyParser.COMMA, i);
		}
		public HashLiteralASTContext(HashLiteralContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitHashLiteralAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HashLiteralContext hashLiteral() throws RecognitionException {
		HashLiteralContext _localctx = new HashLiteralContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_hashLiteral);
		int _la;
		try {
			_localctx = new HashLiteralASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(L_BRACE);
			setState(179);
			hashContent();
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(180);
				match(COMMA);
				setState(181);
				hashContent();
				}
				}
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(187);
			match(R_BRACE);
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

	public static class HashContentContext extends ParserRuleContext {
		public HashContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hashContent; }
	 
		public HashContentContext() { }
		public void copyFrom(HashContentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class HashContentASTContext extends HashContentContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode COLON() { return getToken(MonkeyParser.COLON, 0); }
		public HashContentASTContext(HashContentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitHashContentAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HashContentContext hashContent() throws RecognitionException {
		HashContentContext _localctx = new HashContentContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_hashContent);
		try {
			_localctx = new HashContentASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			expression();
			setState(190);
			match(COLON);
			setState(191);
			expression();
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
		enterRule(_localctx, 42, RULE_expressionList);
		int _la;
		try {
			setState(202);
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
				setState(193);
				expression();
				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(194);
					match(COMMA);
					setState(195);
					expression();
					}
					}
					setState(200);
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

	public static class PrintExpressionContext extends ParserRuleContext {
		public PrintExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printExpression; }
	 
		public PrintExpressionContext() { }
		public void copyFrom(PrintExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PrintExpressionASTContext extends PrintExpressionContext {
		public TerminalNode PUTS() { return getToken(MonkeyParser.PUTS, 0); }
		public TerminalNode L_PAREN() { return getToken(MonkeyParser.L_PAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MonkeyParser.R_PAREN, 0); }
		public PrintExpressionASTContext(PrintExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitPrintExpressionAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintExpressionContext printExpression() throws RecognitionException {
		PrintExpressionContext _localctx = new PrintExpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_printExpression);
		try {
			_localctx = new PrintExpressionASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(PUTS);
			setState(205);
			match(L_PAREN);
			setState(206);
			expression();
			setState(207);
			match(R_PAREN);
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

	public static class IfExpressionContext extends ParserRuleContext {
		public IfExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifExpression; }
	 
		public IfExpressionContext() { }
		public void copyFrom(IfExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IfExpressionASTContext extends IfExpressionContext {
		public TerminalNode IF() { return getToken(MonkeyParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MonkeyParser.ELSE, 0); }
		public IfExpressionASTContext(IfExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitIfExpressionAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfExpressionContext ifExpression() throws RecognitionException {
		IfExpressionContext _localctx = new IfExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ifExpression);
		try {
			_localctx = new IfExpressionASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			match(IF);
			setState(210);
			expression();
			setState(211);
			blockStatement();
			setState(215);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ELSE:
				{
				setState(212);
				match(ELSE);
				setState(213);
				blockStatement();
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

	public static class BlockStatementContext extends ParserRuleContext {
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
	 
		public BlockStatementContext() { }
		public void copyFrom(BlockStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BlockStatementASTContext extends BlockStatementContext {
		public TerminalNode L_BRACE() { return getToken(MonkeyParser.L_BRACE, 0); }
		public TerminalNode R_BRACE() { return getToken(MonkeyParser.R_BRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockStatementASTContext(BlockStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitBlockStatementAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_blockStatement);
		int _la;
		try {
			_localctx = new BlockStatementASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			match(L_BRACE);
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PAREN) | (1L << L_BRACE) | (1L << L_BRACK) | (1L << LET) | (1L << RETURN) | (1L << FN) | (1L << IF) | (1L << PUTS) | (1L << LEN) | (1L << FIRST) | (1L << LAST) | (1L << REST) | (1L << PUSH) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << STRING) | (1L << IDENT))) != 0)) {
				{
				{
				setState(218);
				statement();
				}
				}
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(224);
			match(R_BRACE);
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

	public static class IdentContext extends ParserRuleContext {
		public LetStatementContext decl = null;
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
	 
		public IdentContext() { }
		public void copyFrom(IdentContext ctx) {
			super.copyFrom(ctx);
			this.decl = ctx.decl;
		}
	}
	public static class IdentASTContext extends IdentContext {
		public TerminalNode IDENT() { return getToken(MonkeyParser.IDENT, 0); }
		public IdentASTContext(IdentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MonkeyParserVisitor ) return ((MonkeyParserVisitor<? extends T>)visitor).visitIdentAST(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_ident);
		try {
			_localctx = new IdentASTContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(IDENT);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\u00e7\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\3\2\7\29\n\2\f\2\16\2<\13\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\3\5\3E\n\3\3\4\3\4\3\4\3\4\3\4\5\4L\n\4\3\4\3\4\3\5\3\5\3\5\5"+
		"\5S\n\5\3\6\3\6\3\6\5\6X\n\6\3\7\3\7\3\7\3\b\3\b\7\b_\n\b\f\b\16\bb\13"+
		"\b\3\t\3\t\3\t\3\n\3\n\7\ni\n\n\f\n\16\nl\13\n\3\13\3\13\3\13\3\f\3\f"+
		"\7\fs\n\f\f\f\16\fv\13\f\3\r\3\r\3\r\3\r\5\r|\n\r\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u009a\n\20\3\21"+
		"\3\21\3\21\3\21\3\21\5\21\u00a1\n\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\7\24\u00b0\n\24\f\24\16\24\u00b3\13\24"+
		"\3\25\3\25\3\25\3\25\7\25\u00b9\n\25\f\25\16\25\u00bc\13\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\27\7\27\u00c7\n\27\f\27\16\27\u00ca\13"+
		"\27\3\27\5\27\u00cd\n\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\5\31\u00da\n\31\3\32\3\32\7\32\u00de\n\32\f\32\16\32\u00e1"+
		"\13\32\3\32\3\32\3\33\3\33\3\33\2\2\34\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\64\2\5\3\2\f\20\3\2\22\23\3\2\24\25\2\u00ec\2\66"+
		"\3\2\2\2\4D\3\2\2\2\6F\3\2\2\2\bO\3\2\2\2\nT\3\2\2\2\fY\3\2\2\2\16`\3"+
		"\2\2\2\20c\3\2\2\2\22j\3\2\2\2\24m\3\2\2\2\26t\3\2\2\2\30w\3\2\2\2\32"+
		"}\3\2\2\2\34\u0081\3\2\2\2\36\u0099\3\2\2\2 \u00a0\3\2\2\2\"\u00a2\3\2"+
		"\2\2$\u00a6\3\2\2\2&\u00ac\3\2\2\2(\u00b4\3\2\2\2*\u00bf\3\2\2\2,\u00cc"+
		"\3\2\2\2.\u00ce\3\2\2\2\60\u00d3\3\2\2\2\62\u00db\3\2\2\2\64\u00e4\3\2"+
		"\2\2\66:\b\2\1\2\679\5\4\3\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2"+
		";=\3\2\2\2<:\3\2\2\2=>\b\2\1\2>\3\3\2\2\2?@\7\26\2\2@E\5\6\4\2AB\7\27"+
		"\2\2BE\5\b\5\2CE\5\n\6\2D?\3\2\2\2DA\3\2\2\2DC\3\2\2\2E\5\3\2\2\2FG\7"+
		"%\2\2GH\7\21\2\2HK\5\f\7\2IL\7\t\2\2JL\3\2\2\2KI\3\2\2\2KJ\3\2\2\2LM\3"+
		"\2\2\2MN\b\4\1\2N\7\3\2\2\2OR\5\f\7\2PS\7\t\2\2QS\3\2\2\2RP\3\2\2\2RQ"+
		"\3\2\2\2S\t\3\2\2\2TW\5\f\7\2UX\7\t\2\2VX\3\2\2\2WU\3\2\2\2WV\3\2\2\2"+
		"X\13\3\2\2\2YZ\5\20\t\2Z[\5\16\b\2[\r\3\2\2\2\\]\t\2\2\2]_\5\20\t\2^\\"+
		"\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\17\3\2\2\2b`\3\2\2\2cd\5\24\13"+
		"\2de\5\22\n\2e\21\3\2\2\2fg\t\3\2\2gi\5\24\13\2hf\3\2\2\2il\3\2\2\2jh"+
		"\3\2\2\2jk\3\2\2\2k\23\3\2\2\2lj\3\2\2\2mn\5\30\r\2no\5\26\f\2o\25\3\2"+
		"\2\2pq\t\4\2\2qs\5\30\r\2rp\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\27"+
		"\3\2\2\2vt\3\2\2\2w{\5\36\20\2x|\5\32\16\2y|\5\34\17\2z|\3\2\2\2{x\3\2"+
		"\2\2{y\3\2\2\2{z\3\2\2\2|\31\3\2\2\2}~\7\7\2\2~\177\5\f\7\2\177\u0080"+
		"\7\b\2\2\u0080\33\3\2\2\2\u0081\u0082\7\3\2\2\u0082\u0083\5,\27\2\u0083"+
		"\u0084\7\4\2\2\u0084\35\3\2\2\2\u0085\u009a\7#\2\2\u0086\u009a\7$\2\2"+
		"\u0087\u0088\7%\2\2\u0088\u009a\b\20\1\2\u0089\u009a\7!\2\2\u008a\u009a"+
		"\7\"\2\2\u008b\u008c\7\3\2\2\u008c\u008d\5\f\7\2\u008d\u008e\7\4\2\2\u008e"+
		"\u009a\3\2\2\2\u008f\u009a\5\"\22\2\u0090\u0091\5 \21\2\u0091\u0092\7"+
		"\3\2\2\u0092\u0093\5,\27\2\u0093\u0094\7\4\2\2\u0094\u009a\3\2\2\2\u0095"+
		"\u009a\5$\23\2\u0096\u009a\5(\25\2\u0097\u009a\5.\30\2\u0098\u009a\5\60"+
		"\31\2\u0099\u0085\3\2\2\2\u0099\u0086\3\2\2\2\u0099\u0087\3\2\2\2\u0099"+
		"\u0089\3\2\2\2\u0099\u008a\3\2\2\2\u0099\u008b\3\2\2\2\u0099\u008f\3\2"+
		"\2\2\u0099\u0090\3\2\2\2\u0099\u0095\3\2\2\2\u0099\u0096\3\2\2\2\u0099"+
		"\u0097\3\2\2\2\u0099\u0098\3\2\2\2\u009a\37\3\2\2\2\u009b\u00a1\7\34\2"+
		"\2\u009c\u00a1\7\35\2\2\u009d\u00a1\7\36\2\2\u009e\u00a1\7\37\2\2\u009f"+
		"\u00a1\7 \2\2\u00a0\u009b\3\2\2\2\u00a0\u009c\3\2\2\2\u00a0\u009d\3\2"+
		"\2\2\u00a0\u009e\3\2\2\2\u00a0\u009f\3\2\2\2\u00a1!\3\2\2\2\u00a2\u00a3"+
		"\7\7\2\2\u00a3\u00a4\5,\27\2\u00a4\u00a5\7\b\2\2\u00a5#\3\2\2\2\u00a6"+
		"\u00a7\7\30\2\2\u00a7\u00a8\7\3\2\2\u00a8\u00a9\5&\24\2\u00a9\u00aa\7"+
		"\4\2\2\u00aa\u00ab\5\62\32\2\u00ab%\3\2\2\2\u00ac\u00b1\7%\2\2\u00ad\u00ae"+
		"\7\n\2\2\u00ae\u00b0\7%\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1"+
		"\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\'\3\2\2\2\u00b3\u00b1\3\2\2\2"+
		"\u00b4\u00b5\7\5\2\2\u00b5\u00ba\5*\26\2\u00b6\u00b7\7\n\2\2\u00b7\u00b9"+
		"\5*\26\2\u00b8\u00b6\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba"+
		"\u00bb\3\2\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00be\7\6"+
		"\2\2\u00be)\3\2\2\2\u00bf\u00c0\5\f\7\2\u00c0\u00c1\7\13\2\2\u00c1\u00c2"+
		"\5\f\7\2\u00c2+\3\2\2\2\u00c3\u00c8\5\f\7\2\u00c4\u00c5\7\n\2\2\u00c5"+
		"\u00c7\5\f\7\2\u00c6\u00c4\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2"+
		"\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cd\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb"+
		"\u00cd\3\2\2\2\u00cc\u00c3\3\2\2\2\u00cc\u00cb\3\2\2\2\u00cd-\3\2\2\2"+
		"\u00ce\u00cf\7\33\2\2\u00cf\u00d0\7\3\2\2\u00d0\u00d1\5\f\7\2\u00d1\u00d2"+
		"\7\4\2\2\u00d2/\3\2\2\2\u00d3\u00d4\7\31\2\2\u00d4\u00d5\5\f\7\2\u00d5"+
		"\u00d9\5\62\32\2\u00d6\u00d7\7\32\2\2\u00d7\u00da\5\62\32\2\u00d8\u00da"+
		"\3\2\2\2\u00d9\u00d6\3\2\2\2\u00d9\u00d8\3\2\2\2\u00da\61\3\2\2\2\u00db"+
		"\u00df\7\5\2\2\u00dc\u00de\5\4\3\2\u00dd\u00dc\3\2\2\2\u00de\u00e1\3\2"+
		"\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e2\3\2\2\2\u00e1"+
		"\u00df\3\2\2\2\u00e2\u00e3\7\6\2\2\u00e3\63\3\2\2\2\u00e4\u00e5\7%\2\2"+
		"\u00e5\65\3\2\2\2\23:DKRW`jt{\u0099\u00a0\u00b1\u00ba\u00c8\u00cc\u00d9"+
		"\u00df";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}