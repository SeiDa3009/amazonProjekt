import models.*;

import java.time.LocalDate;
import java.util.*;

//import javax.mail.*;
//import javax.mail.internet.MimeMessage;

public class ProgramAmazon {

    private static Scanner reader = new Scanner(System.in);


    public static void main(String[] args) {

        Shop amazonShop = new Shop();
        char choiceMainMenu, choiceSearchMenu;
        Customer currentCustomer = new Customer(1, "Otto", "Bla", LocalDate.of(2000, 5, 26), Gender.male);
        amazonShop.registerCustomer(currentCustomer);
        int articleIdForBasket;
        int articleCountForBasket;
        int articleIDToFind = 0;

        do{
            choiceMainMenu = mainMenu();
            switch(choiceMainMenu){
                case 's':
                    do{
                        choiceSearchMenu = searchMenu();
                        switch(choiceSearchMenu){
                            case 'a':
                                System.out.println(amazonShop.toString());
                                break;
                            case 'c':
                                printItems(amazonShop.findArticlesByCategories( enterListOfCategories() ));
                                break;
                            case 'i':
                                articleIDToFind = enterArticleID();
                                printItem(amazonShop.findArticleById( articleIDToFind ));
                                break;
                            case 'n':
                                printItems(amazonShop.findArticlesByName( enterPartOfName() ));
                                break;
                            case 'p':
                                double lowerLimit = enterPrice("Please enter the lower price limit: ");
                                double upperLimit = enterPrice("Please enter the upper price limit: ");

                                printItems(amazonShop.findArticlesByPriceRange(lowerLimit, upperLimit));
                                break;
                            case 'x':
                                System.out.print("exits search menu");
                                break;
                            default:
                                System.out.println("You entered a wrong character");
                                break;
                        }
                        // let the customer add articles to his basket
                        if(choiceSearchMenu != 'x'){
                            if(choiceSearchMenu == 'i'){
                                articleIdForBasket = articleIDToFind;
                            }
                            else{
                                articleIdForBasket = enterArticleID();
                            }
                            if(amazonShop.checkIfArticleExists(articleIdForBasket) == null){
                                System.out.println("You entered a wrong articleID!");
                            }
                            else{
                                articleCountForBasket = enterArticleCount();
                                System.out.print("Do you want to add the article to your basket? [y,n]: ");
                                if(reader.next().toLowerCase().charAt(0) == 'y'){
                                    addItemToBasket(amazonShop, currentCustomer.getCustomerId(), articleIdForBasket, articleCountForBasket);
                                }
                            }
                        }
                    }while(choiceSearchMenu != 'x');
                    break;
                case 'b':
                    System.out.println(currentCustomer.getBasket().toString());
                    break;
                case 'c':
                    if(currentCustomer.getBasket().getBasketItems().size() > 0){
                        // show basket
                        System.out.println(currentCustomer.getBasket().toString());
                        // confirm order
                        char confirm = confirmOrder();
                        // send a mail to the customer
                        if(confirm == 'y'){
                            currentCustomer.getBasket().clearBasket();

                            /*try {
                                sendConfirmation(currentCustomer);
                                System.out.println("We sent you an EMail with your order details.");
                            }
                            catch(MessagingException ex){
                                System.out.println("Sorry, it was not possible to send you an EMail with your order details.");
                                System.out.println("We will try it later again");
                            } */
                        }
                        else{
                            System.out.println("You didn't confirm your order.");
                            System.out.println("If you want to confirm, please visit 'confirm order' again.");
                        }
                    }
                    else{
                        System.out.println("there are no items in your basket");
                    }
                    break;
                case 'x':
                    System.out.print("Are you sure, that you want to close the App [y, n]? ");
                    char ch = reader.next().toLowerCase().charAt(0);
                    if(ch != 'y'){
                        choiceMainMenu = 'w';
                    }
                    break;
                default:
                    System.out.println("You entered a wrong character");
                    break;
            }
        }while(choiceMainMenu != 'x');



    }

    public static char mainMenu(){
        System.out.println("s ... search for articles ->");
        System.out.println("b ... show basket");
        System.out.println("c ... confirm order");
        System.out.println("x ... exit program");
        System.out.print("Your choice: ");
        return reader.next().toLowerCase().charAt(0);
    }
    public static char searchMenu(){
        System.out.println("a ... all articles");
        System.out.println("c ... search by category");
        System.out.println("i ... search by ArticleID");
        System.out.println("n ... search by name (part of name)");
        System.out.println("p ... search by pricerange");
        System.out.println("x ... exit search menu");
        System.out.print("Your choice: ");
        return reader.next().toLowerCase().charAt(0);
    }

    public static int enterArticleID(){
        System.out.print("\nPlease enter the ArticleID: ");
        return reader.nextInt();
    }
    public static int enterArticleCount(){
        System.out.print("Count: ");
        return reader.nextInt();
    }
    public static String enterPartOfName(){
        System.out.print("\nPlease enter the articlename or part of the articlename: ");
        return reader.next();
    }
    public static List<String> enterListOfCategories(){
        List<String> categories = new ArrayList<>();
        char choice;
        do{
            System.out.print("enter category: ");
            categories.add(reader.next());
            System.out.print("do you want to add another category [y, n]: ");
            choice = reader.next().toLowerCase().charAt(0);
        }while(choice == 'y');
        return categories;
    }
    public static double enterPrice(String description){
        System.out.print(description);
        return reader.nextDouble();
    }
    public static char confirmOrder(){
        System.out.print("\nDo you want to comfirm your order [y, n]?: ");
        return reader.next().toLowerCase().charAt(0);
    }


    public static boolean addItemToBasket(Shop amazShop, int customerId, int articleIdToAdd, int count){
        return amazShop.getCustomerById(customerId).getBasket().addBasketItem(amazShop, articleIdToAdd, count);
    }
    public static boolean removeItemFromBasket(Shop amazShop, int customerId, int articleIdToRemove){
        return amazShop.getCustomerById(customerId).getBasket().removeBasketItem(articleIdToRemove);
    }

    /*
    public static void sendConfirmation(Customer customer) throws MessagingException{

        Properties props = new Properties();
        props.put("mail.smtp.host", "SMTP.office365.com");
        Session session = Session.getInstance(props, null);

        try {
            // nur beispielhaft gelöst
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom("from@bla.com");
            // eMail von customer verwenden
            msg.setRecipients(Message.RecipientType.TO, customer.getEMail());
            msg.setSubject("Order confirmation");
            msg.setSentDate(new Date());
            msg.setText(customer.getBasket().toString() + "\n");
            Transport.send(msg, "from@bla.com", "2093lwht$%&$%");
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
    } */

    public static void printItem(Article item){
        if(item == null){
            System.out.println("no item found");
        }
        else{
            System.out.println(item);
        }
    }
    public static void printItems(List<Article> items){
        if(items == null){
            System.out.println("no items found");
        }
        else{
            for(Article a : items){
                System.out.println(a);
            }
        }
    }
}
