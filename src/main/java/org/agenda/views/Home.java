package org.agenda.views;

import org.agenda.controller.ContactController;

import java.util.Scanner;

public class Home {
    public static void init() {
        Scanner sc = new Scanner(System.in);
        ContactController contactController = new ContactController();
        boolean executing = true;
        int option;

        while (executing) {
            printMenu();
            System.out.print("Digite a opção desejada:");
            option = sc.nextInt();
            switch (option) {
                case 0 -> {
                    executing = false;
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

    private static void printMenu() {
        System.out.println("[0] Sair");
        System.out.println("[1] Adicionar novo contato");
        System.out.println("[2] Listar todos os contatos");
        System.out.println("[3] Pesquisar contato");
        System.out.println("[4] Remover contato");
        System.out.println("[5] Remover todos contatos");
        System.out.println("[6] Exibir contato");
    }
}
