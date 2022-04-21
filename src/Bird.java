import java.awt.*;

public class Bird {

    private CustomRectangle body;
    private Triangle beak;
    private Circle eye;
    private int anchorX;
    private int anchorY;
    private boolean directionRight;

    private static final int BIRD_BODY_SIZE = 30;
    private static final int BIRD_BEAK_Y = BIRD_BODY_SIZE/2;
    private static final int BIRD_EYE_Y = BIRD_BODY_SIZE/6;
    private static final int BIRD_EYE_X_RIGHT = BIRD_BODY_SIZE/2;
    private static final int BIRD_EYE_X_LEFT = BIRD_BODY_SIZE/6;
    private static final int BIRD_EYE_SIZE = BIRD_BODY_SIZE/3;
    private static final int START_POSITION_X_GAP = 35;
    private static final int START_POSITION_Y_GAP = 70;
    public static final int BIRD_BEAK_SIZE = BIRD_BODY_SIZE/5;


    public Bird(int windowWidth, int windowHeight) {
        this.anchorX = windowWidth/2-START_POSITION_X_GAP;
        this.anchorY = windowHeight/2-START_POSITION_Y_GAP;
        this.directionRight = true;

        this.body = new CustomRectangle(anchorX,anchorY,BIRD_BODY_SIZE,BIRD_BODY_SIZE, Color.GREEN);
        this.beak = new Triangle(anchorX+BIRD_BODY_SIZE, anchorY+BIRD_BEAK_Y, BIRD_BEAK_SIZE,BIRD_BEAK_SIZE, Color.RED,1);
        this.eye = new Circle(anchorX+BIRD_EYE_X_RIGHT,anchorY+BIRD_EYE_Y,BIRD_EYE_SIZE, Color.WHITE);
    }

    public void paint (Graphics graphics) {
        body.paint(graphics);
        beak.paint(graphics);
        eye.paint(graphics);
    }

    public void flip() {
        if (directionRight) {
            eye.update(anchorX+BIRD_EYE_X_LEFT,anchorY+BIRD_EYE_Y);
            beak.update(anchorX,anchorY+BIRD_BEAK_Y,Triangle.DIRECTION_LEFT);
            directionRight = false;
        }
        else {
            eye.update(anchorX+BIRD_EYE_X_RIGHT,anchorY+BIRD_EYE_Y);
            beak.update(anchorX+BIRD_BODY_SIZE,anchorY+BIRD_BEAK_Y,Triangle.DIRECTION_RIGHT);
            directionRight = true;
        }
    }

    public void moveDown () {
        this.anchorY++;
        update(anchorX,anchorY);
    }
    public void moveRight() {
        this.anchorX++;
        update(anchorX,anchorY);
    }
    public void moveLeft() {
        this.anchorX--;
        update(anchorX,anchorY);
    }
    public void moveUp() {
        this.anchorY--;
        update(anchorX,anchorY);
    }
    private void update(int anchorX, int anchorY) {
        this.body.update(anchorX,anchorY);
        this.beak.update(anchorX+(directionRight? BIRD_BODY_SIZE: 0), anchorY+BIRD_BEAK_Y, beak.getDirection());
        this.eye.update(anchorX + (directionRight? BIRD_EYE_X_RIGHT: BIRD_EYE_X_LEFT), anchorY + BIRD_EYE_Y);

    }

    public CustomRectangle getBody() {
        return body;
    }

    public boolean isDirectionRight() {
        return directionRight;
    }

    public boolean touchEdge () {
        boolean check = false;
        if (directionRight) {
            check = anchorX+BIRD_BODY_SIZE+BIRD_BEAK_SIZE == Window.WINDOW_WIDTH-10;
        }
        else {
            check = anchorX-BIRD_BEAK_SIZE==0;
        }
        return check;
    }

    public boolean isBirdCollided(Triangle obstacle) {
        Polygon obstaclePolygon = new Polygon(obstacle.getX(),obstacle.getY(),3);
        return obstaclePolygon.intersects(this.body.getRectangle());
    }

    public void setPosition() {
        this.anchorX = Window.WINDOW_WIDTH/2-START_POSITION_X_GAP;
        this.anchorY = Window.WINDOW_HEIGHT/2-START_POSITION_Y_GAP;
        this.directionRight = true;

        this.body = new CustomRectangle(anchorX,anchorY,BIRD_BODY_SIZE,BIRD_BODY_SIZE, Color.GREEN);
        this.beak = new Triangle(anchorX+BIRD_BODY_SIZE, anchorY+BIRD_BEAK_Y, BIRD_BEAK_SIZE,BIRD_BEAK_SIZE, Color.RED,1);
        this.eye = new Circle(anchorX+BIRD_EYE_X_RIGHT,anchorY+BIRD_EYE_Y,BIRD_EYE_SIZE, Color.WHITE);
    }
}
