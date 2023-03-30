package ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Cli {

    Scanner scan;
    
    public Cli () {

        scan = new Scanner(System.in);

    }

    public void menuInicial () throws IOException {

        Integer condition;

        do {
            
            System.out.println("\n");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Cadastrar Professor");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%1s", "2.Cadastar Aluno");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%1s", "3.Sair");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            condition = Integer.valueOf(scan.nextInt());

            switch (condition) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Cadastrar Professor.");

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Cadastrar Aluno.");

                break;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Sair... Que pena, até a póxima.");

                break;

                default:

                    System.out.printf("|Sua escolha: %-67s|\n", "Nenhuma das existentes.");

                break;

            }
            
            System.out.println("|" + repeteCaracter('-', 80) + "|");

        } while (condition.intValue() != 3);

    }

    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do {@code Cli}. Este método recebe um caracter e um {@code Iteger}
     * e, com base nesse parametro, o mesmo irá repetir o caracter inserido
     * o número de vezes que lhe for informado no parâmetro de {@code tamanho}.
     * 
     * @param c
     * @param tamanho
     * @return String
     * @see String
     * @see Integer
     * @see Arrays
     */
    private String repeteCaracter (char c, Integer tamanho) {

        char[] caracteres = new char[tamanho.intValue()];
        Arrays.fill(caracteres, c);
        return new String(caracteres);

    }
    
}
