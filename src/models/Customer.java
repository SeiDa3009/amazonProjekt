package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {

    // fields
    private int _customerId;
    private String _firstname;
    private String _lastname;
    private LocalDate _birthdate;
    private String _eMail;
    private Gender _gender;
    private List<Address> _addresses = new ArrayList<Address>();
    private Address _shippingAddress;
    private String username;
    private Basket _basket = new Basket();
    private String password;

    // getter/setter
    public int getCustomerId() {
        return _customerId;
    }
    public String getFirstname() {
        return _firstname;
    }
    public void setFirstname(String firstname) {
        this._firstname = firstname;
    }
    public String getLastname() {
        return _lastname;
    }
    public void setLastname(String lastname) {
        this._lastname = lastname;
    }
    public LocalDate getBirthdate() {
        return _birthdate;
    }
    public void setBirthdate(LocalDate birthdate) {
        this._birthdate = birthdate;
    }
    public String getEMail() {
        return _eMail;
    }
    public void setEMail(String eMail) {
        if(eMail.contains("@")){
            this._eMail = eMail.trim();
        }
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String newUsername){
        this.username = newUsername;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String newPassword){
        this.password = newPassword;
    }
    public Gender getGender() {
        return _gender;
    }
    public void setGender(Gender gender) {
        this._gender = gender;
    }
    public List<Address> getAddresses() {
        return _addresses;
    }
    public Address getShippingAddress() {
        return _shippingAddress;
    }
    public void setShippingAddress(Address shippingAddress) {
        this._shippingAddress = shippingAddress;
    }
    public Basket getBasket(){
        return this._basket;
    }
    public void setBasket(Basket basket){
        if(basket != null){
            this._basket = basket;
        }
    }

    // ctors
    public Customer(){
        this(0, "", "", null, Gender.notSpecified, "","");
    }
    public Customer(int id, String firstname, String lastname, LocalDate birthdate, Gender gender, String username, String password){
        this._customerId = id > 0 ? id : 0;
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setBirthdate(birthdate);
        this.setGender(gender);
        this.username = username;
        this.password = password;
    }

    // other methods
    public boolean addAddress(Address addressToAdd){
        if(addressToAdd == null){
            return false;
        }
        return this._addresses.add(addressToAdd);
    }
    public boolean removeAddress(Address addressToRemove){
        if(addressToRemove == null){
            return false;
        }
        return this._addresses.remove(addressToRemove);
    }

    public String getAddressById(int addressId){
        if(this._addresses.size() < 1){
            return "no addresses specified";
        }

        for (Address a : this._addresses) {
            if (a.getAddressId() == addressId) {
                return a.toString();
            }
        }
        return "address with the specified addressId not found";
    }

    @Override
    public String toString(){
        return this.getFirstname() + " " + this.getLastname() + " " + this.getGender() + " " + this.getBirthdate();
    }
}
