package game.gui;

import static game.util.Constant.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import game.components.OutlineLabel;
import game.util.FileManager;
import game.util.ImageManager;
import game.Frame;

public class SettingPane extends JPanel {
        String[] planets = FileManager.getFiles("res/images/planets");

        int pageIndex = 0;

        SettingPane() {
                setLayout(null);

                // turn back btn to MainPane
                JLabel backBtn = new JLabel();
                Image imgBack = new ImageIcon(ImageManager.resizeImage(BULLET_IMG, 59, 50)).getImage()
                                .getScaledInstance(50,
                                                50, Image.SCALE_SMOOTH);
                backBtn.setIcon(new ImageIcon(imgBack));
                backBtn.setBounds(50, 50, 50, 50);
                backBtn.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                                Frame.getInstance().changePanel(new MainPane());
                        }
                });
                add(backBtn);

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

                // calc scale image
                int width = (int) (SCREEN_WIDTH * 0.1);
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
                pageIndex = 1;
                for (int i = 0; i < planets.length; i++) {
                        JLabel planet = new JLabel();
                        if (!PLANET_IMG.equals(planets[i])) {
                                planet.setIcon(new ImageIcon(
                                                ImageManager.resizeImage(planets[i],
                                                                imgWidth, imgWidth, 0, 100)));
                        } else {
                                planet.setIcon(new ImageIcon(
                                                ImageManager.resizeImage(planets[i],
                                                                imgWidth, imgWidth)));
                        }
                        if (i < maxCount) {
                                planet.setBounds(width + 50 + planet.getWidth() / 2 + i * (imgWidth + 20),
                                                20 + div3Height / 2 - imgWidth / 2, imgWidth, imgWidth);
                        }
                        planet.setHorizontalAlignment(JLabel.CENTER);
                        planet.setVerticalAlignment(JLabel.CENTER);
                        background.add(planet);
                        pLabels[i] = planet;

                        // if planet on hover alpha it
                        final int index = i;
                        pLabels[i].addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent evt) {
                                        PLANET_IMG = planets[index];
                                        for (int j = 0; j < pLabels.length; j++) {
                                                if (j != index) {
                                                        pLabels[j].setIcon(new ImageIcon(ImageManager.resizeImage(
                                                                        planets[j], imgWidth,
                                                                        imgWidth, 0, 100)));
                                                }
                                        }
                                }

                                public void mouseEntered(MouseEvent evt) {
                                        planet.setIcon(new ImageIcon(ImageManager.resizeImage(
                                                        planets[index], imgWidth, imgWidth)));
                                }

                                public void mouseExited(MouseEvent evt) {
                                        if (!PLANET_IMG.equals(planets[index])) {
                                                planet.setIcon(new ImageIcon(ImageManager.resizeImage(
                                                                planets[index], imgWidth,
                                                                imgWidth, 0, 100)));
                                        }
                                }
                        });
                }

                if (planets.length > maxCount) {
                        final JLabel arrowPrev = new JLabel();
                        final JLabel arrowNext = new JLabel();
                        // add arrow
                        arrowNext.setIcon(new ImageIcon(ImageManager.resizeImage("res/images/crosshair.png", 50, 50)));
                        arrowNext.setBounds(SCREEN_WIDTH - 80 + 25, div3Height / 2, 50, 50);
                        arrowNext.setHorizontalAlignment(JLabel.CENTER);
                        arrowNext.setVerticalAlignment(JLabel.CENTER);
                        arrowNext.addMouseListener(new MouseAdapter() {

                                public void mouseClicked(MouseEvent evt) {
                                        pageIndex++;

                                        // visible next if
                                        if (pageIndex * maxCount >= planets.length) {
                                                arrowNext.setVisible(false);
                                        }

                                        // visible prev if
                                        if (pageIndex <= 1) {
                                                arrowPrev.setVisible(false);
                                        } else {
                                                arrowPrev.setVisible(true);
                                        }

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
                                                        // set visible
                                                        pLabels[i].setVisible(true);
                                                } else {
                                                        // remove
                                                        pLabels[i].setVisible(false);
                                                }
                                        }
                                }
                        });
                        background.add(arrowNext);

                        // add arrow
                        arrowPrev.setIcon(new ImageIcon(ImageManager.resizeImage("res/images/crosshair.png", 50, 50)));
                        arrowPrev.setBounds(titlePlanet.getX() + titlePlanet.getWidth() - imgWidth / 4 + 10,
                                        div3Height / 2, 50, 50);
                        arrowPrev.setHorizontalAlignment(JLabel.CENTER);
                        arrowPrev.setVerticalAlignment(JLabel.CENTER);
                        arrowPrev.setVisible(false);
                        arrowPrev.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent evt) {
                                        pageIndex--;

                                        // visible next if
                                        if (pageIndex > 0) {
                                                arrowNext.setVisible(true);
                                        }

                                        // visible prev if
                                        if (pageIndex <= 1) {
                                                arrowPrev.setVisible(false);
                                        }

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
                                                        // setVisible
                                                        pLabels[i].setVisible(true);
                                                } else {
                                                        // remove
                                                        pLabels[i].setVisible(false);
                                                }
                                        }
                                }
                        });
                        background.add(arrowPrev);
                }

                // title Space Outline
                OutlineLabel titleSpace = new OutlineLabel("Space",
                                2);
                titleSpace.setOutlineColor(Color.GRAY);
                titleSpace.setFont(fSemiBold.deriveFont(50f));
                titleSpace.setForeground(Color.white);
                titleSpace.setBounds(20 + titleSpace.getWidth() / 2, titlePlanet.getHeight() + 20, width, div3Height);
                titleSpace.setHorizontalAlignment(JLabel.CENTER);
                titleSpace.setVerticalAlignment(JLabel.CENTER);
                background.add(titleSpace);

                // title Meteor Outline
                OutlineLabel titleBullet = new OutlineLabel("Bullet",
                                2);
                titleBullet.setOutlineColor(Color.GRAY);
                titleBullet.setFont(fSemiBold.deriveFont(50f));
                titleBullet.setForeground(Color.white);
                titleBullet.setBounds(20 + titleBullet.getWidth() / 2,
                                titlePlanet.getHeight() + titleBullet.getHeight() + 20, width, div3Height);
                titleBullet.setHorizontalAlignment(JLabel.CENTER);
                titleBullet.setVerticalAlignment(JLabel.CENTER);
                background.add(titleBullet);
        }
}
