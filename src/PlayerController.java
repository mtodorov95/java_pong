import java.awt.event.KeyEvent;

public class PlayerController extends PaddleController{

    public PlayerController(Rect player, InputHandler inputHandler){
        this.paddle = player;
        this.inputHandler = inputHandler;
    }

    @Override
    public void update(double dt) {
        if (inputHandler != null) {
            if (inputHandler.isKeyPressed(KeyEvent.VK_S)) {
                moveDown(dt);
            } else if (inputHandler.isKeyPressed(KeyEvent.VK_W)) {
                moveUp(dt);
            }
        }
    }
}
