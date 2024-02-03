package Solutions;

import Model.Element;
import Model.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Day17 {
    private static int[][] grid;

    public static long part1(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 17: Part 1~~~~~~~~~~~~");

        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        grid = new int[fileContentString.length][];
        int counter = 0;
        for (String s : fileContentString) {
            char[] line = s.toCharArray();
            grid[counter] = new int[line.length];
            for (int j = 0; j < line.length; j++) {
                grid[counter][j] = Integer.parseInt(String.valueOf(line[j]));
            }
            counter++;
        }
        return pathFinding(grid, false);
    }

    private static int pathFinding(int[][] grid, boolean isPart2) {
        Queue<Element> queue = new PriorityQueue<>();
        Set<Node> visited = new HashSet<>();
        int endX = grid[0].length - 1;
        int endY = grid.length - 1;

        Node eastStart = new Node(1, 0, 1, Element.EAST);
        Node southStart = new Node(0, 1, 1, Element.SOUTH);
        queue.add(new Element(eastStart, grid[eastStart.y()][eastStart.x()]));
        queue.add(new Element(southStart, grid[southStart.y()][southStart.x()]));

        while (!queue.isEmpty()) {
            Element current = queue.poll();
            if (visited.contains(current.getNode()))
                continue;
            visited.add(current.getNode());
            if (current.getNode().x() == endX && current.getNode().y() == endY) {
                return current.getHeatLoss();
            }
            queue.addAll(isPart2 ? current.getNeighbours2(grid) : current.getNeighbours(grid));
        }
        return 0;
    }

    public static long part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 17: Part 2~~~~~~~~~~~~");

        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        grid = new int[fileContentString.length][];
        int counter = 0;
        for (String s : fileContentString) {
            char[] line = s.toCharArray();
            grid[counter] = new int[line.length];
            for (int j = 0; j < line.length; j++) {
                grid[counter][j] = Integer.parseInt(String.valueOf(line[j]));
            }
            counter++;
        }
        return pathFinding(grid, true);
    }
}
