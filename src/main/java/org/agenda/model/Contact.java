package org.agenda.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contact {
    private String name;
    private String surname;
    private List<Address> addresses;
    private List<Telephone> telephones;

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;

        addresses = new ArrayList<>();
        telephones = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Telephone> getAllTelephones() {
        return telephones;
    }

    public boolean addTelephone(Telephone telephone) {

        if (telephones.contains(telephone)) return false;

        telephones.add(telephone);
        return true;
    }

    public List<Address> getAllAddresses() {
        return addresses;
    }

    public boolean addAddress(Address address) {

        if (addresses.contains(address)) return false;

        addresses.add(address);
        return true;
    }

    public void removeAddress(int index) {
        addresses.remove(index);
    }

    public void removeTelephone(int index) {
        telephones.remove(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name) && surname.equals(contact.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

}
