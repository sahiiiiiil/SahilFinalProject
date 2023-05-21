import javax.swing.JPanel;
import java.awt.Graphics;
public class Extension extends JPanel{
    public void paintComponent(Graphics paint) {
        super.paintComponent(paint);
        FlappyWorld.flappy.repaint(paint);

    }
}
