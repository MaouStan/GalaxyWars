package game.gui;

import javax.swing.*;

import game.Frame;
import game.components.OutlineLabel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static game.util.Constant.*;

public class SettingPane extends JPanel {
    public SettingPane() {
        setLayout(null);

        // add key listening esc to goto main
        Frame.getInstance().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    Frame.getInstance().changePanel(new MainPane());
                    Frame.getInstance().clearEvent();
                }
            }
        });

        // set Background Image
        JLabel background = new JLabel();
        Image img = new ImageIcon(BG1).getImage().getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(img));
        background.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        background.setHorizontalAlignment(JLabel.CENTER);
        background.setVerticalAlignment(JLabel.CENTER);
        add(background);

        // Turn Back to MainPane Menu
        JLabel back = new JLabel();
        back.setIcon(new ImageIcon(BULLET_IMG));
        back.setBounds(50, 50, 50, 50);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Frame.getInstance().changePanel(new MainPane());
                Frame.getInstance().clearEvent();
            }
        });
        background.add(back);

        // Split Screen width div 3 padding 20
        int padding = 20;
        int widthDiv3 = (SCREEN_WIDTH - padding * 2) / 3;

        // section 1 GameSetting
        OutlineLabel gameSetting = new OutlineLabel("Game Setting", 2);
        gameSetting.setBounds(padding, 150, widthDiv3, 50);
        gameSetting.setHorizontalAlignment(JLabel.CENTER);
        gameSetting.setFont(fSemiBold.deriveFont(30f));
        gameSetting.setVerticalAlignment(JLabel.CENTER);
        background.add(gameSetting);
    }
}
