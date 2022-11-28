package Exercises.ComparableExercise;

import java.util.ArrayList;
import java.util.List;

public class LPContainer {
    private ArrayList<LP> lpList;

    public LPContainer() {
        lpList = new ArrayList<>();
    }

    public void add(LP lp) {
        lpList.add(lp);
    }

    public void remove(LP lp) {
        lpList.remove(lp);
    }

    public List<LP> getLpList() {
        return lpList;
    }

    public void print() {
        for (LP lp : lpList) {
            lp.printInfo();
        }
    }
}
