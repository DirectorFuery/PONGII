import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.swing.*;
import java.awt.*;

import Entities.AIPaddle;
import Entities.Paddle;
import Entities.Pitch;
import Entities.Puck;
import Utils.FrameRateCapper;

public class Controller {
    private char ballChar = '.'; 
    private int fieldWidth = 79;
    private int fieldHeight = 21;

    private FrameRateCapper frameRateCapper = new FrameRateCapper(60);
    private Pitch pitch = new Pitch(fieldWidth, fieldHeight);
    private Puck puck = new Puck(ballChar, fieldWidth, fieldHeight, 5, 3);

    private AIPaddle aiPaddle = new AIPaddle(5, 2, fieldHeight, '>', 0.18);

    private JFrame output = new JFrame("TableHocii");
    private JLabel pitchDisplay = new JLabel();

    String[] pitchBase = this.pitch.RenderPitch();

    private void PrepareOuput() {
        this.pitchDisplay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        this.pitchDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        // Add the button to the frame
        this.output.getContentPane().add(this.pitchDisplay);
        // Set the size of the frame
        this.output.setSize(600, 600);
        // Make the frame visible
        this.output.setVisible(true);
        // Set default close operation
        this.output.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void RenderFrame() {
        String[] renderedPitch = this.pitchBase.clone();
        renderedPitch = this.puck.renderToPitch(renderedPitch);
        this.aiPaddle.renderToPitch(renderedPitch);
        
        this.pitchDisplay.setText("<html><pre>" + String.join("", renderedPitch) + "</pre></html>");
    }

    public void RunGame() {
        PrepareOuput();

        long endex = LocalDateTime.now().plus(1, ChronoUnit.MINUTES).toEpochSecond(java.time.ZoneOffset.UTC);

        this.aiPaddle.bindPuck(this.puck);
        this.puck.start();

        while (LocalDateTime.now().toEpochSecond(java.time.ZoneOffset.UTC) < endex) {
            if (!frameRateCapper.isNextFrame()){
                continue;
            }

            this.puck.update();
            this.aiPaddle.update();

            RenderFrame();
        }
    }
}
