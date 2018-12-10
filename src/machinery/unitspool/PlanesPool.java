package machinery.unitspool;

import machinery.Plane;

public class PlanesPool extends UnitPool<Plane> {
    private PlanesPool() { }

    private static class PlanesPoolHolder {
        private static final PlanesPool PLANES_POOL = new PlanesPool();
    }

    public static PlanesPool getInstance() {
        return PlanesPoolHolder.PLANES_POOL;
    }

    @Override
    protected Plane create() {
        return new Plane();
    }
}
