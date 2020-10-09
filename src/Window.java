import generated.MonkeyParser;
import generated.MonkeyScanner;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

public class Window extends JFrame {

    private RSyntaxTextArea codeArea = new RSyntaxTextArea();
    public JTextArea terminal = new JTextArea();

    public JMenuBar menu;
    public JMenu file ;
    public JMenu edit;
    public JMenu run;



    private File archive;
    private String ruta;
    public JPanel panel = new JPanel();
    private JFileChooser fc = new JFileChooser();

    public Window() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setJMenuBar(getMenu());
        //getPanels();
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

/*
        JMenuItem e1,e2,e3;
        e1 = new JMenuItem("Cut");
        e2 = new JMenuItem("Copy");
        e3 = new JMenuItem("Paste");
*/

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
                        System.out.println("Can't open file");
                        e1.printStackTrace();
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
                    System.out.println("Can't save file");
                    e1.printStackTrace();
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
                        exception.printStackTrace();
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
/*
        //Cut
        e1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                codeArea.getInputMap().put(KeyStroke.getKeyStroke("control X"),"none");// disable cut
            }
        });

        //Copy
        e2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                codeArea.getInputMap().put(KeyStroke.getKeyStroke("control C"), "none"); // disable copy
            }
        });

        //Paste
        e3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                codeArea.getInputMap().put(KeyStroke.getKeyStroke("control V"), "none"); // disable paste
            }
        });
*/



        // Run
        r1.addActionListener(new ActionListener() {
            MonkeyParser parser = null;
            MonkeyScanner scanner = null;
            CharStream input=null;
            CommonTokenStream tokens = null;
            ParseTree tree;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //pinta de rojo al correr
                    //textArea1.getHighlighter().addHighlight(1, 1, redPainter);

                    input = CharStreams.fromString(codeArea.getText());
                    scanner  = new MonkeyScanner(input);
                    tokens  = new CommonTokenStream(scanner);
                    parser = new MonkeyParser(tokens);

                    MonkeyErrorListener errorListener = new MonkeyErrorListener();
                    scanner.removeErrorListeners();
                    parser.removeErrorListeners();
                    scanner.addErrorListener(errorListener);
                    parser.addErrorListener(errorListener);
                    tree = parser.program();

                    if(errorListener.hasErrors()){
                        terminal.setForeground(Color.red);
                        String erros = errorListener.toString();
                        terminal.setText(erros + "\n\n=>Compilation: Failed");
                    }
                    else{
                        terminal.setForeground(Color.green);
                        terminal.setText("Tree: " + tree.toStringTree(parser) + "\n\n=>Compilation: Successful");
                    }

                } catch(Exception e1){
                    System.out.println("The file doesn't exist!");
                    e1.printStackTrace();
                }

            }
        });
        // Generate Tree
        r2.addActionListener(new ActionListener() {
            MonkeyParser parser = null;
            MonkeyScanner scanner = null;
            CharStream input=null;
            CommonTokenStream tokens = null;
            ParseTree tree;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    input = CharStreams.fromString(codeArea.getText());
                    scanner  = new MonkeyScanner(input);
                    tokens = new CommonTokenStream(scanner);
                    parser = new MonkeyParser(tokens);
                    tree = parser.program();
                    TreeViewer viewr = new TreeViewer(Arrays.asList(parser.getRuleNames()),tree);
                    viewr.open();
                } catch(Exception e1){
                    System.out.println("The file doesn't exist!");
                    e1.printStackTrace();
                }
            }
        });


        // add menu to menu bar
        menu.add(file);
        menu.add(edit);
        menu.add(run);
        return menu;
    }

    //split panels
    private JSplitPane getPanels() {

        RSyntaxTextArea textArea = new RSyntaxTextArea(40, 220);
        textArea.setMinimumSize(new Dimension(50, 300));
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.GREEN);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(10,10,10,10));
        this.codeArea = textArea;

        RTextScrollPane sp = new RTextScrollPane(textArea);
        sp.setLineNumbersEnabled(true);
        sp.setFoldIndicatorEnabled(true);

        //terminal
        JTextArea terminal  = new JTextArea("Terminal");
        terminal.setEnabled(false);
        terminal.setBackground(Color.black);
        terminal.setForeground(Color.LIGHT_GRAY);
        terminal.setMargin(new Insets(10,10,10,10));
        this.terminal = terminal;
        return new JSplitPane(JSplitPane.VERTICAL_SPLIT,  sp, terminal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new Window();
            }
        });
    }
}
