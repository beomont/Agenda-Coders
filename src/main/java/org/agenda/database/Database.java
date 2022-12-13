package org.agenda.database;

import org.agenda.model.Address;
import org.agenda.model.Contact;
import org.agenda.model.Telephone;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Path dbPath;
    private List<Contact> contacts;
    private static Database instance;

    private Database() {
        dbPath = Paths.get("src/database/database.json");
        contacts = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void init() throws IOException {
        JSONObject contactsObjects = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
        JSONArray contactsArray = (JSONArray) contactsObjects.get("contacts");
        for (Object contactObject : contactsArray) {

            JSONObject contact = (JSONObject) contactObject;
            String name = (String) contact.get("name");
            String surname = (String) contact.get("surname");
            Contact newContact = new Contact(name, surname);
            getAddresses(contact, newContact);
            getTelephones(contact, newContact);
            contacts.add(newContact);
        }
    }

    private void getAddresses(JSONObject contactObj, Contact newContact) {
        JSONArray AddressList = new JSONArray(contactObj.get("addresses").toString());

        for (int j = 0; j < AddressList.length(); j++) {
            JSONObject address = new JSONObject(AddressList.get(j).toString());

            Address convertedAddress = new Address(
                    address.get("cep").toString(),
                    address.get("address").toString(),
                    address.get("number").toString(),
                    address.get("state").toString(),
                    address.get("city").toString()
            );
            newContact.addAddress(convertedAddress);
        }
    }

    private void getTelephones(JSONObject contactObj, Contact newContact) {
        JSONArray TelephonesList = new JSONArray(contactObj.get("telephones").toString());

        for (int j = 0; j < TelephonesList.length(); j++) {
            JSONObject telephone = new JSONObject(TelephonesList.get(j).toString());

            Telephone ConvertedPhone = new Telephone(
                    telephone.get("ddd").toString(),
                    telephone.get("number").toString()
            );
            newContact.addTelephone(ConvertedPhone);
        }
    }

    public List<Contact> getAll() {
        return contacts;
    }

    public Contact get(int index) {
        return contacts.get(index);
    }

    public List<Contact> searchContact(String value) {
        List<Contact> matchContacts = new ArrayList<>();

        for (Contact contact : contacts) {
            String fullName = contact.getName() + " " + contact.getSurname();
            if (fullName.contains(value)) {
                matchContacts.add(contact);
            }
        }
        return matchContacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public boolean addContact(Contact contact) {
        if (contacts.contains(contact)) return false;

        contacts.add(contact);
        return true;
    }

    public void deleteAll() {
        contacts.clear();
    }

    public void remove(int index) {
        contacts.remove(index);
    }

    public void close() throws IOException {
        Files.newBufferedWriter(dbPath);
        JSONArray contactsArray = new JSONArray();

        for (Contact contact : contacts) {
            JSONObject contactObject = new JSONObject();
            contactObject.put("name", contact.getName());
            contactObject.put("surname", contact.getSurname());
            contactObject.put("addresses", contact.getAllAddresses());
            contactObject.put("telephones", contact.getAllTelephones());
            contactsArray.put(contactObject);
        }

        JSONObject object = new JSONObject().put("contacts", contactsArray);

        Files.writeString(dbPath, object.toString(), StandardOpenOption.WRITE);
    }
}
