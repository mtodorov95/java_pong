import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {

    Graphics2D graphics;

    public Window(){
        this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        graphics = (Graphics2D) this.getGraphics();
    }

    public void update(double dt){
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
    }

    @Override
    public void run() {
        double lastFrame = 0.0;

        while (true){
            double now = Timer.getTime();
            double deltaTime = now - lastFrame;
            lastFrame = now;
            update(deltaTime);

            // Cap FPS to 30
            try{
                Thread.sleep(Constants.FPS);
            }catch (Exception e){
                //
            }
        }
    }
}
