package Model;

public class Card {
    char name;
    int value;

    public Card(char name) {
        this.name = name;
        switch (name) {
            case 'A' -> value = 14;
            case 'K' -> value = 13;
            case 'Q' -> value = 12;
            case 'J' -> value = 1; //for part 1 = 11
            case 'T' -> value = 10;
            default -> value = Integer.parseInt(String.valueOf(name));
        }
    }

    public int getValue() {
        return value;
    }
}
