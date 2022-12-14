package org.agenda.controller;

import org.agenda.database.Database;
import org.agenda.model.Contact;
import org.agenda.model.Telephone;
import org.agenda.views.TelephoneUI;

import java.util.List;

public class TelephoneController {

    public static void create(Contact contact) throws Exception {
        List<String> camposTelephone = TelephoneUI.add();

        String ddd = camposTelephone.get(0).toString();
        String numero = camposTelephone.get(1).toString();

        Telephone telephone = new Telephone(ddd, numero);

        if (contact.addTelephone(telephone)) {
            System.out.println("Telefone Salvo!");

        } else {
            System.out.println("TELEFONE JÁ CADASTRADO!");
        }

    }

    public static void remove(Contact contact) throws Exception {
        contact.removeTelephone(TelephoneUI.delete(contact));
    }

}
