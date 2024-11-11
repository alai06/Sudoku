import java.util.Objects;
public class Coord {
    int x, y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Coord)) return false;

        Coord coord = (Coord) obj;
        return this.x == coord.x && this.y == coord.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
