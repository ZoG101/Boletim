package ui;

import java.util.Arrays;
import java.util.Scanner;

import models.Aluno;
import models.Professor;
import models.Turma;

public abstract class UserCli {

    private Scanner scan;
    Professor usuarioProfessor;
    Aluno usuarioAluno;

    public void alunoLogado (Aluno aluno) {

        Integer opcao;

        do {

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String intrudocao = String.format("|%20s", aluno.getNomeCompleto());
            System.out.printf("%-81s|\n", intrudocao);
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

                    System.out.printf("|Sua escolha: %-67s|\n", "Ver Informações.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.println(aluno.toString());

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Boletim.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.println(aluno.getBoletins());

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

        } while (opcao.intValue() != 3);

    }

    public void registraAluno (Aluno aluno, Professor professor) {

        Integer opcao;

        do {

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Inserir o novo aluno em uma nova turma");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%20s", "1.Inserir o aluno novo em uma turma existente");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%20s", "1.Voltar");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Criar um novo aluno e uma nova turma.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {
                        
                        System.out.printf("|%-80s|\n", "Digite sua senha:");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%1s", "> ");
                        String senha = scan.next();
                        String complementar = scan.nextLine();
                        senha = senha + complementar;
                        System.out.println("|" + repeteCaracter('-', 80) + "|");

                        Turma turmaNova = professor.criaTurma(senha);
                        turmaNova.adicionarAluno(aluno);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    } finally {

                        opcao = Integer.valueOf(3);

                    }

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Criar um aluno e inseri-lo em uma turma existência.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        System.out.println(professor.getTurmas());

                        System.out.printf("|%-80s|\n", "Digite o ID da turma:");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%1s", "> ");
                        String id = scan.next();
                        String complementar = scan.nextLine();
                        id = id + complementar;
                        System.out.println("|" + repeteCaracter('-', 80) + "|");

                        Turma turma = professor.getTurma(id);
                        turma.adicionarAluno(aluno);
                        
                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    } finally {

                        opcao = Integer.valueOf(3);

                    }

                break;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Voltar.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }

        } while (opcao.intValue() != 3);

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
