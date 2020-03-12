package models;

public class Textbook extends Book {

    // fields
    private int _countPages;
    private boolean _isColored;

    // getter/setter
    public int getCountPages() {
        return _countPages;
    }
    public void setCountPages(int countPages) {
        if(countPages >= 0){
            this._countPages = countPages;
        }
    }
    public boolean getIsColored() {
        return _isColored;
    }
    public void setIsColored(boolean isColored) {
        this._isColored = isColored;
    }

    // ctors
    public Textbook(){
        this(0, "", 0.0, "", "", GenreType.notSpecified, 0, false);
    }
    public Textbook(int articleID, String name, double price, String description, String publisher, GenreType genre,
                    int countPages, boolean isColored)
    {
        super(articleID, name, price, description, publisher, genre);

        this.setCountPages(countPages);
        this.setIsColored(isColored);
    }
    // other methods
    @Override
    public String toString(){
        return super.toString() + " " + this.getCountPages() + " " + this.getIsColored();
    }
}
