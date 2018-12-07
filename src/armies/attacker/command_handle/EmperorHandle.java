package armies.attacker.command_handle;

import armies.attacker.Attacker;

/**
 * The first handler in the chain of responsibility
 */
public class EmperorHandle extends Handle {

    public EmperorHandle(Attacker attacker) {
        super(attacker);
    }

    @Override
    public void execute(Handle boss) {
        this.next.execute(this);
    }
}
