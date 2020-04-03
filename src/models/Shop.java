package models;

import java.io.*;
import java.util.*;

public class Shop implements Serializable {

    // fields
    private List<Article> _articles = new ArrayList<Article>();
    private List<Customer> _customers = new ArrayList<Customer>();

    // getter/setter
    public List<Article> getArticles(){
        return this._articles;
    }
    public Customer getCustomerById(int id){
        for(Customer c : this._customers){
            if(c.getCustomerId() == id){
                return c;
            }
        }
        return null;
    }

    // ctor
    public Shop(){
        this.createShopItems();
    }

    // other methods
    public boolean addArticle(Article articelToAdd){
        // check parameters
        if(articelToAdd == null){
            return false;
        }
        return this._articles.add(articelToAdd);
    }
    public boolean removeArticle(Article articelToRemove){
        // check parameters
        if(articelToRemove == null){
            return false;
        }
        return this._articles.remove(articelToRemove);
    }
    public boolean registerCustomer(Customer customerToAdd){
        if(customerToAdd == null){
            return false;
        }
        if(this._customers == null){
            this._customers = new ArrayList<Customer>();
        }
        return this._customers.add(customerToAdd);
    }

    // find-methods
    public Article findArticleById(int idToFind){
        if(_articles.size() >= 0){
            for(Article a : this._articles){
                if(a.getArticleID() == idToFind){
                    return a;
                }
            }
        }
        return null;
    }
    public List<Article> findArticlesByName(String partOfNameToFind){
        if(partOfNameToFind == null){
            return  null;
        }
        List<Article> foundArticles = new ArrayList<Article>();
        for(Article a : this._articles){
            if( a.getName().trim().toLowerCase().contains(partOfNameToFind.trim().toLowerCase()) ){
                foundArticles.add(a);
            }
        }
        if(foundArticles.size() > 0){
            return foundArticles;
        }
        return null;
    }
    public List<Article> findArticlesByCategories(List<String> categoriesToFind){
        if( (categoriesToFind == null) || (categoriesToFind.size() < 1) ){
            return null;
        }
        List<Article> foundArticles = new ArrayList<Article>();
        boolean istFirstHit;
        for (Article a : this._articles){
            istFirstHit = true;
            for(String catArticle : a.getCategories()){
                for(String catToFind : categoriesToFind){
                    if(catArticle.toLowerCase().contains(catToFind.trim().toLowerCase())){
                        if(istFirstHit){
                            foundArticles.add(a);
                            istFirstHit = false;
                        }
                    }
                }
            }
        }
        if(foundArticles.size() >= 1){
            return foundArticles;
        }
        return null;
    }
    public List<Article> findArticlesByPriceRange(double lowValue, double highValue){
        double tmp = lowValue;
        if(lowValue > highValue){
            lowValue = highValue;
            highValue = tmp;
        }
        List<Article> foundArticles = new ArrayList<Article>();
        for(Article a : this._articles) {
            if( (a.getPrice() >= lowValue) && (a.getPrice() <= highValue) ){
                foundArticles.add(a);
            }
        }
        if(foundArticles.size() >= 1){
            return foundArticles;
        }
        return null;
    }

    public Article checkIfArticleExists(int articleIdToCheck){
        if(this._articles.size() < 1) {
            return null;
        }

        for(Article a : this._articles){
            if(a.getArticleID() == articleIdToCheck){
                return a;
            }
        }
        return null;
    }



    // toString()
    @Override
    public String toString(){
        if(this._articles.size() < 1){
            return "There are no items in our shop!";
        }
        String s = "";
        for(Article a : this._articles){
            s += a.toString() + "\n";
        }
        return s;
    }

    private void createShopItems(){
        this.addArticle(new Audiobook(10000, "Scrum", 49.90, "Projektmanagementstrategie mit Hilfe von Scrum verbessern", "Stoked", GenreType.science,
                8, 236));
        this._articles.get(0).addCategory("audiobook");
        this._articles.get(0).addCategory("book");
        this._articles.get(0).addCategory("science");
        this.addArticle(new Audiobook(10001, "Harry Potter", 39.90, "Harry Potter und irgendwas", "Irgendwer", GenreType.fantasy,
                14, 381));
        this._articles.get(1).addCategory("audiobook");
        this._articles.get(1).addCategory("book");
        this._articles.get(1).addCategory("fantasy");
        this.addArticle(new Textbook(10002, "C# for professionals", 54.90, "von den Grundlagen bis zur professionellen Anwendungsentwicklung", "Irgendwer", GenreType.science,
                1294, false));
        this._articles.get(2).addCategory("programming");
        this._articles.get(2).addCategory("book");
        this._articles.get(2).addCategory("science");
        this._articles.get(2).addCategory("c#");
        this.addArticle(new Textbook(10003, "JAVA for professionals", 54.90, "von den Grundlagen bis zur professionellen Anwendungsentwicklung", "IrgendwerAnders", GenreType.science,
                1149, true));
        this._articles.get(3).addCategory("programming");
        this._articles.get(3).addCategory("book");
        this._articles.get(3).addCategory("science");
        this._articles.get(3).addCategory("java");
        this.addArticle(new Smartphone(10004, "Samsung", 893.90, "Galaxy S10", "ASFDX12", "Intel Mobile", 8, 128, true));
        this._articles.get(4).addCategory("technic");
        this._articles.get(4).addCategory("smartphone");
        this._articles.get(4).addCategory("handy");
        this._articles.get(4).addCategory("samsung");
        this.addArticle(new Smartphone(10005, "iPhone", 902.90, "i10", "TZR6", "Intel Mobile", 6, 256, true));
        this._articles.get(5).addCategory("technic");
        this._articles.get(5).addCategory("smartphone");
        this._articles.get(5).addCategory("handy");
        this._articles.get(5).addCategory("iPhone");
        this.addArticle(new TV(10006, "Philips", 1208.90, "großer hochauflösender TV", "GHJHGJ564", "AMD", 4, 48, true));
        this._articles.get(6).addCategory("technic");
        this._articles.get(6).addCategory("tv");
        this._articles.get(6).addCategory("wlan");
        this.addArticle(new TV(10007, "Samsung", 479.90, "sehr guter Fernseher", "IOUIIOU78", "Intel", 2, 32, false));
        this._articles.get(7).addCategory("technic");
        this._articles.get(7).addCategory("tv");
        this.addArticle(new Shoe(10008, "Adidas", 89.90, "Laufschuh", "42", "black/blue", ClothesType.male, true));
        this._articles.get(8).addCategory("clothes");
        this._articles.get(8).addCategory("shoe");
        this._articles.get(8).addCategory("adidas");
        this.addArticle(new Shoe(10009, "Nike", 134.90, "Trekkingschuh", "37", "darkgry/pink", ClothesType.female, true));
        this._articles.get(9).addCategory("clothes");
        this._articles.get(9).addCategory("shoe");
        this._articles.get(9).addCategory("nike");
        this._articles.get(9).addCategory("outdoor");
        this.addArticle(new TShirt(10010, "o'Neil-Shirt", 29.90, "funktionelles Baumwollshirt", "LARGE", "white/green", ClothesType.uni, false));
        this._articles.get(10).addCategory("clothes");
        this._articles.get(10).addCategory("shirt");
        this._articles.get(10).addCategory("cotton");
        this.addArticle(new TShirt(10011, "Billabong", 34.90, "Baumwollshirt mit Aufdruck", "MEDIUM", "white", ClothesType.uni, true));
        this._articles.get(11).addCategory("clothes");
        this._articles.get(11).addCategory("shirt");
        this._articles.get(11).addCategory("cotton");
        this._articles.get(11).addCategory("designable");
    }
    //Verwenden wir für die 1.Aufgabe um die Shopartikel zu speichern (List-Serialize)
    public void serializeArticle(String filename, List<Article> articles){
        try(FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(articles);
        }
        catch (IOException e){
            System.out.println("Serialisierung hat nicht funktioniert!");
        }
    }

    //Verwenden wir für 1.Aufgabe um die Shopartikel zu speichern (List-Serialize)
    public List<Article> deserializeArticle(String filename){
        try(FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (List<Article>)ois.readObject();
        }

        catch (IOException e){
            System.out.println("Deserialisierung hat nicht funktioniert!");
        }
        catch(ClassNotFoundException e){
            System.out.println("Klasse Person oder Address existiert nicht!");
        }
        return null;
    }
}
