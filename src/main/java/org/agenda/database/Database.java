package org.agenda.database;

import org.agenda.model.Contact;
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
    Path dbPath;
    List<Contact> contacts;
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
            contacts.add(new Contact(name, surname));
        }
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void close() throws IOException {
        Files.newBufferedWriter(dbPath);
        JSONArray contactsArray = new JSONArray();

        for (Contact contact : contacts) {
            JSONObject contactObject = new JSONObject();
            contactObject.put("name", contact.getName());
            contactObject.put("surname", contact.getSurname());
            contactsArray.put(contactObject);
        }

        JSONObject object = new JSONObject().put("contacts", contactsArray);

        Files.writeString(dbPath, object.toString(), StandardOpenOption.WRITE);
    }
}
