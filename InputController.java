import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Entities.Paddles.PlayerPaddle;

public class InputController implements KeyListener {
    private PlayerPaddle ppRef;

    public void bindPlayerPaddle(PlayerPaddle pp) {
        this.ppRef = pp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.ppRef.updateInput("up", true);
                break;

            case KeyEvent.VK_DOWN:
                this.ppRef.updateInput("down", true);
                break;

            default:
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.ppRef.updateInput("up", false);
                break;

            case KeyEvent.VK_DOWN:
                this.ppRef.updateInput("down", false);
                break;

            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }
}
