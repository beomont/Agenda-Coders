package org.agenda.utils;

import java.util.Scanner;

public class Input {
    public static int integer() {
        return new Scanner(System.in).nextInt();
    }
//TODO: FAZER VALIDAÇÃO
    public static String string(String nomeDoCampo) {
        System.out.printf("POR FAVOR, INFORME O %s\n", nomeDoCampo);
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

                System.out.printf("MSG: %s, NÃO PODE SER NULO\n", ex.getMessage());
                if (tentadas < tentativas) {
                    isInvalid = true;
                    continue;
                }

                throw new Exception("NÚMERO DE TENTATIVAS EXCEDIDAS");
            }

        } while (isInvalid);

        return input;
    }

}
