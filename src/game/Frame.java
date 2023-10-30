package game;

import javax.swing.*;

import game.gui.MainPane;
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
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(GAME_LOGO);
        setUndecorated(true); // no header
        changePanel(new MainPane());

        // set cursor
        setCursor(CUSTOM_CURSOR);

        // sound
        sound = new SoundManager(bgm1);
        sound.play(true);

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