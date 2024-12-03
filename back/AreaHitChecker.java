package back;

public class AreaHitChecker implements Validation{
    private final short x;
    private final double y;
    private final short r;

    public AreaHitChecker(short x, double y, short r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public boolean validate() {
        if (x >= 0 && y >= 0 && (y <= -0.5 * x + 0.5 * r)) return true;
        else if (x > 0 && y < 0 && x < r  && Math.abs(y) < (double) r /2) return true;
        return x < 0 && y < 0 && Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow((double) r /2, 2);
    }
}
