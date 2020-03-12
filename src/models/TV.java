package models;

public class TV extends Electronic {

    // fields
    private double _screenSize;
    private boolean _hasWLAN;

    // getter/setter
    public double getScreenSize() {
        return _screenSize;
    }
    public void setScreenSize(double screenSize) {
        this._screenSize = screenSize;
    }
    public boolean getHasWLAN() {
        return _hasWLAN;
    }
    public void setHasWLAN(boolean hasWLAN) {
        this._hasWLAN = hasWLAN;
    }

    // ctors
    public TV() {
        this(0, "", 0.0, "", "", "", 0, 0.0, false);
    }
    public TV(int articleID, String name, double price, String description, String powerSupply, String cpu, int countCPUs,
              double screenSize, boolean hasWLAN)
    {
        super(articleID, name, price, description, powerSupply, cpu, countCPUs);

        this.setScreenSize(screenSize);
        this.setHasWLAN(hasWLAN);
    }

    // other methods
    @Override
    public String toString(){
        return super.toString() + " " + this.getScreenSize() + " " + this.getHasWLAN();
    }
}
