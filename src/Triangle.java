import java.awt.*;

public class Triangle {

    private int[] x;
    private int[] y;
    private int direction;
    private int width;
    private int height;
    private Color color;

    public static final int DIRECTION_RIGHT=1;
    public static final int DIRECTION_LEFT=2;
    public static final int DIRECTION_UP=3;
    public static final int DIRECTION_DOWN=4;

    public Triangle(int x, int y, int width, int height, Color color, int direction) {
        this.color = color;
        this.direction = direction;
        this.width = width;
        this.height = height;

        update(x,y,direction);

    }

    private static final int TRIANGLE_POINTS = 3;
    public void paint(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillPolygon(x,y,TRIANGLE_POINTS);
    }

    public void update(int x, int y, int direction) {
        this.direction = direction;
        switch (direction) {
            case DIRECTION_RIGHT:
                this.x= new int[]{x, x + width, x};
                this.y = new int[] {y, y+height/2, y+height};
                break;
            case DIRECTION_LEFT:
                this.x= new int[]{x, x - width, x};
                this.y = new int[] {y, y+height/2, y+height};
                break;
            case DIRECTION_UP:
                this.x= new int[]{x, x + width/2, x+width};
                this.y = new int[] {y, y-height, y};
                break;
            case DIRECTION_DOWN:
                this.x= new int[]{x, x + width/2, x+width};
                this.y = new int[] {y, y+height, y};
                break;

        }
    }

    public String toString() {
        return "("+x[0]+","+y[0]+")"+"("+x[1]+","+y[1]+")"+"("+x[2]+","+y[2]+")";
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }



}
