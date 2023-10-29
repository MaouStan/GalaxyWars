
package game.entity;

import static game.util.Constant.*;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import game.util.ImageManager;
import java.awt.Color;

public class Bullet extends Entity {

    private double deg;
    public int realX, realY;
    private int speed;

    public Bullet(MouseEvent e) {
        super(-PLAYER_WIDTH / 2 + BULLET_WIDTH / 2 - BULLET_WIDTH / 4, -PLAYER_HEIGHT * 2 - BULLET_HEIGHT * 2 + 25,
                ImageManager.resizeImage(BULLET_IMG, BULLET_WIDTH, BULLET_HEIGHT));

        deg = Math.atan2(e.getX() - (SCREEN_WIDTH / 2) - Math.ceil(PLAYER_HEIGHT / 4),
                -(e.getY() - (SCREEN_HEIGHT / 2)) + PLAYER_WIDTH / 2);

        // real Pos
        speed = BULLET_SPEED * 10;
    }

    public boolean isOutOfScreen() {
        return realX < -BULLET_WIDTH / 2 || realX > SCREEN_WIDTH + BULLET_WIDTH / 2 || realY < -BULLET_HEIGHT / 2
                || realY > SCREEN_HEIGHT + BULLET_HEIGHT / 2;
    }

    @Override
    public boolean intersect(Entity entity) {
        if (entity instanceof Meteor) {
            Meteor meteor = (Meteor) entity;
            return this.realX < meteor.x + meteor.image.getWidth() &&
                    this.realX + this.image.getWidth() > meteor.x &&
                    this.realY < meteor.y + meteor.image.getHeight() &&
                    this.realY + this.image.getHeight() > meteor.y;
        }
        return false;
    }

    @Override
    public void update() {

        y -= speed;

        realX = (int) (-(y + speed / 2) * Math.sin(deg));
        realY = (int) (+(y + speed / 2) * Math.cos(deg));

        realX += SCREEN_WIDTH / 2 - BULLET_WIDTH / 2 - 5;
        realY += SCREEN_HEIGHT / 2 - BULLET_HEIGHT / 2 - 5;

    }

    @Override
    public void draw(Graphics2D g2d) {
        // new g2d
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.translate(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        g2d2.rotate(deg);
        g2d2.drawImage(image, x, y, null);
        g2d2.drawRect(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        g2d2.dispose();

        g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.red);
        g2d2.drawRect(realX, realY, BULLET_WIDTH, BULLET_HEIGHT);
        g2d2.dispose();
    }
}