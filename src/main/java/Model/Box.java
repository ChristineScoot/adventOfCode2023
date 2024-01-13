package Model;

import java.util.LinkedList;
import java.util.List;

public class Box {
    List<Lense> lenses = new LinkedList<>();

    public void addLense(Lense lense) {
        if (containsLense(lense)) {
            int index = lenses.indexOf(lense);
            lenses.remove(index);
            lenses.add(index, lense);
        } else
            lenses.add(lense);
    }

    private boolean containsLense(Lense lense) {
        return lenses.contains(lense);
    }

    public void removeLense(Lense lense) {
        if (containsLense(lense))
            lenses.remove(lense);
    }

    public int countFocalLength(int boxNumber) {
        int totalLength = 0;
        for (int i = 0; i < lenses.size(); i++) {
            totalLength += (1 + boxNumber) * (i + 1) * lenses.get(i).getFocalLength();
        }
        return totalLength;
    }
}
