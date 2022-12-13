package org.agenda.views;

import org.agenda.controller.ContactController;
import org.agenda.model.Address;
import org.agenda.model.Contact;
import org.agenda.model.Telephone;
import org.agenda.utils.Input;

import java.util.List;
import java.util.Scanner;

public class ContactUI {

    public static void add() {
        Scanner sc = new Scanner(System.in);
        ContactController contactController = new ContactController();
        String name;
        String surname;

        System.out.println("ADICIONAR NOVO CONTATO:");

        try {
        name = Input.stringNotNullable("NOME: ", 3);
        surname = Input.stringNotNullable("SOBRENOME: ", 3);
        contactController.save(name, surname);
        } catch (Exception ex) {
        	System.out.println("PARÂMETROS INCORRETOS!");
        	System.out.println("VOLTANDO PARA O MENU PRINCIPAL...");
        }
    }


    public static void list(List<Contact> contacts) {
        int index = 0;
        
        System.out.println("------ CONTATOS ------");
        for (Contact contact : contacts) {            
            System.out.println(" ID: " + index);
            System.out.println(" NOME: " + contact.getName() + " " + contact.getSurname());
            System.out.println("----------------------");
            index++;
        }
        System.out.println("");
    }

    public static void search() {
        ContactController contactController = new ContactController();
        Scanner sc = new Scanner(System.in);

        System.out.print("DIGITE O TERMO PARA BUSCAR O CONTATO: ");
        String term = sc.nextLine().trim().toUpperCase();
        contactController.search(term);
    }

    public static void remove() {
        ContactController contactController = new ContactController();
        Scanner sc = new Scanner(System.in);
        Integer index;

        System.out.print("DIGITE O ID QUE DESEJA: ");
        index = sc.nextInt();
        contactController.remove(index);
    }
    
    public static void view(Contact contact) {
        System.out.println("------ CONTATO -------");
        System.out.println(" NOME: " + contact.getName() + " " + contact.getSurname());


        for (Telephone telephone : contact.getAllTelephones()) {
            System.out.println(" NÚMERO: (" + telephone.getDdd() + ") " +  telephone.getNumber());
        }

        for (Address address : contact.getAllAddresses()) {
            System.out.println(" ENDEREÇO: " + address.getAddress() + ", " + address.getNumber() + ", " + address.getCep()
                    + " - " + address.getCity() + " - " + address.getState());
        }

        System.out.println("----------------------");
        System.out.println("");
    }

}
