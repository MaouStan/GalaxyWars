package game.gui;

import static game.util.Constant.BG1;
import static game.util.Constant.fSemiBold;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

import game.Frame;
import game.util.ImageManager;

public class Setting extends JPanel {
    public Setting() {
        setLayout(null);
        setPreferredSize(new Dimension(1920, 1080));

        JLabel bg = new JLabel();
        bg.setBounds(0, 0, 1920, 1080);
        bg.setIcon(new ImageIcon(BG1));
        add(bg);

        JLabel st = new JLabel("Setting");
        st.setBounds(1920/2-100, 20, 300, 70);
        st.setForeground(Color.white);
        st.setFont(fSemiBold.deriveFont(40f));
        bg.add(st);
        
        JLabel text1 = new JLabel("Planet");
        text1.setBounds(100, 200, 300, 70);
        text1.setForeground(Color.white);
        text1.setFont(fSemiBold.deriveFont(36f));
        bg.add(text1);

        JLabel text2 = new JLabel("Game Rules");
        text2.setBounds(100, 500, 300, 70);
        text2.setForeground(Color.white);
        text2.setFont(fSemiBold.deriveFont(36f));
        bg.add(text2);

        JLabel text3 = new JLabel("Volume");
        text3.setBounds(100, 800, 300, 70);
        text3.setForeground(Color.white);
        text3.setFont(fSemiBold.deriveFont(36f));
        bg.add(text3);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setBounds(550, 800, 1100, 70);
        slider.setOpaque(false);
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setForeground(Color.white);
        slider.setFont(fSemiBold.deriveFont(20f));
        bg.add(slider);

        JButton bt = new JButton("Noob");
        bt.setBounds(550, 500, 200, 90);
        bt.setForeground(Color.black);
        bt.setFont(fSemiBold.deriveFont(28f));
        bg.add(bt);

        JButton bt2 = new JButton("Easy");
        bt2.setBounds(800, 500, 200, 90);
        bt2.setForeground(Color.black);
        bt2.setFont(fSemiBold.deriveFont(28f));
        bg.add(bt2);

        JButton bt3 = new JButton("Normal");
        bt3.setBounds(1050, 500, 200, 90);
        bt3.setForeground(Color.black);
        bt3.setFont(fSemiBold.deriveFont(28f));
        bg.add(bt3);

        JButton bt4 = new JButton("Hard");
        bt4.setBounds(1300, 500, 200, 90);
        bt4.setForeground(Color.black);
        bt4.setFont(fSemiBold.deriveFont(28f));
        bg.add(bt4);

        JButton bt5 = new JButton("Hell");
        bt5.setBounds(1550, 500, 200, 90);
        bt5.setForeground(Color.black);
        bt5.setFont(fSemiBold.deriveFont(28f));
        bg.add(bt5);
        
        JLabel back = new JLabel();
        back.setBounds(100, 30, 60, 50);
        back.setIcon(new ImageIcon(ImageManager.resizeImage("res/images/arrow_back.png", 60, 50, 180)));
        bg.add(back);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Victory");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Setting());
            frame.pack();
            frame.setVisible(true);
        });
    }
}
