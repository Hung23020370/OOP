import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

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

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}