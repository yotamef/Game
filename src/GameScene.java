import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameScene extends JPanel {

    private ArrayList<Triangle> floor;
    private ArrayList<Triangle> ceiling;
    private ArrayList<Triangle> rightTriangles;
    private ArrayList<Triangle> leftTriangles;
    private boolean alive;
    private int counter;

    private Bird bird;

    public static final int FLOOR_TRIANGLE_SIZE=30;
    private static final int SIDE_TRIANGLE_HEIGHT = 75;
    private static final int SIDE_TRIANGLE_WIDTH = 37;
    private static final int SIDE_GAP = 15;
    private static final int INSTRUCTIONS_SIZE = 300;
    private static final int BUTTON_SIZE = 100;


    public GameScene(int x, int y, int windowWidth, int windowHeight) {
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setBounds(x,y,windowWidth,windowHeight);

        floor = new ArrayList<>();
        ceiling = new ArrayList<>();
        for(int i=0; i<windowWidth; i=i+FLOOR_TRIANGLE_SIZE+1) {
            Triangle ceilingTriangle = new Triangle(i+1,0,FLOOR_TRIANGLE_SIZE,FLOOR_TRIANGLE_SIZE,Color.BLACK,Triangle.DIRECTION_DOWN);
            ceiling.add(ceilingTriangle);
            Triangle floorTriangle = new Triangle(i+1,windowHeight-FLOOR_TRIANGLE_SIZE-7,FLOOR_TRIANGLE_SIZE,FLOOR_TRIANGLE_SIZE,Color.BLACK,Triangle.DIRECTION_UP);
            floor.add(floorTriangle);
        }

        rightTriangles = new ArrayList<>();
        leftTriangles = new ArrayList<>();

        bird = new Bird(windowWidth,windowHeight);

        JLabel score = new JLabel("score: ");
        score.setBounds(windowWidth/3+50,FLOOR_TRIANGLE_SIZE+5,windowWidth/3,FLOOR_TRIANGLE_SIZE);
        score.setVisible(true);
        this.add(score);

        JLabel instructions = new JLabel("press space to jump and do not touch the spikes!");
        instructions.setBounds(windowWidth/2-INSTRUCTIONS_SIZE/2,windowHeight/2-INSTRUCTIONS_SIZE,INSTRUCTIONS_SIZE,INSTRUCTIONS_SIZE);
        instructions.setVisible(true);
        this.add(instructions);

        JButton start = new JButton("start");
        start.setBounds(windowWidth/2-BUTTON_SIZE/2,windowHeight/2-BUTTON_SIZE,BUTTON_SIZE,BUTTON_SIZE);
        start.setVisible(true);
        this.add(start);

        start.addActionListener((event) -> {
            instructions.setVisible(false);
            start.setVisible(false);
            gameLoop(score);
        });
    }

    private static final int GAME_SPEED = 4;
    private static final int DOWN_SPEED = 8;


    private void gameLoop(JLabel score) {

        alive = true;
        counter = 0;

        score.setText(score.getText() + counter);


        Thread stillAlive = new Thread(() -> {
            while (true) {
                while (alive) {
                    for (Triangle triangle : ceiling) {
                        if (bird.isBirdCollided(triangle)) {
                            alive = false;
                        }
                    }
                    for (Triangle triangle : floor) {
                        if (bird.isBirdCollided(triangle)) {
                            alive = false;
                        }
                    }
                    for (Triangle triangle : rightTriangles) {
                        if (bird.isBirdCollided(triangle)) {
                            alive = false;
                        }
                    }
                    for (Triangle triangle : leftTriangles) {
                        if (bird.isBirdCollided(triangle)) {
                            alive = false;
                        }
                    }
                    try {
                        Thread.sleep(GAME_SPEED);
                    } catch (InterruptedException e) {

                    }
                }
                repaint();
                try {
                    Thread.sleep(GAME_SPEED);
                } catch (Exception e) {

                }
            }
        });
        stillAlive.start();


        Thread down = new Thread (()-> {
            while (true) {
                while (alive) {
                    bird.moveDown();
                    repaint();
                    try {
                        Thread.sleep(DOWN_SPEED);
                    } catch (Exception e) {

                    }
                }
                repaint();
                try {
                    Thread.sleep(GAME_SPEED);
                } catch (Exception e) {

                }
            }
        });
        down.start();

        Thread move = new Thread(() -> {
            while (true) {
                while (alive) {
                    if (bird.isDirectionRight()) {
                        bird.moveRight();
                    } else {
                        bird.moveLeft();
                    }
                    repaint();
                    try {
                        Thread.sleep(GAME_SPEED);
                    } catch (Exception e) {

                    }
                    if (bird.touchEdge()) {
                        bird.flip();
                        counter++;
                        score.setText("score: " + counter);
                        addSideTriangles();
                    }

                }
                repaint();
                try {
                    Thread.sleep(GAME_SPEED);
                } catch (Exception e) {

                }
            }
        });
        move.start();

        JButton playAgain = new JButton("play again");
        playAgain.setBounds(Window.WINDOW_WIDTH/2-BUTTON_SIZE/2,Window.WINDOW_HEIGHT/2-BUTTON_SIZE,BUTTON_SIZE,BUTTON_SIZE);
        playAgain.setVisible(false);
        this.add(playAgain);

        playAgain.addActionListener((event) -> {
            alive = true;
            playAgain.setVisible(false);
            counter = 0;
            repaint();
        });

        Thread playAgain2 = new Thread(() -> {
            while (true) {
                if (!alive) {
                    bird.setPosition();
                    playAgain.setVisible(true);
                    leftTriangles.clear();
                    rightTriangles.clear();
                    repaint();
                }
                try {
                    Thread.sleep(GAME_SPEED);
                } catch (Exception e) {

                }
            }
        });
        playAgain2.start();


        Movement movement = new Movement(bird,down);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(movement);


    }
    public void addSideTriangles() {
        rightTriangles.clear();
        leftTriangles.clear();
        int counter = 0;
        if (bird.isDirectionRight()) {
            for (int i=FLOOR_TRIANGLE_SIZE+1; i<Window.WINDOW_HEIGHT-3*FLOOR_TRIANGLE_SIZE; i= i+ SIDE_TRIANGLE_HEIGHT) {
                Random random = new Random();
                if (random.nextBoolean() && counter<4) {
                    Triangle leftTriangle = new Triangle(Window.WINDOW_WIDTH-SIDE_GAP,i+1,SIDE_TRIANGLE_WIDTH, SIDE_TRIANGLE_HEIGHT,Color.BLACK,Triangle.DIRECTION_LEFT);
                    leftTriangles.add(leftTriangle);
                    counter++;
                }
            }
        }
        else {
            for (int i=FLOOR_TRIANGLE_SIZE+1; i<Window.WINDOW_HEIGHT-3*FLOOR_TRIANGLE_SIZE; i= i+ SIDE_TRIANGLE_HEIGHT) {
                Random random = new Random();
                if (random.nextBoolean() && counter<4) {
                    Triangle rightTriangle = new Triangle(0,i+1,SIDE_TRIANGLE_WIDTH, SIDE_TRIANGLE_HEIGHT,Color.BLACK,Triangle.DIRECTION_RIGHT);
                    rightTriangles.add(rightTriangle);
                    counter++;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            for (Triangle t : floor) {
                t.paint(g);
            }
            for (Triangle t : ceiling) {
                t.paint(g);
            }
            for (Triangle t : rightTriangles) {
                t.paint(g);
            }
            for (Triangle t : leftTriangles) {
                t.paint(g);
            }
        } catch (Exception e) {

        }

        bird.paint(g);

    }
}
