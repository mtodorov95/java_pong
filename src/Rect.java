import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rect {

    public double x, y;
    public double width, height;
    private Color color;

    public Rect(double x, double y, double width, double height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics2D context){
        context.setColor(color);
        context.fill(new Rectangle2D.Double(x,y,width,height));
    }

    public Rectangle2D.Double toRectangle(){
        return new Rectangle2D.Double(x,y,width,height);
    }

    public double getCenterY(){
        return this.y + (this.height/2.0);
    }
}
