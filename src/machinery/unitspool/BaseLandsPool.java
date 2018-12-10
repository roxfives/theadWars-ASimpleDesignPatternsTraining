package machinery.unitspool;

import machinery.BaseLand;

public class BaseLandsPool extends UnitPool<BaseLand>{
    private BaseLandsPool() { }

    private static class BaseLandsPoolHolder {
        private static final BaseLandsPool BASE_LANDS_POOL = new BaseLandsPool();
    }

    public static BaseLandsPool getInstance() {
        return BaseLandsPoolHolder.BASE_LANDS_POOL;
    }

    @Override
    protected BaseLand create() {
        return new BaseLand();
    }
}
