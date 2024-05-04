public class TextGraphics {
    private static TextGraphics instance;
    private char[][] screenBuffer;
    private int screenWidth;
    private int screenHeight;

    private TextGraphics() {
        // Constructor riêng để ngăn việc khởi tạo trực tiếp
    }

    public static TextGraphics getInstance() {
        if (instance == null) {
            instance = new TextGraphics();
        }
        return instance;
    }

    public void init(int width, int height) {
        screenWidth = width;
        screenHeight = height;
        screenBuffer = new char[height][width];
        clearScreen();
    }

    private void clearScreen() {
        for (int i = 0; i < screenHeight; i++) {
            for (int j = 0; j < screenWidth; j++) {
                screenBuffer[i][j] = ' ';
            }
        }
    }
}
