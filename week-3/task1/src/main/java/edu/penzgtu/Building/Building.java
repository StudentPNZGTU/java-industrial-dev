package edu.penzgtu.Building;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;

@Getter
@Setter
@ToString
public class Building {
    private int floors;
    private ArrayList<Integer> elevators;

    public Building(int floors, int countElevators) {
        this.floors = floors;
        this.elevators = initialElevators(countElevators);
    }

    private ArrayList<Integer> initialElevators(int countElevators) {
        ArrayList<Integer> elevators = new ArrayList<>();
        for (int elevator = 0; elevator < countElevators; elevator++) {
            elevators.add(1);
        }
        return elevators;
    }
}
