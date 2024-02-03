package Model;

public class HelperMethods {
    public static void printArray(char[][] arr) {
        for (char[] chars : arr) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    public static void printArray(int[][] arr) {
        for (int[] chars : arr) {
            for (int aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }
}
