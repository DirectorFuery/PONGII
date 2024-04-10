package Entities.Paddles;

import Entities.Puck;

public class AIPaddle extends Paddle {
    public double paddleMoveSpeed;
    
    private Puck puckRef;

    public AIPaddle(int height, int columnPosition, int fieldHeight, char paddleChar, double paddleMoveSpeed) {
        super(height, columnPosition, fieldHeight, paddleChar);

        this.paddleMoveSpeed = paddleMoveSpeed;
    }

    public void bindPuck(Puck puck) {
        this.puckRef = puck;
    }

    public void update() {
        if (this.puckRef.position.y > this.position.y && this.position.y < this.maxYPosition){
            this.position.y += this.paddleMoveSpeed;
        } else if (this.puckRef.position.y < this.position.y && this.position.y > this.minYPosition){
            this.position.y -= this.paddleMoveSpeed;
        }
    }
}
