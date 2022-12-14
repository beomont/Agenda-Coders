package org.agenda.controller;

import org.agenda.database.Database;
import org.agenda.model.Contact;
import org.agenda.views.ContactUI;

public class ContactController {

    public String paginatedList() {
        Database db = Database.getInstance();
        return ContactUI.paginatedList(db.getContacts());
    }

    public void search(String value) {
        Database db = Database.getInstance();
        if (value != null) {
            ContactUI.list(db.searchContact(value));
        } else {
            ContactUI.search();
        }
    }

    public void create() {
        ContactUI.add();
    }

    public void save(String name, String surname) {
        Contact contact = new Contact(name.toUpperCase(), surname.toUpperCase());
        Database db = Database.getInstance();

        if (db.addContact(contact)) {
			System.out.println("");
			System.out.println("-----------------");
			System.out.println("| CONTATO SALVO |");
			System.out.println("-----------------");
			System.out.println("");
		} else {
			System.out.println("");
			System.out.println("----------------------");
			System.out.println("| CONTATO DUPLICADO! |");
			System.out.println("----------------------");
			System.out.println("");
		}
    }

    public void deleteAll() {
        Database db = Database.getInstance();
        db.deleteAll();
    }

    public void remove(Integer index) {

        Database db = Database.getInstance();

        try{
            if (index == null ) {
                ContactUI.remove(db.getContacts());
            } else{
                db.remove(index);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage() + "\n");
        }
    }

    public void view() {
        String option = paginatedList();
        if (option.equals("EDITAR")) {
            viewContactInfo();
        }
    }

    public void viewContactInfo() {
        Database db = Database.getInstance();
        try {
            int index = ContactUI.getIndex();
            ContactUI.view(db.get(index));

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "VOLTANDO AO MENU PRINCIPAL ...\n");
        }
    }
}
