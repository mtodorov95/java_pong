import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {

    Graphics2D graphics;
    InputHandler inputHandler = new InputHandler();
    Rect player;
    Rect ai;
    Rect ball;

    public Window(){
        this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        graphics = (Graphics2D) this.getGraphics();

        this.addKeyListener(inputHandler);

        player = new Rect(30,100,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT,Color.WHITE);
        ai = new Rect(Constants.SCREEN_WIDTH-Constants.PLAYER_WIDTH-30,100,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT,Color.WHITE);
        ball = new Rect(Constants.SCREEN_WIDTH/2,Constants.SCREEN_HEIGHT/2,Constants.BALL_WIDTH,Constants.BALL_WIDTH,Color.WHITE);
    }

    public void update(double dt){
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

        player.draw(graphics);
        ai.draw(graphics);
        ball.draw(graphics);
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
