package game;

import javax.swing.*;

import game.controller.KeyHandler;
import game.entity.Planet;
import game.entity.Player;
import game.gui.GameOverPanel;
import game.gui.PausePanel;
import game.util.SoundManager;

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

    // PLANET
    private int health;
    private Planet planet;

    // Player
    private Player player;

    // Controller
    private KeyHandler keyHandler;

    // GUI
    private PausePanel pausePanel;
    private GameOverPanel gameOverPanel;

    public GamePlay() {
        setSize(getPreferredSize());
        setLayout(null);

        keyHandler = new KeyHandler(this);
        Frame.getInstance().addKeyListener(keyHandler);

        // remove sound frame
        Frame.getInstance().sound.stop();

        // new sound
        Frame.getInstance().sound = new SoundManager(bgm2);
        Frame.getInstance().sound.play(true);

        init();
    }

    private void init() {
        setLayout(null);
        removeAll();
        repaint();
        keyHandler = new KeyHandler(this);
        Frame.getInstance().addKeyListener(keyHandler);

        gameOver = false;
        isPause = false;
        seconds = 0;
        score = 0;
        health = MAX_HEALTH;
        planet = new Planet();
        player = new Player();
    }

    public void start() {
        init();
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        if (thread != null) {
            thread = null;
        }
        // remove other components
        removeAll();
    }

    public void restart() {
        stop();
        start();
    }

    public void togglePause() {
        isPause = !isPause;
    }

    private void update() {
        planet.update();
        player.update();
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

        // Planet
        planet.draw(graphics2d);

        // Player
        player.draw(graphics2d);
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

                    // remove pause panel if has
                    if (pausePanel != null) {
                        remove(pausePanel);
                        pausePanel = null;
                    }

                    if (currentTimeSecond - oldSecondTime >= 1_000_000_000.0f) {
                        seconds++;
                        oldSecondTime = currentTimeSecond;
                    }
                } else {
                    // add pause panel
                    pausePanel = new PausePanel(this);
                    add(pausePanel);
                    // pause until unpause
                    while (isPause) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // Decrement delta
                delta--;
            }
        }
        // gameOverPanel
        repaint();
        gameOverPanel = new GameOverPanel(this);
        gameOverPanel.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        add(gameOverPanel);
        Frame.getInstance().removeKeyListener(keyHandler);
    }

}
