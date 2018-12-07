package armies.attacker;

import java.util.Random;

/**
 * The strategist is responsible for generating the information used for creating the attacking fleet
 */
public class Strategist implements Attacker {
    private Strategist() { }

    private static class StrategistHolder {
        private static final Strategist STRATEGIST = new Strategist();
    }

    public static Strategist getInstance() {
        return StrategistHolder.STRATEGIST;
    }

    // The method that accepts the visitor
    public static void accept(StrategistVisitor visitor) {
        Random random = new Random();

        visitor.setNPlanes(random.nextInt(10) + 5);
        visitor.setNKamikazes(random.nextInt(10) + 5);
        visitor.setNShips(random.nextInt(10) + 5);
    }
}
