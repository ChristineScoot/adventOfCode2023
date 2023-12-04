package Solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day4 {
    public static int part1(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 4: Part 1~~~~~~~~~~~~");
        //use only 1 letter variables :D (for bingo)
        Scanner r = new Scanner(myObj);
        int a;
        int s = 0;
        while (r.hasNextLine()) {
            a = 0;
            String[] l = r.nextLine().split(": ")[1].split(" \\| ");
            String[] w = l[0].split("\\s+");
            HashMap<Integer, Integer> m = new HashMap<>();
            for (String t : w) {
                if (t.equals("")) continue;
                m.put(Integer.parseInt(t.trim()), 0);
            }
            String[] e = l[1].split("\\s+");
            for (String t : e) {
                if (t.equals("")) continue;
                if (m.containsKey(Integer.parseInt(t.trim()))) {
                    a++;
                }
            }
            s += Math.pow(2, a - 1);
        }
        return s;
    }

    public static int part2(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 4: Part 2~~~~~~~~~~~~");
        Scanner r = new Scanner(myObj);
        int a; //number of matches (winning==elf's)
        int s = 0;
        int c = 1; //current card number
        HashMap<Integer, Integer> z = new HashMap<>(); //number of each of the cards
        while (r.hasNextLine()) {
            String L = r.nextLine();
            a = 0;
            String[] l = L.split(": ")[1].split(" \\| ");
            String[] w = l[0].split("\\s+");
            HashMap<Integer, Integer> m = new HashMap<>(); //winning numbers
            for (String t : w) {
                if (t.equals("")) continue;
                m.put(Integer.parseInt(t.trim()), 0);
            }
            String[] e = l[1].split("\\s+"); //elf's numbers
            for (String t : e) {
                if (t.equals("")) continue;
                if (m.containsKey(Integer.parseInt(t.trim()))) {
                    a++;
                }
            }
            //original
            z.merge(c, 1, Integer::sum);
            //a - number of games won
            for (int i = 1; i <= a; i++) {
                z.merge(c + i, z.get(c), Integer::sum); //increment by number of copies
            }
            c++;
        }
        for (int i : z.values()) {
            s += i;
        }
        return s;
    }
}