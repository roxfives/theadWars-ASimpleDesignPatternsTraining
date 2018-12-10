package machinery.unitspool;

import machinery.Ship;

public class ShipsPool extends UnitPool<Ship>{
   @Override
    protected Ship create() {
        return new Ship();
    }
}
