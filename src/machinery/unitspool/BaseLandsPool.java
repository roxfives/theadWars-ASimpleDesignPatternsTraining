package machinery.unitspool;

import machinery.BaseLand;

public class BaseLandsPool extends UnitPool<BaseLand>{
    @Override
    protected BaseLand create() {
        return new BaseLand();
    }
}
