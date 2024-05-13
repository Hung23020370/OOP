import javax.swing.*;
import java.awt.*;

public class MenuGameOver {
    Image gameOverImg;
    Image restartImg;

    public void contentMenu(Graphics g, double score, double bestScore) {
        gameOverImg = new ImageIcon(MenuGameOver.class.getResource("gameover.png")).getImage();
        restartImg = new ImageIcon(MenuGameOver.class.getResource("RESTART.png")).getImage();

        g.drawImage(gameOverImg,75,150,250,250/4,null);
        g.drawImage(restartImg,140,310,120,40,null);

        g.setFont(new Font("04B_19", Font.PLAIN, 30));
        g.drawString("Score: " + String.valueOf((int) score), 140, 250);
        g.drawString("Best: " + String.valueOf((int) bestScore), 140 , 290);

    }
}
