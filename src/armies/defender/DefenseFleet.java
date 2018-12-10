package armies.defender;

import armies.Fleet;
import board.BattleField;
import machinery.MachineType;
import machinery.WarProfiteer;
import machinery.WarUnit;

import java.util.Random;

/**
 * A class which corresponds to a defending fleet
 */
public class DefenseFleet extends Fleet {
    private int nPlanes;

    private int nBaseLand;

    private int nShips;

    private DefenseFleet(int nPlanes, int nBaseLand, int nShips) {
        this.nPlanes = nPlanes;
        this.nBaseLand = nBaseLand;
        this.nShips = nShips;
    }

    public static DefenseFleet createFleet(int planes, int baseLands, int ships) {
        return new DefenseFleet(planes, baseLands, ships);
    }

    // The method to be ran by the defending fleet's thread
    @Override
    public void run() {
        int iPlane = 0;
        int iBaseLand = 0;
        int iShip = 0;

        System.out.println("Running defense fleet");
        // While there are war units and the other hasn't lost
        while((this.nPlanes > 0 || this.nBaseLand > 0 || this.nShips > 0) && BattleField.getInstance().isFighting()) {
            // Generates the war unit
            MachineType type = this.requestWarUnit();
            WarUnit warUnit = WarProfiteer.getInstance().getWarMachine(type); // Requests war unit from object pool

            while(warUnit.getLife() > 0 && BattleField.getInstance().isFighting()) {
                switch (warUnit.getClass().getSimpleName()) {
                    case "Plane":
                        System.out.println("Plane " + iPlane + " defending");
                        break;

                    case "BaseLand":
                        System.out.println("BaseLand " + iBaseLand + " defending");
                        break;

                    case "Ship":
                        System.out.println("Ship " + iShip + " defending");
                        break;
                }

                BattleField.getInstance().defend(warUnit);
            }

            WarProfiteer.getInstance().returnWarMachine(warUnit); // Returns war unit to object pool
            switch (warUnit.getClass().getSimpleName()) { // An id for each war unit
                case "Plane":
                    iPlane++;
                    break;

                case "BaseLand":
                    iBaseLand++;
                    break;

                case "Ship":
                    iShip++;
                    break;
            }
        }

        BattleField.getInstance().setFighting(false);

        System.out.println("Defender has " + ((this.nPlanes == 0 && this.nBaseLand == 0 && this.nShips == 0)? "lost" : "won"));
    }

    // The method that decides which war unit will fight next
    @Override
    protected MachineType requestWarUnit() {
        Random random = new Random();
        int[] availableMachines = { this.nPlanes, this.nBaseLand, this.nShips };
        int index;

        do {
            index = random.nextInt(3);
        } while(availableMachines[index] == 0);

        switch (index) {
            case 0:
                this.nPlanes--;
                return MachineType.AIR;

            case 1:
                this.nBaseLand--;
                return MachineType.LAND;

            case 2:
                this.nShips--;
                return MachineType.SEA;

            default:
                System.out.println("Attacker says: NON EXISTING TYPE: " + index);
                return MachineType.AIR;
        }
    }
}
