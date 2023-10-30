
import javax.swing.SwingUtilities;

import game.Frame;
import game.gui.MainPane;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame window = new Frame();
            window.changePanel(new MainPane());
            window.setVisible(true);
        });
    }
}