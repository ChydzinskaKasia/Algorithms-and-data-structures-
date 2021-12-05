package pl.edu.pw.ee;

public class Conflict implements Comparable<Conflict> {
    private final int value;

    public Conflict(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Conflict newObject = (Conflict) object;
        return value == newObject.value;
    }

    @Override
    public int hashCode() {
        return 7;
    }

    @Override
    public int compareTo(Conflict object) {
        return Integer.compare(value, object.value);
    }
}
