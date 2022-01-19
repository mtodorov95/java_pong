import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {

    public Graphics2D graphics;
    public InputHandler inputHandler = new InputHandler();
    public Rect player;
    public Rect ai;
    public Rect ball;
    public PlayerController playerController;
    public AIController aiController;
    public BallController ballController;
    public Text leftScoreText, rightScoreText;
    public int leftScore, rightScore;
    public boolean isRunning = true;

    public Window(){
        this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.INSETS_BOTTOM = this.getInsets().bottom;

        graphics = (Graphics2D) this.getGraphics();

        this.addKeyListener(inputHandler);

        player = new Rect(30,100,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT,Color.WHITE);
        ai = new Rect(Constants.SCREEN_WIDTH-Constants.PLAYER_WIDTH-30,100,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT,Color.WHITE);
        ball = new Rect(Constants.SCREEN_WIDTH/2.0,Constants.SCREEN_HEIGHT/2.0,Constants.BALL_WIDTH,Constants.BALL_WIDTH,Color.WHITE);

        playerController = new PlayerController(player, inputHandler);
        aiController = new AIController(ai, ball);
        ballController = new BallController(ball, player, ai, this);

        leftScore = 0;
        rightScore = 0;

        leftScoreText = new Text(leftScore, new Font("Times New Roman", Font.BOLD, Constants.TEXT_SIZE), player.x + Constants.TEXT_SIZE, Constants.TEXT_Y);
        rightScoreText = new Text(rightScore, new Font("Times New Roman", Font.BOLD, Constants.TEXT_SIZE), ai.x - Constants.TEXT_SIZE, Constants.TEXT_Y);
    }

    public void update(double dt){
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        graphics.drawImage(dbImage,0,0,this);

        playerController.update(dt);
        aiController.update(dt);
        ballController.update(dt);
    }

    public void draw(Graphics context){
        Graphics2D g = (Graphics2D) context;

        g.setColor(Color.BLACK);
        g.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

        leftScoreText.draw(g);
        rightScoreText.draw(g);

        player.draw(g);
        ai.draw(g);
        ball.draw(g);
    }

    public void updateScore(Rect paddle){
        if (player.equals(paddle)) {
            leftScore++;
            leftScoreText.text = "" + leftScore;
            if (leftScore >= Constants.WIN_SCORE){
                System.out.println("You won");
                Main.changeState(0);
            }
        } else if (ai.equals(paddle)) {
            rightScore++;
            rightScoreText.text = "" + rightScore;
            if (rightScore >= Constants.WIN_SCORE){
                System.out.println("AI won");
                Main.changeState(0);
            }
        }
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

            // Cap FPS
//            try{
//                Thread.sleep(Constants.FPS);
//            }catch (Exception e){
//                //
//            }
        }
        this.dispose();
    }
}
