package org.agenda.views;

import org.agenda.database.Database;
import org.agenda.model.Address;
import org.agenda.model.Contact;
import org.agenda.model.Telephone;
import org.agenda.utils.Input;
import org.agenda.utils.MenuCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TelephoneUI {
    public static List<String> add() {
        Scanner sc = new Scanner(System.in);
        List<String> camposTelephone = new ArrayList<>();
        String ddd, numero;

        System.out.println("Adicionar Telefone:");

        System.out.print("DDD: ");
        ddd = sc.nextLine();
        camposTelephone.add(ddd);


        System.out.print("Numero: ");
        numero = sc.nextLine();
        camposTelephone.add(numero);

        return camposTelephone;

    }

    public static void list(Contact contact) {
        boolean working = true;
        int ammount = 0;
        int start = 0;

        while (working) {

            try {

                if (ammount == 0) {
                    ammount = Input.integer("Informe a Quantidade de telefones por página: ");
                    System.out.println("");
                }

                if (start < 0 || start > contact.getAllTelephones().size()) start = 0;
                if (ammount < 0) ammount = 0;
                if (ammount > contact.getAllTelephones().size()) ammount = contact.getAllTelephones().size() - 1;
                if (start + ammount > contact.getAllTelephones().size())
                    start = contact.getAllTelephones().size() - ammount;

                System.out.println("------ TELEFONES ------");
                if (contact.getAllTelephones().size() == 0) System.out.println("Nenhum telefone encontrado");

//                for (int i = 0; i < 0 + ammount; i++) {
//                    if (i == contact.getAllTelephones().size()) break;
//                    System.out.println("ID: " + i);
//                    System.out.println("NUMERO: " + contact.getAllTelephones().get(i).getDdd() + " " + contact.getAllTelephones().get(i).getNumber());
//                    System.out.println("----------------------");
//                }

                System.out.println(contact.getAllTelephones().get(0).getDdd());

                System.out.println("");

                boolean better = true;

                while (better){
                    int option;
                    if (contact.getAllTelephones().size() > 0) {
                        option = MenuCreator.exec("OPÇÔES DE TELEFONE:", "VOLTAR", "PAGINA SEGUINTE", "PAGINA ANTERIOR", "ADICIONAR TELEFONE", "REMOVER TELEFONE");

                        switch (option) {
                            case 0 -> {
                                working = false;
                            }
                            case 1 -> {
                            }
                            case 2 -> {
                            }
                            case 3 -> {
                            }
                            case 4 -> {
                            }
                            default -> System.out.println("OPÇÂO INVÁLIDA\n");
                        }
                    } else {
                        option = MenuCreator.exec("OPÇÔES DE TELEFONE:", "VOLTAR", "ADICIONAR TELEFONE", "REMOVER TELEFONE");

                        switch (option) {
                            case 0 -> {
                                better = false;
                            }
                            case 1 -> {
                            }
                            case 2 -> {
                            }
                            default -> System.out.println("OPÇÂO INVÁLIDA\n");
                        }

                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " VOLTANDO AO MENU PRINCIPAL...");
            }
        }
    }

    public static int delete(int index) {
        Contact contact = Database.getInstance().getContacts().get(index);
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < contact.getAllTelephones().size(); i++) {
            System.out.println("Index: |" + i + "| " + contact.getAllTelephones().get(i));
        }


        //TO DO SE USUÁRIO INSERIR INDEX INCORRETO, FAZER O TRATAMENTO
        System.out.print("QUAL INDEX DESEJA EXCLUIR? ");
        int indexEscolhido = sc.nextInt();

        return indexEscolhido;
    }
}
