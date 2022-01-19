import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame implements Runnable {

    public Graphics2D graphics;
    public Text startGame, exitGame;
    public MouseHandler mouseHandler;
    public boolean isRunning = true;

    public Menu(){
        this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);

        graphics = (Graphics2D) this.getGraphics();

        startGame = new Text("START GAME", new Font("Times New Roman", Font.BOLD, Constants.TEXT_SIZE), Constants.SCREEN_WIDTH/2.0 - 140, Constants.SCREEN_HEIGHT/2.0);
        exitGame = new Text("EXIT GAME", new Font("Times New Roman", Font.BOLD, Constants.TEXT_SIZE), Constants.SCREEN_WIDTH/2.0 - 120, Constants.SCREEN_HEIGHT/2.0 + 50);
    }

    public void update(double dt){
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        graphics.drawImage(dbImage,0,0,this);

        if (startGame.toRectangle().contains(mouseHandler.getMouseX(), mouseHandler.getMouseY())){
            startGame.color = Color.LIGHT_GRAY;
            if (mouseHandler.isMouseClicked()){
                Main.changeState(1);
            }
        } else if (exitGame.toRectangle().contains(mouseHandler.getMouseX(), mouseHandler.getMouseY())){
            exitGame.color = Color.LIGHT_GRAY;
            if (mouseHandler.isMouseClicked()){
                System.exit(0);
            }
        } else {
            startGame.color = Color.WHITE;
            exitGame.color = Color.WHITE;
        }
    }

    public void draw(Graphics context){
        Graphics2D g = (Graphics2D) context;

        g.setColor(Color.BLACK);
        g.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

        startGame.draw(g);
        exitGame.draw(g);
    }

    public void stop(){
        isRunning = false;
    }

    @Override
    public void run() {
        double lastFrame = 0.0;

        while (isRunning){
            double now = Timer.getTime();
            double deltaTime = now - lastFrame;
            lastFrame = now;
            update(deltaTime);
        }
        this.dispose();
    }
}
