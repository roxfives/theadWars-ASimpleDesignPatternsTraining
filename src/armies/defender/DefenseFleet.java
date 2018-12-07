package armies.defender;

import armies.Fleet;
import board.BattleField;
import machinery.MachineType;
import machinery.Plane;
import machinery.WarProfiteer;
import machinery.WarUnit;

import java.util.ArrayList;
import java.util.Random;

public class DefenseFleet extends Fleet {
    private int nPlanes;

    private int nBaseLand;

    private int nShips;

    public DefenseFleet(int nPlanes, int nBaseLand, int nShips) {
        this.nPlanes = nPlanes;
        this.nBaseLand = nBaseLand;
        this.nShips = nShips;
    }

    @Override
    public void run() {
        int iPlane = 0;
        int iBaseLand = 0;
        int iShip = 0;

        System.out.println("Running defense fleet");
        while((this.nPlanes > 0 || this.nBaseLand > 0 || this.nShips > 0) && BattleField.getInstance().isFighting()) {
            MachineType type = this.requestWarUnit();
            WarUnit warUnit = WarProfiteer.getInstance().getWarMachine(type);

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

            WarProfiteer.getInstance().returnWarMachine(warUnit);
            switch (warUnit.getClass().getSimpleName()) {
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
