package armies.attacker;

import armies.attacker.command_handle.CommanderHandle;
import armies.attacker.command_handle.EmperorHandle;
import armies.attacker.command_handle.GeneralHandle;

/**
 * The emperor is the head of the attackers
 */
public class Emperor implements Attacker {
    private Emperor() { }

    private static class EmperorHolder {
        private static final Emperor EMPEROR = new Emperor();
    }


    public static Emperor getInstance() {
        return EmperorHolder.EMPEROR;
    }

    /**
     * This method is responsible for initiating the chain of responsibility
     */
    public static void wageWar() {
        System.out.println("Emperor is waging war");

        EmperorHandle chain = new EmperorHandle(Emperor.getInstance());

        // Creates the chain
        chain.add(new CommanderHandle(Commander.getInstance()));
        chain.add(new GeneralHandle(General.getInstance()));

        chain.execute(null); // Initiates the chain
    }
}
