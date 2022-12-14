package org.agenda.controller;

import org.agenda.model.Address;
import org.agenda.model.Contact;
import org.agenda.views.AddressUI;

import java.util.List;

public class AddressController {

    public static void create(Contact contact) throws Exception {
        List<String> camposAddress = AddressUI.add();

        String cep = camposAddress.get(0).toString();
        String logradouro = camposAddress.get(1).toString();
        String numero = camposAddress.get(2).toString();
        String estado = camposAddress.get(3).toString();
        String cidade = camposAddress.get(4).toString();

        Address address = new Address(cep, logradouro.toUpperCase(), numero, estado.toUpperCase(), cidade.toUpperCase());

        if (contact.addAddress(address)) {
            System.out.println("");
            System.out.println("-----------------");
            System.out.println("| ENDEREÇO SALVO |");
            System.out.println("-----------------");
            System.out.println("");

        } else {
            System.out.println("");
            System.out.println("-----------------");
            System.out.println("| ENDEREÇO JÁ CADASTRADO |");
            System.out.println("-----------------");
            System.out.println("");
        }

    }

    public static void remove(Contact contact) throws Exception {
        contact.removeAddress(AddressUI.delete(contact));
    }


}
