package Model;

import java.util.Objects;

public class Lense {
    private final String label;
    private int focalLength;

    public Lense(String label, int focalLength) {
        this.label = label;
        this.focalLength = focalLength;
    }

    public Lense(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lense lense = (Lense) o;
        return Objects.equals(label, lense.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    public int getFocalLength() {
        return focalLength;
    }
}
