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

public class GameRulePane extends JPanel {
    public GameRulePane() {
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

        // Sections
        OutlineLabel section = new OutlineLabel("Game Rule", 2);
        section.setOutlineColor(Color.GRAY);
        section.setFont(fSemiBold.deriveFont(50f));
        section.setForeground(Color.white);
        section.setBounds(SCREEN_WIDTH / 2 - 200, 50, 400, 50);
        section.setHorizontalAlignment(JLabel.CENTER);
        section.setVerticalAlignment(JLabel.CENTER);
        background.add(section);

        // Slider Input Difficulty
        OutlineLabel difficulty = new OutlineLabel("Difficulty (" + METEOR_PER_LEVEL / 4 + ")", 2);
        difficulty.setOutlineColor(Color.GRAY);
        difficulty.setFont(fSemiBold.deriveFont(30f));
        difficulty.setForeground(Color.white);
        difficulty.setBounds(SCREEN_WIDTH / 2 - 200, 150, 400, 50);
        difficulty.setHorizontalAlignment(JLabel.CENTER);
        difficulty.setVerticalAlignment(JLabel.CENTER);
        background.add(difficulty);

        JSlider slider = new JSlider(10, 100, METEOR_PER_LEVEL / 4 * 10);
        slider.setBounds(SCREEN_WIDTH / 2 - 200, 200, 400, 50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(10);
        slider.setPaintTrack(true);
        slider.setSnapToTicks(true);
        slider.setOpaque(false);
        slider.setBackground(new Color(0, 0, 0, 0));
        slider.setFont(fSemiBold);
        slider.setForeground(Color.white);
        slider.addChangeListener(e -> {
            int diff = slider.getValue() / 10;
            difficulty.setText("Difficulty (" + diff + ")");

            METEOR_PER_LEVEL = diff * 4;

            if (diff > 5) {
                MAX_HEALTH = Math.max(3, 5 - (diff - 5));
            } else {
                MAX_HEALTH = diff + 5;
            }

            Frame.getInstance().requestFocusInWindow();
        });
        background.add(slider);

    }
}
