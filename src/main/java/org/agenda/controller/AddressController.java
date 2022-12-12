package org.agenda.controller;

import org.agenda.database.Database;
import org.agenda.model.Address;
import org.agenda.model.Contact;
import org.agenda.views.AddressUI;
import org.agenda.views.ContactUI;

import java.util.List;

public class AddressController {

    public static void create(int index) {

        Contact contact = new Contact();
        List<String> camposAddress = AddressUI.add();

        String cep = camposAddress.get(0).toString();
        String logradouro = camposAddress.get(1).toString();
        String numero = camposAddress.get(2).toString();
        String estado = camposAddress.get(3).toString();
        String cidade = camposAddress.get(4).toString();

        Address address = new Address(cep, logradouro, numero, estado, cidade);
        if(contact.get(index).addAddress(address)) {
            System.out.println("Endereço Salvo!");
        } else {
            System.out.println("Endereço já cadastrado.");
        }

    }

    public static void remove(int index) {
        Contact contact = Database.getInstance().getContacts().get(index);
        contact.removeAddress(AddressUI.delete(index));
    }


}
