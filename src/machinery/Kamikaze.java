package machinery;

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

        this.setLife(0);
        return attackGiven * this.getFirepower();
    }

    @Override
    public void takeDamage(double damage) {
        this.setLife(0);
    }

    @Override
    protected void reset() {
        this.setLife(MachineryStats.KAMIKAZE_LIFE);
        this.setFirepower(MachineryStats.KAMIKAZE_FIRE_POWER);
    }
}
