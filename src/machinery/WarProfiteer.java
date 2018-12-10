package machinery;


import machinery.unitspool.BaseLandsPool;
import machinery.unitspool.KamikazesPool;
import machinery.unitspool.PlanesPool;
import machinery.unitspool.ShipsPool;

/**
 * A singleton used as an object pool for war units (for both sides)
 */
public class WarProfiteer {
    private final PlanesPool planesPool;
    private final BaseLandsPool baseLandsPool;
    private final KamikazesPool kamikazesPool;
    private final ShipsPool shipsPool;


    private WarProfiteer() {
        this.planesPool = new PlanesPool();
        this.baseLandsPool = new BaseLandsPool();
        this.kamikazesPool = new KamikazesPool();
        this.shipsPool = new ShipsPool();
    }

    private static class WarProfiteerHolder {
        private static final WarProfiteer WAR_PROFITEER = new WarProfiteer();
    }

    public static WarProfiteer getInstance() {
        return WarProfiteerHolder.WAR_PROFITEER;
    }

    // The method a thread must use to request a war unit
    public synchronized WarUnit getWarMachine(MachineType type) {
        switch (type) {
            case AIR:
                return this.planesPool.getWarMachine();

            case LAND:
                return this.baseLandsPool.getWarMachine();

            case ONE_TIME_ONLY:
                return this.kamikazesPool.getWarMachine();

            case SEA:
                return this.shipsPool.getWarMachine();

            default:
                return null;
        }
    }

    // The method a thread must use to give a war unit back
    public synchronized void returnWarMachine(WarUnit unit) {
        MachineType type = unit.getMachineType();

        unit.reset();
        switch (type) {
            case AIR:
                this.planesPool.returnWarMachine(unit);
                break;

            case LAND:
                this.baseLandsPool.returnWarMachine(unit);
                break;

            case ONE_TIME_ONLY:
                this.kamikazesPool.returnWarMachine(unit);
                break;

            case SEA:
                this.shipsPool.returnWarMachine(unit);
                break;
        }
    }
}
