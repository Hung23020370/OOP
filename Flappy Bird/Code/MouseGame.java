package Code;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseGame implements MouseListener{
    private boolean isPointInRect(int x, int y, int rectX, int rectY, int rectWidth, int rectHeight) {
        return (x >= rectX && x <= rectX + rectWidth && y >= rectY && y <= rectY + rectHeight);
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (FlappyBird.state == FlappyBird.STATE.MENU) {

            //PlayGame
            if (isPointInRect(x, y, 130, 250, 140, 140 / 3)) {
                FlappyBird.state = FlappyBird.STATE.GAME;
            }

            //Help
            if (isPointInRect(x, y, 130, 330, 140, 140 / 3)) {
                FlappyBird.state = FlappyBird.STATE.HELP;
            }

            //QuitGame
            if (isPointInRect(x, y, 130, 410, 140, 140 / 3)) {
                System.exit(1);
            }
        }

        // restart
        if (FlappyBird.gameOver && FlappyBird.state == FlappyBird.STATE.GAME) {
            if (isPointInRect(x, y, 140, 310, 120, 40)) {
                FlappyBird.gameRestart = true;
                FlappyBird.gameOver = false;
                FlappyBird.gameLoop.start();
                FlappyBird.birdTimer.start();
                FlappyBird.placeGroundTimer.start();
            }
        }
        if (FlappyBird.state == FlappyBird.STATE.HELP) {
            if (isPointInRect(x, y, 340, 580, 50, 50)) {
                if (Help.numberPage + 1 <= Help.numberPageMax) {
                    Help.numberPage++;
                }
            }
            if (isPointInRect(x, y, 10, 580, 50, 50)) {
                if (Help.numberPage -1 >= 0) {
                    Help.numberPage--;
                }
            }
            if(isPointInRect(x, y, 10, 10, 90, 30)) {
                FlappyBird.state = FlappyBird.STATE.MENU;
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