package org.agenda.views;

import org.agenda.controller.ContactController;
import org.agenda.model.Address;
import org.agenda.model.Contact;
import org.agenda.model.Telephone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactUI {

    public static void add() {
        Scanner sc = new Scanner(System.in);
        ContactController contactController = new ContactController();
        String name;
        String surname;

        System.out.println("Adicionar novo contato:");
        System.out.print("Nome: ");
        name = sc.nextLine();
        System.out.print("Sobrenome: ");
        surname = sc.nextLine();

        contactController.save(name, surname);

    }

    public static void list(List<Contact> contacts) {
        int index = 0;
        for (Contact contact : contacts) {
            System.out.println("-----CONTATO-----");
            System.out.println("Index: " + index);
            System.out.println("Nome: " + contact.getName());
            System.out.println("Sobrenome: " + contact.getSurname());
            System.out.println("Endereço: " + contact.getAllAddresses());
            System.out.println("----------------------");
            index++;
        }
    }

    public static void search(){
        ContactController contactController = new ContactController();
        Scanner sc = new Scanner(System.in);
        String term;

        System.out.print("Digite o termo para buscar o contato: ");
        term = sc.nextLine();
        contactController.search(term);
    }

    public static void remove(){
        ContactController contactController = new ContactController();
        Scanner sc = new Scanner(System.in);
        Integer index;

        System.out.print("Digite o index que deseja remover: ");
        index = sc.nextInt();
        contactController.remove(index);
    }

    public static void view(Contact contact) {
        System.out.println("-----CONTATO-----");
        System.out.println("Nome: " + contact.getName());
        System.out.println("Sobrenome: " + contact.getSurname());



        for (Telephone telephone: contact.getAllTelephones()) {
            System.out.println("DDD: " + telephone.getDdd());
            System.out.println("Número: " + telephone.getNumber());
        }

        for (Address address : contact.getAllAddresses()) {
            System.out.println("Endereço: " + address.getAddress() + ", " + address.getNumber() + ", " + address.getCep()
                    + " - " + address.getCity() + " - " + address.getState());
        }

        System.out.println("----------------------");
    }


}
