package game.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import game.GamePlay;
import game.entity.Player;

public class MouseHandler implements MouseMotionListener {

    GamePlay gp;
    Player player;

    public MouseHandler(GamePlay gp) {
        this.gp = gp;
        player = gp.getPlayer();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        player.update(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        player.update(e);
    }

}
