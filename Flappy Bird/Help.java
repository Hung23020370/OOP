import javax.swing.*;
import java.awt.*;

public class Help {
    Image leftImg;
    Image rightImg;
    Image backImg;
    Image page1LImg;
    Image page1RImg;
    Image wordpage1Img;
    Image page2LImg;
    Image page2RImg;
    Image wordpage2Img;
    Image page3LImg;
    Image page3RImg;
    Image wordpage3Img;

    int numberPage = 1;
    int numberPageMax = 3;
    public void contentHelp(Graphics g) {
        leftImg = new ImageIcon(MenuGameOver.class.getResource("left.png")).getImage();
        rightImg = new ImageIcon(MenuGameOver.class.getResource("right.png")).getImage();
        backImg = new ImageIcon(MenuGameOver.class.getResource("BACK.png")).getImage();
        //1
        page1LImg = new ImageIcon(MenuGameOver.class.getResource("page1l.jpg")).getImage();
        page1RImg = new ImageIcon(MenuGameOver.class.getResource("page1r.png")).getImage();
        wordpage1Img = new ImageIcon(MenuGameOver.class.getResource("wordpage1.jpg")).getImage();
        //2
        page2LImg = new ImageIcon(MenuGameOver.class.getResource("page2l.png")).getImage();
        page2RImg = new ImageIcon(MenuGameOver.class.getResource("page2r.png")).getImage();
        wordpage1Img = new ImageIcon(MenuGameOver.class.getResource("wordpage2.jpg")).getImage();
        // Vẽ nền
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 400, 640);

        // vẽ nút back
        g.drawImage(backImg,10,10,90,30,null);

        // vẽ nút left - right - image - string
        if(numberPage == 1) {
            g.drawImage(rightImg,340,580,50,50,null);
            g.drawImage(page1LImg,0,210,195,304,null);
            g.drawImage(page1RImg,205,210,195,304,null);
            g.drawImage(wordpage1Img,30,70,340,113,null);
        }
        else if(numberPage == numberPageMax) {
            g.drawImage(leftImg,10,580,50,50,null);
        }
        else {
            g.drawImage(rightImg,340,580,50,50,null);
            g.drawImage(leftImg,10,580,50,50,null);
            g.drawImage(page2LImg,0,210,195,304,null);
            g.drawImage(page2RImg,205,210,195,304,null);
//            g.drawImage(wordpage2Img,30,70,340,113,null);
        }


    }

}
