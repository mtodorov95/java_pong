public class AIController extends PaddleController{

    public AIController(Rect ai, Rect ball){
        this.paddle = ai;
        this.ball = ball;
    }

    @Override
    public void update(double dt) {
        if (ball.y < this.paddle.y){
            moveUp(dt);
        }else if (ball.y + ball.height >this.paddle.y + this.paddle.height){
            moveDown(dt);
        }
    }
}
