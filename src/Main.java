

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){
            // TODO Handler
        }
        Opener ventanaAbrir = new Opener();
        ventanaAbrir.setVisible(true);
        ventanaAbrir.setDefaultCloseOperation(2);
    }
}

