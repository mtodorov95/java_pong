import java.awt.*;

public class Text {
    public String text;
    public Font font;
    public double x, y;

    public Text(String text, Font font, double x, double y){
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public Text(int text, Font font, double x, double y){
        this.text = "" + text;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D context){
        context.setColor(Color.WHITE);
        context.setFont(font);
        context.drawString(text, (float) x, (float) y);
    }
}
