package machinery;


import machinery.unitspool.BaseLandsPool;
import machinery.unitspool.KamikazesPool;
import machinery.unitspool.PlanesPool;
import machinery.unitspool.ShipsPool;

/**
 * A singleton used as an object pool for war units (for both sides)
 */
public class WarProfiteer {
    private WarProfiteer() { }

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
                return PlanesPool.getInstance().getWarMachine();

            case LAND:
                return BaseLandsPool.getInstance().getWarMachine();

            case ONE_TIME_ONLY:
                return KamikazesPool.getInstance().getWarMachine();

            case SEA:
                return ShipsPool.getInstance().getWarMachine();

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
                PlanesPool.getInstance().returnWarMachine(unit);
                break;

            case LAND:
                BaseLandsPool.getInstance().returnWarMachine(unit);
                break;

            case ONE_TIME_ONLY:
                KamikazesPool.getInstance().returnWarMachine(unit);
                break;

            case SEA:
                ShipsPool.getInstance().returnWarMachine(unit);
                break;
        }
    }
}
