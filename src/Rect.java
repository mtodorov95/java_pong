import java.awt.*;

public class Rect {

    private int x, y, width, height;
    private Color color;

    public Rect(int x, int y, int width, int height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics2D context){
        context.setColor(color);
        context.fillRect(x,y,width,height);
    }
}
