package machinery;


public abstract class WarUnit {
    private double life;
    private double originalLife;
    private int firepower;
    private MachineType machineType;

    public abstract double takeAttack(WarUnit unit);

    public double getLife() {
        return this.life;
    }

    public int getFirepower() {
        return this.firepower;
    }

    public MachineType getMachineType() {
        return this.machineType;
    }

    protected void setLife(double life) {
        this.life = life;
    }

    protected void setOriginalLife(double life) {
        this.originalLife = life;
    }

    protected void setFirepower(int firepower) {
        this.firepower = firepower;
    }

    public void setMachineType(MachineType machine) {
        this.machineType = machine;
    }

    public abstract void takeDamage(double damage);

    protected abstract void reset();
}
