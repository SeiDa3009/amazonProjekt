package models;

public class Smartphone extends Electronic {

    // fields
    private double _storage;
    private boolean _isSmartphone;

    // getter/setter
    public double getStorage() {
        return _storage;
    }
    public void setStorage(double storage) {
        this._storage = storage;
    }
    public boolean getIsSmartphone() {
        return _isSmartphone;
    }
    public void setIsSmartphone(boolean isSmartphone) {
        this._isSmartphone = isSmartphone;
    }

    // ctors
    public Smartphone() {
        this(0, "", 0.0, "", "", "", 0, 0.0, false);
    }
    public Smartphone(int articleID, String name, double price, String description, String powerSupply, String cpu, int countCPUs,
                      double storage, boolean isSmartphone)
    {
        super(articleID, name, price, description, powerSupply, cpu, countCPUs);

        this.setStorage(storage);
        this.setIsSmartphone(isSmartphone);
    }

    // other methods
    public String toString(){
        return super.toString() + " " + this.getStorage() + " " + this.getIsSmartphone();
    }
}
