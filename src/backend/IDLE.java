package backend;


import contextualAnalysis.SymbolTable;
import contextualAnalysis.VisitorFuntion;
import contextualAnalysis.VisitorVariable;
import errors.Error;
import errors.MonkeyErrorListener;
import generated.MonkeyParser;
import generated.MonkeyScanner;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.RTextScrollPane;
import tree.Visitor;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


//Singleton class will get all important methods
public class IDLE {
    //Instance used by singleton patern
    private static IDLE instance = null;

    //Compiler vars
    protected MonkeyParser parser = null;
    protected MonkeyScanner scanner = null;
    protected CharStream input = null;
    protected CommonTokenStream tokens = null;
    protected ParseTree tree = null;

    //Errors vars
    protected Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(new Color(153, 5, 5));
    protected MonkeyErrorListener errorListener;
    protected ArrayList<Error> errors = new ArrayList<>();
    public ArrayList<Error> errorsContextual = new ArrayList<>();

    //IDLE interfaz vars
    public RSyntaxTextArea codeArea = new RSyntaxTextArea();
    public RSyntaxTextArea terminal = new RSyntaxTextArea();
    protected JSplitPane panel = new JSplitPane();
    public Boolean showTableSymbol = false;
    public Boolean showConsoleTree = false;

    //Vars to get the user files
    protected File archive = new File("");
    protected String path = "";
    protected JFileChooser fileChooser = new JFileChooser();

    //TODO: Set vars in the first value every time when visitor run
    //Contextual Analysis
    public int tableId = 0;
    public SymbolTable tablaSimbolos = new SymbolTable();
    public ArrayList<TerminalNode> parameters = new ArrayList<>();
    public String callWith = "";

    //Singleton
    public static IDLE getInstance(){
        if (instance == null) {
            instance = new IDLE();
        }
        return instance;
    }

    //update the terminal's color to green
    protected void terminalPass() {
        this.terminal.setForeground(Color.GREEN);
    }

    //Update the terminal's color to red
    protected void terminalFail() {
        this.terminal.setForeground(Color.RED);
    }

    //Mark all errors
    private void markErrors() throws BadLocationException {
        for (int i = 0; i < errors.size() ; i++) {
            int row = errors.get(i).getRow();
            int startIndex = codeArea.getLineStartOffset(row-1);
            int endIndex = codeArea.getLineEndOffset(row-1);
            codeArea.getHighlighter().addHighlight(startIndex, endIndex, painter);
        }

    }

    //Mark all errors Context
    private void markContextErrors() throws BadLocationException {
        for (int i = 0; i < errorsContextual.size() ; i++) {
            int row = errorsContextual.get(i).getRow();
            int startIndex = codeArea.getLineStartOffset(row-1);
            int endIndex = codeArea.getLineEndOffset(row-1);
            codeArea.getHighlighter().addHighlight(startIndex, endIndex, painter);
        }

    }

    //Update the parser
    private void getParser(){
        input = CharStreams.fromString(codeArea.getText());
        scanner  = new MonkeyScanner(input);
        tokens = new CommonTokenStream(scanner);
        parser = new MonkeyParser(tokens);
    }

    //Show Tree
    public TreeViewer generateTree() {
        getParser();
        tree = parser.program();
        return new TreeViewer(Arrays.asList(parser.getRuleNames()),tree);
    }

    //Execute app
    public void run(){
        try {
            codeArea.getHighlighter().removeAllHighlights();
            getParser();
            errorListener = new MonkeyErrorListener();
            scanner.removeErrorListeners();
            parser.removeErrorListeners();
            scanner.addErrorListener(errorListener);
            parser.addErrorListener(errorListener);
            tree = parser.program();

            if(errorListener.hasErrors()){
                showErrors(errorListener);
                terminalFail();
                String erros = errorListener.toString();
                terminal.setText(erros + "\n\n=>COMPILATION: FAILED");
                showErrors(errorListener);

            }else{
                //Check the methods first
                tablaSimbolos = new SymbolTable();
                errorsContextual = new ArrayList<>();

                //Save Funtions
                VisitorFuntion analysisContextualMethods = new VisitorFuntion();
                analysisContextualMethods.visit(tree);

                //Save Variables
                VisitorVariable analysisContextualVariables = new VisitorVariable();
                analysisContextualVariables.visit(tree);

                //Check if the user want to see the symbols Table in console
                String terminalText = "";
                if(showTableSymbol){
                    terminalText = tablaSimbolos.printTables();
                    showTableSymbol = false;
                }else if(showConsoleTree){
                    Visitor newVisitor = new Visitor();
                    newVisitor.visit(tree);
                    terminalText = "\n\n*******************************************************************************************";
                    terminalText += "TREE PRINT: " + tree.toStringTree(parser) + "\n\nTREE PRINT WITH VISITOR DESIGN PATTERN:\n";
                    for(int i = 0;i<newVisitor.VisitorTree.size();i++){
                        terminalText += newVisitor.VisitorTree.get(i);
                    }
                    terminalText +=  "\n\n*******************************************************************************************\n\n";
                    showConsoleTree = false;
                }

                //Normal Run
                if(IDLE.getInstance().errorsContextual.isEmpty()){
                    terminalPass();
                    terminal.setText(terminalText += "\n\n=>COMPILATION: SUCCESSFUL");
                }else{
                    markContextErrors();
                    terminalFail();
                    terminal.setText(terminalText += getContextualErrors() + "\n\n=>COMPILATION: FAILED");
                }

            }

        } catch(Exception e1){}

    }

    //Show errors
    private void showErrors(MonkeyErrorListener errorListener) {
        try {
            errors = errorListener.errorPositions;
            markErrors();
        }catch (Exception e){};
    }

    //Open File
    public void openFile(){
        int selection = fileChooser.showOpenDialog(panel);
        if(selection == JFileChooser.APPROVE_OPTION){
            archive = fileChooser.getSelectedFile();
            path = archive.getAbsolutePath();
            try {
                codeArea.setText(CharStreams.fromFileName(path).toString());
            }catch(Exception e1){
                terminal.setText("Can't open file");
            }
        }

    }

    //Save file
    public void saveFile() {
        String content = codeArea.getText();
        try{
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        } catch(Exception e1){
            terminal.setText("Can't save file");
        }
    }

    //Save file As
    public void saveFileAs() {
        JFileChooser fsave = new JFileChooser();
        fsave.setDialogTitle("Save File");
        int result = fsave.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            String content = codeArea.getText();
            File fi  = fsave.getSelectedFile();
            try{
                FileWriter fw = new FileWriter(fi.getPath());
                fw.write(content);
                fw.flush();
                fw.close();
            } catch (Exception exception) {
                terminal.setText("Can't save as file");
            }
        }
    }

    //split panels
    public JSplitPane getPanels() {
        codeArea = new RSyntaxTextArea(40, 220);
        codeArea.setMinimumSize(new Dimension(50, 300));
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        codeArea.setCodeFoldingEnabled(true);
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setAntiAliasingEnabled(true);
        codeArea.setAutoscrolls(true);

        codeArea.setLineWrap(true);
        codeArea.setWrapStyleWord(true);
        codeArea.setMargin(new Insets(10,10,10,10));
        codeArea.setAutoIndentEnabled(true);

        codeArea.setCloseCurlyBraces(true);
        codeArea.setBracketMatchingEnabled(true);
        codeArea.setCloseMarkupTags(true);
        codeArea.setAnimateBracketMatching(true);

        SyntaxScheme scheme = codeArea.getSyntaxScheme();
        scheme.getStyle(Token.COMMENT_EOL).foreground = new Color(184,183,153);
        scheme.getStyle(Token.COMMENT_MULTILINE).foreground = new Color(184,183,153);
        scheme.getStyle(Token.DATA_TYPE).foreground = new Color(250,32,248);
        scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = Color.cyan;
        scheme.getStyle(Token.LITERAL_CHAR).foreground = Color.cyan;
        scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = new Color(119, 67, 191);
        scheme.getStyle(Token.LITERAL_NUMBER_FLOAT).foreground = new Color(119, 67, 191);
        scheme.getStyle(Token.LITERAL_BOOLEAN).foreground = new Color(249, 135, 17);
        scheme.getStyle(Token.OPERATOR).foreground = Color.magenta;
        scheme.getStyle(Token.IDENTIFIER).foreground = Color.green;
        scheme.getStyle(Token.SEPARATOR).foreground = new Color(253,145,0);
        codeArea.revalidate();

        RTextScrollPane rScroll = new RTextScrollPane(IDLE.getInstance().codeArea);
        rScroll.setLineNumbersEnabled(true);
        rScroll.setFoldIndicatorEnabled(true);

        //terminal
        terminal  = new RSyntaxTextArea("Terminal");
        terminal.setEnabled(false);
        terminal.setBackground(Color.black);
        terminal.setForeground(Color.BLUE);
        terminal.setMargin(new Insets(10,10,10,10));

        JScrollPane jScroll = new JScrollPane(terminal);
        jScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,  rScroll, jScroll);
        return panel;
    }

    //Method checks the contextual errors, return all errors as String
    public String getContextualErrors(){
        String errors = "";
        for (int i = 0; i < errorsContextual.size(); i++) {
            errors += "\n " + errorsContextual.get(i).type +
                    ": (" + errorsContextual.get(i).row + "," +
                    errorsContextual.get(i).colum + ") " + "'"
                    +errorsContextual.get(i).msj+"'" ;
        }
        return errors;
    }

    //Serial, to control de simbols table's ident
    public int getNewId() {
        tableId += 1;
        return tableId;
    }

    //Search parameters in string
    public ArrayList<String> getFuntionInfo(String method){
        String[] lParent = method.split("\\(");
        String[] rParent =  lParent[1].split("\\)");
        String[] param = rParent[0].split("\\,");
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < param.length; i++) {
            list.add(param[i]);
        }
        //Check if aren't parameters with same name
        if(checkParameters(list)){
            return list;
        }
        return null;
    }

    //Method check if are parameters with same name in function declaration
    public Boolean checkParameters(ArrayList<String> list){
        List<String> duplicateList = list
                    .stream()
                    .collect(Collectors.groupingBy(s -> s))
                    .entrySet()
                    .stream()
                    // filter if are more than one parameter with same name
                    .filter(e -> e.getValue().size() > 1)
                    .map(e -> e.getKey())
                    .collect(Collectors.toList());
        //If are not ambiguous parameters'name
        if(duplicateList.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    //Method to check if is Int
    public Boolean isInt(String value){
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            engine.eval(value);
            return true;
        } catch (ScriptException e) {
            return false;
        }

    }

    //Check first value of List [?,.,.,.,.,.]
    public boolean checkFirstParameter(String part) {
        String[] lBrace = part.split("\\[");
        String[] data =  lBrace[1].split("\\,");
        String first = data[0];
        if(first.startsWith("\"")){
            return true;
        }else if(isInt(first)){
            return true;
        }else{
            return false;
        }
    }
}
