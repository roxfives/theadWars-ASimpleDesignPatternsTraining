package machinery.unitspool;

import machinery.Kamikaze;

public class KamikazesPool extends UnitPool<Kamikaze> {
    @Override
    protected Kamikaze create() {
        return new Kamikaze();
    }
}
