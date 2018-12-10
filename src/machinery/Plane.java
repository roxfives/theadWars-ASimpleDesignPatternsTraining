package machinery;

/**
 * A war unit representing a plane
 */
public class Plane extends WarUnit {
    public Plane() {
        this.setMachineType(MachineType.AIR);
        this.reset();
        this.setOriginalLife(this.getLife());
    }

    @Override
    public double takeAttack(WarUnit unit) {
        double attackGiven;
        double attackReceived;

        switch (unit.getMachineType()) {
            case SEA:
                attackGiven = Effect.LITTLE;
                attackReceived = Effect.HARD;
                break;

            case AIR:
                attackGiven = Effect.SOFT;
                attackReceived = Effect.SOFT;
                break;

            case LAND:
                attackGiven = Effect.HARD;
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

        System.out.println("Attack damage: " + attackReceived * unit.getFirepower() + "; Defense damage: " + attackGiven * this.getFirepower());

        return attackGiven * this.getFirepower();
    }

    @Override
    public void takeDamage(double damage) {
        this.setLife(this.getLife() - damage);
    }

    @Override
    public void reset() {
        this.setLife(MachineryStats.PLANE_LIFE);
        this.setFirepower(MachineryStats.PLANE_FIRE_POWER);
    }
}
