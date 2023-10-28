package game;

import javax.swing.*;
import java.awt.*;
import static game.util.Constant.*;
import java.lang.Runnable;

public class GamePlay extends JPanel implements Runnable {

    // GAME
    private boolean gameOver;
    private boolean isPause;
    private Thread thread;
    private int seconds;
    private int score;

    // PLAYER
    private int health;

    public GamePlay() {
        setLayout(null);
        init();
    }

    private void init() {
        gameOver = false;
        isPause = false;
        seconds = 0;
        score = 0;
        health = MAX_HEALTH;
    }

    public void start() {
        init();
        thread = new Thread(this);
        thread.start();
    }

    private void update() {
    }

    public void run() {
        // Initialize time variables
        long oldTime = System.nanoTime();
        long currentTime = System.nanoTime();
        long oldSecondTime = System.nanoTime();
        long currentTimeSecond = System.nanoTime();

        // Initialize delta and frame rate variables
        double delta = 0.0f;
        double ns = 1_000_000_000.0f / FRAME_RATE;

        // Game loop
        while (!gameOver) {
            // Repaint the game
            repaint();

            // Update time variables
            oldTime = currentTime;
            currentTime = System.nanoTime();

            // Update time counter variables
            currentTimeSecond = System.nanoTime();

            // Calculate delta
            delta += (currentTime - oldTime) / ns;

            // Update game state while delta is greater than or equal to 1
            while (delta >= 1) {
                // Update game if not paused
                if (!isPause) {
                    update();
                    if (currentTimeSecond - oldSecondTime >= 1_000_000_000.0f) {
                        seconds++;
                        oldSecondTime = currentTimeSecond;
                    }

                }
                // Decrement delta
                delta--;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BG2, 0, 0, null);
        Graphics2D graphics2d = (Graphics2D) g;

        // Time Counter Center Top "00:00:00"
        graphics2d.setColor(Color.white);
        graphics2d.setFont(fSemiBold.deriveFont(Font.BOLD, 30f));

        String timeString = String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
        int width = graphics2d.getFontMetrics().stringWidth(timeString);
        graphics2d.drawString(timeString, SCREEN_WIDTH / 2 - width / 2, 50);

        // Heart and GrayHeart
        for (int i = 0; i < MAX_HEALTH; i++) {
            if (i < health) {
                g.drawImage(ITEM_HEART, 10 + i * 35, 10, 40, 40, null);
            } else {
                g.drawImage(ITEM_GRAY_HEART, 10 + i * 35, 10, 40, 40, null);
            }
        }

        // Score
        graphics2d.setColor(Color.white);
        graphics2d.setFont(fSemiBold.deriveFont(Font.BOLD, 30f));
        String scoreString = String.format("%06d", score);
        int scoreWidth = graphics2d.getFontMetrics().stringWidth(scoreString);
        graphics2d.drawString(scoreString, SCREEN_WIDTH - scoreWidth - 50, 50);
    }
}
