package armies.attacker;

import armies.Fleet;
import board.BattleField;

/**
 * The General responds directly to the Commander
 */
public class General implements Attacker {
    private static StrategistVisitor visitor = new StrategistVisitor();

    private AttackFleet attackFleet;

    private General() { }

    private static class GeneralHolder {
        private static final General GENERAL = new General();
    }

    public static General getInstance() {
        return GeneralHolder.GENERAL;
    }

    public void planAttack() {
        System.out.println("General is asking strategist to plan the attack");
        Strategist.getInstance().accept(General.visitor); // Uses a Visitor to get information about strategy with Strategist

        System.out.println("Strategist decided for the army to have " + this.visitor.getNPlanes() + " planes, " + this.visitor.getNKamikazes() + " kamikazes and " + this.visitor.getNShips() + " ships");
        System.out.println();

        // Uses a factory method to create the fleet
        this.attackFleet = (AttackFleet) Fleet.createFleet(this, this.visitor.getNPlanes(), this.visitor.getNKamikazes(), this.visitor.getNShips());
    }

    public void sendFleet() {
        System.out.println("General is sending fleet to attack");
        System.out.println();

        BattleField.getInstance().bind(this, this.attackFleet); // Binds the attack fleet with attacker
    }
}
