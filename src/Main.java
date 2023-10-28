
import javax.swing.SwingUtilities;

import game.Frame;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Frame();
        });
    }
}