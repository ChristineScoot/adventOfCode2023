package Solutions;

import Model.Brick;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day22 {
    private static Map<Brick, List<Brick>> supportedBy = new HashMap<>();
    private static Map<Brick, List<Brick>> supports = new HashMap<>();

    public static long part1(String filePath) throws IOException {
        supportedBy = new HashMap<>();
        supports = new HashMap<>();
        System.out.println("~~~~~~~~~~~~Day 22: Part 1~~~~~~~~~~~~");
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        List<Brick> bricks = getSnapshotBricks(fileContentString);
        for (int currentBrickId = 0; currentBrickId < bricks.size(); currentBrickId++) {
            Brick currentBrick=bricks.get(currentBrickId);
            int currentMinZ= 1;
            for (int brickToCheckId = 0; brickToCheckId < currentBrickId; brickToCheckId++) { //can this be reversed and break; in intersects?
                Brick brickToCheck=bricks.get(brickToCheckId);
                if(brickToCheck.intersects(currentBrick))
                    currentMinZ=Math.max(currentMinZ, brickToCheck.getMaxZ()+1);
            }
            int deltaZ=currentMinZ - currentBrick.getMinZ();
            currentBrick.setZ(deltaZ);

        }

        bricks.sort(Brick::compareMinZ);
        countSupportedBricks(bricks);

        long count=0;
        for (Brick currentBrick : bricks) {
            boolean isSafe = true;
            for (Brick supportedBrick : supports.get(currentBrick)) {
                if (supportedBy.get(supportedBrick).size() == 1) {
                    isSafe = false;
                    break;
                }
            }
            count += isSafe ? 1 : 0;
        }
        return count;
    }

    private static void countSupportedBricks(List<Brick> bricks) {
        for (Brick brick:bricks) {
            supportedBy.put(brick, new ArrayList<>());
            supports.put(brick, new ArrayList<>());
        }
        for (int currentBrickId = 0; currentBrickId < bricks.size(); currentBrickId++) {
            Brick currentBrick = bricks.get(currentBrickId);
            for (int brickToCheckId = 0; brickToCheckId < currentBrickId; brickToCheckId++) { //can this be reversed and break; in intersects?
                Brick brickToCheck = bricks.get(brickToCheckId);
                if(brickToCheck.intersects(currentBrick) && brickToCheck.getMaxZ()+1==currentBrick.getMinZ()){
                    List<Brick> temp= supportedBy.remove(currentBrick);
                    temp.add(brickToCheck);
                    supportedBy.put(currentBrick,temp);

                    temp=supports.remove(brickToCheck);
                    temp.add(currentBrick);
                    supports.put(brickToCheck, temp);
                }
            }
        }
    }

    private static List<Brick> getSnapshotBricks(String[] fileContentString) {
        List<Brick> bricks = new LinkedList<>();
        for (String line : fileContentString) {
            String[] coordinates = line.split("[,~]");
                bricks.add(new Brick(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2]),
                        Integer.parseInt(coordinates[3]), Integer.parseInt(coordinates[4]), Integer.parseInt(coordinates[5])));
        }
        bricks.sort(Brick::compareMinZ);
        return bricks;
    }
}
