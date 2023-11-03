package game.gui;

import static game.util.Constant.BG2;
import static game.util.Constant.fBold;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EndGame extends JPanel {

    private static final int PANEL_WIDTH = 1960;
    private static final int PANEL_HEIGHT = 1080;
    private BufferedImage image;
    private BufferedImage image1;

    private int imageX; // ตำแหน่งในแกน X ของรูปภาพ
    private int imageY; // ตำแหน่งในแกน Y ของรูปภาพ
    private int textX; // ตำแหน่งในแกน X ของข้อความ
    private int textY; // ตำแหน่งในแกน Y ของข้อความ

    private Timer timer;

    public EndGame() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(null);
        setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 125));

        try {
            image1 = ImageIO.read(new File("res/images/nextlevelxx.Wpng.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ตำแหน่งเริ่มต้นของรูปภาพและข้อความ
        imageX = PANEL_WIDTH / 2 - image1.getWidth() / 2;
        imageY = PANEL_HEIGHT;
        textX = PANEL_WIDTH / 2;
        textY = PANEL_HEIGHT;

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // เลื่อนรูปภาพและข้อความขึ้นไป
                if (imageY > PANEL_HEIGHT / 2 - 420) {
                    imageY -= 1;
                }
                if (textY > PANEL_HEIGHT / 2 - 120) {
                    textY -= 1;
                }
                repaint(); // อัปเดตหน้าจอ
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BG2, 0, 0, getWidth(), getHeight(), null);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        if (image1 != null) {
            // วาดรูปภาพและข้อความในตำแหน่งที่กำหนด
            g.drawImage(image1, imageX, imageY - 110, null);
        }

        Font textFont = new Font("SansSerif", Font.BOLD, 98);
        g.setFont(textFont);
        g.setColor(Color.WHITE);
        g.setFont(fBold.deriveFont(Font.BOLD, 98f)); // ปรับให้เป็นตามขนาดจอด้วย
        g.drawString("Victory Galaxy Wars", PANEL_WIDTH / 2 - g.getFontMetrics().stringWidth("Victory Galaxy Wars") / 2,
                textY - 150);

        g.setFont(fBold.deriveFont(Font.BOLD, 48f)); // ปรับให้เป็นตามขนาดจอด้วย
        g.drawString("----------------- Developer -----------------",
                PANEL_WIDTH / 2 - g.getFontMetrics().stringWidth("----------------- Developer -----------------") / 2,
                textY - 70);

        Font DeveloperFont = fBold.deriveFont(Font.BOLD, 32f); // 48 เป็นขนาดตัวหนังสือ
        g.setFont(DeveloperFont);
        g.drawString("65011212112 Phothiphong Meethonglang : Code and Desgin",
                PANEL_WIDTH / 2 - 30
                        - g.getFontMetrics().stringWidth("65011212178 Nitipong Boonprasert : Code and Desgin") / 2,
                textY);
        g.drawString("65011212178 Nitipong Boonprasert : Code and Desgin",
                PANEL_WIDTH / 2 - 30
                        - g.getFontMetrics().stringWidth("65011212178 Nitipong Boonprasert : Code and Desgin") / 2,
                textY + 70);
        g.drawString("65011212148 Apidsada Laochai : Desgin and Assets",
                PANEL_WIDTH / 2 - 30
                        - g.getFontMetrics().stringWidth("65011212178 Nitipong Boonprasert : Code and Desgin") / 2,
                textY + 140);
        g.drawString("65011212151 Atsadawut Trakanjun : Desgin and Assets",
                PANEL_WIDTH / 2 - 30
                        - g.getFontMetrics().stringWidth("65011212178 Nitipong Boonprasert : Code and Desgin") / 2,
                textY + 210);
        g.drawString("65011212048 Wiritphon Worawatthanakul : Sound and Assets",
                PANEL_WIDTH / 2 - 30
                        - g.getFontMetrics().stringWidth("65011212178 Nitipong Boonprasert : Code and Desgin") / 2,
                textY + 280);

        g.setFont(fBold.deriveFont(Font.BOLD, 48f)); // ปรับให้เป็นตามขนาดจอด้วย
        g.drawString("----------------- Teacher -----------------",
        PANEL_WIDTH / 2 - g.getFontMetrics().stringWidth("----------------- Teacher -----------------") / 2,
        textY + 350);
        g.setFont(fBold.deriveFont(Font.BOLD, 36f)); // ปรับให้เป็นตามขนาดจอด้วย
        g.drawString("Natthariya Laopracha",
                PANEL_WIDTH / 2 - 30
                        - g.getFontMetrics().stringWidth("Natthariya Laopracha") / 2,
                textY + 420);
        g.setFont(DeveloperFont);
        g.drawString("Object Oriented Programming 1204203",
                PANEL_WIDTH / 2 - 30
                        - g.getFontMetrics().stringWidth("Object Oriented Programming 1204203") / 2,
                textY + 480);
        g.drawString("Final Project OOP : 35 Score",
                PANEL_WIDTH / 2 - 30
                        - g.getFontMetrics().stringWidth("Final Project OOP : 35 Score") / 2,
                textY + 540);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Victory");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new EndGame());
            frame.pack();
            frame.setVisible(true);
        });
    }
}
