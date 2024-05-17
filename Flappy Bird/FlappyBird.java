import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    float boardWidth = 400;
    float boardHeight = 640;

    // Ảnh
    Image backgroundImg;
    Image birdImg1;
    Image birdImg2;
    Image birdImg3;
    Image topPipeImg;
    Image bottomPipeImg;
    Image topRedPipeImg;
    Image bottomRedPipeImg;
    Image fireImg;
    Image groudImg;

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

    //GroundImg
    class Ground {
        float x = boardWidth;
        float y = boardHeight - 80;
        float width = (float) (boardWidth * 2);
        float height = boardWidth/4;
        Image img;
        Ground(Image img) {
            this.img = img;
        }
    }


    //Skill
    float skillX = boardWidth + 100;
    float skillY = (boardHeight - 120) /2 ;
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

    public enum STATE {
        MENU,
        HELP,
        GAME
    }

    Bird bird;
    static Timer gameLoop; // Thời gian vẽ lại bố cục
    static Timer birdTimer;
    Timer placePipesTimer;  // Thời gian vẽ ống
    static Timer placeGroundTimer;
    boolean gameStarted = false;
    static boolean gameOver = false;
    static boolean gameRestart = true;
    float velocityY = -7;  // vận tốc bay lên
    float velocityX = -3; // vận tốc bay ngang
    float gravity = 0.4f; // trọng lực
    ArrayList <Bird> birds;
    ArrayList <Pipe> pipes;
    ArrayList <Skill> skills;
    ArrayList <Ground> grounds;
    private int frameCount = 3;
    private int currentFrame = 0; // Chỉ số của hình ảnh hiện tại trong chuỗi hình ảnh

    Random random = new Random();

    double score = 0;
    double bestScore = 0;

    static STATE state = STATE.MENU;
    MenuGameStart menuGameStart;
    MenuGameOver menuGameOver;
    Help help;

    FlappyBird(){
        setPreferredSize(new Dimension((int) boardWidth,(int) boardHeight));

        setFocusable(true);
        addKeyListener(this);
        addMouseListener(new MouseGame());

        backgroundImg = new ImageIcon(getClass().getResource("bg.png")).getImage();
        groudImg = new ImageIcon(getClass().getResource("ground.png")).getImage();
        birdImg1 = new ImageIcon(getClass().getResource("bird1.png")).getImage();
        birdImg2 = new ImageIcon(getClass().getResource("bird2.png")).getImage();
        birdImg3 = new ImageIcon(getClass().getResource("bird3.png")).getImage();


        topPipeImg = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();
        topRedPipeImg = new ImageIcon(getClass().getResource("toppipered.png")).getImage();
        bottomRedPipeImg = new ImageIcon(getClass().getResource("bottompipered.png")).getImage();

        //Skill
        fireImg = new ImageIcon(getClass().getResource("fire.png")).getImage();

        birds = new ArrayList<>();
        pipes = new ArrayList<>();
        skills = new ArrayList<>();
        grounds = new ArrayList<>();

        bird = new Bird(birdImg1);
        birds.add(bird);
        bird = new Bird(birdImg2);
        birds.add(bird);
        bird = new Bird(birdImg3);
        birds.add(bird);

        Ground ground = new Ground(groudImg);
        ground.x = 0;
        grounds.add(ground);

        birdTimer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFrame = (currentFrame + 1) % frameCount;
                repaint();
            }
        });

        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomPipes();
            }
        });

        placeGroundTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ground ground= new Ground(groudImg);
                grounds.add(ground);
            }
        });

        menuGameStart = new MenuGameStart();
        menuGameOver = new MenuGameOver();
        help = new Help();

        gameLoop = new Timer(1000/60,this);

        gameLoop.start();
        birdTimer.start();
        placeGroundTimer.start();
    }

    public void addSkill() {
        Skill fireTop = new Skill(fireImg);
        fireTop.y = 50;
        skills.add(fireTop);

        Skill fireCenter = new Skill(fireImg);
        skills.add(fireCenter);

        Skill fireBottom = new Skill(fireImg);
        fireBottom.y = 470;
        skills.add(fireBottom);
    }

    public void randomPipes() {
        double randomNumber = random.nextDouble() + score / 500;
        if(randomNumber < 0.8) {
            placePipes(topPipeImg, bottomPipeImg);
        }
        else {
            placePipes(topRedPipeImg, bottomRedPipeImg);
            addSkill();
        }
    }

    public void placePipes(Image topPipeImg, Image bottomPipeImg) {
        float randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
        float openingSpace  = boardHeight/5;

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(state == STATE.GAME) draw(g);
        else if(state == STATE.MENU) menuGameStart.contentMenu(g);
        else if(state == STATE.HELP) help.contentHelp(g);
    }

    private void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, (int) boardWidth, (int) boardHeight, null);
        Bird bird = birds.get(currentFrame);
        g.drawImage(bird.img, (int) bird.x, (int) bird.y, (int) bird.width, (int) bird.height, null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, (int) pipe.x, (int) pipe.y, (int) pipe.width, (int) pipe.height, null);
        }

        for (int i = 0; i < skills.size(); i++) {
            Skill skill = skills.get(i);
            g.drawImage(skill.img, (int) skill.x, (int) skill.y, (int) skill.width, (int) skill.height, null);
        }

        for (int i = 0; i < grounds.size(); i++) {
            Ground ground = grounds.get(i);
            g.drawImage(ground.img, (int) ground.x, (int) ground.y, (int) ground.width, (int) ground.height, null);
        }

        g.setColor(Color.WHITE);
        bestScore = Math.max(score, bestScore);

        if (gameOver) {
            menuGameOver.contentMenu(g,score,bestScore);
        }
        else{
            g.setFont(new Font("04B_19", Font.PLAIN, 35));
            g.drawString(String.valueOf((int) score), 190, 40);
        }
    }
    public void move() {
        if(gameStarted) {
            velocityY += gravity;
            for (int i = 0; i < birds.size(); i++) {
                Bird bird = birds.get(i);
                bird.y += velocityY;
                bird.y = Math.max(0, bird.y);
            }
        }

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;
            if(!pipe.passed && bird.x > pipe.x + pipe.width) {
                score += 0.5;
                pipe.passed = true;
            }
            if(collisionPipe(bird, pipe)) gameOver = true;
        }
        for (int i = 0; i < skills.size(); i++) {
            Skill skill = skills.get(i);
            skill.x += 2 * velocityX;
            if(collisionSkill(bird, skill)) gameOver = true;
        }

        for (int i = 0; i < grounds.size(); i++) {
            Ground ground = grounds.get(i);
            ground.x += - 2;
        }

        if(bird.y >= boardHeight - 104) gameOver = true;
    }
    boolean  collisionPipe(Bird a, Pipe b) {
        return a.x < b.x + b.width &&   //a's top left corner doesn't reach b's top right corner
                a.x + a.width > b.x &&   //a's top right corner passes b's top left corner
                a.y < b.y + b.height &&  //a's top left corner doesn't reach b's bottom left corner
                a.y + a.height > b.y;    //a's bottom left corner passes b's top left corner
    }
    boolean  collisionSkill(Bird a, Skill b) {
        return a.x < b.x + b.width &&   //a's top left corner doesn't reach b's top right corner
                a.x + a.width > b.x &&   //a's top right corner passes b's top left corner
                a.y < b.y + b.height &&  //a's top left corner doesn't reach b's bottom left corner
                a.y + a.height > b.y;    //a's bottom left corner passes b's top left corner
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(state == STATE.MENU || state == STATE.HELP) repaint();
        if(state == STATE.GAME) {
            if(gameOver){
                birdTimer.stop();
                placePipesTimer.stop();
                placeGroundTimer.stop();
                gameLoop.stop();
                birds.clear();
                pipes.clear();
                skills.clear();
                bird = new Bird(birdImg1);
                birds.add(bird);
                bird = new Bird(birdImg2);
                birds.add(bird);
                bird = new Bird(birdImg3);
                birds.add(bird);
                score = 0;
                velocityY = -7;
                gameStarted = false;
                return;
            }
            move();
            repaint();
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(state == STATE.GAME) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(!gameStarted && gameRestart) {
                gameStarted = true; // Khi người dùng nhấn phím cách, trò chơi bắt đầu.
                gameRestart = false;
                placePipesTimer.start();
                return;
            }
            velocityY = -7;
        }
}
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}