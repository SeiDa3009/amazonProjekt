package models;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Login {
    private Scanner reader = new Scanner(System.in);
    //Wird für Aufgabe 2 verwendet

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
    public char loginMenu(){
        System.out.println("l ... login");
        System.out.println("r ... register");
        System.out.print("Select one: ");
        return reader.next().toLowerCase().charAt(0);
    }

    //Verwenden wir für die 2.Aufgabe um Username und Password in Dateien zu schreiben
    private void appendText(String text, BufferedWriter writer) {
        try {
            writer.write(text + "\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Fehler: Text konnte nicht in der Datei abgelegt werden!");
        }
    }

    //Verwenden wir für die 2.Aufgabe um Username und Password in Dateien zu schreiben
    private BufferedWriter openFile(String filename) {
        try {
            return Files.newBufferedWriter(Paths.get(filename), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Fehler: Datei konnte nicht geöffnet werden.");
        }
        return null;
    }

    //Verwenden wir für die 2. Aufgabe um die Zeile zu lesen welche richtiger Username zu welchem richtigen Password gehören
    private List<String> readLineByLineMod(String filename) {
        List<String> content = new ArrayList<String>();
        try {
            return Files.readAllLines(Paths.get(filename));

        } catch (IOException e) {
            System.out.println("Fehler!");
        }
        return null;

    }

    //Verwenden wir für die 2. Aufgabe um Username zu registrieren und in Datei zu schreiben
    public void registerUsername(String filename){
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
    public void registerPassword(String filename){
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
    public List<Integer> controlUsername(String filename, String username){
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
    public boolean controlPassword(List<Integer> count, String filename, String password){

        List<String> passwords = readLineByLineMod(filename);
        for (int i = 0; i < count.size();i++){
            if(password.equals(passwords.get(count.get(i)))){
                return true;
            }
        }
        return false;
    }
    //Wichtig für die 3. Aufgabe um zu unterscheiden welche Person welcher Warenkorb gehört
    public int IDCalculator(String fileUsername, String username, String filePassword, String password){
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
    public String enterUsername(){
        System.out.print("Enter Username: ");
        return reader.next();
    }
    //Eingabe für Password (Aufgabe 2/3)
    public String enterPassword(){
        System.out.print("Enter Password: ");
        return reader.next();
    }
    // Dient dazu Eingabe von Username und Password zu lesen
    public void readLine(String filename, int id) {
        List<String> content = new ArrayList<String>();
        try {
            content = Files.readAllLines(Paths.get(filename));
            System.out.println(content.get(id));

        } catch (IOException e) {
            System.out.println("Fehler!");
        }

    }

    //Erstellte Methoden für Auftrag:
    //File-Erstellung
    public void createFile(String filename) {
        try {
            Files.createFile(Paths.get(filename));
        } catch (FileAlreadyExistsException e) {
            System.out.println("Fehler: Datei existiert bereits!");
        } catch (IOException e) {
            System.out.println("Fehler: IO-Fehler");
        }
    }
}
