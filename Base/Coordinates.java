package Base;
import java.util.Objects;

public class Coordinates {
    private double x;
    private Float y;
    public double getX() {
        return x;
    }
    public Float getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(Float y) {
        this.y = y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.x, x) == 0 && y.equals(that.y);
    }
    @Override
    public String toString() {
        return "Координаты [" +
                "x=" + x +
                ", y=" + y +
                "]";
    }
}
