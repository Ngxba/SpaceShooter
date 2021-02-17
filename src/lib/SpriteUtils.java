package lib;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteUtils {

    public static BufferedImage loadImage(String url) {
        try {
            return ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static BufferedImage resize(String url, int width, int height) {
        BufferedImage image = loadImage(url);
        BufferedImage outputImage = new BufferedImage(width , height, image.getType());

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        return outputImage;
    }

    public static BufferedImage resize(String url, float ratio) {
        BufferedImage image = loadImage(url);
        int width = (int)(image.getWidth() * ratio);
        int height = (int) (image.getHeight() * ratio);
        BufferedImage outputImage = new BufferedImage(width , height, image.getType());

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        return outputImage;
    }

    public static BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, img.getType());
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0,  null);
        g2d.dispose();

        return rotated;
    }
}

