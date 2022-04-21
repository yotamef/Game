import javax.swing.*;

public class Window extends JFrame {

    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 600;

    public Window() {
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        GameScene gameScene = new GameScene(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        this.add(gameScene);
        this.setVisible(true);



    }

    public static void main(String[] args) {
        Window main = new Window();

    }

}



