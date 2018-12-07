package board;

import armies.Fleet;
import armies.attacker.AttackFleet;
import armies.attacker.Attacker;
import armies.defender.DefenseFleet;
import machinery.WarUnit;

/**
 * A singleton used as a mediator between fighting war units
 */
public class BattleField {
    private AttackFleet attackFleet;
    private DefenseFleet defenseFleet;

    private Thread attacker;
    private Thread defender;

    private WarUnit attackerUnit = null;
    private WarUnit defenderUnit = null;

    private boolean fighting = true;

    private BattleField() { }

    private static class BattleFieldHolder {
        private static final BattleField BATTLEFIELD = new BattleField();
    }

    public static BattleField getInstance() {
        return BattleFieldHolder.BATTLEFIELD;
    }

    public boolean isFighting() {
        return this.fighting;
    }

    public synchronized void setFighting(boolean fighting) {
        this.fighting = fighting;
        if(!fighting) {
            this.notifyAll();
        }
    }

    public void bind(Object player, Fleet fleet) {
        if(player instanceof Attacker) {
            this.attackFleet = (AttackFleet) fleet;
        } else {
            this.defenseFleet = (DefenseFleet) fleet;
        }
    }

    public void start() {
        this.attacker = new Thread(this.attackFleet);
        this.defender = new Thread(this.defenseFleet);

        this.attacker.start();
        this.defender.start();
    }

    // The attacking unit in the round calls this method
    public synchronized void attack(WarUnit unit) {
        if(!this.isFighting()) {
            return;
        }

        this.attackerUnit = unit;
        if(!tryRound()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // The defending unit in the round calls this method
    public synchronized void defend(WarUnit unit) {
        if(!this.isFighting()) {
            return;
        }

        this.defenderUnit = unit;
        if(!tryRound()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // The threads must go through this method so the round can be run
    private boolean tryRound() {
        if(this.attackerUnit != null && this.defenderUnit != null) {
            this.attackerUnit.takeDamage(this.defenderUnit.takeAttack(this.attackerUnit));
            this.notifyAll();

            this.attackerUnit = null;
            this.defenderUnit = null;

            System.out.println();
            return true;
        }

        return false;
    }
}
