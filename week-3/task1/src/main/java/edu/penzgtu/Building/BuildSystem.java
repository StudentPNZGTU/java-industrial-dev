package edu.penzgtu.Building;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildSystem implements BuildSystemInterface {
    private Building building;

    public BuildSystem(Building building) {this.building = building;}

    @Override
    public int searchElevator(int floorNum) {
        int min = 1000000000; int ind = 0;
        for (int i = 0; i < building.getElevators().size(); i++) {
            if (Math.abs(floorNum - building.getElevators().get(i)) < min) {
                min = Math.abs(floorNum - building.getElevators().get(i));
                ind = i;
            }
        }
        building.getElevators().set(ind, floorNum);
        System.out.println("Elevator number "+(ind+1)+" is located on floor "+floorNum);
        // There should always be an elevator on the ground floor
        if (!building.getElevators().contains(1)) {
            System.out.println("Elevator number "+(searchElevator(1)+1)+" descends to the ground floor");
        }
        return ind;
    }
}
