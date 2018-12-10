package machinery.unitspool;

import machinery.Ship;

public class ShipsPool extends UnitPool<Ship>{
    private ShipsPool() { }

    private static class ShipsPoolHolder {
        private static final ShipsPool SHIPS_POOL = new ShipsPool();
    }

    public static ShipsPool getInstance() {
        return ShipsPoolHolder.SHIPS_POOL;
    }

    @Override
    protected Ship create() {
        return new Ship();
    }
}
