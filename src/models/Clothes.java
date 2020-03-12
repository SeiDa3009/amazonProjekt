package models;

public abstract class Clothes extends Article {

    // fields
    private String _size;
    private String _color;

    // getter/setter
    public String getSize() {
        return _size;
    }
    public void setSize(String size) {
        this._size = size;
    }
    public String getColor() {
        return _color;
    }
    public void setColor(String color) {
        this._color = color;
    }

    // ctors
    public Clothes(){
        this(0, "", 0.0, "", "", "");
    }
    public Clothes(int articleID, String name, double price, String description, String size, String color){
        super(articleID, name, price, description);

        this.setSize(size);
        this.setColor(color);
    }

    // other methods
    @Override
    public String toString(){
        return super.toString() + " " + this.getSize() + " " + this.getColor();
    }
}
