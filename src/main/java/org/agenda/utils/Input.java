package org.agenda.utils;

import java.util.Scanner;

public class Input {
    public static int integer() {
        return new Scanner(System.in).nextInt();
    }

    public static String string(String nomeDoCampo) {
        System.out.printf("Por favor informe o %s\n", nomeDoCampo);
        return new Scanner(System.in).nextLine().trim();
    }

    public static String stringNotNullable(String nomeDoCampo, int tentativas) throws Exception {
        int tentadas = 0;
        boolean isInvalid;
        String input = "";

        do {
            isInvalid = false;

            try {
                input = string(nomeDoCampo);

                if (input.isBlank()) throw new Exception(nomeDoCampo);


            } catch (Exception ex) {
                tentadas += 1;

                System.out.printf("msg: %s, não pode ser nulo\n", ex.getMessage());
                if (tentadas < tentativas) {
                    isInvalid = true;
                    continue;
                }

                throw new Exception("Número de tentativas excedidas");
            }

        } while (isInvalid);

        return input;
    }

}
