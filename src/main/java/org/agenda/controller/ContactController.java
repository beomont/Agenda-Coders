package org.agenda.controller;

import org.agenda.model.Contact;
import org.agenda.views.ContactUI;

import java.util.Scanner;

public class ContactController {

    public void list() {
        Contact contact = new Contact();
        ContactUI.list(contact.getAll());
    }

    public void search(String value) {
        if (value != null) {
            Contact contact = new Contact();
            ContactUI.list(contact.searchContact(value));
        } else {
            ContactUI.search();
        }
    }

    public void create() {
        ContactUI.add();
    }

    public void save(String name, String surname) {
        Contact contact = new Contact(name, surname);
        if (contact.save()) {
            System.out.println("contato salvo");
        } else {
            System.out.println("erro ao salvar o contato");
        }
    }

    public void deleteAll() {
        Contact contact = new Contact();
        contact.deleteAll();
    }

    public void remove(Integer index) {
        if (index != null) {
            Contact contact = new Contact();
            contact.remove(index);
        } else {
            ContactUI.remove();
        }
    }

    public void view() {
        list();
        Scanner sc = new Scanner(System.in);
        int index;
        System.out.print("Digite o index que deseja exibir: ");
        index = sc.nextInt();

        Contact contact = new Contact();
        ContactUI.view(contact.get(index));

    }
}
