package ui;

import java.util.Arrays;
import java.util.Scanner;

import models.Aluno;
import models.Professor;

public abstract class UserCli {

    private Scanner scan;
    Professor usuarioProfessor;
    Aluno usuarioAluno;

    public void alunoLogado () {

        Integer opcao;

        do {

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Ver informações");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%20s", "1.Boletim");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%20s", "1.Sair");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Cadastrar Professor.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Cadastrar Aluno.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Sair... Que pena, até a póxima.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }

        }while (opcao.intValue() != 3);

    }
    
    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do {@code Cli}. Este método recebe um caracter e um {@code Iteger}
     * e, com base nesse parametro, o mesmo irá repetir o caracter inserido
     * o número de vezes que lhe for informado no parâmetro de {@code tamanho}.
     * 
     * @param c
     * @param tamanho
     * @return {@value String} com um caractere repetido quantas vezes
     *         for escolhido.
     * @see String
     * @see Integer
     * @see Arrays
     */
    public String repeteCaracter (char c, Integer tamanho) {

        char[] caracteres = new char[tamanho.intValue()];
        Arrays.fill(caracteres, c);
        return new String(caracteres);

    }

}
