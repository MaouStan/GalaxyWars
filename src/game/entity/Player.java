package game.entity;

import static game.util.Constant.*;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import game.GamePlay;
import game.util.ImageManager;
import game.util.SoundManager;

public class Player extends Entity {

    private double deg;
    GamePlay gp;

    public Player(GamePlay gp) {
        super(-PLAYER_WIDTH / 2, -PLAYER_HEIGHT * 2 - 50,
                ImageManager.resizeImage(PLAYER_IMG, PLAYER_WIDTH, PLAYER_HEIGHT));
        deg = 0;
        this.gp = gp;
    }

    public void shoot(MouseEvent e) {
        Bullet bullet = new Bullet(e);
        gp.addBullet(bullet);
        SoundManager.play(SHOOT_SOUND);
    }

    @Override
    public void update() {
    }

    public void update(MouseEvent e) {
        if (GamePlay.getInstance().isPause()) {
            return;
        }

        deg = Math.atan2(e.getX() - (SCREEN_WIDTH / 2) - Math.ceil(PLAYER_HEIGHT / 4),
                -(e.getY() - (SCREEN_HEIGHT / 2)) + PLAYER_WIDTH / 2);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // new g2d
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.translate(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        g2d2.rotate(deg);
        g2d2.drawImage(image, x, y, null);
        g2d2.drawRect(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        g2d2.dispose();
    }

}
