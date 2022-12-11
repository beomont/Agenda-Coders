package org.agenda;

import org.agenda.database.Database;
import org.agenda.views.Home;

public class Main {
    public static void main(String[] args) {
        Database db = Database.getInstance();

        try {
            db.init();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.init();

        try {
            db.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}