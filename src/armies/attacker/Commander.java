package armies.attacker;

/**
 * The commander responds directly to the emperor
 */
public class Commander implements Attacker {
    private Commander() { }

    private static class CommanderHolder {
        private static final Commander COMMANDER = new Commander();
    }

    public static Commander getInstance() {
        return CommanderHolder.COMMANDER;
    }
}
