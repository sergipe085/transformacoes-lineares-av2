import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Drawing extends Canvas {
     static double time_passed = 0;
     static double delta_time = 0;

    public Object[] objects;

    public Drawing(Object[] objects) {
        this.objects = objects;
    }

    public void OpenWindow() {
        JFrame frame = new JFrame("AV2 MAT");
        this.setSize(600, 500);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        float deltaTime = 0.0f;
        while (true) {
            long start = System.currentTimeMillis();
            this.repaint();
            frame.repaint();
            long end = System.currentTimeMillis() - start; // Delta time in ms
            float seconds = end / 1000.0f; // Delta time in s converted to float
            deltaTime = seconds;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        for (Object object : objects) {
            object.Draw(g);
        }
    }
}