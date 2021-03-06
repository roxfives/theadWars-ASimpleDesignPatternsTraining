package machinery;

/**
 * A war unit representing a kamikaze
 */
public class Kamikaze extends WarUnit {
    public Kamikaze() {
        this.setMachineType(MachineType.ONE_TIME_ONLY);
        this.reset();
        this.setOriginalLife(this.getLife());
    }

    @Override
    public double takeAttack(WarUnit unit) {
        double attackGiven;

        attackGiven = Effect.SUPER;

        System.out.println("Attack damage: " + this.getLife() + "; Defense damage: " + attackGiven * this.getFirepower());

        this.setLife(0);

        return attackGiven * this.getFirepower();
    }

    @Override
    public void takeDamage(double damage) {
        this.setLife(0);
    }

    @Override
    public void reset() {
        this.setLife(MachineryStats.KAMIKAZE_LIFE);
        this.setFirepower(MachineryStats.KAMIKAZE_FIRE_POWER);
    }
}
