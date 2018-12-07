package armies.attacker.command_handle;

import armies.attacker.Attacker;

public class EmperorHandle extends Handle {

    public EmperorHandle(Attacker jap) {
        super(jap);
    }

    @Override
    public void execute(Handle boss) {
        this.next.execute(this);
    }
}
