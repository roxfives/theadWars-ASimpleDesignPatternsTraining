package armies.attacker;

import java.util.Random;

public class Strategist implements Attacker {
    private static int nPlanes;

    private static int nKamikazes;

    private static int nShips;

    private Strategist() { }

    private static class StrategistHolder {
        private static final Strategist STRATEGIST = new Strategist();
    }

    public static Strategist getInstance() {
        return StrategistHolder.STRATEGIST;
    }

    public static void accept(StrategistVisitor visitor) {
        Random random = new Random();

        visitor.setNPlanes(random.nextInt(10) + 5);
        visitor.setNKamikazes(random.nextInt(10) + 5);
        visitor.setNShips(random.nextInt(10) + 5);
    }
}
