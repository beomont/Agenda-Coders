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

        Address address = new Address(cep, logradouro, numero, estado, cidade);

        if (contact.addAddress(address)) {
            System.out.println("Endereço Salvo!");

        } else {
            System.out.println("ENDEREÇO JÁ CADASTRADO!");
        }

    }

    public static void remove(Contact contact) throws Exception {
        contact.removeAddress(AddressUI.delete(contact));
    }


}
