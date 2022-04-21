import java.awt.*;

public class CustomRectangle {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public CustomRectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(this.x, this.y, this.width, this.height);
    }

    public void update(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle(this.x,this.y,this.width,this.height);
        return rectangle;
    }

    public String toString() {
        return "("+this.x+","+this.y+")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
