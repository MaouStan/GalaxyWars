package game;

import javax.swing.*;

import game.controller.KeyHandler;
import game.controller.MouseHandler;
import game.entity.Bullet;
import game.entity.Meteor;
import game.entity.Planet;
import game.entity.Player;
import game.gui.GameOverPanel;
import game.gui.PausePanel;
import game.util.SoundManager;

import java.awt.*;
import static game.util.Constant.*;
import java.lang.Runnable;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class GamePlay extends JPanel implements Runnable {
    // GAME
    private boolean gameOver;
    private boolean isPause;
    private Thread thread;
    private int seconds;
    private int score;
    private int level;
    private int spawnedMeteor;
    private int killedMeteor;
    private long lastTimeMeteorSpawn;
    private long nextDelaySpawn;
    private Timer timer;
    private boolean inTimer = false;

    // ITEM
    private int automaticTime;
    private int freezeTime;
    private int protectTime;

    // PLANET
    private int health;
    private Planet planet;

    // Player
    private Player player;

    // Bullet
    private ArrayList<Bullet> bullets;

    // Meteor
    private ArrayList<Meteor> meteors;

    // Controller
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    // GUI
    private PausePanel pausePanel;
    private GameOverPanel gameOverPanel;

    public GamePlay() {
        setSize(getPreferredSize());
        setLayout(null);

        inTimer = false;

        if (sound_state) {
            // remove sound frame
            Frame.getInstance().sound.stop();

            // new sound
            Frame.getInstance().sound = new SoundManager(bgm2);
            Frame.getInstance().sound.play(true);
        }
    }

    private void init() {
        setLayout(null);
        removeAll();
        repaint();

        gameOver = false;
        isPause = false;

        freezeTime = 0;
        protectTime = 0;
        automaticTime = 0;

        seconds = 0;

        score = 0;

        spawnedMeteor = 0;
        killedMeteor = 0;
        nextDelaySpawn = 0;

        level = 0;
        health = MAX_HEALTH;
        lastTimeMeteorSpawn = System.currentTimeMillis();
        planet = new Planet();
        player = new Player(this);
        bullets = new ArrayList<Bullet>();
        meteors = new ArrayList<Meteor>();

        keyHandler = new KeyHandler(this);
        Frame.getInstance().addKeyListener(keyHandler);

        mouseHandler = new MouseHandler(this);
        Frame.getInstance().addMouseListener(mouseHandler);
        Frame.getInstance().addMouseMotionListener(mouseHandler);
    }

    public void start() {
        init();
        thread = new Thread(this);

        SoundManager.play(COUNT_SOUND);

        if (inTimer)
            return;

        inTimer = true;

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                level++;
                killedMeteor = 0;
                spawnedMeteor = 0;
                nextDelaySpawn = random(100, ENEMY_SPAWN_DELAY);
                METEOR_SPEED = Math.min(level, 5);
                timer.cancel();
                inTimer = false;
            }
        }, 3500, 3500);

        thread.start();
    }

    public void stop() {
        if (thread != null) {
            thread = null;
        }
        // remove other components
        removeAll();
    }

    public void gameOver() {
        repaint();
        gameOverPanel = new GameOverPanel(this);
        gameOverPanel.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        add(gameOverPanel);

        Frame.getInstance().removeKeyListener(keyHandler);
        Frame.getInstance().removeMouseListener(mouseHandler);
        Frame.getInstance().removeMouseMotionListener(mouseHandler);
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

        // bullet for i
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update();
            if (bullet.isOutOfScreen()) {
                bullets.remove(i);
                i--;
            }
        }

        // meteor for i
        for (int i = 0; i < meteors.size(); i++) {
            Meteor meteor = meteors.get(i);
            meteor.update();
            if (meteor.exploding) {
                continue;
            }
            if (meteor.intersect(planet)) {
                meteors.remove(i);
                i--;
                health--;
            } else if (meteor.destroyed) {
                meteors.remove(i);
                i--;
            }
        }

        // meteor and bullet
        for (int i = 0; i < meteors.size(); i++) {
            Meteor meteor = meteors.get(i);
            if (meteor.exploding) {
                continue;
            }
            for (int j = 0; j < bullets.size(); j++) {
                Bullet bullet = bullets.get(j);
                if (bullet.intersect(meteor)) {
                    // meteors.remove(i);
                    // i--;

                    meteor.explosion();

                    bullets.remove(j);
                    j--;
                    score += 10;
                    killedMeteor++;
                }
            }
        }

        // spawn ENEMY_SPAWN_DELAY
        if (System.currentTimeMillis() - lastTimeMeteorSpawn > nextDelaySpawn && spawnedMeteor < level * 5
                && spawnedMeteor < MAX_COUNT_METEOR) {
            lastTimeMeteorSpawn = System.currentTimeMillis();
            meteors.add(new Meteor());
            spawnedMeteor++;
            nextDelaySpawn = random(100, ENEMY_SPAWN_DELAY);
        }

        // level up
        if (level != 0 && spawnedMeteor == Math.min(MAX_COUNT_METEOR, level * 5)
                && killedMeteor >= Math.min(MAX_COUNT_METEOR, level * 5)) {

            if (!inTimer) {
                inTimer = true;
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        level++;
                        killedMeteor = 0;
                        spawnedMeteor = 0;
                        nextDelaySpawn = random(100, ENEMY_SPAWN_DELAY);
                        METEOR_SPEED = Math.min(level, 5);
                        timer.cancel();
                        inTimer = false;
                    }
                }, 2000, 2000);
            }
        }

        if (health <= 0) {
            gameOver = true;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    private int random(int from, long enemySpawnDelay) {
        return (int) (Math.floor(Math.random() * (enemySpawnDelay - from + 1)) + from);
    }

    public void applayEffect(int effect) {
        switch (effect) {
            case 0:
                freezeTime = FREEZE_TIME;
                break;
            case 1:
                protectTime = PROTECT_TIME;
                break;
            case 2:
                automaticTime = AUTOMATIC_TIME;
                break;
        }
    }

    public boolean isFreeze() {
        return freezeTime > 0;
    }

    public boolean isAutomatic() {
        return automaticTime > 0;
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

        // Level
        graphics2d.setColor(Color.white);
        graphics2d.setFont(fSemiBold.deriveFont(Font.BOLD, 30f));
        String levelString = String.format("Level : %02d", level);
        graphics2d.drawString(levelString, 15, 100);

        // Enemy Count size / max
        graphics2d.setColor(Color.white);
        graphics2d.setFont(fSemiBold.deriveFont(Font.BOLD, 30f));
        String enemyString = String.format("%02d / %02d", killedMeteor, Math.min(MAX_COUNT_METEOR, level * 5));
        int enemyWidth = graphics2d.getFontMetrics().stringWidth(enemyString);
        graphics2d.drawString(enemyString, SCREEN_WIDTH - enemyWidth - 50, 100);

        // Planet
        planet.draw(graphics2d);

        // Player
        player.draw(graphics2d);

        // Bullet
        ArrayList<Bullet> bulletsCopy = new ArrayList<>(bullets);
        for (Bullet bullet : bulletsCopy) {
            bullet.draw(graphics2d);
        }

        // Meteor
        ArrayList<Meteor> meteorsCopy = new ArrayList<>(meteors);
        for (Meteor meteor : meteorsCopy) {
            meteor.draw(graphics2d);
        }

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
        gameOver();
    }

}
