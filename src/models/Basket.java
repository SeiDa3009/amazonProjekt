package models;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    // fields
    private List<BasketItem> _basketItems = new ArrayList<BasketItem>();

    // getter/setter

    public List<BasketItem> getBasketItems() {
        return _basketItems;
    }

    // ctors

    // other methods
    public boolean addBasketItem(Shop shop, int articleIdToAdd, int count){
        if(shop == null){
            return false;
        }
        count = count >= 1 ? count : 1;
        Article articleToAdd = shop.checkIfArticleExists(articleIdToAdd);
        if(articleToAdd != null){
            return this._basketItems.add(new BasketItem(articleToAdd, count));
        }
        return false;
    }
    public boolean removeBasketItem(int articleIdToRemove){
        if(this._basketItems.size() <= 0) {
            return false;
        }
        for(BasketItem bi : this._basketItems){
            if(bi.getArticle().getArticleID() == articleIdToRemove){
                return this._basketItems.remove(bi);
            }
        }
        return false;
    }
    public void clearBasket(){
        this._basketItems.clear();
    }

    @Override
    public String toString(){
        String s = "\n";
        double totalPrice = 0.0;
        if(this._basketItems.size() >= 1){
            for(BasketItem bi : this._basketItems){
                s += bi.toString() + "\n";
                totalPrice += bi.getArticle().getPrice() * bi.getCount();
            }
            s += String.format("\n%66s%10.2f\n\n", "Total price: ", totalPrice);
            return s;
        }
        else{
            return "no items in your basket";
        }
    }

}
