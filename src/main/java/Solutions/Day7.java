package Solutions;

import Model.Hand;
import Model.HandType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {

    public static int part1(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 7: Part 1~~~~~~~~~~~~For this part change in Card.java: case 'J' -> value = 11;");
        Scanner scanner = new Scanner(myObj);
        int winnings = 0;

        Map<HandType, List<Hand>> map = new LinkedHashMap<>();
        map.put(HandType.HIGH_CARD, new LinkedList<>());
        map.put(HandType.ONE_PAIR, new LinkedList<>());
        map.put(HandType.TWO_PAIR, new LinkedList<>());
        map.put(HandType.THREE_OF_A_KIND, new LinkedList<>());
        map.put(HandType.FULL_HOUSE, new LinkedList<>());
        map.put(HandType.FOUR_OF_A_KIND, new LinkedList<>());
        map.put(HandType.FIVE_OF_A_KIND, new LinkedList<>());

        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("\\s");
            int bid = Integer.parseInt(line[1].trim());
            Hand hand = new Hand(line[0], bid);
            HandType type = hand.getType();
            map.computeIfAbsent(type, k -> new LinkedList<>()).add(hand);
        }
        int rank = 1;
        for (List<Hand> list : map.values()) {
            if (list.size() > 0) {
                list.sort((object1, object2) -> {
                    for (int i = 0; i < 5; i++) {
                        int firstValueOfACard = object1.getCards().get(i).getValue();
                        int secondValueOfACard = object2.getCards().get(i).getValue();
                        if (firstValueOfACard == secondValueOfACard)
                            continue;
                        return firstValueOfACard - secondValueOfACard;
                    }
                    return 0;
                });
            }
            for (Hand hand : list) {
                winnings += rank * hand.getBid();
                rank++;
            }
        }
        return winnings;
    }

    public static int part2(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 7: Part 2~~~~~~~~~~~~");
        Scanner scanner = new Scanner(myObj);
        int winnings = 0;

        Map<HandType, List<Hand>> map = new LinkedHashMap<>();
        map.put(HandType.HIGH_CARD, new LinkedList<>());
        map.put(HandType.ONE_PAIR, new LinkedList<>());
        map.put(HandType.TWO_PAIR, new LinkedList<>());
        map.put(HandType.THREE_OF_A_KIND, new LinkedList<>());
        map.put(HandType.FULL_HOUSE, new LinkedList<>());
        map.put(HandType.FOUR_OF_A_KIND, new LinkedList<>());
        map.put(HandType.FIVE_OF_A_KIND, new LinkedList<>());

        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("\\s");
            int bid = Integer.parseInt(line[1].trim());
            Hand hand = new Hand(line[0], bid);
            HandType type = hand.getTypePart2();
            map.computeIfAbsent(type, k -> new LinkedList<>()).add(hand);
        }
        int rank = 1;
        for (List<Hand> list : map.values()) {
            if (list.size() > 0) {
                list.sort((object1, object2) -> {
                    for (int i = 0; i < 5; i++) {
                        int firstValueOfACard = object1.getCards().get(i).getValue();
                        int secondValueOfACard = object2.getCards().get(i).getValue();
                        if (firstValueOfACard == secondValueOfACard)
                            continue;
                        return firstValueOfACard - secondValueOfACard;
                    }
                    return 0;
                });
            }
            for (Hand hand : list) {
                winnings += rank * hand.getBid();
                rank++;
            }
        }
        return winnings;
    }
}
