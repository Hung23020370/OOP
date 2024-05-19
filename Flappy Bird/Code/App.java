package Code;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        int boardWidth = 400;
        int boardHeight = 640;

        JFrame jFrame = new JFrame("Flappy Bird");
        jFrame.setSize(boardWidth,boardHeight);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false); // Khong thay doi kich thuoc cua so duoc
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyBird flappyBird = new FlappyBird();
        jFrame.add(flappyBird);
        jFrame.pack(); // Điều chỉnh cửa sổ phù hợp với các thành phần bên trong
        jFrame.requestFocus();
        jFrame.setVisible(true);

    }
}
