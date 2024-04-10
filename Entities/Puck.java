package Entities;

import Entities.Paddles.*;

import Utils.Position;
import Utils.Vector2;
import Utils.Statics;

public class Puck {
    public Position position = new Position(0, 0);
    public int width;
    public int height;

    public char ballChar;

    private Vector2 movementVector = new Vector2();
    private int minXPos = 0;
    private int maxXPos;
    private int minYPos = 0;
    private int maxYPos;

    private int heightMid;
    private int widthMid;

    private AIPaddle aiPaddleRef;
    private PlayerPaddle playerPaddleRef;

    public Puck(char ballChar, int fieldWidth, int fieldHeight, int width, int height){
        this.position.x = Statics.getHalfPointOfOddNumber(fieldWidth);
        this.position.y = Statics.getHalfPointOfOddNumber(fieldHeight);

        this.width = width;
        this.height = height;

        this.ballChar = ballChar;

        this.heightMid = Statics.getHalfPointOfOddNumber(height);
        this.widthMid = Statics.getHalfPointOfOddNumber(width);

        this.minXPos = 1 + this.widthMid;
        this.maxXPos = fieldWidth - this.widthMid;
        this.minYPos = 1 + this.heightMid;  // top of pitch
        this.maxYPos = fieldHeight - this.heightMid;
    }

    public void start() {
        this.movementVector.setAngle(30); // should use rand
        this.movementVector.setMagnitude(0.5);
    }

    public void update() {
        this.movementVector.applyProjectionsToPosition(this.position);

        boolean[] collisions = checkCollisions();

        // check x collisions
        if (collisions[0] || collisions[1]) {
            // SCORE
            this.movementVector.setMagnitude(0);
        }
        
        // check y collisions
        if (collisions[2] || collisions[3]) {
            this.movementVector.invertY();
        }

        // check paddle collisions
        if (collisions[4] || collisions[5]) {
            this.movementVector.invertX();
        }
    }

    public void bindPaddle(AIPaddle paddle) {
        this.aiPaddleRef = paddle;
    }

    public void bindPaddle(PlayerPaddle paddle) {
        this.playerPaddleRef = paddle;
    }

    /* 
    *[Left, Right, Top, Bottom, aiPaddle, playerPaddel] 
    */
    public boolean[] checkCollisions() {
        boolean[] collisions = new boolean[]{false, false, false, false, false, false};

        boolean checkAIPaddle = this.position.x <= this.aiPaddleRef.position.x + (this.widthMid) + 1;
        boolean checkPlayerPaddle =this.position.x >= this.playerPaddleRef.position.x - (this.widthMid - 1);

        collisions[0] = this.position.x <= this.minXPos;
        collisions[1] = this.position.x >= this.maxXPos;
        collisions[2] = this.position.y <= this.minYPos;
        collisions[3] = this.position.y >= this.maxYPos;

        if (checkAIPaddle) {
            collisions[4] = Math.abs(this.position.y - this.aiPaddleRef.position.y) < (this.aiPaddleRef.heightMid - 1);
        } else if (checkPlayerPaddle) {
            collisions[5] = Math.abs(this.position.y - this.playerPaddleRef.position.y) < (this.playerPaddleRef.heightMid - 1);
        }

        return collisions;
    }

    public void renderToPitch(String[] pitch) {
        for (int i = 1; i <= this.height; i++) {
            int currentDrawWidth = 0;

            // above center of ball
            if (i < this.heightMid) {
                currentDrawWidth = this.width - ((this.heightMid  - i) * 2);
            }
            // center of ball 
            else if (i == this.heightMid) {
                currentDrawWidth = this.width;
            }
            // below center of ball
            else {
                currentDrawWidth = this.width - ((i - this.heightMid) * 2);
            }

            int drawYStart = (int)this.position.y - this.heightMid - 1;
            int drawXStart = (int)this.position.x - this.widthMid;
            
            StringBuilder lineBuilder = new StringBuilder(pitch[drawYStart + i]);

            // impose ball chars to line
            for (int j = 0; j < currentDrawWidth; j++) {
                // center chars in ball
                int leftOffest = (this.width - currentDrawWidth) / 2;

                lineBuilder.setCharAt(drawXStart + leftOffest + j, ballChar);
            } 

            // store line in var
            pitch[drawYStart + i] = lineBuilder.toString();
        }
    }
}
