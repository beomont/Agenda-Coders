package org.agenda.controller;

import org.agenda.database.Database;
import org.agenda.model.Contact;
import org.agenda.views.ContactUI;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactController {

    public String paginatedList() {
        Database db = Database.getInstance();
        return ContactUI.paginatedList(db.getContacts());
    }

    public void list() {
        Database db = Database.getInstance();
        ContactUI.list(db.getContacts());
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
        Contact contact = new Contact(name, surname);
        Database db = Database.getInstance();

        if (db.addContact(contact)) {
            System.out.println("Contato salvo");
        } else {
            System.out.println("Contato duplicado!");
        }
    }

    public void deleteAll() {
        Database db = Database.getInstance();
        db.deleteAll();
    }

    public void remove(Integer index) {
        Database db = Database.getInstance();

        if (index != null) {
            db.remove(index);
        } else {
            ContactUI.remove();
        }
    }

    public void view() {
        Database db = Database.getInstance();
        String option = paginatedList();

        if (option.equals("EDITAR")) {
            System.out.println(option);
            try {
                int index = ContactUI.getIndex();
                ContactUI.view(db.get(index));
                menuEdit(index);

            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "VOLTANDO AO MENU PRINCIPAL ...");
            }
        }


    }

    public void menuEdit(int index) {
        boolean executing = true;

        while (executing) {

            System.out.println(".:: DESEJA EDITAR O CONTATO? ::.");

            Scanner sc = new Scanner(System.in);

            System.out.println("[1] - SIM");
            System.out.println("[2] - NÃO");
            String chooseInsert = sc.nextLine();

            switch (chooseInsert) {
                case "1":
                    System.out.println(".:: SELECIONE A OPÇÃO QUE DESEJA EDITAR ::.");
                    System.out.println("[1] - ADICIONAR TELEFONE");
                    System.out.println("[2] - REMOVER TELEFONE");
                    System.out.println("[3] - ADICIONAR ENDEREÇO");
                    System.out.println("[4] - REMOVER ENDEREÇO");
                    String choose = sc.nextLine();

                    switch (choose) {
                        case "1":
                            TelephoneController.create(index);
                            break;
                        case "2":
                            TelephoneController.remove(index);
                            break;
                        case "3":
                            AddressController.create(index);
                            break;
                        case "4":
                            AddressController.remove(index);
                            break;
                        default:
                            System.out.printf("Opção (%s) é inválida!%n%n", choose);
                    }
                    break;
                case "2":
                    executing = false;
                    break;
                default:
                    System.out.printf("Opção (%s) é inválida!%n%n", chooseInsert);
            }

        }
    }
}
