import java.awt.event.KeyEvent;

public class PlayerController {
    private Rect player;
    private InputHandler inputHandler;

    public PlayerController(Rect player, InputHandler inputHandler){
        this.player = player;
        this.inputHandler = inputHandler;
    }

    public void update(double dt){
        if (inputHandler.isKeyPressed(KeyEvent.VK_S)){
            if ((player.y + Constants.MOVEMENT_SPEED*dt)+player.height < Constants.SCREEN_HEIGHT-Constants.INSETS_BOTTOM){
                this.player.y += Constants.MOVEMENT_SPEED * dt;
            }
        }else if (inputHandler.isKeyPressed(KeyEvent.VK_W)){
            if (player.y - Constants.MOVEMENT_SPEED*dt > Constants.TOOLBAR_HEIGHT) {
                this.player.y -= Constants.MOVEMENT_SPEED * dt;
            }
        }
    }
}
