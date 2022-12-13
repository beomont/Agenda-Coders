package org.agenda.views;

import org.agenda.controller.ContactController;
import org.agenda.model.Address;
import org.agenda.model.Contact;
import org.agenda.model.Telephone;
import org.agenda.utils.Input;
import org.agenda.utils.MenuCreator;

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
        if (name.isBlank()) {
            System.out.println("Nome inválido");
            return;
        }
        System.out.print("Sobrenome: ");
        surname = sc.nextLine();
        if (surname.isBlank()) {
            System.out.println("Sobrenome inválido");
            return;
        }

        contactController.save(name, surname);

    }

    public static void list(List<Contact> contacts) {
        int index = 0;

       try {
           if (contacts.size() > 1){

               System.out.println("");
               for (Contact contact : contacts) {
                   System.out.println("-------- CONTATO -------");
                   System.out.println("ID: " + index);
                   System.out.println("Nome: " + contact.getName());
                   System.out.println("Sobrenome: " + contact.getSurname());
                   System.out.println("------------------------");
                   index++;
               }

               int indexOption = getIndex();
               ContactUI.view(contacts.get(indexOption));
           }else{
               ContactUI.view(contacts.get(0));
           }
       }catch (Exception ex){
           System.out.println("Opção inválida");
       }


    }

    public static int getIndex() throws Exception {
        int index = 0;

        index = Input.integer("Digite o ID que deseja exibir: ");
        System.out.println("");
        return index;
    }

    public static String paginatedList(List<Contact> contacts) {
        boolean working = true;
        int ammount = 0;
        int start = 0;
        String option = "";

        while (working) {
            try {
                if (ammount == 0) {
                    ammount = Input.integer("Informe a Quantidade de contatos por página: ");
                    System.out.println("");
                }

                if (start < 0 || start > contacts.size()) start = 0;
                if (ammount < 0) ammount = 0;
                if (ammount > contacts.size()) ammount = contacts.size();
                if (start + ammount > contacts.size()) start = contacts.size() - ammount;

                System.out.println("------ CONTATOS ------");

                for (int i = start; i < start + ammount; i++) {
                    if (i == contacts.size()) break;
                    System.out.println("ID: " + i);
                    System.out.println("NOME: " + contacts.get(i).getName() + " " + contacts.get(i).getSurname());
                    System.out.println("----------------------");
                }
                System.out.println("");

                boolean better = true;

                while (better) {
                    switch (MenuCreator.exec(".:: NAVEGAÇÂO ::.", "SAIR", "PAGINA SEGUINTE", "PAGINA ANTERIOR", "EXIBIR CONTATO")) {
                        case 0 -> {
                            better = false;
                            working = false;
                            option = "VOLTAR";
                        }
                        case 1 -> {
                            start = start + ammount;
                            better = false;
                        }
                        case 2 -> {
                            start = start - ammount;
                            better = false;
                        }
                        case 3 -> {
                            better = false;
                            working = false;
                            option = "EDITAR";
                        }
                        default -> System.out.println(" OPÇÂO INVÁLIDA\n");
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " VOLTANDO AO MENU PRINCIPAL...");
            }

        }
        return option;

    }

    public static void search() {
        ContactController contactController = new ContactController();
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o termo para buscar o contato: ");
        String term = sc.nextLine().trim().toUpperCase();
        contactController.search(term);
    }

    public static void remove() {
        ContactController contactController = new ContactController();
        Scanner sc = new Scanner(System.in);
        Integer index;

        System.out.print("Digite o index que deseja remover: ");
        index = sc.nextInt();
        contactController.remove(index);
    }

    public static void view(Contact contact) {
        boolean working = true;

        while (working) {
            System.out.println("------- CONTATO -------");
            System.out.println(" Nome: " + contact.getName());
            System.out.println(" Sobrenome: " + contact.getSurname());
            System.out.println("-----------------------");
            System.out.println("");

            int option = MenuCreator.exec(".:: OPÇÔES DE CONTATO ::.", "VOLTAR", "LISTAR ENDERECOS", "LISTAR TELEFONES");

            switch (option) {
                case 0 -> {
                    working = false;
                }
                case 1 -> AddressUI.list(contact);
                case 2 -> TelephoneUI.list(contact);

                default -> System.out.println("OPCAO INCORRETA \n");
            }
        }
    }


}
