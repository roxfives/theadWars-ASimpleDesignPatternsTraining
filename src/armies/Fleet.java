package armies;

import armies.attacker.AttackFleet;
import armies.attacker.Attacker;
import armies.defender.DefenseFleet;
import machinery.MachineType;

/**
 * Any fleet must extend from this abstract class
 */
public abstract class Fleet implements Runnable {
    public static Fleet createFleet(Object general, int machinesOne, int machinesTwo, int machinesThree) {
        if(general instanceof Attacker) {
            return new AttackFleet(machinesOne, machinesTwo, machinesThree);
        } else {
            return new DefenseFleet(machinesOne, machinesTwo, machinesThree);
        }
    }

    protected abstract MachineType requestWarUnit();
}
