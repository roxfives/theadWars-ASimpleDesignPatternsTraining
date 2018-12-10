package armies;

import machinery.MachineType;

/**
 * Any fleet must extend from this abstract class
 */
public abstract class Fleet implements Runnable {
    protected abstract MachineType requestWarUnit();
}
