package org.agenda.views;

import org.agenda.database.Database;
import org.agenda.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TelephoneUI {
    public static List<String> add() {
        Scanner sc = new Scanner(System.in);
        List<String> camposTelephone = new ArrayList<>();
        String ddd, numero;

        System.out.println("ADICIONAR TELEFONE:");

        System.out.print("DDD: ");
        ddd = sc.nextLine();
        camposTelephone.add(ddd);


        System.out.print("NUMERO:");
        numero = sc.nextLine();
        camposTelephone.add(numero);

        return camposTelephone;

    }

    public static int delete(int index) {
        Contact contact = Database.getInstance().getContacts().get(index);
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < contact.getAllTelephones().size(); i++) {
            System.out.println("ID: |" + i + "| " + contact.getAllTelephones().get(i));
        }


        //TO DO SE USUÁRIO INSERIR INDEX INCORRETO, FAZER O TRATAMENTO
        System.out.print("QUAL INDEX DESEJA EXCLUIR? ");
        int indexEscolhido = sc.nextInt();

        return indexEscolhido;
    }
}
