package frontend;

import backend.IDLE;
import org.antlr.v4.gui.TreeViewer;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

//Class Window, will show the code area and the terminal too
//----------------------------------------------------------------------------------------------------------------------
public class Window extends JFrame {
    public Window() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setJMenuBar(getMenu());
        add(IDLE.getInstance().getPanels());
        pack();
        this.setVisible(true);
    }

    //------------------------------------------------------------------------------------------------------------------
    //Menu
    public JMenuBar getMenu(){
        JMenuBar menu;
        JMenu file ;
        JMenu edit;
        JMenu run;
        JMenu view;
        menu = new JMenuBar();

        //-----------------------------------------------------
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

        //-----------------------------------------------------
        // create a Edit options
        edit = new JMenu("Edit");

        // create menuitems
        ActionMap actions = IDLE.getInstance().codeArea.getActionMap();
        Action copy = actions.get(DefaultEditorKit.copyAction);
        Action paste = actions.get(DefaultEditorKit.pasteAction);
        Action cut = actions.get(DefaultEditorKit.cutAction);

        // basics events
        copy.putValue(Action.NAME, "Copy");
        copy.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getAWTKeyStroke('C', InputEvent.CTRL_MASK));

        cut.putValue(Action.NAME, "Cut");
        cut.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getAWTKeyStroke('X', InputEvent.CTRL_MASK));

        paste.putValue(Action.NAME, "Paste");
        paste.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getAWTKeyStroke('V', InputEvent.CTRL_MASK));

        // add menu items to menu
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);

        //-----------------------------------------------------
        // create a Run options
        run = new JMenu("Run");

        // Run Options
        JMenuItem r1,r2;
        r1 = new JMenuItem("Run");
        r2 = new JMenuItem("Generate Tree");
        run.add(r1);run.add(r2);


        //-----------------------------------------------------
        // create a View options
        view = new JMenu("View");

        // create menuitems
        JMenuItem v1,v2;
        v1 = new JMenuItem("Symbol Table");
        v2 = new JMenuItem("Console Tree");
        // add menu items to menu
        view.add(v1);view.add(v2);

        //--------------------------------------------------------------------------------------------------------------
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
                IDLE.getInstance().openFile();
            }
        });

        // Save File
        f3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDLE.getInstance().saveFile();
            }
        });

        // Save File As
        f4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDLE.getInstance().saveFileAs();
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
                IDLE.getInstance().run();
            }
        });
        // Generate Tree
        r2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    TreeViewer viewr = IDLE.getInstance().generateTree();
                    viewr.open();
                } catch(Exception e1){
                    IDLE.getInstance().terminal.setText(e1.getMessage());
                }
            }
        });
        //Symbol's Table
        v1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDLE.getInstance().showTableSymbol = true;
                IDLE.getInstance().run();
            }
        });
        // Console Tree
        v2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDLE.getInstance().showConsoleTree = true;
                IDLE.getInstance().run();
            }
        });
        //-----------------------------------------------------
        // add menu to menu bar
        menu.add(file);
        menu.add(edit);
        menu.add(run);
        menu.add(view);
        return menu;
    }

    //------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new Window();
            }
        });
    }
}
