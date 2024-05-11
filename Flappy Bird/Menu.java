import javax.swing.*;
import java.awt.*;

public class Menu {
    static Image playButtonEnImg ;
    static Image helpButtonEnImg ;
    static Image quitButtonEnImg ;


    public static void contentMenu(Graphics g) {
        playButtonEnImg = new ImageIcon(Menu.class.getResource("Play.png")).getImage();
        helpButtonEnImg = new ImageIcon(Menu.class.getResource("Help.png")).getImage();
        quitButtonEnImg = new ImageIcon(Menu.class.getResource("Quit.png")).getImage();

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",Font.BOLD,40));
        g.drawString("FLAPPY BIRD", 80, 150);
        g.drawImage(playButtonEnImg,100,200,200,80,null);
        g.drawImage(helpButtonEnImg,100,300,200,80,null);
        g.drawImage(quitButtonEnImg,100,400,200,80,null);

    }

}
