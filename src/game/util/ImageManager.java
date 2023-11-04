package game.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageManager {
    public static BufferedImage load(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

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

    public static BufferedImage resizeImage(String imagePath, int width, int height, int deg) {
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File(imagePath));
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.rotate(Math.toRadians(deg), width / 2, height / 2);
            g2d.drawImage(originalImage, 0, 0, width, height, null);
            g2d.dispose();
            return resizedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return originalImage;
    }

    public static BufferedImage resizeImage(String imagePath, int width, int height, int deg, int alpha) {
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File(imagePath));
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.rotate(Math.toRadians(deg), width / 2, height / 2);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha / 255f));
            g2d.drawImage(originalImage, 0, 0, width, height, null);
            g2d.dispose();
            return resizedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return originalImage;
    }
}
