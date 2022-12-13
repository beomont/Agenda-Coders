package org.agenda.controller;

import java.util.Scanner;

import org.agenda.database.Database;
import org.agenda.model.Contact;
import org.agenda.utils.MenuCreator;
import org.agenda.views.ContactUI;

public class ContactController {

	public void list() {
		Database db = Database.getInstance();
		ContactUI.list(db.getAll());
	}

	public void search(String value) {
		Database db = Database.getInstance();
		if (value != null) {
			ContactUI.list(db.searchContact(value));
		} else {
			ContactUI.search();
		}
	}

	public void create() {
		ContactUI.add();
	}

	public void save(String name, String surname) {
		Contact contact = new Contact(name, surname);
		Database db = Database.getInstance();

		if (db.addContact(contact)) {
			System.out.println("");
			System.out.println("-----------------");
			System.out.println("| CONTATO SALVO |");
			System.out.println("-----------------");
			System.out.println("");
		} else {
			System.out.println("");
			System.out.println("----------------------");
			System.out.println("| CONTATO DUPLICADO! |");
			System.out.println("----------------------");
			System.out.println("");
		}
	}

	public void deleteAll() {
		Database db = Database.getInstance();
		db.deleteAll();
	}

	public void remove(Integer index) {
		Database db = Database.getInstance();

		if (index != null) {
			db.remove(index);
		} else {
			ContactUI.remove();
		}
	}

	public void view() {
		Database db = Database.getInstance();
		Scanner sc = new Scanner(System.in);
		int index;
		list();
		System.out.print("Digite o ID que deseja exibir: ");
		index = sc.nextInt();

		System.out.println("");

		ContactUI.view(db.get(index));

		menuEdit(index);

	}

	public void menuEdit(int index) {
		boolean executing = true;

		while (executing) {

			int option = MenuCreator.exec(".:: DESEJA EDITAR O CONTATO? ::.", "NÃO - VOLTAR AO MENU PRINCIPAL", "SIM");

			switch (option) {
			case 1:
				boolean inception = true;
				while (inception) {
				int editOptions = MenuCreator.exec(".:: SELECIONE A OPÇÃO QUE DESEJA EDITAR ::.", "VOLTAR AO MENU PRINCIPAL",
						"ADICIONAR TELEFONE", "REMOVER TELEFONE", "ADICIONAR ENDEREÇO", "REMOVER ENDEREÇO");
				
				switch (editOptions) {
				case 1:
					TelephoneController.create(index);
					break;
				case 2:
					TelephoneController.remove(index);
					break;
				case 3:
					AddressController.create(index);
					break;
				case 4:
					AddressController.remove(index);
					break;
				case 0:
					inception = false;
					executing = false;
					System.out.println("VOLTANDO AO MENU PRINCIPAL...");
					break;
				default:
					System.out.printf("Opção (%d) é inválida!%n%n", editOptions);
				
				}
			}   
				break;
			case 0:
				executing = false;
				System.out.println("VOLTANDO AO MENU PRINCIPAL...");
				break;				
			default:
				System.out.printf("OPÇÃO (%d) É INVÁLIDA!%n%n", option);
			}

		}
	}
}
