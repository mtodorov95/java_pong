public abstract class PaddleController {
    protected Rect paddle;
    protected InputHandler inputHandler;
    protected Rect ball;

    public abstract void update(double dt);

    protected void moveUp(double dt){
        if (paddle.y - Constants.MOVEMENT_SPEED*dt > Constants.TOOLBAR_HEIGHT) {
            this.paddle.y -= Constants.MOVEMENT_SPEED * dt;
        }
    }

    protected void moveDown(double dt){
        if ((paddle.y + Constants.MOVEMENT_SPEED*dt)+paddle.height < Constants.SCREEN_HEIGHT-Constants.INSETS_BOTTOM){
            this.paddle.y += Constants.MOVEMENT_SPEED * dt;
        }
    }
}
