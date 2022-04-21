import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener {

    private Bird bird;
    private Thread down;

    public Movement(Bird bird, Thread down) {
        this.bird = bird;
        this.down = down;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case (int) KeyEvent.VK_SPACE:

                new Thread(() ->{
                    for (int i = 0; i < 90; i++) {
                        bird.moveUp();
                        try {
                            Thread.sleep(2);
                        } catch (Exception e2) {

                        }
                    }
                }).start();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
