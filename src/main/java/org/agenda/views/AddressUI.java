package org.agenda.views;

import org.agenda.controller.AddressController;
import org.agenda.controller.ContactController;
import org.agenda.model.Address;

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

}
