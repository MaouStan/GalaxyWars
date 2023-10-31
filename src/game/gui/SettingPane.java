package game.gui;

import static game.util.Constant.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.image.BufferedImage;
import game.components.OutlineLabel;
import game.util.ImageManager;

public class SettingPane extends JPanel {
        String[] planets = { "planet01.png", "planet02.png", "planet03.png", "planet04.png", "planet05.png",
                        "planet06.png", "planet07.png", "planet08.png", "planet09.png", "planet04.png",
                        "planet05.png",
                        "planet06.png", "planet07.png", "planet08.png", "planet09.png" };
        int pageIndex = 0;

        SettingPane() {
                setLayout(null);
                JLabel background = new JLabel();
                Image img = new ImageIcon(BG1).getImage().getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT,
                                Image.SCALE_SMOOTH);
                background.setIcon(new ImageIcon(img));
                background.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
                background.setHorizontalAlignment(JLabel.CENTER);
                background.setVerticalAlignment(JLabel.CENTER);
                add(background);

                // div 3 width for select 3 planet, bullet and meteor arrow style slide
                int div3Height = SCREEN_HEIGHT / 3 - 20;

                // calc scale iamge
                int width = (int) (SCREEN_WIDTH * 0.1);
                int height = (int) (SCREEN_HEIGHT * 0.1);
                int imgWidth = 150;

                // title Planet Outline
                OutlineLabel titlePlanet = new OutlineLabel("Planet", 2);
                titlePlanet.setOutlineColor(Color.GRAY);
                titlePlanet.setFont(fSemiBold.deriveFont(50f));
                titlePlanet.setForeground(Color.white);
                titlePlanet.setBounds(20 + titlePlanet.getWidth() / 2, 20, width, div3Height);
                titlePlanet.setHorizontalAlignment(JLabel.CENTER);
                titlePlanet.setVerticalAlignment(JLabel.CENTER);
                background.add(titlePlanet);

                // image meteor slide right side
                JLabel[] pLabels = new JLabel[planets.length];

                // calc max count for screen width
                int maxCount = SCREEN_WIDTH / (imgWidth + 20) - 2;

                // add image to right side style can slide
                pageIndex = 0;
                for (int i = 0; i < planets.length; i++) {
                        JLabel planet = new JLabel();
                        planet.setIcon(
                                        new ImageIcon(ImageManager.resizeImage("res/images/planets/" + planets[i],
                                                        imgWidth, imgWidth)));
                        if (i < maxCount) {
                                planet.setBounds(width + 50 + planet.getWidth() / 2 + i * (imgWidth + 20),
                                                20 + div3Height / 2 - imgWidth / 2, imgWidth,
                                                imgWidth);
                        }
                        planet.setHorizontalAlignment(JLabel.CENTER);
                        planet.setVerticalAlignment(JLabel.CENTER);
                        background.add(planet);
                        pLabels[i] = planet;
                }

                if (planets.length > maxCount) {
                        // add arrow
                        JLabel arrowNext = new JLabel();
                        arrowNext.setIcon(new ImageIcon(ImageManager.resizeImage("res/images/crosshair.png", 50, 50)));
                        arrowNext.setBounds(SCREEN_WIDTH - 80, 20 + div3Height / 2 - imgWidth / 2, 50, 50);
                        arrowNext.setHorizontalAlignment(JLabel.CENTER);
                        arrowNext.setVerticalAlignment(JLabel.CENTER);
                        arrowNext.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent evt) {
                                        pageIndex++;
                                        // loop copy next lastIndex
                                        String[] temp = new String[maxCount];
                                        // copy planets since last index
                                        for (int i = 0; i < maxCount; i++) {
                                                if (maxCount + i < planets.length) {
                                                        temp[i] = planets[maxCount + i];
                                                }
                                        }

                                        // update image
                                        for (int i = 0; i < pLabels.length; i++) {
                                                if (i < temp.length && temp[i] != null) {
                                                        pLabels[i].setIcon(new ImageIcon(ImageManager.resizeImage(
                                                                        "res/images/planets/" + temp[i], imgWidth,
                                                                        imgWidth)));
                                                } else {
                                                        // remove
                                                        pLabels[i].setIcon(null);
                                                }
                                        }
                                }
                        });
                        background.add(arrowNext);

                        // add arrow
                        JLabel arrowPrev = new JLabel();
                        arrowPrev.setIcon(new ImageIcon(ImageManager.resizeImage("res/images/crosshair.png", 50, 50)));
                        arrowPrev.setBounds(titlePlanet.getX() + titlePlanet.getWidth() - imgWidth / 4 + 10,
                                        20 + div3Height / 2 + imgWidth / 2, 50, 50);
                        arrowPrev.setHorizontalAlignment(JLabel.CENTER);
                        arrowPrev.setVerticalAlignment(JLabel.CENTER);
                        arrowPrev.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent evt) {
                                        pageIndex--;
                                        // loop copy next lastIndex
                                        String[] temp = new String[maxCount];
                                        // copy planets since last index
                                        for (int i = 0; i < maxCount; i++) {
                                                if (i < planets.length) {
                                                        temp[i] = planets[i];
                                                }
                                        }
                                        // update image
                                        for (int i = 0; i < pLabels.length; i++) {
                                                if (i < temp.length && temp[i] != null) {
                                                        pLabels[i].setIcon(new ImageIcon(ImageManager.resizeImage(
                                                                        "res/images/planets/" + temp[i], imgWidth,
                                                                        imgWidth)));
                                                } else {
                                                        // remove
                                                        pLabels[i].setIcon(null);
                                                }
                                        }
                                }
                        });
                        background.add(arrowPrev);
                }

                // title Bullet Outline
                OutlineLabel titleBullet = new OutlineLabel("Bullet", 2);
                titleBullet.setOutlineColor(Color.GRAY);
                titleBullet.setFont(fSemiBold.deriveFont(50f));
                titleBullet.setForeground(Color.white);
                titleBullet.setBounds(20 + titleBullet.getWidth() / 2, titlePlanet.getHeight() + 20,
                                width,
                                div3Height);
                titleBullet.setHorizontalAlignment(JLabel.CENTER);
                titleBullet.setVerticalAlignment(JLabel.CENTER);
                background.add(titleBullet);

                // title Meteor Outline
                OutlineLabel titleMeteor = new OutlineLabel("Meteor", 2);
                titleMeteor.setOutlineColor(Color.GRAY);
                titleMeteor.setFont(fSemiBold.deriveFont(50f));
                titleMeteor.setForeground(Color.white);
                titleMeteor.setBounds(20 + titleMeteor.getWidth() / 2,
                                titlePlanet.getHeight() + titleBullet.getHeight() + 20,
                                width,
                                div3Height);
                titleMeteor.setHorizontalAlignment(JLabel.CENTER);
                titleMeteor.setVerticalAlignment(JLabel.CENTER);
                background.add(titleMeteor);
        }
}
