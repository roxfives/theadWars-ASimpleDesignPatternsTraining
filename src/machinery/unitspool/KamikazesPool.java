package machinery.unitspool;

import machinery.Kamikaze;

public class KamikazesPool extends UnitPool<Kamikaze> {
    private KamikazesPool() { }

    private static class KamikazesPoolHolder {
        private static final KamikazesPool KAMIKAZES_POOL = new KamikazesPool();
    }

    public static KamikazesPool getInstance() {
        return KamikazesPoolHolder.KAMIKAZES_POOL;
    }

    @Override
    protected Kamikaze create() {
        return new Kamikaze();
    }
}
