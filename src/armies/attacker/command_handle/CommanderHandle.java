package armies.attacker.command_handle;

import armies.attacker.Emperor;
import armies.attacker.Attacker;

public class CommanderHandle extends Handle {

    public CommanderHandle(Attacker jap) {
        super(jap);
    }

    @Override
    public void execute(Handle boss) {
        if(boss.getResponsible() instanceof Emperor) {
            System.out.println("Commander received the emperor's command to wage war");
            this.next.execute(this);
        }
    }
}
