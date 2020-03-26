package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Article implements Serializable{

    // fields
    private int _articleID;
    private String _name;
    private double _price;
    private String _description;
    private List<String> _photoList = new ArrayList<String>();
    private List<String> _categories = new ArrayList<String>();

    // getter/setter
    public int getArticleID() {
        return _articleID;
    }
    protected void setArticleID(int articleID){
        if(articleID >= 0){
            this._articleID = articleID;
        }
    }
    public String getName() {
        return _name;
    }
    public void setName(String name) {
        this._name = name;
    }
    public double getPrice() {
        return _price;
    }
    public void setPrice(double price) {
        if(price >= 0.0){
            this._price = price;
        }
    }
    public String getDescription() {
        return _description;
    }
    public void setDescription(String description) {
        this._description = description;
    }
    public List<String> getPhotoList() {
        return _photoList;
    }
    public List<String> getCategories() {
        return _categories;
    }

    // ctors
    public Article(){
        this(0, "", 0.0, "");
    }
    public Article(int articleID, String name, double price, String description){
        this.setArticleID(articleID);
        this.setName(name);
        this.setPrice(price);
        this.setDescription(description);
    }

    // other methods
    @Override
    public String toString(){
        String s = this.getArticleID() + " " + this.getName() + " " + this.getPrice() + "€";
        if(this._categories.size() > 0){
            s += "[";
            for(String c : this._categories){
                s += c + ", ";
            }
            s = s.substring(0, s.length()-2);
            s += "]";
        }
        return s;
    }

    public boolean addArticlePhoto(String filename){
        // check parameter
        if(filename.trim() != ""){
            return this._photoList.add(filename);
        }
        return false;
    }
    public boolean removeArticlePhoto(String filename){
        // returns true, if the filename was in the list
        //         false, if the filename was not in the list
        return this._photoList.remove(filename.trim());
    }
    public boolean addCategory(String category){
        // check parameter
        if(category.trim() != ""){
            return this._categories.add(category);
        }
        return false;
    }
    public boolean removeCategory(String category){
        return this._photoList.remove(category.trim());
    }
}
