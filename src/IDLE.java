import generated.MonkeyParser;
import generated.MonkeyScanner;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import java.awt.*;
import java.io.File;

public class IDLE {
    private static IDLE instance = null;

    public MonkeyParser parser = null;
    public MonkeyScanner scanner = null;
    public CharStream input = null;
    public CommonTokenStream tokens = null;
    public ParseTree tree = null;

    public RSyntaxTextArea codeArea = new RSyntaxTextArea();
    public RSyntaxTextArea terminal = new RSyntaxTextArea();
    public JScrollPane scrollPane = new JScrollPane(codeArea);
    public JSplitPane panel = new JSplitPane();
    public Font font = new Font("Console", Font.BOLD, 14);

    public File archive = new File("");
    public String ruta = "";
    public JFileChooser fc = new JFileChooser();


    public static IDLE getInstance(){
        if (instance == null) {
            instance = new IDLE();
        }
        return instance;
    }



    public void terminalPass() {
        this.terminal.setForeground(Color.GREEN);
    }

    public void terminalFail() {
        this.terminal.setForeground(Color.RED);
    }

    public String getXYText(int x, int y) {
        int caretPosition = y;
        int line= x;
        Element root = codeArea.getDocument().getDefaultRootElement();
        Element elem = root.getElement(x);
        int endOffset = elem.getEndOffset();
        int lineStart = elem.getStartOffset();
        String text = "";
        try {
            text = codeArea.getText(lineStart, endOffset-lineStart).trim();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return text;
    }
}
