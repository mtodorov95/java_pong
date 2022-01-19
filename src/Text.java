import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Text {
    public String text;
    public Font font;
    public double x, y;
    public double width, height;
    public Color color = Color.WHITE;

    public Text(String text, Font font, double x, double y){
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
        this.width = font.getSize()*text.length();
        this.height = font.getSize();
    }

    public Text(int text, Font font, double x, double y){
        this.text = "" + text;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D context){
        context.setColor(color);
        context.setFont(font);
        context.drawString(text, (float) x, (float) y);
    }

    public Rectangle2D.Double toRectangle(){
        return new Rectangle2D.Double(this.x, this.y - this.height, this.width, this.height);
    }
}
