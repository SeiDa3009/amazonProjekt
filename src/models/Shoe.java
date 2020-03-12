package models;

public class Shoe extends Clothes {

    // fields
    private ClothesType _type;
    private boolean _hasShoelaces;

    // getter/setter
    public ClothesType getClothesType() {
        return _type;
    }
    public void setClothesType(ClothesType type) {
        this._type = type;
    }
    public boolean getHasShoelaces() {
        return _hasShoelaces;
    }
    public void setHasShoelaces(boolean hasShoelaces) {
        this._hasShoelaces = hasShoelaces;
    }

    // ctors
    public Shoe(){
        this(0, "", 0.0, "", "", "", ClothesType.uni, true);
    }
    public Shoe(int articleID, String name, double price, String description, String size, String color,
                ClothesType type, boolean hasShoelaces)
    {
        super(articleID, name, price, description, size, color);

        this.setClothesType(type);
        this.setHasShoelaces(hasShoelaces);
    }

    // other methods
    @Override
    public String toString(){
        return super.toString() + " " + this.getClothesType() + " " + this.getHasShoelaces();
    }
}
