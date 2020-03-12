package models;

public class TShirt extends Clothes {

    // fields
    private ClothesType _type;
    private boolean _hasChooseableDesign;

    // getter/setter
    public ClothesType getType() {
        return _type;
    }
    public void setType(ClothesType type) {
        this._type = type;
    }
    public boolean getHasChooseableDesign() {
        return _hasChooseableDesign;
    }
    public void setHasChooseableDesign(boolean hasChooseableDesign) {
        this._hasChooseableDesign = hasChooseableDesign;
    }

    // ctors
    public TShirt(){
        this(0, "", 0.0, "", "", "", ClothesType.uni, false);
    }
    public TShirt(int articleID, String name, double price, String description, String size, String color,
                  ClothesType type, boolean hasChooseableDesign)
    {
        super(articleID, name, price, description, size, color);

        this.setType(type);
        this.setHasChooseableDesign(hasChooseableDesign);
    }

    // other methods
    @Override
    public String toString(){
        return super.toString() + " " + this.getType() + " " + this.getHasChooseableDesign();
    }
}
