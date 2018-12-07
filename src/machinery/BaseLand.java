package machinery;

/**
 * A war unit representing a base land
 */
public class BaseLand extends WarUnit {
    public BaseLand() {
        this.setMachineType(MachineType.LAND);
        this.reset();
        this.setOriginalLife(this.getLife());
    }

    @Override
    public double takeAttack(WarUnit unit) {
        double attackGiven;
        double attackReceived;

        switch (unit.getMachineType()) {
            case SEA:
                attackGiven = Effect.MEDIUM;
                attackReceived = Effect.MEDIUM;
                break;

            case AIR:
                attackGiven = Effect.MEDIUM;
                attackReceived = Effect.HARD;
                break;

            case ONE_TIME_ONLY:
                attackGiven = Effect.SUPER;
                attackReceived = Effect.SUPER;
                break;

            default:
                attackGiven = Effect.MEDIUM;
                attackReceived = Effect.MEDIUM;

        }

        this.setLife(this.getLife() - (attackReceived * unit.getFirepower()));

        return attackGiven * this.getFirepower();
    }

    @Override
    public void takeDamage(double damage) {
        this.setLife(this.getLife() - damage);
    }

    @Override
    protected void reset() {
        this.setLife(MachineryStats.BASE_LAND_LIFE);
        this.setFirepower(MachineryStats.BASE_LAND_FIRE_POWER);
    }
}
