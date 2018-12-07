package armies.attacker;

import armies.Fleet;
import board.BattleField;

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
        Strategist.getInstance().accept(General.visitor);

        System.out.println("Strategist decided for the army to have " + this.visitor.getNPlanes() + " planes, " + this.visitor.getNKamikazes() + " kamikazes and " + this.visitor.getNShips() + " ships");
        System.out.println();

        this.attackFleet = (AttackFleet) Fleet.createFleet(this, this.visitor.getNPlanes(), this.visitor.getNKamikazes(), this.visitor.getNShips());
    }

    public void sendFleet() {
        System.out.println("General is sending fleet to attack");
        System.out.println();

        BattleField.getInstance().bind(this, this.attackFleet);
    }
}
