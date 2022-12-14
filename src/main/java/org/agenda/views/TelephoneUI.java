package org.agenda.views;

import org.agenda.controller.TelephoneController;
import org.agenda.model.Contact;
import org.agenda.model.Telephone;
import org.agenda.utils.Input;
import org.agenda.utils.MenuCreator;

import java.util.ArrayList;
import java.util.List;

public class TelephoneUI {
    public static List<String> add() throws Exception {
        List<String> camposTelephone = new ArrayList<>();
        String ddd, numero;

        System.out.println("ADICIONAR TELEFONE:");

        ddd = Input.stringNotNullable("DDD: ", 3);
        camposTelephone.add(ddd);

        numero = Input.stringNotNullable("NÚMERO: ", 3);

        camposTelephone.add(numero);

        return camposTelephone;
    }

    public static void list(Contact contact) {
        boolean working = true;
        int originalAmmount = 0;
        int ammount = 0;
        int start = 0;

        while (working) {

            try {

                if (ammount == 0) {
                    ammount = Input.integer("Informe a Quantidade de telefones por página: ");
                    originalAmmount = ammount;
                    System.out.println("");
                }

                if (start < 0 || start > contact.getAllTelephones().size()) start = 0;
                if (ammount < 0) ammount = 0;
                if (ammount > contact.getAllTelephones().size()) ammount = contact.getAllTelephones().size();
                if (start + ammount > contact.getAllTelephones().size())
                    start = contact.getAllTelephones().size() - ammount;

                System.out.println("------ TELEFONES ------");
                if (contact.getAllTelephones().size() == 0) System.out.println("NENHUM TELEFONE ENCONTRADO");

                for (int i = start; i < start + ammount; i++) {
                    if (i == contact.getAllTelephones().size()) break;
                    Telephone phoneTemp = contact.getAllTelephones().get(i);

                    System.out.println("ID: " + i);
                    System.out.println("NUMERO: " + phoneTemp.getDdd() + " " + phoneTemp.getNumber());
                    System.out.println("----------------------");
                }

                System.out.println("");


                int option;
                if (contact.getAllTelephones().size() > 0) {
                    option = MenuCreator.exec("OPÇÔES DE TELEFONE:", "VOLTAR", "PAGINA SEGUINTE", "PAGINA ANTERIOR", "ADICIONAR TELEFONE", "REMOVER TELEFONE");

                    switch (option) {
                        case 0 -> {
                            working = false;
                        }
                        case 1 -> {
                            start = start + ammount;
                        }
                        case 2 -> {
                            start = start - ammount;
                        }
                        case 3 -> {
                            TelephoneController.create(contact);
                        }

                        case 4 -> {
                            TelephoneController.remove(contact);
                        }

                        default -> System.out.println("OPÇÂO INVÁLIDA\n");
                    }
                } else {
                    option = MenuCreator.exec("OPÇÔES DE TELEFONE:", "VOLTAR", "ADICIONAR TELEFONE", "REMOVER TELEFONE");

                    switch (option) {
                        case 0 -> {
                            working = false;
                        }
                        case 1 -> {
                            TelephoneController.create(contact);
                        }
                        case 2 -> {
                            TelephoneController.remove(contact);
                        }
                        default -> System.out.println("OPÇÂO INVÁLIDA\n");
                    }

                }

                ammount = originalAmmount;
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " VOLTANDO AO MENU PRINCIPAL...");
            }
        }
    }

    public static int delete(Contact contact) throws Exception {
        boolean working = true;
        int tentativas = 0;
        int index = 0;

        while (working) {

            System.out.println("------- TELEFONES -------");

            for (int i = 0; i < contact.getAllTelephones().size(); i++) {
                Telephone phoneTemp = contact.getAllTelephones().get(i);
                System.out.println(" ID: " + i);
                System.out.println(" NUMERO: (" + phoneTemp.getDdd() + ") " + phoneTemp.getNumber());
                System.out.println("----------------------");
            }
            System.out.println("");

            index = Input.integer("DIGITE O ID DO TELEFONE QUE DESEJA REMOVER: ");
            System.out.println("");

            if (index >= contact.getAllTelephones().size()) {
                System.out.println("OPÇÂO INVÀLIDA\n");
                tentativas++;
                continue;
            }

            if (tentativas > 3) {
                throw new Exception("MULTIPLAS TENTATIVAS INCORRETAS");
            }
        }

        return index;
    }
}
