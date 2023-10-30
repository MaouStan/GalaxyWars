package game.item;

import static game.util.Constant.AUTOMATIC_TIME;
import static game.util.Constant.FREEZE_TIME;
import static game.util.Constant.ITEM_DAMAGE;
import static game.util.Constant.ITEM_FREEZE;
import static game.util.Constant.ITEM_HEART;
import static game.util.Constant.ITEM_SHIELD;
import static game.util.Constant.PROTECT_TIME;

import java.awt.image.BufferedImage;

public enum ItemType {
    HEART("Heart", "Heal +1 Heart", ITEM_HEART, 1),
    SHIELD("Shield", "Protect 3 sec", ITEM_SHIELD, PROTECT_TIME),
    FREEZE("Freeze", "Freeze 3 sec", ITEM_FREEZE, FREEZE_TIME),
    DAMAGE("Auto", "Automatic Mode", ITEM_DAMAGE, AUTOMATIC_TIME);

    private final String name;
    private final String description;
    private final BufferedImage image;
    private int effectTime;

    ItemType(String name, String description, BufferedImage image, int effectTime) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.effectTime = effectTime;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getEffectTime() {
        return effectTime;
    }
}
