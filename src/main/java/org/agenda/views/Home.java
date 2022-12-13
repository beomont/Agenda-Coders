package org.agenda.views;

import org.agenda.controller.ContactController;
import org.agenda.utils.MenuCreator;

import java.util.Scanner;

public class Home {
    public static void init() {
        Scanner sc = new Scanner(System.in);
        ContactController contactController = new ContactController();
        boolean executing = true;
        int option;

        while (executing) {
            option = MenuCreator.exec("Digite a opção desejada:", "sair", "Adicionar contato",
                    "Listar contatos", "Pesquisar contato", "Remover contato", "Remover todos contatos",
                    "Exibir contato");

            switch (option) {
                case 0 -> {
                    System.out.println("Aplicação encerrada!");
                    executing = false;
                    continue;
                }
                case 1 -> {
                    contactController.create();
                }
                case 2 -> {
                    contactController.list();
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
                case 6 -> {
                    contactController.view();
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }
}
