//Hauptprogramm mit Encoding Fehler:



import models.*;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.*;

//import javax.mail.*;
//import javax.mail.internet.MimeMessage;

public class ProgramAmazon {

    private static Scanner reader = new Scanner(System.in);


    public static void main(String[] args) {

        Shop amazonShop = new Shop();
        char choiceMainMenu, choiceSearchMenu, choiceLoginMenu;
        Customer currentCustomer = new Customer(1, "Otto", "Bla", LocalDate.of(2000, 5, 26), Gender.male);
        amazonShop.registerCustomer(currentCustomer);
        int articleIdForBasket;
        int articleCountForBasket;
        int articleIDToFind = 0;
        String fileArticle = "Article.bin";
        List<Article> article = amazonShop.getArticles();
        boolean correctLogin = false;
        String usernameConfig = "username.txt";
        String passwordConfig = "password.txt";
        int counter = 0;
        boolean fileExists = false;
        List<Article> loadedArticle = new ArrayList<>();
        String fileBasket = "basket";


        if(Files.exists(Paths.get(fileArticle))){
            loadedArticle = deserializeArticle(fileArticle);
            fileExists = true;
        }
        else{
            createFile(fileArticle);
            serializeArticle(fileArticle, article);
        }

        do {
            choiceLoginMenu = loginMenu();
            switch (choiceLoginMenu) {
                case 'l':
                    do {
                        System.out.println("Login:");
                        String username = enterUsername();
                        String password = enterPassword();
                        List<Integer> count = controlUsername(usernameConfig,username);
                        Boolean correctData = controlPassword(count, passwordConfig,password);
                        correctLogin = login(correctData);
                        if (!correctLogin) {
                            System.out.println("Wrong ... try again" + "\n");
                            counter += 1;
                            if (counter >= 3) {
                                System.out.println("Login failed!");
                                System.out.println("Exit program");
                                System.exit(0);
                            }
                        }
                    }while (!correctLogin);
                    break;
                case 'r':
                    System.out.println("Register:");
                    registerUsername(usernameConfig);
                    registerPassword(passwordConfig);
                    correctLogin = false;
                    break;
            }
        }while (!correctLogin);
        System.out.println(" ");
        System.out.println("Calculating ID...");
        int id = IDCalculator(usernameConfig, enterUsername(), passwordConfig, enterPassword());
        fileBasket += id;
        //Beschriftung des Files mit ID
        fileBasket += ".txt";

        do {
            System.out.println(" ");
            choiceMainMenu = mainMenu();
            switch(choiceMainMenu){
                case 's':
                    if(Files.exists(Paths.get(fileBasket))){
                        currentCustomer.setBasket(deserializeBasket(fileBasket));
                    }
                    do{
                        choiceSearchMenu = searchMenu();
                        switch(choiceSearchMenu){
                            case 'a':
                                if(fileExists){
                                    for (int k = 0; k < loadedArticle.size(); k++){
                                        System.out.println(loadedArticle.get(k));
                                    }
                                }
                                else{
                                    System.out.println(amazonShop.toString());
                                }

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
                case 'u':
                    if (id >= 9999){
                        System.out.println("Error!");
                    }
                    System.out.print("Username: ");
                    readLine(usernameConfig, id);
                    System.out.print("Password: ");
                    readLine(passwordConfig, id);
                    System.out.println(" ");
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
                    if(currentCustomer.getBasket().getBasketItems().size() > 0){

                    System.out.println(fileBasket); //Files mit Basket starten mit id = 0
                    if(!Files.exists(Paths.get(fileBasket))){
                        createFile(fileBasket);
                    }
                    serializeBasket(fileBasket, currentCustomer.getBasket());
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
        System.out.println("u ... user-manager");
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
    //Erstellte Methoden für Auftrag:
    //File-Erstellung
    private static void createFile(String filename) {
        try {
            Files.createFile(Paths.get(filename));
        } catch (FileAlreadyExistsException e) {
            System.out.println("Fehler: Datei existiert bereits!");
        } catch (IOException e) {
            System.out.println("Fehler: IO-Fehler");
        }
    }
    //Verwenden wir für die 1.Aufgabe um die Shopartikel zu speichern (List-Serialize)
    public static void serializeArticle(String filename, List<Article> articles){
        try(FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(articles);
        }
        catch (IOException e){
            System.out.println("Serialisierung hat nicht funktioniert!");
        }
    }
    //Verwenden wir für 1.Aufgabe um die Shopartikel zu speichern (List-Serialize)
    public static List<Article> deserializeArticle(String filename){
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
    //Verwenden wir für die 2.Aufgabe um zu überprüfen ob login richtig war
    public static boolean login(boolean correctData){
        if(correctData){
            return true;
        }
        else{
            return false;
        }
    }
    //Verwenden wir für die 2.Aufgabe
    //Login Menü
    public static char loginMenu(){
        System.out.println("l ... login");
        System.out.println("r ... register");
        System.out.print("Select one: ");
        return reader.next().toLowerCase().charAt(0);
    }
    //Verwenden wir für die 2.Aufgabe um Username und Password in Dateien zu schreiben
    private static void appendText(String text, BufferedWriter writer) {
        try {
            writer.write(text + "\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Fehler: Text konnte nicht in der Datei abgelegt werden!");
        }
    }
    //Verwenden wir für die 2.Aufgabe um Username und Password in Dateien zu schreiben
    private static BufferedWriter openFile(String filename) {
        try {
            return Files.newBufferedWriter(Paths.get(filename), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Fehler: Datei konnte nicht geöffnet werden.");
        }
        return null;
    }
    //Verwenden wir für die 2. Aufgabe um die Zeile zu lesen welche richtiger Username zu welchem richtigen Password gehören
    private static List<String> readLineByLineMod(String filename) {
        List<String> content = new ArrayList<String>();
        try {
            return Files.readAllLines(Paths.get(filename));

        } catch (IOException e) {
            System.out.println("Fehler!");
        }
        return null;

    }
    //Verwenden wir für die 2. Aufgabe um Username zu registrieren und in Datei zu schreiben
    public static void registerUsername(String filename){
        BufferedWriter writer = null;
        System.out.print("Username: ");
        String username = reader.next();

        if(!Files.exists(Paths.get(filename))){
            createFile(filename);
        }
        writer = openFile(filename);
        if (writer != null) {
            appendText(username, writer);
        }
    }
    //Verwenden wir für die 2. Aufgabe um Password zu registrieren und in Datei zu schreiben
    public static void registerPassword(String filename){
        BufferedWriter writer = null;
        System.out.print("Password: ");
        String password = reader.next();
        if(!Files.exists(Paths.get(filename))){
            createFile(filename);
        }
        writer = openFile(filename);
        if (writer != null) {
            appendText(password, writer);
        }
    }
    //Verwenden wir für die 2.Aufgabe um Username zu kontrollieren bzw. überprüfen ob er übereinstimmt
    public static List<Integer> controlUsername(String filename, String username){
        List<Integer> count = new ArrayList<Integer>();
        List<String> usernames = readLineByLineMod(filename);
        for (int i = 0;i < usernames.size();i++){
            if(username.equals(usernames.get(i))){
                count.add(i);
            }
        }
        return count;
    }
    //Verwenden wir für die 2.Aufgabe um Password zu kontrollieren bzw. überprüfen ob dieser übereinstimmt
    public static boolean controlPassword(List<Integer> count, String filename, String password){

        List<String> passwords = readLineByLineMod(filename);
        for (int i = 0; i < count.size();i++){
            if(password.equals(passwords.get(count.get(i)))){
                return true;
            }
        }
        return false;
    }
    //Wichtig für die 3. Aufgabe um zu unterscheiden welche Person welcher Warenkorb gehört
    public static int IDCalculator(String fileUsername, String username, String filePassword, String password){
        List<Integer> count = controlUsername(fileUsername, username);
        List<String> passwords = readLineByLineMod(filePassword);
        for(int i = 0; i < count.size(); i++){
            if(password.equals(passwords.get(count.get(i)))){
                int id = count.get(i) + 1;  // 0 ist eine schlechte ID deswegen +1
                System.out.println("Your ID: " + id);
                return count.get(i);
            }
        }
        return 9999;
    }
    //Eingabe für Username (Aufgabe 2/3)
    public static String enterUsername(){
        System.out.print("Enter Username: ");
        return reader.next();
    }
    //Eingabe für Password (Aufgabe 2/3)
    public static String enterPassword(){
        System.out.print("Enter Password: ");
        return reader.next();
    }
    // Dient dazu Eingabe von Username und Password zu lesen
    private static void readLine(String filename, int id) {
        List<String> content = new ArrayList<String>();
        try {
            content = Files.readAllLines(Paths.get(filename));
            System.out.println(content.get(id));

        } catch (IOException e) {
            System.out.println("Fehler!");
        }

    }
    //Dient für die 3. Aufgabe um Basket zu speichern, falls jemand das Programm mit Artikel im Basket beendet
    public static void serializeBasket(String filename, Basket basket){
        try(FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){

            oos.writeObject(basket);

        }
        catch (IOException e){
            System.out.println("Serialisierung hat nicht funktioniert!");
        }
    }
    //Dient für die 3. Aufgabe um Basket zu laden und um bei erneutiger Öffnung die Artikel im Warenkorb wiederherzustellen
    public static Basket deserializeBasket(String filename){
        try(FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis)){

            return (Basket)ois.readObject();

        }
        catch (IOException e){
            System.out.println("Deserialisierung hat nicht funktioniert!");
        }
        catch (ClassNotFoundException e){
            System.out.println("Basket existiert nicht!");
        }
        return null;
    }



}
