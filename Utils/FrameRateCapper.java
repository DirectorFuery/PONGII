package Utils;

public class FrameRateCapper {
    private long frameInterval;
    private long nextFrameAfter = 0;

    public FrameRateCapper(int fps) {
        // Calculate the frame interval in nanoseconds
        this.frameInterval = (long) (1e9 / fps);
    }

    public void start() {
        this.nextFrameAfter = System.nanoTime() + this.frameInterval;
    }

    public boolean isNextFrame() {
        long now = System.nanoTime();

        if (now > nextFrameAfter) {
            this.nextFrameAfter = now + this.frameInterval;
            return true;
        }

        return false;
    }
}

