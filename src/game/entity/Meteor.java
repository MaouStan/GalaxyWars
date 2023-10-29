package game.entity;

import static game.util.Constant.*;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import game.util.ImageManager;
import game.util.SoundManager;

public class Meteor extends Entity {
    double size;
    double deg;
    public boolean destroyed = false;
    Point target;

    // Explosion animation variables
    private BufferedImage spriteExplosion;
    private int explosionState = 0;
    private int explosionStateX = 0;
    private int explosionStateY = 0;
    public boolean exploding = false;

    public Meteor() {
        super(0, 0, null);
        int type = random(1, 4);
        double coordsX = 0;
        double coordsY = 0;

        switch (type) {
            case 1:
                coordsX = random(0, SCREEN_WIDTH);
                coordsY = 0 - 150;
                break;
            case 2:
                coordsX = SCREEN_WIDTH + 150;
                coordsY = random(0, SCREEN_HEIGHT);
                break;
            case 3:
                coordsX = random(0, SCREEN_WIDTH);
                coordsY = SCREEN_HEIGHT + 150;
                break;
            case 4:
                coordsX = 0 - 150;
                coordsY = random(0, SCREEN_HEIGHT);
                break;
        }

        x = (int) coordsX;
        y = (int) coordsY;
        size = 3;
        deg = Math.atan2(coordsX - (SCREEN_WIDTH / 2), -(coordsY - (SCREEN_HEIGHT / 2)));
        image = ImageManager.resizeImage("res/images/meteor.png", METEOR_SIZE, METEOR_SIZE);

        target = new Point();
        target.x = (int) (SCREEN_WIDTH / 2);
        target.y = (int) (SCREEN_HEIGHT / 2);

        // Load explosion sprite sheet
        spriteExplosion = ImageManager.load("res/images/effects/explosion.png");
    }

    private int random(int from, int to) {
        return (int) (Math.floor(Math.random() * (to - from + 1)) + from);
    }

    public void explosion() {
        if (!destroyed && !exploding) {
            exploding = true;
            SoundManager.play(BOOM_SOUND);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    explosionStateX = (explosionState % 4) * 256;
                    explosionStateY = (explosionState / 4) * 256;
                    explosionState++;
                    if (explosionState == 16) {
                        destroyed = true;
                        exploding = false;
                        timer.cancel();
                    }
                }
            }, 0, 50);
        }
    }

    @Override
    public void update() {
        if (destroyed) {
            return;
        }
        if (exploding) {
            return;
        }
        double dx = target.x - x;
        double dy = target.y - y;
        double distance = Math.max(1, (int) Math.sqrt(dx * dx + dy * dy));
        x += (int) (dx / distance * size);
        y += (int) (dy / distance * size);
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (exploding) {
            g2d.drawImage(spriteExplosion, x - 128, y - 128, x + 128, y + 128, explosionStateX, explosionStateY,
                    explosionStateX + 256, explosionStateY + 256, null);
        } else if (!destroyed) {
            g2d.drawImage(image, x, y, null);
        }
    }
}