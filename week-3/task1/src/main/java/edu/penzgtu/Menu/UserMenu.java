package edu.penzgtu.Menu;

import edu.penzgtu.Building.BuildSystem;
import edu.penzgtu.Building.Building;

public class UserMenu extends Menu implements UserMenuInterface {

    private final BuildSystem buildSystem = new BuildSystem(initializeBuilding());
    @Override
    public void movement() {
        System.out.print("Elevators created: "); outElevators();
        int floor; int length = buildSystem.getBuilding().getFloors();
        while (true) {
            floor = inputInteger();
            if (floor == -1) {break;}
            if (floor < 1 || floor > length) {print("Error floor");continue;}
            clear();
            print("Before:");outElevators();
            buildSystem.searchElevator(floor);
            print("After:");outElevators();
        }
    }
    @Override
    public Building initializeBuilding() {
        print(
                "Enter the number of floors and elevators of the building. " +
                "By default (0\\n0), the building has 9 floors and 3 elevators. Exit: -1"
        );
        int floors = inputInteger(); int elevators = inputInteger();

        if (floors == 0 && elevators == 0) {
            return new Building(9, 3);
        } else if ((floors < 1 || elevators < 1) || elevators > floors) {
            print("Error input");
            System.exit(0);
        }
        return new Building(floors, elevators);
    }

    @Override
    public void outElevators() {print(buildSystem.getBuilding().getElevators().toString());}
}
