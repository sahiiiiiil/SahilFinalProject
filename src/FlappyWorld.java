
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
public class FlappyWorld implements ActionListener{

    private int score = 0;
    private JLabel score1;
    private JPanel gamePanel;
    private JButton startButton;
    private JLabel bird;

    private JLabel topPipe; //top and bottom pipes
    private JLabel bottomPipe;

    public Rectangle bird1;
    public boolean started;


    private final int pipeHole = 300; //gap between pipes
    private final int pipeX = 100; //width of Pipe

    private final int gameDimension = 600;
    //There seems to be an error on this constructor because I haven't finished it yet
    public static FlappyWorld flappy;
    public ArrayList<Rectangle> pipes;
    public int secs;
    public int yVelo;
    public boolean gameOver;

    public Extension extension;
    public FlappyWorld() {
        flappy = this;
        extension = new Extension();
        JFrame s = new JFrame();
        s.add(extension);
        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s.setResizable(true);
        s.setLocationRelativeTo(null);
        s.setSize(gameDimension, gameDimension);
        s.setVisible(true);

        Timer timer = new Timer(20, this);

        bird1 = new Rectangle(gameDimension/2,gameDimension/2, 20, 20);
        pipes = new ArrayList<Rectangle>();
        nPipe(true);
        nPipe(true);
        nPipe(true);
        nPipe(true);



        timer.start();

    }

    public void actionPerformed(ActionEvent e){
        secs += 1;
        if (started) {
            for (int i = 0; i < pipes.size(); i++) {
                Rectangle pipe = pipes.get(i);
                pipe.x -= 10;
            }
            if ((secs % 2 == 0) && yVelo < 15) {
                yVelo += 1;
            }

            for (int i = 0; i < pipes.size(); i++) {
                Rectangle pipe = pipes.get(i);
                if (pipe.x + pipe.width > 0) {
                    pipes.remove(pipe);
                    nPipe(false);
                    if (pipe.y == 0) {
                        nPipe(false);
                    }
                }

            }


            bird1.y += yVelo;
            for (Rectangle pipe : pipes) {
                if (pipe.intersects(bird1)) {
                    gameOver = true;
                }
            }
            if (bird1.y > gameDimension-150|| bird1.y < 0 ) {
                gameOver = true;
            }
        }
        extension.repaint();

    }


    public static void main(String[] args) {
        flappy = new FlappyWorld();

    }






    public void repaint(Graphics paint) { //for animation
        paint.setColor(Color.blue);
        paint.fillRect(0, 0, gameDimension, gameDimension);
        paint.setColor(Color.green);
        paint.fillRect(0, gameDimension -150, gameDimension, 150);
        paint.setColor(Color.yellow);
        paint.fillRect(bird1.x, bird1.y, bird1.width, bird1.height);
        for(Rectangle pipe: pipes) {
            paintPipe(paint, pipe);
        }
    }
    public void paintPipe(Graphics paint, Rectangle pipe) { // making new pipe
        paint.setColor(Color.red);
        paint.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);

    }
    public void nPipe(boolean indicator){
        int height = 50 + (int)(Math.random() * 250); // pipe between 50 and 300
        if (indicator) {
            pipes.add(new Rectangle(gameDimension + pipeX + pipes.size() * 300, gameDimension - height - 150, gameDimension/2, gameDimension));
            pipes.add(new Rectangle(gameDimension + pipeX + (pipes.size() - 1) * 300, 0, gameDimension/2, gameDimension - height - pipeHole));
        } else {
            pipes.add(new Rectangle(pipes.get(pipes.size()-1).x + gameDimension, gameDimension - height - 150, gameDimension/2, gameDimension));
            pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x, 0, gameDimension/2, gameDimension - height - pipeHole));
        }

    }
}