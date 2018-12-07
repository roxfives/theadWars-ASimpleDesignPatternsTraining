package armies.attacker;

/**
 * The visitor the General uses to get information about the attacking fleet with strategist
 */
public class StrategistVisitor {
    private int nPlanes;

    private int nKamikazes;

    private int nShips;


    public void visit(Strategist strategist) {}

    public int getNPlanes() {
        return this.nPlanes;
    }

    public int getNKamikazes() {
        return this.nKamikazes;
    }

    public int getNShips() {
        return this.nShips;
    }

    public void setNPlanes(int nPlanes) {
        this.nPlanes = nPlanes;
    }

    public void setNKamikazes(int nKamikazes) {
        this.nKamikazes = nKamikazes;
    }

    public void setNShips(int nShips) {
        this.nShips = nShips;
    }
}
