package org.agenda.controller;

import org.agenda.database.Database;
import org.agenda.model.Address;
import org.agenda.model.Contact;
import org.agenda.model.Telephone;
import org.agenda.views.AddressUI;
import org.agenda.views.TelephoneUI;

import java.util.List;

public class TelephoneController {

    public static void create(int index) {

        Contact contact = new Contact();
        List<String> camposTelephone = TelephoneUI.add();

        String ddd = camposTelephone.get(0).toString();
        String numero = camposTelephone.get(1).toString();


        Telephone telephone = new Telephone(ddd, numero);

        if(contact.get(index).addTelephone(telephone)){
            System.out.println("Telefone Salvo!");
        } else {
            System.out.println("Telefone já cadastrado.");
        }

    }

    public static void remove(int index) {
        Contact contact = Database.getInstance().getContacts().get(index);
        contact.removeTelephone(TelephoneUI.delete(index));
    }

}
