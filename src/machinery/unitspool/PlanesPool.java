package machinery.unitspool;

import machinery.Plane;

public class PlanesPool extends UnitPool<Plane> {
    @Override
    protected Plane create() {
        return new Plane();
    }
}
