import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Window extends JFrame {
    private RSyntaxTextArea codeArea;
    private JTextArea terminal;
    public JMenu file;
    public JMenu run;
    private File archive;
    private String ruta;
    public JPanel panel;
    private JFileChooser fc = new JFileChooser();

    public Window() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
/*
        // File Options
        JMenuItem f1,f2,f3,f4,f5;
        f1 = new JMenuItem("New File");
        f2 = new JMenuItem("Open File");
        f3 = new JMenuItem("Save File");
        f4 = new JMenuItem("Save File As");
        f5 = new JMenuItem("Close");
        file = new JMenu();
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);
        file.add(f5);

        JMenuItem r1,r2;
        r1 = new JMenuItem("Run");
        r2 = new JMenuItem("Generate Tree");
        run = new JMenu();
        run.add(r1);
        run.add(r2);

        add(this.file);
        add(this.run);

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
                    } catch(Exception e1){System.out.println("Error con Archivo");e1.printStackTrace();}
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
                } catch(Exception e1){System.out.println("Error con Archivo");e1.printStackTrace();}
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
                    java.io.File fi  = fsave.getSelectedFile();
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

                } catch(Exception e1){System.out.println("The file doesn't exist!");e1.printStackTrace();}

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
                } catch(Exception e1){System.out.println("The file doesn't exist!");e1.printStackTrace();}

            }
        });


 */


        add(getPanels(), BorderLayout.CENTER);
        this.setVisible(true);


    }


    //menu

    //split panels
    private JSplitPane getPanels() {

        //JPanel jPanel = new JPanel();
        //jPanel.add(file, run);

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

        //jPanel.add(sp);

        //terminal
        JTextArea terminal  = new JTextArea("Terminal");
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
