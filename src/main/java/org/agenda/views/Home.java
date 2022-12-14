package org.agenda.views;

import org.agenda.controller.ContactController;
import org.agenda.utils.MenuCreator;

public class Home {
    public static void init() {
            ContactController contactController = new ContactController();
        boolean executing = true;
        int option;

        while (executing) {

            option = MenuCreator.exec("DIGITE A OPÇÃO DESEJADA:", "SAIR", "ADICIONAR CONTATO",
                    "LISTAR CONTATOS", "PESQUISAR CONTATOS", "REMOVER CONTATO", "REMOVER TODOS CONTATOS");


            switch (option) {
                case 0 -> {
                    System.out.println("APLICAÇÃO ENCERRADA!");
                    executing = false;
                }
                case 1 -> {
                    contactController.create();
                }
                case 2 -> {
                    contactController.view();
                }
                case 3 -> {
                    contactController.search(null);
                }
                case 4 -> {
                    contactController.remove(null);
                }
                case 5 -> {
                    contactController.deleteAll();
                }

                default -> System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }
}
