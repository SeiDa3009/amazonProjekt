package models;

public class BasketItem {

    // fields
    private Article _article;
    private int _count;

    // getter/setter
    public Article getArticle() {
        return _article;
    }
    public void setArticle(Article article) {
        this._article = article;
    }
    public int getCount() {
        return _count;
    }
    public void setCount(int count) {
        this._count = count > 0 ? count : 0;
    }

    // ctors
    public BasketItem(){
        this(null, 0);
    }
    public BasketItem(Article article){
        this(article, 1);
    }
    public BasketItem(Article article, int count){
        if(article == null){
            return;
        }
        this.setArticle(article);
        this.setCount(count);
    }
    // other methods
    @Override
    public String toString(){
        return String.format("%10d%40s%10.2f%6d%10.2f", this._article.getArticleID(), this._article.getName(),
                this._article.getPrice(), this.getCount(), this._article.getPrice() * this.getCount());
    }
}
