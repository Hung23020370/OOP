package Code;

import javax.swing.*;
import java.awt.*;

public class MenuGameStart {
     Image playButtonEnImg ;
     Image helpButtonEnImg ;
     Image quitButtonEnImg ;
     Image backgroundImg;
     Image groudImg;
     Image menuImg;



    public void contentMenu(Graphics g) {
        menuImg = new ImageIcon(MenuGameStart.class.getResource("/Ảnh/Menu.png")).getImage();
        playButtonEnImg = new ImageIcon(MenuGameStart.class.getResource("/Ảnh/NEW GAME.png")).getImage();
        helpButtonEnImg = new ImageIcon(MenuGameStart.class.getResource("/Ảnh/HELP.png")).getImage();
        quitButtonEnImg = new ImageIcon(MenuGameStart.class.getResource("/Ảnh/QUIT.png")).getImage();
        backgroundImg = new ImageIcon(MenuGameStart.class.getResource("/Ảnh/bg.png")).getImage();
        groudImg = new ImageIcon(MenuGameStart.class.getResource("/Ảnh/ground.png")).getImage();

        g.drawImage(backgroundImg,0,0,400,640,null);
        g.drawImage(groudImg,0,560,800,100,null);
        g.drawImage(playButtonEnImg,130,250,140,140/3,null);
        g.drawImage(helpButtonEnImg,130,330,140,140/3,null);
        g.drawImage(quitButtonEnImg,130,410,140,140/3,null);
        g.drawImage(menuImg,75,100,250,125,null);

    }

}