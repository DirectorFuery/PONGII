package Entities.Paddles;

import java.util.HashMap;
import java.util.Map;

public class PlayerPaddle extends Paddle {
    private double paddleMoveSpeed;

    private static final Map<String, Boolean> inputs = new HashMap<String, Boolean>() {{
        put("up", false);
        put("down", false);
    }};

    public PlayerPaddle(int height, int columnPosition, int fieldHeight, char paddleChar, double paddleMoveSpeed) {
        super(height, columnPosition, fieldHeight, paddleChar);

        this.paddleMoveSpeed = paddleMoveSpeed;
    }

    // inputs are inverted because the pitch renders upside down :3 
    public void update() {
        // if up and below ymax move up
        if (inputs.get("up") && this.position.y > this.minYPosition) {
            this.position.y -= this.paddleMoveSpeed;
        }

        // if down and below ymin move down
        if (inputs.get("down") && this.position.y < this.maxYPosition) {
            this.position.y += this.paddleMoveSpeed;
        }
    }

    public void updateInput(String inputName, Boolean state) {
        inputs.put(inputName, state);
    }
}
