package machinery.unitspool;

import java.util.ArrayList;
import java.util.Stack;

public abstract class UnitPool<WarUnit> {
    // War Units repositories
    private Stack<WarUnit> availableUnits = new Stack<>();
    private ArrayList<WarUnit> lockedUnits = new ArrayList<>();

    private static final int MAX_WAR_MACHINE = 2;

    public synchronized WarUnit getWarMachine() {
        if(this.availableUnits.size() > 0) {
            System.out.println("Giving existing unit: " + this.availableUnits.size());
            return this.availableUnits.pop();
        } else {
            while(this.lockedUnits.size() == UnitPool.MAX_WAR_MACHINE) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            WarUnit warUnit = create();

            System.out.println("Creating new unit: " + this.availableUnits.size());
            lockedUnits.add(warUnit);
            return warUnit;
        }
    }

    public synchronized void returnWarMachine(machinery.WarUnit unit) {
       unit.reset();

        System.out.println("Returning unit: " + this.lockedUnits.size());
        this.lockedUnits.remove(unit);
        this.availableUnits.push((WarUnit) unit);

        this.notifyAll();
    }

    protected abstract WarUnit create();
}
