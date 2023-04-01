package ui;

import java.util.Arrays;
import java.util.Scanner;

import data.DataHelper;
import models.Materia;
import models.Professor;

public class Cli {

    private Scanner scan;
    
    public Cli () {

        scan = new Scanner(System.in, "UTF-8");

    }

    public void menuInicialVazio () {

        Integer opcao;

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
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Cadastrar Professor.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    this.cadastroProfessor();

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

        } while (opcao.intValue() != 3);

    }

    public void menuIniciaComLogin () {

        Integer opcao;

        do {
            
            System.out.println("\n");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Cadastrar Professor");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%1s", "2.Cadastar Aluno");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%1s", "3.Login Aluno");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String quartaOpcao = String.format("|%1s", "4.Login Professor");
            System.out.printf("%-81s|\n", quartaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String quintaOpcao = String.format("|%1s", "5.Sair");
            System.out.printf("%-81s|\n", quintaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Cadastrar Professor.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    this.cadastroProfessor();

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Cadastrar Aluno.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Logar como aluno.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 4:

                    System.out.printf("|Sua escolha: %-67s|\n", "Logar como professor.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 5:

                    System.out.printf("|Sua escolha: %-67s|\n", "Sair... Que pena, até a póxima.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }

        } while (opcao.intValue() != 5);

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
    private String repeteCaracter (char c, Integer tamanho) {

        char[] caracteres = new char[tamanho.intValue()];
        Arrays.fill(caracteres, c);
        return new String(caracteres);

    }

    private void cadastroProfessor () {

        String opcao;
        String primeiroNome;
        String segundoNome;
        String cpf;
        String telefone;
        String email;
        String nomeUsuario;
        String senha;
        String materia;

        do {

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Sua escolha: %-67s|\n", "Digite o seu primeiro nome:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            primeiroNome = scan.nextLine();

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Sua escolha: %-67s|\n", "Digite seu último nome:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            segundoNome = scan.nextLine();

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Sua escolha: %-67s|\n", "Digite seu cpf:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            cpf = scan.nextLine();

            while (!DataHelper.verificaCPF(cpf)) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|Sua escolha: %-67s|\n", "CPF INVÁLIDO! TENTE NOVAMENTE.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                cpf = scan.nextLine();

            }

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Sua escolha: %-67s|\n", "Digite seu número de telefone:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            telefone = scan.nextLine();

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Sua escolha: %-67s|\n", "Digite seu e-mail:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            email = scan.nextLine();

            while (!DataHelper.verificaEmail(email)) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|Sua escolha: %-67s|\n", "E-MAIL INVÁLIDO! TENTE NOVAMENTE.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                email = scan.nextLine();

            }

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Sua escolha: %-67s|\n", "Digite seu nome de usuário:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            nomeUsuario = scan.nextLine();

            while (!DataHelper.verificaUsuário(nomeUsuario)) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|Sua escolha: %-67s|\n", "NOME DE USUÁRIO INVÁLIDO! TENTE NOVAMENTE.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                nomeUsuario = scan.nextLine();

            }

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Sua escolha: %-67s|\n", "Digite sus senha:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            senha = scan.nextLine();

            while (!DataHelper.verificaSenha(senha)) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|Sua escolha: %-67s|\n", "SENHA INVÁLIDA! TENTE NOVAMENTE.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|Sua escolha: %-67s|\n", "A senha deve conter entre 6 a 20 caracteres,");
                System.out.printf("|Sua escolha: %-67s|\n", "deve conter pelo menos uma letra maiúscula,");
                System.out.printf("|Sua escolha: %-67s|\n", "um número e não deve conter símbolos.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                senha = scan.nextLine();

            }
            
            Boolean condition = Boolean.FALSE;

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Sua escolha: %-67s|\n", "Digite a matéria:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            do {

                System.out.printf("|%1s", "> ");
                materia = scan.nextLine();
                Materia[] materias = Materia.values();
                
                for (Materia m : materias) {
                    
                    if (m.toString().compareToIgnoreCase(materia) == 0) {

                        condition = Boolean.TRUE;
                        materia = m.toString();
                        break;

                    }

                }

            } while (condition != Boolean.TRUE);

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Sua escolha: %-67s|\n", "Confirme as informações");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Primeiro nome: %-67s|\n", primeiroNome);
            System.out.printf("|Segundo nome: %-67s|\n", segundoNome);
            System.out.printf("|CPF: %-67s|\n", cpf);
            System.out.printf("|Telefone: %-67s|\n", telefone);
            System.out.printf("|E-mail: %-67s|\n", email);
            System.out.printf("|Nome de usuário: %-67s|\n", nomeUsuario);
            System.out.printf("|Senha: %-67s|\n", senha);
            System.out.printf("|Matéria: %-67s|\n", materia);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Sua escolha: %-67s|\n", "As informações acima estão corretas? (S/N)");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            
            System.out.printf("|%1s", "> ");
            opcao = scan.nextLine();

        } while ((opcao.compareToIgnoreCase("n") == 0) || (opcao.compareToIgnoreCase("não") == 0) || (opcao.compareToIgnoreCase("nao") == 0));

        Professor p = new Professor(primeiroNome, segundoNome, cpf, telefone, email, nomeUsuario, senha, materia);

        System.out.println(p.toString());

    }
    
}
