import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

import javax.swing.JPanel;

public class Display extends JPanel {

    private final int HEIGHT = 50;
    private final int WIDTH = 300;
    private final int FONT_SIZE = 35;
    private final int FONT_PIXELS = 18;

    private String value;

    private Font font;

    public Display() {
        value = "";
        font = new Font("Times New Roman", Font.BOLD, FONT_SIZE);

        initDisplay();
    }

    private void initDisplay() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.LIGHT_GRAY);
    }

    public void write(int value) {
        if (value != 0) {
            this.value = Integer.toString(value);
        } else {
            this.value = "";
        }

        repaint();
    }

    private void show(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(font);
        g2d.drawString(value, WIDTH - ((value.length() * FONT_PIXELS) + 10), HEIGHT - 5);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        show(g);
    }
}