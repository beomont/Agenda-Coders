package org.agenda.views;

import org.agenda.controller.ContactController;
import org.agenda.model.Contact;
import org.agenda.utils.Input;
import org.agenda.utils.MenuCreator;


import java.util.List;

public class ContactUI {

    public static void add() {
        ContactController contactController = new ContactController();
        String name;
        String surname;

        System.out.println("ADICIONAR NOVO CONTATO:");

        try {
            name = Input.stringNotNullable("NOME: ", 3);
            surname = Input.stringNotNullable("SOBRENOME: ", 3);

            contactController.save(name, surname);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("voltando...\n");
        }
    }


    public static void list(List<Contact> contacts) {
        int index = 0;
        int tentativas = 0;
        boolean working;

        do {
            working = false;

            try {

                if (contacts.size() == 0) {
                    System.out.println("NENHUM CONTATO ENCONTRADO PARA O TERMO INFORMADO\n");
                    break;
                }

                if (contacts.size() > 1) {

                    System.out.println("");
                    for (Contact contact : contacts) {
                        System.out.println("-------- CONTATO -------");
                        System.out.println("ID: " + index);
                        System.out.println("Nome: " + contact.getName() + " " + contact.getSurname());
                        System.out.println("------------------------");
                        index++;
                    }
                    System.out.println("");

                    int indexOption = getIndex();
                    if (indexOption > contacts.size()) {
                        System.out.println("-> Opção inválida\n");
                        index = 0;
                        tentativas++;
                        working = true;
                        continue;
                    }

                    if (tentativas > 3) {
                        System.out.println("-> Número de tentativas excedidas");
                        System.out.println("voltando...\n");
                        break;
                    }

                    ContactUI.view(contacts.get(indexOption));

                } else {
                    ContactUI.view(contacts.get(0));
                    break;
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage()+ "\n");
                break;
            }

        } while (working);
    }

    public static int getIndex() throws Exception {
        int index = 0;

        index = Input.integer("DIGITE O ID QUE DESEJA EXIBIR: ");
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
                    if (contacts.size() == 0) {
                        switch (MenuCreator.exec(".:: NAVEGAÇÂO ::.", "SAIR", "ADICIONAR CONTATO")) {
                            case 0 -> {
                                better = false;
                                working = false;
                                option = "VOLTAR";
                            }
                            case 1 -> {
                                add();
                                better = false;
                            }
                            default -> System.out.println(" OPÇÂO INVÁLIDA\n");
                        }
                    } else {
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

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " VOLTANDO AO MENU PRINCIPAL...");
            }

        }
        return option;
    }

    public static void search() {
        ContactController contactController = new ContactController();
        String term = Input.string("DIGITE O TERMO PARA BUSCAR O CONTATO: ");
        contactController.search(term.toUpperCase());
    }

    public static void remove(List<Contact> contacts) throws Exception {
        ContactController contactController = new ContactController();

        boolean working = true;
        int tentativas = 0;

        while (working) {
            System.out.println("------ CONTATOS ------");

            if (contacts.size() == 0) {
                System.out.println("NENHUM CONTATO CADASTRADO NA AGENDA");
                System.out.println("voltando...\n");
                break;
            }

            for (int i = 0; i < contacts.size(); i++) {
                System.out.println("ID: " + i + " => NOME: " + contacts.get(i).getName() + " " + contacts.get(i).getSurname());
                System.out.println("----------------------");
            }

            int index = Input.integer("DIGITE O ID DO CONTATO QUE DESEJA REMOVER: ");
            System.out.println("");

            if (index < contacts.size()) {
                contactController.remove(index);
                break;
            }
            System.out.println("-> Opção inválida\n");
            tentativas++;

            if (tentativas > 3) {
                throw new Exception("-> Multiplas tentativas incorretas\n");
            }
        }


    }

    public static void view(Contact contact) {
        boolean working = true;

        while (working) {
            System.out.println("------- CONTATO -------");
            System.out.println(" NOME: " + contact.getName());
            System.out.println(" SOBRENOME: " + contact.getSurname());
            System.out.println("-----------------------");
            System.out.println("");

            int option = MenuCreator.exec(".:: OPÇÔES DE CONTATO ::.", "VOLTAR", "LISTAR ENDERECOS", "LISTAR TELEFONES");

            switch (option) {
                case 0 -> {
                    working = false;
                }
                case 1 -> AddressUI.list(contact);
                case 2 -> TelephoneUI.list(contact);

                default -> System.out.println("-> Opção inválida \n");
            }
        }
    }

}
