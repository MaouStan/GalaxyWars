package game.gui;

import javax.swing.*;

import static game.util.Constant.BG2;
import static game.util.Constant.fBold;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Nextlevel extends JPanel {
    private static final int PANEL_WIDTH = 1960;
    private static final int PANEL_HEIGHT = 1080;
    private static final int TEXT_WIDTH = 20;
    private BufferedImage image;
    private BufferedImage image1;

    private int textX = -TEXT_WIDTH;
    private Timer timer;
    private boolean slowingDown = false; // เพิ่มตัวแปรเพื่อแสดงถึงความช้าลง

    public Nextlevel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(null);
        setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 125));
         // no header

        try {
            // โหลดรูปภาพจากไฟล์
            image1 = ImageIO.read(new File("res/images/nextlevelxx.Wpng.png"));
            image = ImageIO.read(new File("res/images/player-next.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!slowingDown) {
                    textX += 20;
                    if (textX + 48 * 4 + 170 >= (PANEL_WIDTH / 2)) {
                        slowingDown = true; // เมื่อถึงกลางจอกลับเริ่มช้าลง
                    }
                } else {
                    textX += 1;
                    if (textX > PANEL_WIDTH / 2 - 230) {
                        slowingDown = false; // เมื่อกลับไปความเร็วปกติ
                    }
                    if (textX > PANEL_WIDTH) {
                        textX = -TEXT_WIDTH;
                    }
                }
                repaint();
            }
        });
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BG2,0,0, getWidth(), getHeight(), null);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        Font textFont = new Font("SansSerif", Font.BOLD, 98); // Set the font
        Color textColor = Color.WHITE; // Set the text color

        int textY = getHeight() / 2 - 48;
        g.setColor(Color.white);
        g.setFont(fBold.deriveFont(Font.BOLD, 72f));
        
        String textWidth = "Next Level";
        int Nextwidth = g.getFontMetrics().stringWidth(textWidth);
        
        // Draw the image at the same position as the text
        if (image != null) {
            g.drawImage(image1, getWidth()/2 - image1.getWidth()/2, getHeight()/2-420, null);
            g.drawImage(image, textX+Nextwidth/2 - image.getWidth()/2, textY+60, this);
        }
        
        g.drawString("Next Level", getWidth()/2-Nextwidth/2 , getHeight()/2-120);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Text Scrolling Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Nextlevel());
            frame.pack();
            frame.setVisible(true);
        });
    }
}