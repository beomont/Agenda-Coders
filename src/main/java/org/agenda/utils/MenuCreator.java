package org.agenda.utils;

public class MenuCreator {

    public static int exec(String Message, String... options){
        int chooseYourDestiny = 0;
        int tentadas = 0;

        boolean isInvalid;

        do {
            isInvalid = false;
            try {
                System.out.println(Message);
                for (int i = 0; i < options.length; i++) {
                    System.out.printf("[%d] - %s\n", i, options[i]);
                }

                System.out.print("ESCOLHA UMA DAS OPÇÕES: ");
                chooseYourDestiny = Input.integer();

                System.out.println("");

            } catch (Exception ex) {
                tentadas += 1;
                System.out.printf(ex.getMessage() + "\n\n");
                if (tentadas < 3) {
                    isInvalid = true;
                    continue;
                }
                break;
            }

        } while (isInvalid);

        return chooseYourDestiny;
    }

}
