import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

import generated.MonkeyParser;
import generated.MonkeyScanner;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

public class Opener extends JFrame{

    // -- JFrame Vars --
    private JPanel panel;
    private JTextArea textArea2;
    private JMenu File;
    private JMenu Run;
    private JTextArea textArea1;
    private JScrollPane jsp;


    // -- Other Vars --

    File archive;
    String ruta;
    CharStream input=null;
    JFileChooser fc = new JFileChooser();
    private static JTextArea lines;

    public Opener() {

        // -- Frame Settings --

        add(panel);
        setSize(700,600);
        setLocationRelativeTo(null);
        setTitle("Editor");
        Highlighter.HighlightPainter redPainter ; // Color de higlight en el texto

        // -- Menu Bar Settings --

        // File Options

        JMenuItem f1,f2,f3,f4,f5;
        f1 = new JMenuItem("New File");
        f2 = new JMenuItem("Open File");
        f3 = new JMenuItem("Save File");
        f4 = new JMenuItem("Save File As");
        f5 = new JMenuItem("Close");
        File.add(f1);File.add(f2); File.add(f3);File.add(f4);File.add(f5);

        /*
        //Edit Options
        JMenuItem e1;
        e1 = new JMenuItem("Edit");
        Edit.add(e1);
        */

        // Run Options
        JMenuItem r1,r2;
        r1 = new JMenuItem("Run");
        r2 = new JMenuItem("Generate Tree");
        Run.add(r1);Run.add(r2);

        // -- RowHeader --

        lines = new JTextArea("1");
        lines.setBackground(Color.LIGHT_GRAY);
        lines.setEditable(false);
        textArea1.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int caretPosition = textArea1.getDocument().getLength();
                Element root = textArea1.getDocument().getDefaultRootElement();
                String text = "1" + System.getProperty("line.separator");
                for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                    text += i + System.getProperty("line.separator");
                }
                return text;
            }
            @Override
            public void changedUpdate(DocumentEvent de) {
                lines.setText(getText());
            }
            @Override
            public void insertUpdate(DocumentEvent de) {
                lines.setText(getText());
            }
            @Override
            public void removeUpdate(DocumentEvent de) {
                lines.setText(getText());
            }
        });
        jsp.getViewport().add(textArea1);
        jsp.setRowHeaderView(lines);

        redPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);


        // -- Action Listeners --

        // New File
        f1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
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
                        textArea1.setText(CharStreams.fromFileName(ruta).toString());
                    } catch(Exception e1){System.out.println("Error con Archivo");e1.printStackTrace();}
                }
            }
        });
        // Save File
        f3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String content = textArea1.getText();
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
                    String content = textArea1.getText();
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
                    textArea1.getHighlighter().addHighlight(1, 1, redPainter);

                    input = CharStreams.fromString(textArea1.getText());
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
                        System.out.println("Compilation: Failed");
                        System.out.println(errorListener.toString());
                    }
                    else{
                        textArea2.setText("Tree: " + tree.toStringTree(parser) + "\nCompilation: Successful");
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
                    input = CharStreams.fromString(textArea1.getText());
                    scanner  = new MonkeyScanner(input);
                    tokens = new CommonTokenStream(scanner);
                    parser = new MonkeyParser(tokens);
                    tree = parser.program();
                    //Arbol mediante String
                    //System.out.println(tree.toStringTree(parser));
                    TreeViewer viewr = new TreeViewer(Arrays.asList(parser.getRuleNames()),tree);
                    viewr.open();
                } catch(Exception e1){System.out.println("No hay archivo");e1.printStackTrace();}

            }
        });

    }
}