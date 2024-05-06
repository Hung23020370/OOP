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
    float boardWidth = 360;
    float boardHeight = 640;

    // Ảnh
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;
    Image topRedPipeImg;
    Image bottomRedPipeImg;
    Image topBluePipeImg;
    Image bottomBluePipeImg;
    Image fireImg;

    //Bird
    float birdX = boardWidth/8;
    float birdY = boardHeight/2;
    float birdWidth = 34;
    float birdHeight = 24;
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


    //Pipes
    float pipeX = boardWidth;
    float pipeY = 0;
    float pipeWidth = 64;
    float pipeHeight = 512;
    class Pipe {
        float x = pipeX;
        float y = pipeY;
        float width = pipeWidth;
        float height = pipeHeight;
        Image img;
        boolean passed = false;// false - chim chưa bay qua ống và true là chim đã bay qua ống
        Pipe(Image img) {
            this.img = img;
        }
    }

    //Skill
    float skillX = boardWidth + 70;
    float skillY = (boardHeight - 40) /2 ;
    float skillWidth = 70;
    float skillHeight = 40;
    class Skill {
        float x = skillX;
        float y = skillY;
        float width = skillWidth;
        float height = skillHeight;
        Image img;
        Skill(Image img) {
            this.img = img;
        }
    }

    Bird bird;
    Timer gameLoop; // Thời gian vẽ lại bố cục
    Timer placePipesTimer;  // Thời gian vẽ ống
    boolean gameStarted = false;
    boolean gameOver = false;
    float velocityY = -3;  // vận tốc bay lên
    float velpcityX = -4; // vận tốc bay ngang
    float gravity = 0.1f; // trọng lực
    ArrayList <Pipe> pipes;
    ArrayList <Skill> skills;
    Random random = new Random();

    double score = 0;
    FlappyBird(){
        setPreferredSize(new Dimension((int) boardWidth,(int) boardHeight));

        setFocusable(true);
        addKeyListener(this);

        //setBackground(Color.BLUE);
        backgroundImg = new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("flappybird.png")).getImage();

        topPipeImg = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();
        topRedPipeImg = new ImageIcon(getClass().getResource("toppipered.png")).getImage();
        bottomRedPipeImg = new ImageIcon(getClass().getResource("bottompipered.png")).getImage();

        //Skill
        fireImg = new ImageIcon(getClass().getResource("fire.png")).getImage();

        bird = new Bird(birdImg);
        pipes = new ArrayList<>();
        skills = new ArrayList<>();

        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomPipes();
            }
        });
        //placePipesTimer.start();
        gameLoop = new Timer(1000/60,this);
        //gameLoop.start();
    }
    public void addSkill() {
        Skill fireTop = new Skill(fireImg);
        fireTop.y = 80;
        skills.add(fireTop);

        Skill fireCenter = new Skill(fireImg);
        skills.add(fireCenter);

        Skill fireBottom = new Skill(fireImg);
        fireBottom.y = 520;
        skills.add(fireBottom);
    }

    public void randomPipes() {
        double randomNumber = random.nextDouble();
        if(randomNumber < 0.3) {
            placePipes(topRedPipeImg, bottomRedPipeImg);
            addSkill();
        }
        else {
            placePipes(topPipeImg, bottomPipeImg);
        }
    }

    public void placePipes(Image topPipeImg, Image bottomPipeImg) {
        float randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
        float openingSpace  = boardHeight/4;

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
        g.drawImage(backgroundImg,0,0,(int) boardWidth,(int)boardHeight,null);
        g.drawImage(birdImg,(int)bird.x,(int)bird.y,(int)bird.width,(int)bird.height,null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img,(int)pipe.x,(int)pipe.y,(int)pipe.width,(int)pipe.height,null);
        }

        for (int i = 0; i < skills.size(); i++) {
            Skill skill = skills.get(i);
            g.drawImage(skill.img,(int)skill.x,(int)skill.y,(int)skill.width,(int)skill.height,null);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.BOLD,35));
        if(gameOver) {
            g.drawString("GAME OVER: " + String.valueOf((int) score), 60, 320);
        }
        else {
            g.drawString(String.valueOf((int) score), 180, 35);
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
        for (int i = 0; i < skills.size(); i++) {
            Skill skill = skills.get(i);
            skill.x += 2 * velpcityX;
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
        //velocityY = -8;
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(!gameStarted) {
                gameStarted = true; // Khi người dùng nhấn phím cách, trò chơi bắt đầu.
                gameLoop.start(); // Bắt đầu vòng lặp game khi bắt đầu.
                placePipesTimer.start();
                return;
            }
            if(gameOver) {
                bird.y = birdY;
                velocityY = -4;
                pipes.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placePipesTimer.start();
            }
            velocityY = -3;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}