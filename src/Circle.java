import java.awt.*;

public class Circle {

    private int x;
    private int y;
    private int diameter;
    private Color color;

    public Circle(int x, int y, int diameter, Color color) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = color;
    }

    public void paint (Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval(x,y,diameter,diameter);
    }

    public void update(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
