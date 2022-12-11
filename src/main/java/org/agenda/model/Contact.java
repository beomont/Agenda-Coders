package org.agenda.model;

import org.agenda.database.Database;
import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String name;
    private String surname;
    private List<Address> addresses;
    private List<Telephone> telephones;

    public Contact() {

    }

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;

        addresses = new ArrayList<>();
        telephones = new ArrayList<>();
    }


    public List<Contact> getAll() {
        Database db = Database.getInstance();
        return db.getContacts();
    }

    public Contact get(int index) {
        Database db = Database.getInstance();
        return db.getContacts().get(index);
    }

    public List<Contact> searchContact(String value) {
        List<Contact> contacts = this.getAll();
        List<Contact> matchContacts = new ArrayList<>();

        for (Contact contact: contacts) {
            String fullName = contact.getName() + " " + contact.getSurname();
            if(fullName.contains(value)) {
                matchContacts.add(contact);
            }
        }
        return matchContacts;
    }

    public boolean save() {
        Database db = Database.getInstance();
        List<Contact> contacts = db.getContacts();

        contacts.add(this);
        return true;
    }

    public void deleteAll() {
        Database db = Database.getInstance();
        List<Contact> contacts = db.getContacts();
        contacts.clear();
    }

    public void remove(int index) {
        Database db = Database.getInstance();
        List<Contact> contacts = db.getContacts();
        contacts.remove(index);
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

    public void addTelephone(Telephone telephone) {
        telephones.add(telephone);
    }

    public List<Address> getAllAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }


}
