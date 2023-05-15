import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FlappyWorld extends JFrame{
    private Timer timer;
    private int score = 0;
    private JLabel score1;
    private JPanel gamePanel;
    private JButton start;
    private JLabel bird;

    private JLabel topPipe; //top and bottom pipes
    private JLabel bottomPipe;

    private int birdCord; //where the bird is
    private int PipeCord; //where the pipe is

    private final int pipeHole = 200; //gap between pipes
    private final int pipeX = 60; //width of Pipe
    private final int pipeHeight = 300;
    private final int birdSize = 40; //dimension of bird will be both width and height
    private final int gameDimension = 600;
    //There seems to be an error on this constructor because I haven't finished it yet
    public void FlappyGame(JFrame world) { //I haven't finished this yet but now that I've mapped it out I want to finish the game panel with pipes and bird and add it to flappy world
        world.setTitle("Flappy World");
        world.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        world.setSize(gameDimension, gameDimension);
        world.setVisible(true);
        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setBackground(Color.BLUE);
        bird = new JLabel(new ImageIcon("bird.png"));
        bird.setSize(birdSize, birdSize);
        bird.setLocation(100,200);
        gamePanel.add(bird);
        topPipe = new JLabel(new ImageIcon("pipe.png")); //add topPipe
        topPipe.setSize(pipeX, pipeHeight);
        topPipe.setLocation(pipeX, 0);
        gamePanel.add(topPipe);
        bottomPipe = new JLabel(new ImageIcon("pipe.png")); //add bottomPipe
        bottomPipe.setSize(pipeX, pipeHeight);
        bottomPipe.setLocation(gameDimension, gameDimension - pipeHeight - pipeHole);
        gamePanel.add(bottomPipe);



    }


    public class Pipe { //this will be the obstacles/objects coming at the bird
        private int PipeCord;
        public Pipe(int PipeCord) {
            this.PipeCord = PipeCord;

        }
    }
    public static void main(String[] args) {

    }


}