import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.TimerTask;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 360;
    int boardHeight = 640;
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;
    //Bird
    float birdX = boardWidth/8;
    float birdY = boardHeight/2;
    float birdWidth = 34;
    float birdHeight = 24;

    //Pipes
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    class Bird {
        float x = birdX;
        float y = birdY;
        float width = birdWidth;
        float height = birdHeight;
        Image img;
        Bird(Image img) {
            this.img = img;
        }
    }
    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;
        Pipe(Image img) {
            this.img = img;
        }
    }
    Bird bird;
    Timer gameLoop;
    Timer placePipesTimer;
    boolean gameStarted = false;
    boolean gameOver = false;
    float velocityY = 0;
    float velpcityX = -4;
    float gravity = 0.4f;
    ArrayList<Pipe> pipes;
    Random random = new Random();

    double score = 0;
    FlappyBird(){
        setPreferredSize(new Dimension(boardWidth,boardHeight));

        setFocusable(true);
        addKeyListener(this);

        //setBackground(Color.BLUE);
        backgroundImg = new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();

        bird = new Bird(birdImg);
        pipes = new ArrayList<>();

        placePipesTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        //placePipesTimer.start();
        gameLoop = new Timer(1000/60,this);
        //gameLoop.start();
    }

    public void placePipes() {
        int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
        int openingSpace  = boardHeight/4;

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.drawImage(backgroundImg,0,0,boardWidth,boardHeight,null);
        g.drawImage(birdImg,(int)bird.x,(int)bird.y,(int)bird.width,(int)bird.height,null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img,pipe.x,pipe.y,pipe.width,pipe.height,null);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.PLAIN,32));
        if(gameOver) {
            g.drawString("GAME OVER: " + String.valueOf((int) score), 10, 35);
        }
        else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }

    }
    public void move() {
        velocityY += gravity ;
        bird.y += velocityY ;
        bird.y = Math.max(0,bird.y);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velpcityX;
            if(!pipe.passed && bird.x > pipe.x + pipe.width) {
                score += 0.5;
                pipe.passed = true;
            }
            if(collision(bird, pipe)) gameOver = true;
        }
        if(bird.y > boardHeight) gameOver = true;
    }
    boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width &&   //a's top left corner doesn't reach b's top right corner
                a.x + a.width > b.x &&   //a's top right corner passes b's top left corner
                a.y < b.y + b.height &&  //a's top left corner doesn't reach b's bottom left corner
                a.y + a.height > b.y;    //a's bottom left corner passes b's top left corner
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameStarted)
            return;
        repaint();
        move();
        if(gameOver) {
            placePipesTimer.stop();
            gameLoop.stop();
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        velocityY = -8;
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(!gameStarted) {
                gameStarted = true; // Khi người dùng nhấn phím cách, trò chơi bắt đầu.
                gameLoop.start(); // Bắt đầu vòng lặp game khi bắt đầu.
                placePipesTimer.start();
                return;
            }
            if(gameOver) {
                bird.y = birdY;
                velocityY = -8;
                pipes.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placePipesTimer.start();
            }
            velocityY = -8;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}