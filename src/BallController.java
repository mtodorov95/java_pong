public class BallController {

    Rect ball, leftPaddle, rightPaddle;
    Window window;

    private double xVelocity = -100.0;
    private double yVelocity = 1.0;

    public BallController(Rect ball, Rect leftPaddle, Rect rightPaddle, Window window){
        this.ball = ball;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.window = window;
    }

    public void update(double dt){
        if (xVelocity<0) {
            if (checkCollision(leftPaddle)) {
                calculateNewVelocity(leftPaddle);
            }else if (this.ball.x < this.leftPaddle.x){
                window.updateScore(rightPaddle);
                this.ball.x = Constants.SCREEN_WIDTH/2.0;
                this.ball.y = Constants.SCREEN_HEIGHT/2.0;
                xVelocity = 100.0;
                yVelocity = 1.0;
            }
        }else{
            if (checkCollision(rightPaddle)) {
                calculateNewVelocity(rightPaddle);
            }else if (this.ball.x + this.ball.width > this.rightPaddle.x + this.rightPaddle.width){
                window.updateScore(leftPaddle);
                this.ball.x = Constants.SCREEN_WIDTH/2.0;
                this.ball.y = Constants.SCREEN_HEIGHT/2.0;
                xVelocity = -100.0;
                yVelocity = 1.0;
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

    private double getVelocityAngle(Rect paddle){
        double relativeIntersectY = paddle.getCenterY() - ball.getCenterY();
        double normIntersectY = relativeIntersectY / (paddle.height/2);
        double theta = normIntersectY * Constants.MAX_ANGLE;

        return Math.toRadians(theta);
    }

    private void calculateNewVelocity(Rect paddle){
        double theta = getVelocityAngle(paddle);
        double newVx = Math.abs(Math.cos(theta) * Constants.BALL_SPEED);
        double newVy = -Math.sin(theta) * Constants.BALL_SPEED;
        double oldSign = Math.signum(xVelocity);

        this.xVelocity = newVx * (-1.0 * oldSign);
        this.yVelocity = newVy;
    }
}
