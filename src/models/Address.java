package models;

public class Address {

    // fields
    private int _addressId;
    private String _street;
    private String _streetNumber;
    private String _postalCode;
    private String _city;
    private String _state;

    // getter/setter
    public int getAddressId() {
        return _addressId;
    }
    public String getStreet() {
        return _street;
    }
    public void setStreet(String street) {
        this._street = street;
    }
    public String getStreetNumber() {
        return _streetNumber;
    }
    public void setStreetNumber(String streetNumber) {
        this._streetNumber = streetNumber;
    }
    public String getPostalCode() {
        return _postalCode;
    }
    public void setPostalCode(String postalCode) {
        this._postalCode = postalCode;
    }
    public String getCity() {
        return _city;
    }
    public void setCity(String city) {
        this._city = city;
    }
    public String getState() {
        return _state;
    }
    public void setState(String state) {
        this._state = state;
    }

    // ctors
    public Address(){
        this(0, "", "", "", "", "");
    }
    public Address(int id, String street, String streetNr, String postalCode, String city, String state){
        this._addressId = id > 0 ? id : 0;
        this.setStreet(street);
        this.setStreetNumber(streetNr);
        this.setPostalCode(postalCode);
        this.setCity(city);
        this.setState(state);
    }

    // other methods
    @Override
    public String toString(){
        return this.getStreet() + " " + this.getStreetNumber() + "\n" +
                this.getPostalCode() + " " + this.getCity() + "\n" +
                this.getState();
    }
}