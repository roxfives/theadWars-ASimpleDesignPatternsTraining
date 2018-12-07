package armies.attacker.command_handle;

import armies.attacker.Attacker;

public abstract class Handle {
    protected Handle next;

    final private Attacker RESPONSIBLE;

    public Handle(Attacker jap) {
        this.RESPONSIBLE = jap;
    }

    public void add(Handle next) {
        if(this.next == null) {
            this.next = next;
        } else {
            this.next.add(next);
        }
    }

    public Attacker getResponsible() {
        return this.RESPONSIBLE;
    }

    abstract public void execute(Handle boss);
}
