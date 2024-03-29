package ui;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.DataHelper;
import models.Aluno;
import models.Boletim;
import models.Materia;
import models.Nivel;
import models.Professor;
import models.Turma;
import models.Usuario;

/**
 * A classe {@code UserCli} é feita como uma interface
 * de linha de comando (CLI), ou seja, aqui se tem
 * o menu de {@code Usuario} logado.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.0
 * @see Aluno
 * @see Professor
 * @see Turma
 * @see String
 */
public abstract class UserCli {

    private Scanner scan = new Scanner(System.in, Charset.defaultCharset());
    Professor usuarioProfessor;
    Aluno usuarioAluno;

    /**
     * Método que cria um menu para {@code Aluno}.
     * 
     * @param aluno
     * @see Aluno
     * @see Integer
     * @see String
     * @see Exception
     */
    protected void alunoLogado (Aluno aluno) {

        Integer opcao;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String intrudocao = String.format("|%14s", aluno.getNomeCompleto());
            System.out.printf("%-81s|\n", intrudocao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Ver informações de perfil");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%9s", "2.Boletim");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%6s", "3.Sair");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Ver Informações de perfil.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        this.editorPerfil(aluno);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());
                        
                    }

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Boletim.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.println(aluno.getBoletins());

                break;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Sair.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        aluno.logout();

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());
                        
                    }

                break;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|\n", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }

        } while (opcao.intValue() != 3);

    }

    /**
     * Método que cria um menu para {@code Professor}.
     * 
     * @param professor
     * @see Professor
     * @see Integer
     * @see String
     * @see Exception
     * @see Turma
     * @see Aluno
     * @see Boolean
     */
    protected void professorLogado (Professor professor) {

        Integer opcao;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String intrudocao = String.format("|%11s", professor.getNomeCompleto());
            System.out.printf("%-81s|\n", intrudocao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Ver informações de perfil");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%18s", "2.Procurar boletim");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%15s", "3.Exibir turmas");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String quartaOpcao = String.format("|%20s", "4.Procurar turma por ID");
            System.out.printf("%-81s|\n", quartaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String quintaOpcao = String.format("|%13s", "5.Criar turma");
            System.out.printf("%-81s|\n", quintaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String sextaOpcao = String.format("|%20s", "6.Procurar aluno por nome");
            System.out.printf("%-81s|\n", sextaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String setimaOpcao = String.format("|%20s", "7.Procurar aluno por ID");
            System.out.printf("%-81s|\n", setimaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String oitavaOpcao = String.format("|%15s", "8.Criar boletim");
            System.out.printf("%-81s|\n", oitavaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String nonaOpcao = String.format("|%6s", "9.Sair");
            System.out.printf("%-81s|\n", nonaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Ver Informações de perfil.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        this.editorPerfil(professor);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());
                        
                    }

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar boletim.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    Boletim boletimRetornado = null;
                    
                    try {

                        boletimRetornado = this.procuraBoletim(professor);

                    } catch (Exception e) {

                        System.err.println(e.getMessage());

                    }

                    if (boletimRetornado == null) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Nenhum boletim encontrado!");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        break;

                    }

                    System.out.println(boletimRetornado);

                    try {
                        
                        this.controleBoletim(boletimRetornado);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                break;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Exibir turmas.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    Map<String, Turma> turmas = null;

                    try {
                        
                        turmas = professor.getTurmas();

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    if ((turmas == null) || (turmas.isEmpty())) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Você não possui turmas!");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        break;

                    }

                    System.out.println(turmas.values());

                break;

                case 4:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar turma por ID.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    Turma turmaRetornada = null;

                    try {

                        turmaRetornada = this.retornaTurma(professor);

                    } catch (Exception e) {
                        
                        System.out.println(e.getMessage());

                    }

                    if (turmaRetornada == null) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Nenhuma turma foi encontrada!");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        break;

                    }

                    System.out.println(turmaRetornada);

                break;

                case 5:

                    System.out.printf("|Sua escolha: %-67s|\n", "Criar turma.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    Boolean result = Boolean.FALSE;

                    try {

                        result = this.criaTurma(professor);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    if (!result) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Nenhuma turma foi criada!");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        break;

                    }

                    System.out.println();
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.printf("|%-80s|\n", "Turma foi criada com sucesso!");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 6:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar aluno por nome.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    Aluno alunoRetornado = null;

                    try {
                        
                        alunoRetornado = this.retornaAluno(professor);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    if (alunoRetornado == null) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Nenhum aluno foi encontrado!");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        break;

                    }

                    this.exibeAluno(professor, alunoRetornado);

                break;

                case 7:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar aluno por ID.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    Aluno alunoRetornadoPorId = null;

                    try {
                        
                        alunoRetornadoPorId = this.retornaAlunoID(professor);

                    } catch (Exception e) {
                        
                        System.out.println(e.getMessage());
                        
                    }

                    if (alunoRetornadoPorId == null) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Nenhum aluno foi encontrado!");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        break;

                    }

                    this.exibeAluno(professor, alunoRetornadoPorId);

                break;

                case 8:

                    System.out.printf("|Sua escolha: %-67s|\n", "Criar boletim.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    Boolean resultado = null;

                    try {

                        resultado = this.criaBoletim(professor);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());
                        resultado = Boolean.FALSE;
                        
                    }

                    if (resultado.booleanValue()) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Boletim cridao com sucesso!");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        break;

                    }

                break;

                case 9:

                    System.out.printf("|Sua escolha: %-67s|\n", "Sair.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {
                        
                        professor.logout();

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());
                        
                    }

                break;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|\n", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }

        } while (opcao.intValue() != 9);

    }

    /**
     * Método para exibir um determinado {@code Aluno}
     * mesmo que ele não esteja logado.
     * 
     * @param professor
     * @param aluno
     * @see Professor
     * @see Aluno
     */
    private void exibeAluno (Professor professor, Aluno aluno) {

        System.out.println();
        aluno.login(professor, aluno);
        System.out.println(aluno);
        aluno.logout();

    }

    /**
     * Método que cria um menu para editar um perfil
     * de {@code Usuario}.
     * 
     * @param user
     * @see Usuario
     * @see Integer
     * @see String
     * @see Boolean
     * @see Exception
     */
    private void editorPerfil (Usuario user) {

        Integer opcao;

        do {

            System.out.println();
            System.out.println(user);

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%14s", "1.Alterar nome");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%13s", "2.Alterar CPF");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%18s", "3.Alterar telefone");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String quartaOpcao = String.format("|%16s", "4.Alterar E-mail");
            System.out.printf("%-81s|\n", quartaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String quintaOpcao = String.format("|%20s", "5.Alterar nome de usuário");
            System.out.printf("%-81s|\n", quintaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String sextaOpcao = String.format("|%20s", "6.Alterar senha de usuário");
            System.out.printf("%-81s|\n", sextaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String setimaOpcao = String.format("|%8s", "7.Voltar");
            System.out.printf("%-81s|\n", setimaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            Boolean resultado = null;

            switch (opcao) {

                case 1:

                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.printf("|Sua escolha: %-67s|\n", "Alterar nome.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    resultado = Boolean.FALSE;

                    try {
                        
                        resultado = this.alterarNome(user);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());
                        
                    }

                    if (resultado.booleanValue()) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Nome alterado com sucesso!");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");

                    }

                break;

                case 2:

                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.printf("|Sua escolha: %-67s|\n", "Alterar CPF.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    resultado = Boolean.FALSE;

                    try {
                        
                        resultado = this.alterarCPF(user);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());
                        
                    }

                    if (resultado.booleanValue()) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "CPF alterado com sucesso!");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");

                    }

                break;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Alterar telefone.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    resultado = Boolean.FALSE;

                    try {

                        resultado = this.alterarTelefone(user);

                    } catch (Exception e) {

                        System.err.println(e.getMessage());

                    }

                    if (resultado.booleanValue()) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Telefone alterado com sucesso!");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");

                    }

                break;

                case 4:

                    System.out.printf("|Sua escolha: %-67s|\n", "Alterar E-mail.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    resultado = Boolean.FALSE;

                    try {

                        resultado = this.alterarEmail(user);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());
                        
                    }

                    if (resultado.booleanValue()) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "E-mail alterado com sucesso!");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");

                    }

                break;

                case 5:

                    System.out.printf("|Sua escolha: %-67s|\n", "Alterar nome de usuário.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    resultado = Boolean.FALSE;

                    try {

                        resultado = this.alterarNomeUsuario(user);

                    } catch (Exception e) {

                        System.err.println(e.getMessage());

                    }

                    if (resultado) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Seu nome de usuário foi redefinido com sucesso!");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");

                    }

                break;

                case 6:

                    System.out.printf("|Sua escolha: %-67s|\n", "Alterar senha.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    resultado = Boolean.FALSE;

                    try {
                        
                        resultado = this.alterarSenha(user);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());
                        
                    }

                    if (resultado.booleanValue()) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Sua senha foi alterada com sucesso!");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");

                    }

                break;

                case 7:

                    System.out.printf("|Sua escolha: %-67s|\n", "Voltando...");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|\n", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }

        } while (opcao != 7);

    }

    /**
     * Método para alteração de senha do {@code Usuario}
     * 
     * @param user
     * @return {@value true} caso a senha seja alterada com sucesso;
     *          {@value false} caso não seja alterada com sucesso.
     * @see Boolean
     * @see Usuario
     * @see String
     * @see DataHelper
     */
    private Boolean alterarSenha (Usuario user) {

        String opcao;
        String senha;
        String complementar;
        Boolean confirmaSenha = Boolean.FALSE;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite sua senha:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            senha = scan.next();
            complementar = scan.nextLine();
            senha = senha + complementar;

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Confirme sua senha:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            String confirmacao = scan.next();
            complementar = scan.nextLine();
            confirmacao = confirmacao + complementar;
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            if (senha.compareTo(confirmacao) == 0) confirmaSenha = Boolean.TRUE;

            while ((!DataHelper.verificaSenha(senha)) || (!confirmaSenha)) {

                System.out.println();
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|ATENÇÃO: %-71s|\n", "FORMATO DE SENHA INVÁLIDA OU A SENHA NÃO FOI REPETIDA CORRETAMENTE!");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "A senha deve conter entre 6 a 20 caracteres,");
                System.out.printf("|%-80s|\n", "deve conter pelo menos uma letra maiúscula,");
                System.out.printf("|%-80s|\n", "um número e não deve conter estes símbolos [ !@#$%^&*_=+-].");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "TENTE NOVAMENTE:");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                senha = scan.next();
                complementar = scan.nextLine();
                senha = senha + complementar;

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Confirme sua senha:");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                confirmacao = scan.next();
                complementar = scan.nextLine();
                confirmacao = confirmacao + complementar;
                System.out.println("|" + repeteCaracter('-', 80) + "|");

                if (senha.compareTo(confirmacao) == 0) confirmaSenha = Boolean.TRUE;

            }
            
            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|ATENÇÃO: %-71s|\n", "CONFIRME A INFORMAÇÃO ABAIXO:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Senha: %-73s|\n", senha);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "A informação acima está correta? (S/N)");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = scan.next();
            System.out.println("|" + repeteCaracter('-', 80) + "|");

        } while ((opcao.equalsIgnoreCase("n")) || (opcao.equalsIgnoreCase("não")) || (opcao.equalsIgnoreCase("nao")));

        user.redefinirSenhaAutenticado(senha);
        return Boolean.TRUE;

    }

    /**
     * Método para alterar o nome de {@code Usuario}.
     * 
     * @param user
     * @return {@value true} caso o nome de usuário seja alterado com sucesso;
     *          {@value false} caso não seja alterado com sucesso.
     * @see Boolean
     * @see Usuario
     * @see String
     * @see DataHelper
     */
    private Boolean alterarNomeUsuario (Usuario user) {

        String opcao;
        String nomeUsuario;
        String complementar;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite seu nome de usuário:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            nomeUsuario = scan.next();
            complementar = scan.nextLine();
            nomeUsuario = nomeUsuario + complementar;
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            while (!DataHelper.verificaUsuário(nomeUsuario)) {

                System.out.println();
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|ATENÇÃO: %-71s|\n", "NOME DE USUÁRIO INVÁLIDO! TENTE NOVAMENTE.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                nomeUsuario = scan.next();
                complementar = scan.nextLine();
                nomeUsuario = nomeUsuario + complementar;

            }

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|ATENÇÃO: %-71s|\n", "CONFIRME A INFORMAÇÃO ABAIXO:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Nome de usuário: %-63s|\n", nomeUsuario);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "A informação acima está correta? (S/N)");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = scan.next();
            System.out.println("|" + repeteCaracter('-', 80) + "|");

        } while ((opcao.equalsIgnoreCase("n")) || (opcao.equalsIgnoreCase("não")) || (opcao.equalsIgnoreCase("nao")));

        user.mudarUsuario(nomeUsuario);
        return Boolean.TRUE;

    }

    /**
     * Método para alteração de e-mail do {@code Usuario}.
     * 
     * @param user
     * @return {@value true} caso o e-mail seja alterado com sucesso;
     *          {@value false} caso não seja alterado com sucesso.
     * @see Boolean
     * @see Usuario
     * @see String
     * @see DataHelper 
     */
    private Boolean alterarEmail (Usuario user) {


        String opcao;
        String email;
        String complementar;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite seu e-mail:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            email = scan.next();
            complementar = scan.nextLine();
            email = email + complementar;

            while (!DataHelper.verificaEmail(email)) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|ATENÇÃO: %-71s|\n", "E-MAIL INVÁLIDO! TENTE NOVAMENTE.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                email = scan.next();
                complementar = scan.nextLine();
                email = email + complementar;

            }

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|ATENÇÃO: %-71s|\n", "CONFIRME A INFORMAÇÃO ABAIXO:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|E-mail: %-72s|\n", email);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "A informação acima está correta? (S/N)");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = scan.next();
            System.out.println("|" + repeteCaracter('-', 80) + "|");

        } while ((opcao.equalsIgnoreCase("n")) || (opcao.equalsIgnoreCase("não")) || (opcao.equalsIgnoreCase("nao")));

        user.redefinirEmail(email);
        return Boolean.TRUE;

    }

    /**
     * Método para alterar o número de telefone do {@code Usuario}.
     * 
     * @param user
     * @return {@value true} caso o número de telefone seja alterado com sucesso;
     *          {@value false} caso não seja alterado com sucesso.
     * @see Boolean
     * @see Usuario
     * @see String
     * @see DataHelper
     */
    private Boolean alterarTelefone (Usuario user) {

        String opcao;
        String telefone;
        String complementar;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite seu número de telefone:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            telefone = scan.next();
            complementar = scan.nextLine();
            telefone = telefone + complementar;

            while (!DataHelper.verificaTelefone(telefone)) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|ATENÇÃO: %-71s|\n", "NÚMERO DE TELEFONE INVÁLIDO! TENTE NOVAMENTE.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                telefone = scan.next();
                complementar = scan.nextLine();
                telefone = telefone + complementar;

            }

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|ATENÇÃO: %-71s|\n", "CONFIRME A INFORMAÇÃO ABAIXO:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Telefone: %-70s|\n", telefone);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "A informação acima está correta? (S/N)");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = scan.next();
            System.out.println("|" + repeteCaracter('-', 80) + "|");

        } while ((opcao.equalsIgnoreCase("n")) || (opcao.equalsIgnoreCase("não")) || (opcao.equalsIgnoreCase("nao")));

        telefone = DataHelper.formataTelefone(telefone);

        user.redefinirTelefone(telefone);
        return Boolean.TRUE;

    }

    /**
     * Método para alteração de CPF do {@code Usuario}.
     * 
     * @param user
     * @return {@value true} caso o CPF seja alterado com sucesso.
     *          {@value flase} caso não seja alterado com sucesso.
     * @see Boolean
     * @see Usuario
     * @see String
     * @see DataHelper
     */
    private Boolean alterarCPF (Usuario user) {

        String cpf;
        String complementar;
        String opcao;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite seu cpf:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            cpf = scan.next();
            complementar = scan.nextLine();
            cpf = cpf + complementar;

            while (!DataHelper.verificaCPF(cpf)) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|ATENÇÃO: %-71s|\n", "CPF INVÁLIDO! TENTE NOVAMENTE.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                cpf = scan.next();
                complementar = scan.nextLine();
                cpf = cpf + complementar;

            }

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|ATENÇÃO: %-71s|\n", "CONFIRME A INFORMAÇÃO ABAIXO:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|CPF: %-75s|\n", DataHelper.reformataCPF(cpf));
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "A informação acima está correta? (S/N)");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = scan.next();
            System.out.println("|" + repeteCaracter('-', 80) + "|");

        } while ((opcao.equalsIgnoreCase("n")) || (opcao.equalsIgnoreCase("não")) || (opcao.equalsIgnoreCase("nao")));

        cpf = DataHelper.formataCPF(cpf);

        user.redefinirCPF(cpf);
        return Boolean.TRUE;
        
    }

    /**
     * Método para alteração do nome do {@code Usuario}.
     * 
     * @param user
     * @return {@value true} caso o nome seja alterado com sucesso;
     *          {@value false} caso nçaseja alterado com sucesso.
     * @see Boolean
     * @see Usuario
     * @see String
     * @see Pattern
     * @see Matcher
     */
    private Boolean alterarNome (Usuario user) {

        String opcao;
        String primeiroNome;
        String sobrenome;
        String nomeCompleto;
        String complementar;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite o seu nome completo:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            nomeCompleto = scan.next();
            complementar = scan.nextLine();
            nomeCompleto = nomeCompleto + complementar;
            nomeCompleto = nomeCompleto.toUpperCase();

            final String primeiroNomeRegex = "(^[a-zA-Z]+)";
            final String sobrenomeRegex = "([a-zA-Z]+$)";

            Pattern formato = Pattern.compile(primeiroNomeRegex, Pattern.MULTILINE);
            Matcher confirma = formato.matcher(nomeCompleto);

            String nomeCompletoAux = nomeCompleto;

            if ((!confirma.find()) || (nomeCompletoAux.length() < 4)) {

                nomeCompleto = "";

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Digite APENAS o seu primeiro nome:");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                primeiroNome = scan.next();
                System.out.println("|" + repeteCaracter('-', 80) + "|");

                nomeCompleto = nomeCompleto + primeiroNome;
                nomeCompleto = nomeCompleto.toUpperCase();
                primeiroNome = primeiroNome.toUpperCase();

            } else {

                primeiroNome = confirma.group(1);

            }
            
            formato = Pattern.compile(sobrenomeRegex, Pattern.MULTILINE);
            confirma = formato.matcher(nomeCompleto);

            if (!confirma.find() || (nomeCompletoAux.length() < 4)) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Digite APENAS o seu último nome:");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                sobrenome = scan.next();
                System.out.println("|" + repeteCaracter('-', 80) + "|");

                nomeCompleto = nomeCompleto + " " + sobrenome;
                nomeCompleto = nomeCompleto.toUpperCase();
                sobrenome = sobrenome.toUpperCase();

            } else {

                sobrenome = confirma.group(1);

            }

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|ATENÇÃO: %-71s|\n", "CONFIRME AS INFORMAÇÕES ABAIXO:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Nome completo: %-65s|\n", nomeCompleto);
            System.out.printf("|Primeiro nome: %-65s|\n", primeiroNome);
            System.out.printf("|Sobrenome: %-69s|\n", sobrenome);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "As informações acima estão corretas? (S/N)");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = scan.next();
            System.out.println("|" + repeteCaracter('-', 80) + "|");

        } while ((opcao.equalsIgnoreCase("n")) || (opcao.equalsIgnoreCase("não")) || (opcao.equalsIgnoreCase("nao")));

        user.redefinirNome(nomeCompleto, primeiroNome, sobrenome);
        return Boolean.TRUE;

    }

    /**
     * Método que cria um menu para controle do {@code Boletim}
     * 
     * @param boletim
     * @see Boletim
     * @see Integer
     * @see String
     * @see Exception
     */
    private void controleBoletim (Boletim boletim) {

        Integer opcao;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%15s", "1.Gerar Boletim");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%20s", "2.Verificar estado de aprovação");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%20s", "3.Porcentagem de faltas");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String quartaOpcao = String.format("|%20s", "4.Verificar matéria do boletim");
            System.out.printf("%-81s|\n", quartaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String quintaOpcao = String.format("|%20s", "5.Verificar média do aluno");
            System.out.printf("%-81s|\n", quintaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String sextaOpcao = String.format("|%20s", "6.Verificar nível do aluno");
            System.out.printf("%-81s|\n", sextaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String setimaOpcao = String.format("|%20s", "7.Verificar última nota adicionada");
            System.out.printf("%-81s|\n", setimaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String oitavaOpcao = String.format("|%16s", "8.Adicionar nota");
            System.out.printf("%-81s|\n", oitavaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String nonaOpcao = String.format("|%17s", "9.Adicionar falta");
            System.out.printf("%-81s|\n", nonaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String decimaOpcao = String.format("|%20s", "10.Adicionar mais de uma falta");
            System.out.printf("%-81s|\n", decimaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String decimaPrimeiraOpcao = String.format("|%7s", "11.Sair");
            System.out.printf("%-81s|\n", decimaPrimeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Gerar boletim.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.println();

                    try {

                        boletim.gerarBoletim();

                    } catch (Exception e) {

                        System.err.println(e.getMessage());

                    }
                    
                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Verificar estado de aprovação.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    Boolean aprovacao = null;

                    try {

                        aprovacao = boletim.getAprovado();

                    } catch (Exception e) {

                        System.err.println(e.getMessage());

                    }

                    if (aprovacao == null) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|Estado: %-72s|\n", "Não foi possível verificar a estado de aprovação.");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        break;

                    } else if (aprovacao.booleanValue()) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|Estado: %-72s|\n", "Aprovado(a).");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");

                    } else {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|Estado: %-72s|\n", "Reprovado(a).");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");

                    }
                    
                break;
            
                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Porcentagem de faltas.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    System.out.println();
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        System.out.printf("|Porcentagem: %-67s|\n", (boletim.getFalta() + "%"));

                    } catch (Exception e) {

                        System.err.println(e.getMessage());

                    }

                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    
                break;

                case 4:

                    System.out.printf("|Sua escolha: %-67s|\n", "Verificar matéria do boletim.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    System.out.println();
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        System.out.printf("|Matéria: %-71s|\n", boletim.getMateria());

                    } catch (Exception e) {

                        System.err.println(e.getMessage());

                    }

                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    
                break;

                case 5:

                    System.out.printf("|Sua escolha: %-67s|\n", "Verificar média do aluno.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    System.out.println();
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        System.out.printf("|Média: %-73s|\n", boletim.getMediaFormatada());

                    } catch (Exception e) {

                        System.err.println(e.getMessage());

                    }

                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    
                break;

                case 6:

                    System.out.printf("|Sua escolha: %-67s|\n", "Verificar nível do aluno.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    System.out.println();
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        System.out.printf("|Nível de escolaridade: %-57s|\n", boletim.getNivel());

                    } catch (Exception e) {

                        System.err.println(e.getMessage());

                    }

                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    
                break;

                case 7:

                    System.out.printf("|Sua escolha: %-67s|\n", "Verificar última nota adicionada.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    System.out.println();
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        System.out.printf("|%-80s|\n", boletim.getUltimaNotaAdicionada());

                    } catch (Exception e) {

                        System.err.println(e.getMessage());

                    }

                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    
                break;

                case 8:

                    System.out.printf("|Sua escolha: %-67s|\n", "Adicionar nota.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    Boolean resultado = Boolean.FALSE;

                    try {

                        resultado = this.adicionarNotas(boletim);

                    } catch (Exception e) {

                        System.err.println(e.getMessage());

                    }

                    if (resultado.booleanValue()) {

                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Nota(s) adicionadas com sucesso.");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");

                    }
                    
                break;

                case 9:

                    System.out.printf("|Sua escolha: %-67s|\n", "Adicionar falta.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        boletim.setFalta();

                    } catch (Exception e) {

                        System.err.println(e.getMessage());
                        break;

                    }

                    System.out.println();
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.printf("|%-80s|\n", "Falta adicionada.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    
                break;

                case 10:

                    System.out.printf("|Sua escolha: %-67s|\n", "Adicionar mais de uma falta.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    Boolean resultadoAdicao = Boolean.FALSE;

                    try {

                        resultadoAdicao = this.adicionarFaltas(boletim);

                    } catch (Exception e) {

                        System.err.println(e.getMessage());
                        break;

                    }

                    if (resultadoAdicao) {

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Faltas adicionadas.");
                        System.out.println("|" + repeteCaracter('-', 80) + "|");

                    }
                    
                break;

                case 11:

                    System.out.printf("|Sua escolha: %-67s|\n", "Voltando...");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    
                break;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|\n", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }
            
        } while (opcao.intValue() != 11);

    }

    /**
     * Método que cria um menu para inserção de mais de uma falta
     * para o {@code Boletim} de um determinado {@code Aluno}.
     * 
     * @param boletim
     * @return {@value true} em caso de inserção co sucesso;
     *          {@value false} em caso de inserção sem sucesso.
     * @see Boolean
     * @see Boletim
     * @see String
     * @see InputStream
     * @see InputStreamReader
     * @see BufferedReader
     * @see Exception
     * @see Pattern
     * @see Matcher
     * @see Integer
     */
    private Boolean adicionarFaltas(Boletim boletim) {

        System.out.println();
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%-80s|\n", "Digite o número de faltas:");
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%-80s|\n", "Aperte 'ENTER' em vazio para cancelar");
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%1s", "> ");
        InputStream fis = System.in;
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String faltas = "";

        try {
            
            faltas = br.readLine();

        } catch (Exception e) {
           
            System.err.println(e.getMessage());
            
        }

        String complementar = scan.nextLine();
        faltas = faltas + complementar;

        Pattern formato = Pattern.compile("^([0-9]){0,3}$");
        Matcher matcher = formato.matcher(faltas);

        if (faltas.isBlank()) {

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Nenhuma nota foi adicionada.");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            return Boolean.FALSE;

        } else if (!matcher.matches()) {

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Você não digitou um número.");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Nenhuma nota foi adicionada.");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            return Boolean.FALSE;

        } 

        try {
            
            Integer faltasAdicionadas = Integer.valueOf(faltas);
            boletim.setFalta(faltasAdicionadas);

        } catch (Exception e) {
            
            System.err.println(e.getMessage());
            return Boolean.FALSE;

        }

        return Boolean.TRUE;

    }

    /**
     * Método para adição de notas em um {@code Boletim} de
     * um determinado {@code Aluno}.
     * 
     * @param boletim
     * @return {@value true} em coso de inserção com sucesso;
     *          {@value false} em caso de inserção sem sucesso.
     * @see Boolean
     * @see Boletim
     * @see String
     * @see Nivel
     * @see Double
     * @see Exception
     */
    private Boolean adicionarNotas (Boletim boletim) {

        String nota;
        int count = 0;

        System.out.println();
        if (boletim.getNivel().equalsIgnoreCase(Nivel.GRADUACAO.toString())) {

            if (boletim.getListNotas().size() == 3) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Todas as notas deste aluno(a) já foram adicionadas.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");

                return Boolean.FALSE;

            }

            while (boletim.getListNotas().size() < 3) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Digite a " + (boletim.getListNotas().size() + 1) + "ª nota do aluno(a):");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                nota = scan.next();
                String complementar = scan.nextLine();
                nota = nota + complementar;
                System.out.println("|" + repeteCaracter('-', 80) + "|");

                if (nota.isEmpty()) {

                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.printf("|%-80s|\n", "Foram adicionadas " + count + " notas.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    if (count == 0) return Boolean.FALSE;
                    return Boolean.TRUE;

                }

                Double notaInserida = Double.valueOf(nota);

                try {
                    
                    boletim.setNota(notaInserida);

                } catch (Exception e) {
                    
                    System.err.println(e.getMessage());
                    
                }

                count++;

            }

        } else if (boletim.getNivel().equalsIgnoreCase(Nivel.POSGRADUACAO.toString())) {

            if (boletim.getListNotas().size() == 2) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Todas as notas deste aluno(a) já foram adicionadas.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");

                return Boolean.FALSE;

            }

            while (boletim.getListNotas().size() < 2) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Digite a " + (boletim.getListNotas().size() + 1) + "ª nota do aluno(a):");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                nota = scan.next();
                String complementar = scan.nextLine();
                nota = nota + complementar;
                System.out.println("|" + repeteCaracter('-', 80) + "|");

                if (nota.isEmpty()) {

                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.printf("|%-80s|\n", "Foram adicionadas " + count + " notas.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    if (count == 0) return Boolean.FALSE;
                    return Boolean.TRUE;

                }

                Double notaInserida = Double.valueOf(nota);

                try {
                    
                    boletim.setNota(notaInserida);

                } catch (Exception e) {
                    
                    System.err.println(e.getMessage());
                    
                }

                count++;

            }

        }

        return Boolean.TRUE;

    }

    /**
     * Método para criação de um {@code Boletim} para um
     * determinado {@code Aluno} de um determinado {@code Professor}.
     * 
     * @param professor
     * @return {@value true} Caso o boletim seja criado com sucesso;
     *          {@value false} Caso não seja criado com sucesso.
     * @see Boolean
     * @see Professor
     * @see Integer
     * @see Aluno
     * @see Boolean
     * @see String
     * @see Exception
     */
    private Boolean criaBoletim (Professor professor) {
        
        Integer opcao = null;
        Aluno aluno = null;
        Boolean resultado = null;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Procurar aluno por nome");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%1s", "2.Procurar aluno por ID");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%1s", "3.Voltar");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar aluno por nome.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        aluno = this.retornaAluno(professor);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }

                    if (aluno == null) {

                        System.err.println("\nNenhum aluno criado.");
                        break;

                    }

                    try {
                        
                        resultado = this.criaBoletimAluno(professor, aluno);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    if (!resultado) {

                        System.err.println("\nNenhum boletim criado.");
                        break;

                    }
                    
                return resultado;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar aluno por ID.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        aluno = this.retornaAlunoID(professor);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }

                    if (aluno == null) {

                        System.err.println("\nNenhum aluno encontrado.");
                        break;

                    }

                    try {
                        
                        resultado = this.criaBoletimAluno(professor, aluno);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    if (!resultado) {

                        System.err.println("\nNenhum boletim encontrado.");
                        break;

                    }
                    
                return resultado;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Voltando...");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                return null;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|\n", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }

        } while (opcao.intValue() != 3);

        return null;

    }

    /**
     * Método auxiliar para criar e inserir o {@code Boletim}
     * de um determinado {@code Professor} dentro do {@code Aluno}.
     * 
     * @param professor
     * @param aluno
     * @return {@value true} caso seja criado com sucesso.
     *          {@value false} caso não seja criado com sucesso.
     * @see Boolean
     * @see Professor
     * @see Aluno
     * @see Exception
     */
    private Boolean criaBoletimAluno (Professor professor, Aluno aluno) {

        Boolean resultado = Boolean.FALSE;

        try {
            
            aluno.criarBoletim(professor);
            resultado = Boolean.TRUE;

        } catch (Exception e) {
            
            System.err.println(e.getMessage());
            resultado = Boolean.FALSE;

        }

        return resultado;

    }

    /**
     * Método para criação de uma turma de um determinado {@code Professor}.
     * 
     * @param professor
     * @return {@value true} caso a turma seja criada com sucesso;
     *          {@value false} caso não seja criada com sucesso.
     * @see Boolean
     * @see Professor
     * @see Integer
     * @see String
     */
    private Boolean criaTurma (Professor professor) {

        String senha = null;
        Integer count = Integer.valueOf(3);

        do {

            System.out.println();

            if (count < 3) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Você tem mais " + count + " tentativas!");

            }

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite sua senha:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            senha = new String(System.console().readPassword());
            System.out.println();

            count--;

        } while ((!professor.autentica(senha)) && (count.intValue() > 0));

        try {
            
            professor.criaTurma(senha);

        } catch (Exception e) {

            System.err.println(e.getMessage());
            return Boolean.FALSE;

        }

        return Boolean.TRUE;

    }

    /**
     * Método que cria um menu para procurar um determinado {@code Boletim}
     * de um {@code Aluno} da própria matéria do {@code Professor}
     * ou de outro
     * 
     * @return {@value Boletim} caso ele seja encontrado;
     *          {@value null} caso o boletim não seja encontrado.
     * @see Boletim
     * @see Professor
     * @see Integer
     * @see Aluno
     * @see Boletim
     * @see String
     * @see Exception
     */
    private Boletim procuraBoletim (Professor professor) {
        
        Integer opcao = null;
        Aluno aluno = null;
        Boletim boletimAluno = null;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Procurar aluno por nome");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%1s", "2.Procurar aluno por ID");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%1s", "3.Voltar");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar aluno por nome.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        aluno = this.retornaAluno(professor);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }

                    if (aluno == null) {

                        System.err.println("\nNenhum aluno encontrado.");
                        break;

                    }

                    try {
                        
                        boletimAluno = this.retornaBoletim(professor, aluno);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    if (boletimAluno == null) {

                        System.err.println("\nNenhum boletim seu encontrado.");
                        break;

                    }
                    
                return boletimAluno;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar aluno por ID.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        aluno = this.retornaAlunoID(professor);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }

                    if (aluno == null) {

                        System.err.println("\nNenhum aluno encontrado.");
                        break;

                    }

                    try {
                        
                        boletimAluno = this.retornaBoletim(professor, aluno);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    if (boletimAluno == null) {

                        System.err.println("\nNenhum boletim encontrado.");
                        break;

                    }
                    
                return boletimAluno;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Voltando...");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                return null;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|\n", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }

        } while (opcao.intValue() != 3);

        return null;

    }

    /**
     * Método auxiliar para procurar e retornar um {@code Boletim} de
     * um determinado {@code Aluno}.
     * 
     * @param professor
     * @param aluno
     * @return {@value Boletim} caso seja encontrado;
     *          {@value null} caso não seja encontrado.
     * @see Boletim
     * @see Professor
     * @see Aluno
     * @see Integer
     * @see Exception
     * @see String
     */
    private Boletim retornaBoletim (Professor professor, Aluno aluno) {

        Integer subOpcao = Integer.valueOf(this.subMenuBoletim());
        Boletim boletimAluno = null;

        switch (subOpcao.intValue()) {

            case 1:
                
                System.out.printf("|Sua escolha: %-67s|\n", "Procurar pelo boletim da própria matéria.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");

                try {
                    
                    boletimAluno = professor.procuraBoletim(professor.getMateria(), aluno);

                } catch (Exception e) {
                    
                    System.err.println(e.getMessage());

                }

                if (boletimAluno != null) return boletimAluno;

            break;

            case 2:

                System.out.printf("|Sua escolha: %-67s|\n", "Procurar pelo boletim de outra matéria.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");

                String materia = this.retornarMateria();

                if (materia == null) break;

                try {
                    
                    boletimAluno = professor.procuraBoletim(materia, aluno);

                } catch (Exception e) {
                    
                    System.out.println(e.getMessage());
                    
                }

                if (boletimAluno != null) return boletimAluno;

            break;

            case 3:

                System.err.printf("|Sua escolha: %-67s|\n", "Cancelar.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");

            return null;
        
            default:

                System.err.println(String.format("|Sua escolha: %-67s|\n", "Nenhuma das existentes."));
                System.out.println("|" + repeteCaracter('-', 80) + "|");

            break;

        }

        return boletimAluno;

    }

    /**
     * Método auxiliar para procura de {@code Boletim}.
     * 
     * @return {@value Integer} contendo a opção do usuário.
     * @see Integer
     * @see String
     */
    private Integer subMenuBoletim () {

        System.out.println();
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        String opcao1 = String.format("|%20s", "1.Procurar pelo boletim da própria matéria");
        System.out.printf("%-81s|\n", opcao1);
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        String opcao2 = String.format("|%20s", "2.Procurar pelo boletim de outra matéria");
        System.out.printf("%-81s|\n", opcao2);
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        String opcao3 = String.format("|%10s", "3.Cancelar");
        System.out.printf("%-81s|\n", opcao3);
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%1s", "> ");
        Integer subOpcao = Integer.valueOf(scan.nextInt());
        System.out.println("|" + repeteCaracter('-', 80) + "|");

        return subOpcao;

    }

    /**
     * Método que procura e retorna uma determinada
     * {@code Materia} inserida pelo {@code Usuario}
     * 
     * @return {@value Materia} em formato de {@value String} caso ela exista;
     *          {@value null} caso não seja encontrada.
     * @see String
     * @see Boolean
     * @see Integer
     * @see InputStream
     * @see InputStreamReader
     * @see BufferedReader
     * @see Exception
     * @see Materia
     */
    private String retornarMateria () {

        Boolean condition = Boolean.TRUE;
        Integer count = Integer.valueOf(0);

        do {

            System.out.println();

            if (count.intValue() > 0) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Nenhuma matéria com esse nome foi encontrada.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");

            }

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite a matéria (ENTER para voltar):");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            String materia = "";

            try {

                InputStream fis = System.in;
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                materia = br.readLine();

            } catch (Exception e) {
            
                System.err.println(e.getMessage());
                
            }

            System.out.println("|" + repeteCaracter('-', 80) + "|");

            Materia[] materias = Materia.values();

            if (materia.isBlank()) break;

            for (Materia m : materias) {

                if (m.toString().equalsIgnoreCase(materia) || m.name().equalsIgnoreCase(materia)) {

                    condition = Boolean.TRUE;
                    return materia = m.toString();

                }

            }

            if (count.intValue() == 0) count++;

        } while (condition != Boolean.TRUE);

        return null;

    }

    /**
     * Método para procurar uma determinada {@code Turma}
     * do {@code Professor}.
     * 
     * @param professor
     * @return {@value Turma} caso seja encontrado;
     *          {@value null} caso não seja encontrada.
     * @see Turma
     * @see Professor
     * @see Boolean
     * @see Integer
     * @see String
     * @see InputStream
     * @see InputStreamReader
     * @see BufferedReader
     * @see Exception
     */
    private Turma retornaTurma (Professor professor) {

        System.out.println();

        Boolean condition = Boolean.TRUE;
        Integer count = Integer.valueOf(0);

        do {

            if (count.intValue() > 0) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Nenhuma turma com esse ID foi encontrada.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");

            }

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite o ID (ENTER para voltar):");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            String id = "";

            try {

                InputStream fis = System.in;
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                id = br.readLine();

            } catch (Exception e) {
            
                System.err.println(e.getMessage());
                
            }

            System.out.println("|" + repeteCaracter('-', 80) + "|");

            if (id.isBlank()) break;

            Turma turma = null;

            try {
                
                turma = professor.getTurma(id);

            } catch (Exception e) {
                
                System.err.println(e.getMessage());
                
            }

            if ((count.intValue() == 0) && (turma == null)) count++;
            else return turma;

        } while (condition != Boolean.TRUE);

        return null;


    }

    /**
     * Método para procura de {@code Aluno} por nome.
     * 
     * @param professor
     * @return {@value Aluno} caso seja encontrado;
     *          {@value null} caso não seja encontrado.
     * @see Aluno
     * @see Professor
     * @see String
     * @see List
     * @see Exception
     * @see Integer
     */
    private Aluno retornaAluno (Professor professor) {

        Aluno aluno = null;

        System.out.println();
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%-80s|\n", "Digite o nome do aluno:");
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%1s", "> ");
        String alunoNome = scan.next();
        String complementar = scan.nextLine();
        alunoNome = alunoNome + complementar;
        System.out.println("|" + repeteCaracter('-', 80) + "|");

        List<Aluno> alunosRetornados = null;

        try {

            alunosRetornados = professor.procuraAluno(alunoNome);
            
        } catch (Exception e) {
            
            System.err.println(e.getMessage());
            
        }

        if ((!alunosRetornados.isEmpty()) && (alunosRetornados != null)) {

            for (int i = 0; i < alunosRetornados.size(); i++) {

                System.out.println();
                alunosRetornados.get(i).login(professor, alunosRetornados.get(i));
                System.out.println((i + 1) + "." + alunosRetornados.get(i));
                alunosRetornados.get(i).logout();

            }

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite o número da posição do aluno:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            Integer alunoPos = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            aluno = alunosRetornados.get(alunoPos - 1);

            if (aluno != null) return aluno;

        }

        return null;

    }

    /**
     * Método para procura de {@code Aluno} por nome.
     * 
     * @param professor
     * @return {@value Aluno} caso seja encontrado;
     *          {@value null} caso não seja encontrado.
     * @see Aluno
     * @see Professor
     * @see String
     * @see Exception
     */
    private Aluno retornaAlunoID (Professor professor) {

        Aluno aluno = null;

        System.out.println();
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%-80s|\n", "Digite o ID do aluno:");
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%1s", "> ");
        String alunoID = scan.next();
        String complementar = scan.nextLine();
        alunoID = alunoID + complementar;
        System.out.println("|" + repeteCaracter('-', 80) + "|");

        alunoID.replaceAll("^[ !#$%^&*=+-]&", "");

        try {

            aluno = professor.procuraAlunoID(alunoID);
            
        } catch (Exception e) {
            
            System.err.println(e.getMessage());
            
        }

        return aluno;

    }

    /**
     * Método auxiliar para inserção de um {@code Aluno} em uma
     * {@code Turma} existente ou criar um nova e inseri-lo lá.
     * 
     * @param aluno
     * @param professor
     * @see Aluno
     * @see Professor
     * @see Integer
     * @see String
     * @see Turma
     * @see Exception
     */
    protected void registraAluno (Aluno aluno, Professor professor) {

        Integer opcao;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Inserir o novo aluno em uma nova turma");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%20s", "2.Inserir o aluno novo em uma turma existente");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%8s", "3.Cancelar");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            if (opcao.intValue() == -1) opcao = Integer.valueOf(0);

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Criar um novo aluno e uma nova turma.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        for (int i = 3; i > 0; i--) {
                        
                            System.out.println();
                            System.out.println("|" + repeteCaracter('-', 80) + "|");
                            System.out.printf("|%-80s|\n", "Digite sua senha de professor:");
                            System.out.println("|" + repeteCaracter('-', 80) + "|");
                            System.out.printf("|%1s", "> ");
                            String senha = new String(System.console().readPassword());
                            System.out.println("|" + repeteCaracter('-', 80) + "|");
                            Turma turmaNova = null;

                            try {
                                
                                turmaNova = professor.criaTurma(senha);
                                turmaNova.adicionarAluno(aluno);
                                System.out.println();
                                System.out.println("|" + repeteCaracter('-', 80) + "|");
                                System.out.printf("|%-80s|\n", "Aluno criado com sucesso dentro da turma de id: " + turmaNova.getId());
                                System.out.println("|" + repeteCaracter('-', 80) + "|");
                                break;

                            } catch (Exception e) {
                                
                                System.err.println(e.getMessage());
                                System.out.println("\nNúmeros restantes de tentativas: " + i);

                            }

                        }

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    try {
                        
                        professor.logout();

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    opcao = Integer.valueOf(-1);

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Criar um aluno e inseri-lo em uma turma existente.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    Turma turmaNova = null;

                    try {

                        String id = this.getTurmaId(professor);
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        turmaNova = professor.getTurma(id);
                        turmaNova.adicionarAluno(aluno);

                        System.out.println();
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        System.out.printf("|%-80s|\n", "Aluno criado com sucesso dentro da turma de id: " + turmaNova.getId());
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        
                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    try {
                        
                        professor.logout();

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    opcao = Integer.valueOf(-1);

                break;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Cancelar.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {
                        
                        professor.apagaUsuario(professor, aluno.getNomeUsuario(professor));

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    try {
                        
                        professor.logout();

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                break;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|\n", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }

        } while ((opcao.intValue() != 3) && (opcao.intValue() != -1));

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
    protected String repeteCaracter (char c, Integer tamanho) {

        char[] caracteres = new char[tamanho.intValue()];
        Arrays.fill(caracteres, c);
        return new String(caracteres);

    }

    /**
     * Método auxiliar que cria um sub-menu
     * para solicitar o ID da turma ao {@code Usuario}
     * e retorna esse ID em formato de {@code String}
     * 
     * @param professor
     * @return {@value ID digitado}
     * @see String
     * @see Professor
     */
    private String getTurmaId (Professor professor) {

        System.out.println();
        System.out.println(professor.getTurmas().values());
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
