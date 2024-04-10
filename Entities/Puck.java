package Entities;

import Utils.Position;
import Utils.Vector2;
import Utils.Statics;

public class Puck {
    public Position position = new Position(0, 0);
    public int width;
    public int height;

    private Vector2 movementVector = new Vector2();
    private int minXPos = 0;
    private int maxXPos;
    private int minYPos = 0;
    private int maxYPos;

    public char ballChar;

    private int heightMid;
    private int widthMid;

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
        this.maxYPos = 1 + fieldHeight - this.heightMid;
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
            this.movementVector.invertX();
        }
        
        // check y collisions
        if (collisions[2] || collisions[3]) {
            this.movementVector.invertY();
        }
    }

    public boolean[] checkCollisions() {
        // [xMin, xMax, yMin, yMax]
        boolean[] collisions = new boolean[]{false, false, false, false};

        collisions[0] = this.position.x <= this.minXPos;
        collisions[1] = this.position.x >= this.maxXPos;
        collisions[2] = this.position.y <= this.minYPos;
        collisions[3] = this.position.y >= this.maxYPos;

        return collisions;
    }

    public String[] renderToPitch(String[] pitch) {
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

        return pitch;
    }
}
