package armies.attacker;

public class Commander implements Attacker {
    private Commander() { }

    private static class CommanderHolder {
        private static final Commander COMMANDER = new Commander();
    }

    public static Commander getInstance() {
        return CommanderHolder.COMMANDER;
    }
}
