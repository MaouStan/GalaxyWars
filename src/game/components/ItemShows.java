package game.components;

import javax.swing.JPanel;

import javax.swing.JLabel;

import java.awt.Color;
import static game.util.Constant.*;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class ItemShows extends JPanel {
    private final BufferedImage[] ITEM_IMAGE = {
            ITEM_HEART,
            ITEM_SHIELD,
            ITEM_FREEZE,
            ITEM_DAMAGE };
    private final String[] ITEM_NAME = { "Health +1", "Protect 3 sec", "Freeze 3 sec", "Damage Up" };

    public ItemShows(int spaceY) {
        setLayout(null);
        setBackground(new Color(255, 255, 255, 0));

        // Title "Item"
        OutlineLabel title = new OutlineLabel("Item", 2);
        title.setOutlineColor(Color.GRAY);
        title.setFont(fRegular.deriveFont(Font.BOLD, 30f));
        title.setForeground(Color.white);
        title.setBounds(0, 0, 200, 50);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        add(title);

        for (int i = 0; i < ITEM_IMAGE.length; i++) {
            // ICON
            JLabel icon = new JLabel();
            icon.setIcon(new ImageIcon(ITEM_IMAGE[i]));
            icon.setBounds(0, i * spaceY + 50, 50, 50);
            add(icon);

            // NAME
            OutlineLabel name = new OutlineLabel(ITEM_NAME[i], 1);
            name.setOutlineColor(Color.GRAY);
            name.setForeground(Color.white);
            name.setFont(fRegular.deriveFont(Font.BOLD, 16f));
            name.setBounds(60, i * spaceY + 50, 250, 50);
            add(name);
        }
    }
}
