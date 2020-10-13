import generated.MonkeyParser;
import generated.MonkeyScanner;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

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
    protected ArrayList<Error> errors = new ArrayList<>();
    protected Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
    protected MonkeyErrorListener errorListener;

    //IDLE interfaz vars
    protected RSyntaxTextArea codeArea = new RSyntaxTextArea();
    protected RSyntaxTextArea terminal = new RSyntaxTextArea();
    protected JSplitPane panel = new JSplitPane();

    //Vars to get the user files
    protected File archive = new File("");
    protected String path = "";
    protected JFileChooser fileChooser = new JFileChooser();

    //Singleton
    protected static IDLE getInstance(){
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

    //Update the parser
    private void getParser(){
        input = CharStreams.fromString(codeArea.getText());
        scanner  = new MonkeyScanner(input);
        tokens = new CommonTokenStream(scanner);
        parser = new MonkeyParser(tokens);
    }

    //Show Tree
    protected TreeViewer generateTree() {
        getParser();
        tree = parser.program();
        return new TreeViewer(Arrays.asList(parser.getRuleNames()),tree);
    }

    //Execute app
    protected void run(){
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
                terminal.setText(erros + "\n\n=>Compilation: Failed");
                showErrors(errorListener);
            }
            else{
                terminalPass();
                terminal.setText("Tree: " + tree.toStringTree(parser) + "\n\n=>Compilation: Successful");
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
    protected void openFile(){
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
    protected void saveFile() {
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
    protected void saveFileAs() {
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
    protected JSplitPane getPanels() {
        codeArea = new RSyntaxTextArea(40, 220);
        codeArea.setMinimumSize(new Dimension(50, 300));
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        codeArea.setCodeFoldingEnabled(true);
        codeArea.setBackground(Color.DARK_GRAY);
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

        RTextScrollPane sp = new RTextScrollPane(IDLE.getInstance().codeArea);
        sp.setLineNumbersEnabled(true);
        sp.setFoldIndicatorEnabled(true);

        //terminal
        terminal  = new RSyntaxTextArea("Terminal");
        terminal.setEnabled(false);
        terminal.setBackground(Color.black);
        terminal.setForeground(Color.BLUE);
        terminal.setMargin(new Insets(10,10,10,10));
        panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,  sp, terminal);
        return panel;
    }
}
