import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseGame implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if(FlappyBird.state == FlappyBird.STATE.MENU) {
            //PlayGame
            if (x >= 130 && x <= 270 && y >= 250 && y <= 250 + 140 / 3) {
                FlappyBird.state = FlappyBird.STATE.GAME;
            }

            //QuitGame
            if (x >= 130 && x <= 270 && y >= 410 && y <= 410 + 140 / 3) {
                System.exit(1);
            }
        }

        // restart
        if(FlappyBird.gameOver && FlappyBird.state == FlappyBird.STATE.GAME) {
            if(x >= 140 && x <= 260 && y >=310 && y<=350) {
                FlappyBird.gameRestart = true;
                FlappyBird.gameOver = false;
                FlappyBird.gameLoop.start();
                FlappyBird.birdTimer.start();
                FlappyBird.placeGroundTimer.start();
            }
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