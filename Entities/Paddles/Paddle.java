package Entities.Paddles;

import Utils.Position;
import Utils.Statics;

public class Paddle {
    public int height;
    public Position position;
    public char paddleChar;

    protected int maxYPosition;
    protected int minYPosition;
    public int heightMid;

    public Paddle(int height, int columnPosition, int fieldHeight, char paddleChar) {
        this.height = height;
        this.heightMid = Statics.getHalfPointOfOddNumber(height);
        this.position = new Position(columnPosition, Statics.getHalfPointOfOddNumber(fieldHeight));

        this.paddleChar = paddleChar;

        this.minYPosition = this.heightMid + 2;
        this.maxYPosition = fieldHeight - this.heightMid;
    }

    public void renderToPitch(String[] pitch) {
        int drawYStart = (int) this.position.y - this.heightMid;
        int drawYEnd = drawYStart + this.height;
        for (int i = drawYStart; i < drawYEnd; i++) {
            StringBuilder lineBuilder = new StringBuilder(pitch[i]);
            lineBuilder.setCharAt((int) this.position.x, paddleChar);
            pitch[i] = lineBuilder.toString();
        }
    }
}
