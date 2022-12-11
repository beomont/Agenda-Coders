package org.agenda.model;

public class Address {
    private String cep;
    private String address;
    private String number;
    private String state;
    private String city;

    public Address() {

    }

    public Address(String cep, String address, String number, String state, String city) {
        this.cep = cep;
        this.address = address;
        this.number = number;
        this.state = state;
        this.city = city;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
