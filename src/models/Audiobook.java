package models;

public class Audiobook extends Book {

    // fields
    private int _countTitles;
    private int _durationInMinutes;

    // getter/setter
    public int getCountTitles() {
        return _countTitles;
    }
    public void setCountTitles(int countTitles) {
        this._countTitles = countTitles;
    }
    public int getDurationInMinutes() {
        return _durationInMinutes;
    }
    public void setDurationInMinutes(int durationInMinutes) {
        this._durationInMinutes = durationInMinutes;
    }

    // ctors
    public Audiobook(){
        this(0, "", 0.0, "", "", GenreType.notSpecified, 0, 0);
    }
    public Audiobook(int articleID, String name, double price, String description, String publisher, GenreType genre,
                     int countTitles, int durationInMinutes){
        super(articleID, name, price, description, publisher, genre);

        this.setCountTitles(countTitles);
        this.setDurationInMinutes(durationInMinutes);
    }

    // other methods
    @Override
    public String toString(){
        return super.toString() + " " + this.getCountTitles() + " " + this.getDurationInMinutes();
    }



}
