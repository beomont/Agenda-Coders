package org.agenda.views;

import org.agenda.database.Database;
import org.agenda.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressUI {

    public static List<String> add() {
        Scanner sc = new Scanner(System.in);
        List<String> camposAdress = new ArrayList<>();
        String cep, street, number, state, city;

        System.out.println("Adicionar Endereço:");

        System.out.print("CEP: ");
        cep = sc.nextLine();
        camposAdress.add(cep);

        System.out.print("Logradouro: ");
        street = sc.nextLine();
        camposAdress.add(street);

        System.out.print("Numero: ");
        number = sc.nextLine();
        camposAdress.add(number);

        System.out.print("Estado: ");
        state = sc.nextLine();
        camposAdress.add(state);

        System.out.print("Cidade: ");
        city = sc.nextLine();
        camposAdress.add(city);

        return camposAdress;

    }

    public static int delete(int index){
        Contact contact = Database.getInstance().getContacts().get(index);
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < contact.getAllAddresses().size(); i++){
            System.out.println("Index: |" + i  + "| "+ contact.getAllAddresses().get(i));
        }


        //TO DO SE USUÁRIO INSERIR INDEX INCORRETO, FAZER O TRATAMENTO
        System.out.print("QUAL INDEX DESEJA EXCLUIR? ");
        int indexEscolhido = sc.nextInt();

        return indexEscolhido;
    }

}
