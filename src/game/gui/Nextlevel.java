package game.gui;

import javax.swing.*;

import game.GamePlay;
import game.util.ImageManager;

import static game.util.Constant.PLANET_SIZE;
import static game.util.Constant.PLAYER_HEIGHT;
import static game.util.Constant.PLAYER_WIDTH;
import static game.util.Constant.fSemiBold;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class NextLevel extends JPanel {
    private BufferedImage LOGO;
    private BufferedImage SPACE;
    private int spaceX;
    private Timer timer;
    private long startTime;
    private boolean isRunning;
    private int lineLength;

    GamePlay gp;

    public NextLevel(GamePlay gp) {
        this.gp = gp;

        isRunning = true;

        setLayout(null);
        setBounds(0, 0, gp.getWidth(), gp.getHeight());
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 125));

        LOGO = ImageManager.resizeImage("res/images/nextlevelxx.Wpng.png", PLANET_SIZE * 2, PLANET_SIZE * 2);
        SPACE = ImageManager.resizeImage("res/images/player-next.png", PLAYER_WIDTH, PLAYER_HEIGHT);

        spaceX = -SPACE.getWidth();
        lineLength = 0;

        Timer delayTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTime = System.currentTimeMillis();
                timer.start();
            }
        });
        delayTimer.setRepeats(false); // Make the timer only fire once
        delayTimer.start();

        timer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                double progress = elapsedTime / 5000.0; // 5 seconds = 5000 milliseconds

                if (progress < 0.4) {
                    // First phase: move to center over 2 seconds
                    spaceX = (int) (-SPACE.getWidth() + progress * 2.5 * (getWidth() / 2 + SPACE.getWidth() / 2));
                } else if (progress < 0.6) {
                    // Second phase: pause at center for 1 second
                    spaceX = (getWidth() - SPACE.getWidth()) / 2;
                } else {
                    // Third phase: move to end over 2 seconds
                    spaceX = (int) ((getWidth() - SPACE.getWidth()) / 2
                            + (progress - 0.6) * 2.5 * (getWidth() / 2 + SPACE.getWidth() / 2));
                }
                if (progress >= 1.0) {
                    timer.stop();
                    isRunning = false;
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw image center
        g.drawImage(LOGO, getWidth() / 2 - LOGO.getWidth() / 2, getHeight() / 2 - (LOGO.getHeight() / 2) * 2,
                null);

        // Draw String "Next Level" center
        g.setColor(Color.white);
        g.setFont(fSemiBold.deriveFont(Font.BOLD, 40f));
        // calc String Width
        int width = g.getFontMetrics().stringWidth("Next Level");
        g.drawString("Next Level", getWidth() / 2 - width / 2, getHeight() / 2);

        // Draw image
        g.drawImage(SPACE, spaceX - SPACE.getWidth() / 2, getHeight() / 2 + (SPACE.getHeight() / 2) * 2,
                null);

        // Draw Line trail width of SPACE
        g.setColor(Color.white);
        g.fillRect(0, getHeight() / 2 + (SPACE.getHeight() / 2) * 3, spaceX - SPACE.getWidth() / 2, 5);

    }

    public boolean isRunning() {
        return isRunning;
    }
}