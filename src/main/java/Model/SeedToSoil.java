package Model;

import java.math.BigInteger;

public class SeedToSoil {
    private final BigInteger source;
    private final BigInteger destination;
    private final BigInteger range;

    public SeedToSoil(BigInteger destination, BigInteger source, BigInteger range) {
        this.source = source;
        this.destination = destination;
        this.range = range;
    }

    public BigInteger getSource() {
        return source;
    }

    public boolean isWithinRange(BigInteger seed) {
        return (source.compareTo(seed) <= 0 && seed.compareTo(source.add(range)) < 0);
    }

    public BigInteger getDestination(BigInteger seed) {
        BigInteger length = seed.subtract(source);
        return destination.add(length);
    }
}
