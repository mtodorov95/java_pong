public class BallController {

    Rect ball, leftPaddle, rightPaddle;

    private double xVelocity = -150.0;
    private double yVelocity = 25.0;

    public BallController(Rect ball, Rect leftPaddle, Rect rightPaddle){
        this.ball = ball;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    public void update(double dt){
        if (xVelocity<0) {
            if (checkCollision(leftPaddle)) {
                this.xVelocity *= -1;
                this.yVelocity *= -1;
            }else if (this.ball.x < this.leftPaddle.x){
                System.out.println("AI + 1");
            }
        }else{
            if (checkCollision(rightPaddle)) {
                this.xVelocity *= -1;
                this.yVelocity *= -1;
            }else if (this.ball.x + this.ball.width > this.rightPaddle.x + this.rightPaddle.width){
                System.out.println("Player + 1");
            }
        }

        if (yVelocity<0) {
            if (this.ball.y < Constants.TOOLBAR_HEIGHT){
                this.yVelocity *= -1;
            }
        }else{
            if (this.ball.y + this.ball.height > Constants.SCREEN_HEIGHT-Constants.INSETS_BOTTOM){
                this.yVelocity *= -1;
            }
        }


        this.ball.x+=xVelocity*dt;
        this.ball.y+=yVelocity*dt;
    }

    private boolean checkCollision(Rect other){
        return this.ball.toRectangle().intersects(other.toRectangle());
    }
}
