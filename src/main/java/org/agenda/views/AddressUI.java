package org.agenda.views;

import org.agenda.controller.AddressController;
import org.agenda.model.Address;
import org.agenda.model.Contact;
import org.agenda.utils.Input;
import org.agenda.utils.MenuCreator;

import java.util.ArrayList;
import java.util.List;

public class AddressUI {

    public static List<String> add() throws Exception {
        List<String> camposAdress = new ArrayList<>();
        String cep, street, number, state, city;

        System.out.println("ADICIONAR ENDEREÇO:");

        cep = Input.stringNotNullable("CEP: ", 3);
        camposAdress.add(cep);

        street = Input.stringNotNullable("LOGRADOURO: ", 3);
        camposAdress.add(street);

        number = Input.stringNotNullable("NUMERO: ", 3);
        camposAdress.add(number);

        state = Input.stringNotNullable("ESTADO: ", 3);
        camposAdress.add(state);

        city = Input.stringNotNullable("CIDADE: ", 3);
        camposAdress.add(city);

        return camposAdress;

    }


    public static void list(Contact contact) {

        boolean working = true;
        int originalAmmount = 0;
        int ammount = 0;
        int start = 0;

        while (working) {

            try {

                if (ammount == 0) {
                    ammount = Input.integer("QUANTIDADE DE ENDEREÇOS POR PÁGINA: ");
                    originalAmmount = ammount;
                    System.out.println("");
                }

                if (start < 0 || start > contact.getAllAddresses().size()) start = 0;
                if (ammount < 0) ammount = 0;
                if (ammount > contact.getAllAddresses().size()) ammount = contact.getAllAddresses().size();
                if (start + ammount > contact.getAllAddresses().size())
                    start = contact.getAllAddresses().size() - ammount;

                System.out.println("----------- ENDEREÇOS -----------");
                if (contact.getAllAddresses().size() == 0) System.out.println("NENHUM ENDEREÇO ENCONTRADO");

                for (int i = start; i < start + ammount; i++) {
                    if (i == contact.getAllAddresses().size()) break;
                    Address addressTemp = contact.getAllAddresses().get(i);

                    System.out.println("ID: " + i);
                    System.out.println("ENDEREÇO: " + addressTemp.getAddress() + ", "
                            + addressTemp.getNumber() + ", "
                            + addressTemp.getCep() + " - "
                            + addressTemp.getCity() + " - "
                            + addressTemp.getState()
                    );
                    System.out.println("---------------------------------");
                }

                System.out.println("");


                int option;
                if (contact.getAllAddresses().size() > 0) {
                    option = MenuCreator.exec("OPÇÔES DE ENDEREÇO:", "VOLTAR", "PAGINA SEGUINTE", "PAGINA ANTERIOR", "ADICIONAR ENDEREÇO", "REMOVER ENDEREÇO");

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
                            AddressController.create(contact);
                        }

                        case 4 -> {
                            AddressController.remove(contact);
                        }

                        default -> System.out.println("-> Opção inválida\n");
                    }
                } else {
                    option = MenuCreator.exec("OPÇÔES DE ENDEREÇO:", "VOLTAR", "ADICIONAR ENDEREÇO", "REMOVER ENDEREÇO");

                    switch (option) {
                        case 0 -> {
                            working = false;
                        }
                        case 1 -> {
                            AddressController.create(contact);
                        }
                        case 2 -> {
                            AddressController.remove(contact);
                        }
                        default -> System.out.println("-> Opção inválida\n");
                    }

                }
                option = 0;
                ammount = originalAmmount;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("voltando...\n");
            }
        }
    }

    public static int delete(Contact contact) throws Exception {
        boolean working = true;
        int tentativas = 0;
        int index = 0;

        while (working) {

            System.out.println("------------ ENDEREÇOS ------------");

            for (int i = 0; i < contact.getAllAddresses().size(); i++) {
                Address addressTemp = contact.getAllAddresses().get(i);

                System.out.println(" ID: " + i);
                System.out.println(" ENDEREÇO: " + addressTemp.getAddress() + ", "
                        + addressTemp.getNumber() + ", "
                        + addressTemp.getCep() + " - "
                        + addressTemp.getCity() + " - "
                        + addressTemp.getState()
                );
                System.out.println("--------------------------------");
            }
            System.out.println("");

            index = Input.integer("DIGITE O ID DO ENDEREÇO QUE DESEJA REMOVER: ");
            System.out.println("");

            if (index >= contact.getAllAddresses().size()) {
                System.out.println("-> Opção inválida\n");
                index = 0;
                tentativas++;
                continue;
            }

            if (tentativas > 3) {
                throw new Exception("-> Multiplas tentativas inválidas");
            }
            break;
        }
        return index;
    }

}
