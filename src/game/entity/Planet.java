package game.entity;

import static game.util.Constant.*;

import game.GamePlay;
import game.util.ImageManager;
import java.awt.Graphics2D;

public class Planet extends Entity {

    private double angle = 0;

    public Planet() {
        super(SCREEN_WIDTH / 2 - PLANET_SIZE / 2, SCREEN_HEIGHT / 2 - PLANET_SIZE / 2,
                ImageManager.resizeImage("res/images/planets/planet00.png", PLANET_SIZE, PLANET_SIZE));
    }

    @Override
    public void update() {
        // check game pause
        if (GamePlay.getInstance().isPause()) {
            return;
        }
        angle += 0.01;
    }

    @Override
    public void draw(Graphics2D g2d) {
        // new g2d
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.rotate(angle, x + PLANET_SIZE / 2, y + PLANET_SIZE / 2);
        g2d2.drawImage(image, x, y, null);
        g2d2.dispose();

        // draw hit box
        g2d.drawRect(x, y, image.getWidth(), image.getHeight());
    }
}
