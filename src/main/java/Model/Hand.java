package Model;

import java.util.*;

public class Hand {
    List<Card> cards = new LinkedList<>();
    int bid;

    public Hand(String cards, int bid) {
        this.bid = bid;
        for (char card :
                cards.toCharArray()) {
            this.cards.add(new Card(card));
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getBid() {
        return bid;
    }

    public HandType getType() {
        Map<Character, Integer> map = new HashMap<>();
        for (Card card : cards) {
            map.merge(card.name, 1, Integer::sum);
        }

        if (map.values().size() == 1)
            return HandType.FIVE_OF_A_KIND;
        if (map.values().size() == 5)
            return HandType.HIGH_CARD;
        if (map.values().size() == 4)
            return HandType.ONE_PAIR;
        if (map.containsValue(4))
            return HandType.FOUR_OF_A_KIND;
        if (map.values().size() == 2)
            return HandType.FULL_HOUSE;
        if (map.values().size() == 3 && map.containsValue(3))
            return HandType.THREE_OF_A_KIND;
        return HandType.TWO_PAIR;
    }

    public HandType getTypePart2() {
        Map<Character, Integer> map = new HashMap<>();
        for (Card card : cards) {
            map.merge(card.name, 1, Integer::sum);
        }
        if (map.containsKey('J')) {
            Integer numberOfJs = map.remove('J');
            if (map.isEmpty()) {
                map.put('2', numberOfJs);
            } else {
                Character maxChar = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
                map.merge(maxChar, numberOfJs, Integer::sum);
            }
        }

        if (map.values().size() == 1)
            return HandType.FIVE_OF_A_KIND;
        if (map.values().size() == 5)
            return HandType.HIGH_CARD;
        if (map.values().size() == 4)
            return HandType.ONE_PAIR;
        if (map.containsValue(4))
            return HandType.FOUR_OF_A_KIND;
        if (map.values().size() == 2)
            return HandType.FULL_HOUSE;
        if (map.values().size() == 3 && map.containsValue(3))
            return HandType.THREE_OF_A_KIND;
        return HandType.TWO_PAIR;
    }
}
