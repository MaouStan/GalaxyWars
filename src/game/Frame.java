package game;

import javax.swing.*;

import game.gui.GameRulePane;
import game.util.SoundManager;

import java.awt.Dimension;
import static game.util.Constant.*;

public class Frame extends JFrame {
    public static Frame instance;
    public SoundManager sound;

    public Frame() {
        super("Galaxy War");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // fullScreen
        setIconImage(GAME_LOGO);
        setUndecorated(true); // no header

        // set cursor
        setCursor(CUSTOM_CURSOR);

        // ======= SINGLETON ======
        if (instance == null) {
            instance = this;
        }

    }

    public static Frame getInstance() {
        return instance;
    }

    public void changePanel(JPanel panel) {
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public class getInstance {

        public SoundManager sound;
    }
}