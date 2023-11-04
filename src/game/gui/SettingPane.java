package game.gui;

import javax.swing.*;

import game.Frame;
import game.components.OutlineLabel;
import game.util.SoundManager;

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

        // =============== section 1 GameSetting ================ \\
        OutlineLabel gameSetting = new OutlineLabel("Game Setting", 2);
        gameSetting.setBounds(padding, 50, widthDiv3, 50);
        gameSetting.setFont(fSemiBold.deriveFont(30f));
        gameSetting.setHorizontalAlignment(JLabel.CENTER);
        gameSetting.setVerticalAlignment(JLabel.CENTER);
        background.add(gameSetting);

        // radio slider config volume
        JLabel volume = new JLabel(Float.toString(SoundManager.getVolumeNumber()));
        volume.setIcon(new ImageIcon(SOUND_ON));
        volume.setForeground(Color.white);
        volume.setHorizontalAlignment(JLabel.CENTER);
        volume.setVerticalAlignment(JLabel.CENTER);
        volume.setFont(fRegular.deriveFont(30f));
        volume.setBounds(padding, 150, widthDiv3, 50);
        background.add(volume);
        JSlider slVolume = new JSlider(0, 100, (int) (SoundManager.getVolumeNumber()));
        slVolume.setBounds(padding + widthDiv3 / 4, 200, widthDiv3 / 2, 50);
        slVolume.setMajorTickSpacing(1);
        slVolume.setMinorTickSpacing(1);
        slVolume.setPaintTicks(true);
        slVolume.setPaintLabels(false);
        slVolume.setOpaque(false);
        slVolume.addChangeListener(e -> {
            SoundManager.setVolumeNumber((float) slVolume.getValue());
            volume.setText(Float.toString(SoundManager.getVolumeNumber()));
        });
        background.add(slVolume);

        // =============== section 2 Selector ================ \\

        OutlineLabel selector = new OutlineLabel("Selector", 2);
        selector.setBounds(widthDiv3 + padding, 50, widthDiv3, 50);
        selector.setFont(fSemiBold.deriveFont(30f));
        selector.setHorizontalAlignment(JLabel.CENTER);
        selector.setVerticalAlignment(JLabel.CENTER);
        background.add(selector);

        // =============== section 3 Game Rule ================ \\
        OutlineLabel gameRule = new OutlineLabel("Game Rule", 2);
        gameRule.setBounds(widthDiv3 * 2 + padding, 50, widthDiv3, 50);
        gameRule.setFont(fSemiBold.deriveFont(30f));
        gameRule.setHorizontalAlignment(JLabel.CENTER);
        gameRule.setVerticalAlignment(JLabel.CENTER);
        background.add(gameRule);
    }
}
