package org.agenda.controller;

import org.agenda.model.Address;
import org.agenda.model.Contact;
import org.agenda.views.AddressUI;
import org.agenda.views.ContactUI;

import java.util.List;
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

        menuInsercao(index);

    }

    public void menuInsercao(int index) {
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
                            //CONTROLERTELEFONE CREATE
                            break;
                        case "2":
                            //CONTROLERTELEFONE REMOVE
                            break;
                        case "3":
                            AddressController.create(index);
                            break;
                        case "4":
                            //REMOVE ENDEREÇO
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
