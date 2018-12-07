package board;

import armies.attacker.Emperor;
import armies.defender.WatchTower;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        WatchTower.getInstance().defenseUp();
        Emperor.wageWar();

        BattleField.getInstance().start();
    }
}
