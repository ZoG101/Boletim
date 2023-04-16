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
            String primeiraOpcao = String.format("|%20s", "1.Ver informações de perfil");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%20s", "2.Boletim");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%20s", "3.Sair");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Ver Informações de perfil.");
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

    public void professorLogado (Professor professor) {

        Integer opcao;

        do {

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String intrudocao = String.format("|%20s", professor.getNomeCompleto());
            System.out.printf("%-81s|\n", intrudocao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Ver informações de perfil");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%20s", "2.Procurar boletim");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%20s", "3.Procurar aluno");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String quartaOpcao = String.format("|%20s", "4.Exibir turmas");
            System.out.printf("%-81s|\n", quartaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String quintaOpcao = String.format("|%20s", "5.Procurar turma por ID");
            System.out.printf("%-81s|\n", quintaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String sextaOpcao = String.format("|%20s", "6.Criar turma");
            System.out.printf("%-81s|\n", sextaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String setimaOpcao = String.format("|%20s", "7.Procurar aluno por nome");
            System.out.printf("%-81s|\n", setimaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String oitavaOpcao = String.format("|%20s", "8.Procurar aluno por ID");
            System.out.printf("%-81s|\n", oitavaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String nonaOpcao = String.format("|%20s", "9.Criar boletim");
            System.out.printf("%-81s|\n", nonaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String decimaOpcao = String.format("|%20s", "10.Procurar boletim");
            System.out.printf("%-81s|\n", decimaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Ver Informações de perfil.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.println(professor.toString());

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar boletim.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.println(professor);

                break;

                case 4:

                    System.out.printf("|Sua escolha: %-67s|\n", "Exibir turmas.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 5:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar turma por ID.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 6:

                    System.out.printf("|Sua escolha: %-67s|\n", "Criar turma.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 7:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar aluno por nome.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 8:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar aluno por ID.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 9:

                    System.out.printf("|Sua escolha: %-67s|\n", "Criar boletim.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 10:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar boletim.");
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
            String segundaOpcao = String.format("|%20s", "2.Inserir o aluno novo em uma turma existente");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%20s", "3.Voltar");
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

                    System.out.printf("|Sua escolha: %-67s|\n", "Criar um aluno e inseri-lo em uma turma existente.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        String id = this.getTurmaId(professor);
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

    private String getTurmaId (Professor professor) {

        System.out.println(professor.getTurmas());
        System.out.println();
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%-80s|\n", "Digite o ID da turma:");
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%1s", "> ");
        String id = scan.next();
        String complementar = scan.nextLine();
        return id = id + complementar;

    }

}
