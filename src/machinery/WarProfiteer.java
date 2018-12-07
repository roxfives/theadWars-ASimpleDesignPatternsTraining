package machinery;


import java.util.ArrayList;
import java.util.Stack;

public class WarProfiteer {
    private Stack<Plane> availablePlanes = new Stack<>();
    private ArrayList<Plane> lockedPlanes = new ArrayList<>();

    private Stack<BaseLand> availableBaseLands = new Stack<>();
    private ArrayList<BaseLand> lockedBaseLands = new ArrayList<>();

    private Stack<Kamikaze> availableKamikazes = new Stack<>();
    private ArrayList<Kamikaze> lockedKamikazes = new ArrayList<>();

    private Stack<Ship> availableShips = new Stack<>();
    private ArrayList<Ship> lockedShips = new ArrayList<>();


    private WarProfiteer() { }

    private static class WarProfiteerHolder {
        private static final WarProfiteer WAR_PROFITEER = new WarProfiteer();
    }

    public static WarProfiteer getInstance() {
        return WarProfiteerHolder.WAR_PROFITEER;
    }

    public synchronized WarUnit getWarMachine(MachineType type) {
        switch (type) {
            case AIR:
                if(this.availablePlanes.size() > 0) {
                    System.out.println("Giving existing unit: " + this.availablePlanes.size());
                    return this.availablePlanes.pop();
                } else {
                    Plane plane = new Plane();

                    System.out.println("Creating new unit: " + this.availablePlanes.size());
                    lockedPlanes.add(plane);
                    return plane;
                }

            case LAND:
                if(this.availableBaseLands.size() > 0) {
                    System.out.println("Giving existing unit: " + this.availableBaseLands.size());
                    return this.availableBaseLands.pop();
                } else {
                    BaseLand base = new BaseLand();

                    System.out.println("Creating new unit: " + this.availableBaseLands.size());
                    lockedBaseLands.add(base);
                    return base;
                }

            case ONE_TIME_ONLY:
                if(this.availableKamikazes.size() > 0) {
                    System.out.println("Giving existing unit: " + this.availableKamikazes.size());
                    return this.availableKamikazes.pop();
                } else {
                    Kamikaze kamikaze = new Kamikaze();

                    System.out.println("Creating new unit: " + this.availableKamikazes.size());
                    lockedKamikazes.add(kamikaze);
                    return kamikaze;
                }

            case SEA:
                if(this.availableShips.size() > 0) {
                    System.out.println("Giving existing unit: " + this.availableShips.size());
                    return this.availableShips.pop();
                } else {
                    Ship ship = new Ship();

                    System.out.println("Creating new unit: " + this.availableShips.size());
                    lockedShips.add(ship);
                    return ship;
                }

            default:
                return null;
        }
    }

    public synchronized void returnWarMachine(WarUnit unit) {
        MachineType type = unit.getMachineType();

        unit.reset();
        System.out.println("Returning unit: " + this.lockedPlanes.size());
        switch (type) {
            case AIR:
                this.lockedPlanes.remove(unit);
                this.availablePlanes.push((Plane) unit);
                break;

            case LAND:
                this.lockedBaseLands.remove(unit);
                this.availableBaseLands.push((BaseLand) unit);
                break;

            case ONE_TIME_ONLY:
                this.lockedKamikazes.remove(unit);
                this.availableKamikazes.push((Kamikaze) unit);
                break;

            case SEA:
                this.lockedShips.remove(unit);
                this.availableShips.push((Ship) unit);
                break;
        }
    }
}
