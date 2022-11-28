package Exercises.ComparableExercise;

import java.util.Collections;

public class TryMe {
    public static void main(String[] args) {
        LP lp1 = new LP("The Dark Side of the Moon", "Pink Floyd", 1973);
        LP lp2 = new LP("Highway 61 Revisited", "Bob Dylan", 1965);
        LP lp3 = new LP("Abbey Road", "The Beatles", 1969);
        LP lp4 = new LP("Highway to Hell", "AC/DC", 1979);

        LPContainer lpContainer = new LPContainer();
        lpContainer.add(lp1);
        lpContainer.add(lp2);
        lpContainer.add(lp3);
        lpContainer.add(lp4);

        lpContainer.print();
        System.out.println("*********************");
        Collections.sort(lpContainer.getLpList());
        lpContainer.print();
    }
}
