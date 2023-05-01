package Models;
import java.util.Objects;

public class Coordinates {
    private int x;
    private float y;

    public Coordinates(int x, Float y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    @Override
    public String toString() {
        return "Координаты [" +
                "x=" + x +
                ", y=" + y +
                "]";
    }
}
