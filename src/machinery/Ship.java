package machinery;

/**
 * A war unit representing a ship
 */
public class Ship extends WarUnit {
    public Ship() {
        this.setMachineType(MachineType.SEA);
        this.reset();
        this.setOriginalLife(this.getLife());
    }

    @Override
    public double takeAttack(WarUnit unit) {
        double attackGiven;
        double attackReceived;

        switch (unit.getMachineType()) {
            case SEA:
                attackGiven = Effect.HARD;
                attackReceived = Effect.HARD;
                break;

            case AIR:
                attackGiven = Effect.HARD;
                attackReceived = Effect.MEDIUM;
                break;

            case LAND:
                attackGiven = Effect.MEDIUM;
                attackReceived = Effect.MEDIUM;
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
        this.setLife(MachineryStats.SHIP_LIFE);
        this.setFirepower(MachineryStats.SHIP_FIRE_POWER);
    }
}