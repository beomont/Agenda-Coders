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
            option = MenuCreator.exec("DIGITE A OPÇÃO DESEJADA:", "SAIR", "ADICIONAR CONTATO",
                    "LISTAR CONTATOS", "PESQUISAR CONTATOS", "REMOVER CONTATO", "REMOVER TODOS CONTATOS",
                    "EXIBIR CONTATO");

            switch (option) {
                case 0 -> {
                    System.out.println("APLICAÇÃO ENCERRADA!");
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
                default -> System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }
}
