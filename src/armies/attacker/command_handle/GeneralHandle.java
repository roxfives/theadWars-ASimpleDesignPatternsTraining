package armies.attacker.command_handle;

import armies.attacker.Attacker;
import armies.attacker.Commander;
import armies.attacker.General;

public class GeneralHandle extends Handle {
    public GeneralHandle(Attacker jap) {
        super(jap);
    }

    @Override
    public void execute(Handle boss) {
        if(boss.getResponsible() instanceof Commander) {
            System.out.println("General received the commander's command to wage war");

            General general = (General) this.getResponsible();
            general.planAttack(); // Plans the attack with strategist
            general.sendFleet(); // Initiates attack
        }
    }
}
