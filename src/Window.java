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
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

import static sun.plugin.javascript.navig.JSType.Document;

public class Window extends JFrame {
    MonkeyParser parser = null;
    MonkeyScanner scanner = null;
    CharStream input=null;
    CommonTokenStream tokens = null;
    ParseTree tree;

    private RSyntaxTextArea codeArea = new RSyntaxTextArea();
    public JTextArea terminal = new JTextArea();
    private Highlighter.HighlightPainter cyanPainter;
    public JMenuBar menu;
    public JMenu file ;
    public JMenu edit;
    public JMenu run;
    private File archive;
    private String ruta;
    public JSplitPane panel;
    private JFileChooser fc = new JFileChooser();

    public Window() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setJMenuBar(getMenu());
        add(getPanels());
        this.setVisible(true);
    }

    public JMenuBar getMenu(){
        menu = new JMenuBar();

        // create a File options
        file = new JMenu("File");

        // create menuitems
        JMenuItem f1,f2,f3,f4,f5;
        f1 = new JMenuItem("New");
        f2 = new JMenuItem("Open");
        f3 = new JMenuItem("Save");
        f4 = new JMenuItem("Save As");
        f5 = new JMenuItem("Close");

        // add menu items to menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);
        file.add(f5);

        // create a Edit options
        edit = new JMenu("Edit");

        // create menuitems
        ActionMap actions = codeArea.getActionMap();
        Action copy = actions.get(DefaultEditorKit.copyAction);
        Action paste = actions.get(DefaultEditorKit.pasteAction);
        Action cut = actions.get(DefaultEditorKit.cutAction);

        copy.putValue(Action.NAME, "Copy");
        copy.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getAWTKeyStroke('C', Event.CTRL_MASK));

        cut.putValue(Action.NAME, "Cut");
        cut.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getAWTKeyStroke('X', Event.CTRL_MASK));

        paste.putValue(Action.NAME, "Paste");
        paste.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getAWTKeyStroke('V', Event.CTRL_MASK));

        // add menu items to menu
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);

        // create a Run options
        run = new JMenu("Run");
        // Run Options
        JMenuItem r1,r2;
        r1 = new JMenuItem("Run");
        r2 = new JMenuItem("Generate Tree");
        run.add(r1);run.add(r2);


        // -- Action Listeners --

        // New File
        f1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codeArea.setText("");
            }
        });

        // Open File
        f2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = fc.showOpenDialog(panel);
                if(selection == JFileChooser.APPROVE_OPTION){
                    archive = fc.getSelectedFile();
                    ruta = archive.getAbsolutePath();
                    try {
                        codeArea.setText(CharStreams.fromFileName(ruta).toString());
                    }catch(Exception e1){
                        JOptionPane.showMessageDialog(null, "Can't open file");
                        terminal.setText(e1.getMessage());
                    }
                }
            }
        });

        // Save File
        f3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String content = codeArea.getText();
                try{
                    FileWriter fw = new FileWriter(ruta);
                    fw.write(content);
                    fw.flush();
                    fw.close();
                } catch(Exception e1){
                    JOptionPane.showMessageDialog(null, "Can't save file");
                    terminal.setText(e1.getMessage());
                }
            }
        });

        // Save File As
        f4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        terminal.setText(exception.getMessage());
                    }
                }

            }
        });

        // Close
        f5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        // Run
        r1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    parser = getParser();

                    MonkeyErrorListener errorListener = new MonkeyErrorListener();
                    scanner.removeErrorListeners();
                    parser.removeErrorListeners();
                    scanner.addErrorListener(errorListener);
                    parser.addErrorListener(errorListener);
                    tree = parser.program();

                    if(errorListener.hasErrors()){
                        showErrors(errorListener);
                        terminal.setForeground(Color.red);
                        String erros = errorListener.toString();
                        terminal.setText(erros + "\n\n=>Compilation: Failed");
                    }
                    else{
                        terminal.setForeground(Color.green);
                        terminal.setText("Tree: " + tree.toStringTree(parser) + "\n\n=>Compilation: Successful");
                    }

                } catch(Exception e1){
                    //JOptionPane.showMessageDialog(null, "The file doesn't exist!");
                    terminal.setText(e1.getMessage());

                }

            }
        });
        // Generate Tree
        r2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    parser = getParser();
                    tree = parser.program();
                    TreeViewer viewr = new TreeViewer(Arrays.asList(parser.getRuleNames()),tree);
                    viewr.open();
                } catch(Exception e1){
                    //JOptionPane.showMessageDialog(null, "The file doesn't exist!");
                    terminal.setText(e1.getMessage());
                }
            }
        });


        // add menu to menu bar
        menu.add(file);
        menu.add(edit);
        menu.add(run);
        return menu;
    }

    //Here should mark the text with errors
    private void showErrors(MonkeyErrorListener errorListener) {
        cyanPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.cyan);
        for (int i = 0; i < errorListener.errorPositions.size() ; i+=2) {
            /*
            try {
                codeArea.setCaretPosition(errorListener.errorPositions.get(i));
                codeArea.getHighlighter().addHighlight(errorListener.errorPositions.get(0),(errorListener.errorPositions.get(i+1)) , cyanPainter);
            } catch (BadLocationException ble) {

            }

             */
            System.out.println("("+ errorListener.errorPositions.get(i) + ", "+ errorListener.errorPositions.get(i+1)+")" );
        }
    }

    //split panels
    private JSplitPane getPanels() {

        codeArea = new RSyntaxTextArea(40, 220);
        codeArea.setMinimumSize(new Dimension(50, 300));
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        codeArea.setCodeFoldingEnabled(true);
        codeArea.setBackground(Color.DARK_GRAY);
        codeArea.setForeground(Color.GREEN);
        codeArea.setLineWrap(true);
        codeArea.setWrapStyleWord(true);
        codeArea.setMargin(new Insets(10,10,10,10));
        codeArea.setAutoIndentEnabled(true);
        codeArea.setCloseCurlyBraces(true);
        codeArea.setBracketMatchingEnabled(true);
        codeArea.setCloseMarkupTags(true);
        codeArea.setAnimateBracketMatching(true);

        RTextScrollPane sp = new RTextScrollPane(codeArea);
        sp.setLineNumbersEnabled(true);
        sp.setFoldIndicatorEnabled(true);

        //terminal
        terminal  = new JTextArea("Terminal");
        terminal.setEnabled(false);
        terminal.setBackground(Color.black);
        terminal.setForeground(Color.LIGHT_GRAY);
        terminal.setMargin(new Insets(10,10,10,10));
        panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,  sp, terminal);
        return panel;
    }

    private MonkeyParser getParser(){
        input = CharStreams.fromString(codeArea.getText());
        scanner  = new MonkeyScanner(input);
        tokens = new CommonTokenStream(scanner);
        parser = new MonkeyParser(tokens);
        return parser;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new Window();
            }
        });
    }
}
