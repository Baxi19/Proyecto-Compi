import generated.MonkeyParser;
import generated.MonkeyScanner;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;


public class Window extends JFrame {

    private Highlighter.HighlightPainter cyanPainter;
    public JMenuBar menu;
    public JMenu file ;
    public JMenu edit;
    public JMenu run;

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
        ActionMap actions = IDLE.getInstance().codeArea.getActionMap();
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
                IDLE.getInstance().codeArea.setText("");
            }
        });

        // Open File
        f2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = IDLE.getInstance().fc.showOpenDialog(IDLE.getInstance().panel);
                if(selection == JFileChooser.APPROVE_OPTION){
                    IDLE.getInstance().archive = IDLE.getInstance().fc.getSelectedFile();
                    IDLE.getInstance().ruta = IDLE.getInstance().archive.getAbsolutePath();
                    try {
                        IDLE.getInstance().codeArea.setText(CharStreams.fromFileName(IDLE.getInstance().ruta).toString());
                    }catch(Exception e1){
                        JOptionPane.showMessageDialog(null, "Can't open file");
                        IDLE.getInstance().terminal.setText(e1.getMessage());
                    }
                }
            }
        });

        // Save File
        f3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String content = IDLE.getInstance().codeArea.getText();
                try{
                    FileWriter fw = new FileWriter(IDLE.getInstance().ruta);
                    fw.write(content);
                    fw.flush();
                    fw.close();
                } catch(Exception e1){
                    JOptionPane.showMessageDialog(null, "Can't save file");
                    IDLE.getInstance().terminal.setText(e1.getMessage());
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
                    String content = IDLE.getInstance().codeArea.getText();
                    File fi  = fsave.getSelectedFile();
                    try{
                        FileWriter fw = new FileWriter(fi.getPath());
                        fw.write(content);
                        fw.flush();
                        fw.close();
                    } catch (Exception exception) {
                        IDLE.getInstance().terminal.setText(exception.getMessage());
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
                    IDLE.getInstance().parser = getParser();

                    MonkeyErrorListener errorListener = new MonkeyErrorListener();
                    IDLE.getInstance().scanner.removeErrorListeners();
                    IDLE.getInstance().parser.removeErrorListeners();
                    IDLE.getInstance().scanner.addErrorListener(errorListener);
                    IDLE.getInstance().parser.addErrorListener(errorListener);
                    IDLE.getInstance().tree = IDLE.getInstance().parser.program();

                    if(errorListener.hasErrors()){
                        showErrors(errorListener);
                        //IDLE.getInstance().terminal.setForeground(Color.red);
                        IDLE.getInstance().terminalFail();
                        String erros = errorListener.toString();
                        IDLE.getInstance().terminal.setText(erros + "\n\n=>Compilation: Failed");
                    }
                    else{
                        //IDLE.getInstance().terminal.setForeground(Color.green);
                        IDLE.getInstance().terminalPass();
                        IDLE.getInstance().terminal.setText("Tree: " + IDLE.getInstance().tree.toStringTree(IDLE.getInstance().parser) + "\n\n=>Compilation: Successful");
                    }

                } catch(Exception e1){
                    //JOptionPane.showMessageDialog(null, "The file doesn't exist!");
                    IDLE.getInstance().terminal.setText(e1.getMessage());

                }

            }
        });
        // Generate Tree
        r2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    IDLE.getInstance().parser = getParser();
                    IDLE.getInstance().tree = IDLE.getInstance().parser.program();
                    TreeViewer viewr = new TreeViewer(Arrays.asList(IDLE.getInstance().parser.getRuleNames()),IDLE.getInstance().tree);
                    viewr.open();
                } catch(Exception e1){
                    //JOptionPane.showMessageDialog(null, "The file doesn't exist!");
                    IDLE.getInstance().terminal.setText(e1.getMessage());
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

        for (int i = 0; i < errorListener.errorPositions.size() ; i++) {
            IDLE.getInstance().getXYText(errorListener.errorPositions.get(i).getRow(), errorListener.errorPositions.get(i).getColum());
        }
        /*
        int linePosition = 0;
        cyanPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.cyan);
        for (String line : IDLE.getInstance().codeArea.getText().split("\\n")){
            //doStuffWithLine(line);
            linePosition++;
        }
        */
    }


    //split panels
    private JSplitPane getPanels() {
        IDLE.getInstance().codeArea = new RSyntaxTextArea(40, 220);
        IDLE.getInstance().codeArea.setMinimumSize(new Dimension(50, 300));
        IDLE.getInstance().codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        IDLE.getInstance().codeArea.setCodeFoldingEnabled(true);
        IDLE.getInstance().codeArea.setBackground(Color.DARK_GRAY);
        IDLE.getInstance().codeArea.setForeground(Color.GREEN);
        IDLE.getInstance().codeArea.setLineWrap(true);
        IDLE.getInstance().codeArea.setWrapStyleWord(true);
        IDLE.getInstance().codeArea.setMargin(new Insets(10,10,10,10));
        IDLE.getInstance().codeArea.setAutoIndentEnabled(true);
        IDLE.getInstance().codeArea.setCloseCurlyBraces(true);
        IDLE.getInstance().codeArea.setBracketMatchingEnabled(true);
        IDLE.getInstance().codeArea.setCloseMarkupTags(true);
        IDLE.getInstance().codeArea.setAnimateBracketMatching(true);

        RTextScrollPane sp = new RTextScrollPane(IDLE.getInstance().codeArea);
        sp.setLineNumbersEnabled(true);
        sp.setFoldIndicatorEnabled(true);

        //terminal
        IDLE.getInstance().terminal  = new RSyntaxTextArea("Terminal");
        IDLE.getInstance().terminal.setEnabled(false);
        IDLE.getInstance().terminal.setBackground(Color.black);
        IDLE.getInstance().terminal.setForeground(Color.LIGHT_GRAY);
        IDLE.getInstance().terminal.setMargin(new Insets(10,10,10,10));
        IDLE.getInstance().panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,  sp, IDLE.getInstance().terminal);
        return IDLE.getInstance().panel;
    }

    private MonkeyParser getParser(){
        IDLE.getInstance().input = CharStreams.fromString(IDLE.getInstance().codeArea.getText());
        IDLE.getInstance().scanner  = new MonkeyScanner(IDLE.getInstance().input);
        IDLE.getInstance().tokens = new CommonTokenStream(IDLE.getInstance().scanner);
        IDLE.getInstance().parser = new MonkeyParser(IDLE.getInstance().tokens);
        return IDLE.getInstance().parser;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new Window();
            }
        });
    }
}
