package game.util;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.sound.sampled.Clip;

public class Constant {
    // WINDOW GAME SETTINGS
    public static final int SCREEN_WIDTH = 1360;
    public static final int SCREEN_HEIGHT = 768;
    public static final int FRAME_RATE = 60;
    public static boolean sound_state = true;

    // ========== ASSETS ========== \\
    // GAME LOGO
    public static BufferedImage GAME_LOGO;

    // Images
    public static BufferedImage SOUND_ON;
    public static BufferedImage SOUND_OFF;
    public static BufferedImage BG1;

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

            GAME_LOGO = resizeImage("res/images/icon.jpg", 64, 64);

            BG1 = resizeImage("res/images/bg/bg1.jpg", SCREEN_WIDTH, SCREEN_HEIGHT);

            bgm1 = SoundManager.getClip("res/sounds/bgm1.wav");

            SOUND_ON = resizeImage("res/images/sound_on.png", 50, 50);
            SOUND_OFF = resizeImage("res/images/sound_off.png", 50, 50);

            ITEM_HEART = resizeImage("res/images/items/heart.png", 50, 50);
            ITEM_GRAY_HEART = resizeImage("res/images/items/heart_gray.png", 50, 50);
            ITEM_SHIELD = resizeImage("res/images/items/shield.png", 50, 50);
            ITEM_FREEZE = resizeImage("res/images/items/freeze.png", 50, 50);
            ITEM_DAMAGE = resizeImage("res/images/items/damage.png", 50, 50);

            fRegular = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/Orbitron-Regular.ttf"));
            fSemiBold = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/Orbitron-SemiBold.ttf"));
            fBold = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/Orbitron-Bold.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    // resizeImage
    public static BufferedImage resizeImage(String imagePath, int width, int height) {
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File(imagePath));
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(originalImage, 0, 0, width, height, null);
            g2d.dispose();
            return resizedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return originalImage;
    }
}
