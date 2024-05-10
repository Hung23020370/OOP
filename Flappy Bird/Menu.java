import javax.swing.*;
import java.awt.*;

public class Menu {
    static Image playButtonImg ;
    static Image helpButtonImg ;
    static Image quitButtonImg ;


    public static void contentMenu(Graphics g) {
        playButtonImg = new ImageIcon(Menu.class.getResource("Play.png")).getImage();
        helpButtonImg = new ImageIcon(Menu.class.getResource("Help.png")).getImage();
        quitButtonImg = new ImageIcon(Menu.class.getResource("Quit.png")).getImage();

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",Font.BOLD,40));
        g.drawString("FLAPPY BIRD", 80, 150);
        g.drawImage(playButtonImg,100,200,200,80,null);
        g.drawImage(helpButtonImg,100,300,200,80,null);
        g.drawImage(quitButtonImg,100,400,200,80,null);

    }

}
