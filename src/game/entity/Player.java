package game.entity;

import static game.util.Constant.PLAYER_SIZE;
import static game.util.Constant.SCREEN_HEIGHT;
import static game.util.Constant.SCREEN_WIDTH;

import java.awt.Graphics2D;

import game.util.ImageManager;

public class Player extends Entity {

    public Player() {
        super(0, 0, ImageManager.resizeImage("res/images/player.png", PLAYER_SIZE, PLAYER_SIZE));
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g2d) {
        // new g2d
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        // trans to center
        g2d2.translate(SCREEN_WIDTH / 2 - PLAYER_SIZE / 2, SCREEN_HEIGHT / 2 - PLAYER_SIZE / 2);
        g2d2.drawImage(image, 0, 0, null);
        g2d2.dispose();
    }

}
