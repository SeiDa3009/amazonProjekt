package models;

public abstract class Book extends Article {

    // fields
    private String _publisher;
    private GenreType _genre;

    // getter/setter
    public String getPublisher(){
        return this._publisher;
    }
    public void setPublisher(String publisher){
        this._publisher = publisher;
    }
    public GenreType getGenre() {
        return _genre;
    }
    public void setGenre(GenreType genre) {
        this._genre = genre;
    }

    // ctors
    public Book(){
        this(0, "", 0.0, "", "", GenreType.notSpecified);
    }
    public Book(int articleID, String name, double price, String description, String publisher, GenreType genre){
        super(articleID, name, price, description);

        this.setPublisher(publisher);
        this.setGenre(genre);
    }

    @Override
    public String toString(){
        return super.toString() + " " + this.getPublisher() + " " + this.getGenre();
    }
}
