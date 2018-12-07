package board;

import armies.attacker.Emperor;
import armies.defender.WatchTower;

// The entry point of the application
public class Application {
    public static void main(String[] args) {
        WatchTower.getInstance().defenseUp(); // Start defenses
        Emperor.wageWar(); // The attack starts

        // Starts war
        BattleField.getInstance().start();
    }
}
