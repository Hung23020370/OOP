import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput extends JPanel implements MouseListener, ActionListener {

    // Các hình ảnh
    private Graphics g;
    static Image quitButtonEnImg = new ImageIcon(MouseInput.class.getResource("Quit.png")).getImage();
    static Image quitButtonExImg = new ImageIcon(MouseInput.class.getResource("Quit2.png")).getImage();

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

//        g.drawImage(playButtonImg,100,200,200,80,null);
//        g.drawImage(helpButtonImg,100,300,200,80,null);
//        g.drawImage(quitButtonImg,100,400,200,80,null);

        //PlayGame
        if(x >= 100 && x <= 300 && y >= 200 && y <= 280) {
            FlappyBird.state = FlappyBird.STATE.GAME;
        }

        //QuitGame
        if(x >= 100 && x <= 300 && y >= 400 && y <= 480) {
            System.exit(1);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    // Các phương thức của MouseListener
    @Override
    public void mouseEntered(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x >= 100 && x <= 300 && y >= 400 && y <= 480) {
            g.drawImage(quitButtonEnImg, 100, 400, 200, 80, null);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x >= 100 && x <= 300 && y >= 400 && y <= 480) {
            g.drawImage(quitButtonExImg, 100, 400, 200, 80, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    // Các phương thức khác của MouseListener
}
