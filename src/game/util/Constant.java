package game.util;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.sound.sampled.Clip;

public class Constant {
    // WINDOW GAME SETTINGS
    public static final int SCREEN_WIDTH = 1360;
    public static final int SCREEN_HEIGHT = 768;
    public static final int FRAME_RATE = 60;
    public static boolean sound_state = true;

    // ========== PLAYER ========== \\
    public static final int MAX_HEALTH = 5;

    // ========== ASSETS ========== \\
    // GAME
    public static BufferedImage GAME_LOGO;
    public static BufferedImage reloadImg;
    public static BufferedImage reloadImg2;

    // Images
    public static BufferedImage SOUND_ON;
    public static BufferedImage SOUND_OFF;
    public static BufferedImage BG1;
    public static BufferedImage BG2;

    // Items
    public static BufferedImage ITEM_HEART;
    public static BufferedImage ITEM_GRAY_HEART;
    public static BufferedImage ITEM_SHIELD;
    public static BufferedImage ITEM_FREEZE;
    public static BufferedImage ITEM_DAMAGE;

    // Fonts
    public static Font fRegular;
    public static Font fSemiBold;
    public static Font fBold;

    // Sounds
    public static Clip bgm1;
    public static Clip bgm2;

    // Cursor
    private final static String CURSOR_PATH = "res/images/crosshair.png";
    public static Cursor CUSTOM_CURSOR;

    // load assets
    static {
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            // Change the size of the image to 50x50
            Image image = toolkit.getImage(CURSOR_PATH).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            Point hotspot = new Point(25, 25);
            CUSTOM_CURSOR = toolkit.createCustomCursor(image, hotspot, "Crosshair");

            GAME_LOGO = ImageManager.resizeImage("res/images/icon.jpg", 64, 64);
            reloadImg = ImageManager.resizeImage("res/images/reload.png", 350, 350);
            reloadImg2 = ImageManager.resizeImage("res/images/reload2.png", 350, 350);

            BG1 = ImageManager.resizeImage("res/images/bg/bg1.jpg", SCREEN_WIDTH, SCREEN_HEIGHT);
            BG2 = ImageManager.resizeImage("res/images/bg/bg2.jpg", SCREEN_WIDTH, SCREEN_HEIGHT);

            bgm1 = SoundManager.getClip("res/sounds/bgm1.wav");
            bgm2 = SoundManager.getClip("res/sounds/bgm2.wav");

            SOUND_ON = ImageManager.resizeImage("res/images/sound_on.png", 50, 50);
            SOUND_OFF = ImageManager.resizeImage("res/images/sound_off.png", 50, 50);

            ITEM_HEART = ImageManager.resizeImage("res/images/items/heart.png", 50, 50);
            ITEM_GRAY_HEART = ImageManager.resizeImage("res/images/items/heart_gray.png", 50, 50);
            ITEM_SHIELD = ImageManager.resizeImage("res/images/items/shield.png", 50, 50);
            ITEM_FREEZE = ImageManager.resizeImage("res/images/items/freeze.png", 50, 50);
            ITEM_DAMAGE = ImageManager.resizeImage("res/images/items/damage.png", 50, 50);

            fRegular = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/Orbitron-Regular.ttf"));
            fSemiBold = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/Orbitron-SemiBold.ttf"));
            fBold = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/Orbitron-Bold.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
