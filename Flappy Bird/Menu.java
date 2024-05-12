import javax.swing.*;
import java.awt.*;

public class Menu {
    static Image playButtonEnImg ;
    static Image helpButtonEnImg ;
    static Image quitButtonEnImg ;
    static Image backgroundImg;
    static Image groudImg;



    public static void contentMenu(Graphics g) {
        playButtonEnImg = new ImageIcon(Menu.class.getResource("Play.png")).getImage();
        helpButtonEnImg = new ImageIcon(Menu.class.getResource("Help.png")).getImage();
        quitButtonEnImg = new ImageIcon(Menu.class.getResource("Quit.png")).getImage();
        backgroundImg = new ImageIcon(Menu.class.getResource("bg.png")).getImage();
        groudImg = new ImageIcon(Menu.class.getResource("ground.png")).getImage();

        g.drawImage(backgroundImg,0,0,400,640,null);
        g.drawImage(groudImg,0,560,800,100,null);
        g.drawImage(playButtonEnImg,100,200,200,80,null);
        g.drawImage(helpButtonEnImg,100,300,200,80,null);
        g.drawImage(quitButtonEnImg,100,400,200,80,null);

    }

}