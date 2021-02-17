package lib;

public class FrameCounter {
    int countMax;
    int count;

    public FrameCounter(int countMax) {
        this.countMax = countMax;
        this.count = 0;

    }

    public boolean run() {
        if (this.count >= this.countMax) {
            return true;
        }
        this.count++;
        return false;
    }


    public void reset() {
        this.count = 0;
    }
}
