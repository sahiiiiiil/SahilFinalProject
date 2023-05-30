
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
public class FlappyWorld implements ActionListener, MouseListener{

    private int score = 0;
    private int highScore = 0;

    public Rectangle bird1;


    private final int pipeHole = 300; //gap between pipes
    private final int pipeX = 100; //width of Pipe

    private final int gameDimension = 800;
    //There seems to be an error on this constructor because I haven't finished it yet
    public static FlappyWorld flappyWorld;
    public ArrayList<Rectangle> pipes;
    public int secs;
    public int yVelo;
    public boolean gameOver;
    public boolean started = false;

    public Extension extension;
    public FlappyWorld() {
        extension = new Extension();
        JFrame s = new JFrame();
        s.add(extension);
        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s.setResizable(false);
        s.addMouseListener(this);
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
        if (started && !gameOver) {
            for (int i = 0; i < pipes.size(); i++) {
                Rectangle pipe = pipes.get(i);
                pipe.x -= 10;
            }
            if ((secs % 2 == 0) && yVelo < 15) {
                yVelo += 1;
            }
            for (int i = 0; i < pipes.size(); i++) {
                Rectangle pipe = pipes.get(i);
                if (pipe.x + pipe.width < 0) {
                    pipes.remove(pipe);
                    if (pipe.y == 0) {
                        nPipe(false);
                    }
                }
            }

            bird1.y += yVelo;
            for (Rectangle pipe : pipes) {
                if (bird1.x + bird1.width /2 > pipe.x + pipe.width/2 - 10 && bird1.x+ bird1.width/2 < pipe.x + pipe.width/2 + 10 ){
                    score++;
                }
                if (pipe.intersects(bird1)) {
                    gameOver = true;
                }
            }
            if (bird1.y > gameDimension - 120 || bird1.y < 0) {
                gameOver = true;
            }
            extension.repaint();
        }
    }
    public void mouseClicked(MouseEvent e){
        flap();
    }
    public void mousePressed(MouseEvent e){

    }
    public void mouseReleased(MouseEvent e){

    }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e){

    }

    public static void main(String[] args) {
        flappyWorld = new FlappyWorld();



    }






    public void repaint(Graphics paint) { //for animation

        paint.setColor(Color.blue);
        paint.fillRect(0, 0, gameDimension, gameDimension);
        paint.setColor(Color.white);
        paint.setColor(Color.orange);
        paint.fillRect(0, gameDimension -150, gameDimension, 150);
        paint.setColor(Color.yellow);
        paint.fillRect(bird1.x, bird1.y, bird1.width, bird1.height);
        paint.setColor(Color.black);
        paint.fillRect(bird1.x+2, bird1.y+2, bird1.width/4, bird1.height/4);
        paint.fillRect(bird1.x+14, bird1.y+2, bird1.width/4, bird1.height/4);
        paint.fillRect(bird1.x + 2, bird1.y + 16, bird1.width - 4, bird1.height/6);
        paint.fillRect(bird1.x+1, bird1.y + 12, bird1.width/6, bird1.height/4);
        paint.fillRect(bird1.x+1 + bird1.width-4, bird1.y + 12, bird1.width/8, bird1.height/4);

        for (Rectangle pipe: pipes){
            paintPipe(paint, pipe);
        }
        paint.setColor(Color.white);
        paint.setFont(new Font("Arial", 1, 75));
        if (!gameOver && !started){
            paint.drawString("Click to Start", 100, gameDimension/2);
        }
        paint.setFont(new Font("Arial", 1, 130));
        if (gameOver){
            paint.drawString("You Lost!", 100, gameDimension/2);
        }
        paint.setFont(new Font("Arial", 1, 25));
        if (! gameOver &&  started){
            paint.drawString(String.valueOf(score/4), gameDimension/2 - 25, 100 );
            paint.drawString("High Score: " + String.valueOf(highScore/4), gameDimension - 200, 100);
        }

    }
    public void paintPipe(Graphics paint, Rectangle pipe) { // making new pipe
        paint.setColor(Color.green);
        paint.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);

    }
    public void nPipe(boolean indicator){
        int height = 50 + (int)(Math.random() * 250); // pipe between 50 and 300
        if (indicator) {
            pipes.add(new Rectangle(gameDimension + pipeX + pipes.size() * 300, gameDimension - height - 150, gameDimension/6, gameDimension));
            pipes.add(new Rectangle(gameDimension + pipeX + (pipes.size() - 1) * 300, 0, gameDimension/6, gameDimension - height - pipeHole));
        } else {
            pipes.add(new Rectangle(pipes.get(pipes.size()-1).x + gameDimension, gameDimension - height - 150, gameDimension/6, gameDimension));
            pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x, 0, gameDimension/6, gameDimension - height - pipeHole));
        }

    }
    public void flap(){
        if (gameOver){
            bird1 = new Rectangle(gameDimension/2,gameDimension/2, 20, 20);
            pipes.clear();
            yVelo = 0;
            if (score > highScore){
                highScore = score;
            }
            score = 0;

            nPipe(true);
            nPipe(true);
            nPipe(true);
            nPipe(true);

            gameOver = false;

        }
        if (!started) {
            started = true;
        } else if (!gameOver){
            if (yVelo > 0){
                yVelo = 0;
            }
            yVelo -= 8;
        }
    }
}