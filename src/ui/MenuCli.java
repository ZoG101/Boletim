package ui;

import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.DataHelper;
import models.Aluno;
import models.Materia;
import models.Nivel;
import models.Professor;

public class MenuCli extends UserCli {

    private Scanner scan;
    private Charset charset;
    
    public MenuCli () {

        charset = Charset.defaultCharset();
        scan = new Scanner(System.in, charset);

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

                    try {
                        
                        this.cadastroProfessor();

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());
                        e.printStackTrace();

                    } finally {

                        opcao = Integer.valueOf(menuInicialComLogin());

                    }
                    

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Cadastrar Aluno.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {
                        
                        Professor professorLogado = this.loginProfessor();
                        Aluno aluno = this.cadastroAluno();
                        this.registraAluno(aluno, professorLogado);

                    } catch (Exception e) {
                       
                        System.err.println(e.getMessage());
                        e.printStackTrace();

                    } finally {

                        opcao = Integer.valueOf(menuInicialComLogin());

                    }

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

    public Integer menuInicialComLogin () {

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

                    try {

                        this.cadastroProfessor();
                        
                    } catch (Exception e) {
                       
                        System.err.println(e.getMessage());
                        e.printStackTrace();

                    }

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Cadastrar Aluno.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {
                        
                        Professor professorLogado = this.loginProfessor();
                        Aluno aluno = this.cadastroAluno();
                        this.registraAluno(aluno, professorLogado);

                    } catch (Exception e) {
                       
                        System.err.println(e.getMessage());
                        e.printStackTrace();

                    } finally {

                        opcao = Integer.valueOf(menuInicialComLogin());

                    }

                break;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Logar como aluno.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {
                        
                        Aluno alunoLogado = this.loginAluno();
                        super.alunoLogado(alunoLogado);

                    } catch (Exception e) {
                       
                        System.err.println(e.getMessage());
                        e.printStackTrace();

                    }

                break;

                case 4:

                    System.out.printf("|Sua escolha: %-67s|\n", "Logar como professor.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {
                        
                        Professor professorLogado = this.loginProfessor();

                    } catch (Exception e) {
                       
                        System.err.println(e.getMessage());
                        e.printStackTrace();

                    }

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

        return 3;

    }

    private void cadastroProfessor () {

        String opcao;
        String nomeCompleto;
        String primeiroNome;
        String sobrenome;
        String cpf;
        String telefone;
        String email;
        String nomeUsuario;
        String senha;
        String materia;
        String complementar;
        Boolean confirmaSenha = Boolean.FALSE;

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

            if ((!confirma.find()) || (nomeCompleto.length() < 4)) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Digite APENAS o seu primeiro nome:");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                primeiroNome = scan.next();

            } else {

                primeiroNome = confirma.group(1);

            }
            
            formato = Pattern.compile(sobrenomeRegex, Pattern.MULTILINE);
            confirma = formato.matcher(nomeCompleto);

            if (!confirma.find()) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Digite APENAS o seu último nome:");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                sobrenome = scan.next();

            } else {

                sobrenome = confirma.group(1);

            }

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

            cpf = DataHelper.formataCPF(cpf);

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

            telefone = DataHelper.formataTelefone(telefone);

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

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite seu nome de usuário:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            nomeUsuario = scan.next();
            complementar = scan.nextLine();
            nomeUsuario = nomeUsuario + complementar;

            while (!DataHelper.verificaUsuário(nomeUsuario)) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|ATENÇÃO: %-71s|\n", "NOME DE USUÁRIO INVÁLIDO! TENTE NOVAMENTE.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                nomeUsuario = scan.next();
                complementar = scan.nextLine();
                nomeUsuario = nomeUsuario + complementar;

            }

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

            if (senha.compareTo(confirmacao) == 0) confirmaSenha = Boolean.TRUE;

            while ((!DataHelper.verificaSenha(senha)) || (!confirmaSenha)) {

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

                if (senha.compareTo(confirmacao) == 0) confirmaSenha = Boolean.TRUE;

            }
            
            Boolean condition = Boolean.FALSE;

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite a matéria:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            materia = scan.next();
            complementar = scan.nextLine();
            materia = materia + complementar;
            Materia[] materias = Materia.values();

            for (Materia m : materias) {

                if (m.toString().equalsIgnoreCase(materia) || m.name().equalsIgnoreCase(materia)) {

                    condition = Boolean.TRUE;
                    materia = m.toString();
                    break;

                }

            }

            while (!condition) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|ATENÇÃO: %-71s|\n", "Matéria inválida! Tente novamente:");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                materia = scan.next();
                complementar = scan.nextLine();
                materia = materia + complementar;
                
                for (Materia m : materias) {

                    if (m.toString().equalsIgnoreCase(materia) || m.name().equalsIgnoreCase(materia)) {

                        condition = Boolean.TRUE;
                        materia = m.toString();
                        break;

                    }

                }

            }

            System.out.println("|" + repeteCaracter('-', 80) + "|");

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|ATENÇÃO: %-71s|\n", "CONFIRME AS INFORMAÇÕES ABAIXO:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Nome completo: %-65s|\n", nomeCompleto);
            System.out.printf("|Primeiro nome: %-65s|\n", primeiroNome);
            System.out.printf("|Sobrenome: %-69s|\n", sobrenome);
            System.out.printf("|CPF: %-75s|\n", DataHelper.reformataCPF(cpf));
            System.out.printf("|Telefone: %-70s|\n", telefone);
            System.out.printf("|E-mail: %-72s|\n", email);
            System.out.printf("|Nome de usuário: %-63s|\n", nomeUsuario);
            String senhaCensurada = new String(repeteCaracter('*', senha.length()));
            System.out.printf("|Senha: %-73s|\n", senhaCensurada);
            System.out.printf("|Matéria: %-71s|\n", materia);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "As informações acima estão corretas? (S/N)");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            
            System.out.printf("|%1s", "> ");
            opcao = scan.next();

        } while ((opcao.compareToIgnoreCase("n") == 0) || (opcao.compareToIgnoreCase("não") == 0) || (opcao.compareToIgnoreCase("nao") == 0));

        Professor p = new Professor(nomeCompleto, primeiroNome, sobrenome, cpf, telefone, email, nomeUsuario, senha, materia);

        System.out.println(p.toString());

    }

    private Aluno cadastroAluno () {

        String opcao;
        String nomeCompleto;
        String primeiroNome;
        String sobrenome;
        String cpf;
        String telefone;
        String email;
        String nomeUsuario;
        String senha;
        String nivel;
        String complementar;
        Boolean confirmaSenha = Boolean.FALSE;

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

            if ((!confirma.find()) || (nomeCompleto.length() < 4)) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Digite APENAS o seu primeiro nome:");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                primeiroNome = scan.next();

            } else {

                primeiroNome = confirma.group(1);

            }
            
            formato = Pattern.compile(sobrenomeRegex, Pattern.MULTILINE);
            confirma = formato.matcher(nomeCompleto);

            if (!confirma.find()) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Digite APENAS o seu último nome:");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                sobrenome = scan.next();

            } else {

                sobrenome = confirma.group(1);

            }

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

            cpf = DataHelper.formataCPF(cpf);

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

            telefone = DataHelper.formataTelefone(telefone);

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

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite seu nome de usuário:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            nomeUsuario = scan.next();
            complementar = scan.nextLine();
            nomeUsuario = nomeUsuario + complementar;

            while (!DataHelper.verificaUsuário(nomeUsuario)) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|ATENÇÃO: %-71s|\n", "NOME DE USUÁRIO INVÁLIDO! TENTE NOVAMENTE.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                nomeUsuario = scan.next();
                complementar = scan.nextLine();
                nomeUsuario = nomeUsuario + complementar;

            }

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

            if (senha.compareTo(confirmacao) == 0) confirmaSenha = Boolean.TRUE;

            while ((!DataHelper.verificaSenha(senha)) || (!confirmaSenha)) {

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

                if (senha.compareTo(confirmacao) == 0) confirmaSenha = Boolean.TRUE;

            }
            
            Boolean condition = Boolean.FALSE;

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite o nível escolar:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "(Graduação/Pós-Graduação)");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            nivel = scan.next();
            complementar = scan.nextLine();
            nivel = nivel + complementar;
            Nivel[] niveis = Nivel.values();

            for (Nivel n : niveis) {

                if (n.toString().equalsIgnoreCase(nivel) || n.name().equalsIgnoreCase(nivel)) {

                    condition = Boolean.TRUE;
                    nivel = n.toString();
                    break;

                }

            }

            

            while (!condition) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|ATENÇÃO: %-71s|\n", "Nível inválido! Tente novamente:");
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%1s", "> ");
                nivel = scan.next();
                complementar = scan.nextLine();
                nivel = nivel + complementar;
                
                for (Nivel n : niveis) {

                    if (n.toString().equalsIgnoreCase(nivel) || n.name().equalsIgnoreCase(nivel)) {

                        condition = Boolean.TRUE;
                        nivel = n.toString();
                        break;

                    }

                }

            }

            System.out.println("|" + repeteCaracter('-', 80) + "|");

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|ATENÇÃO: %-71s|\n", "CONFIRME AS INFORMAÇÕES ABAIXO:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|Nome completo: %-65s|\n", nomeCompleto);
            System.out.printf("|Primeiro nome: %-65s|\n", primeiroNome);
            System.out.printf("|Sobrenome: %-69s|\n", sobrenome);
            System.out.printf("|CPF: %-75s|\n", DataHelper.reformataCPF(cpf));
            System.out.printf("|Telefone: %-70s|\n", telefone);
            System.out.printf("|E-mail: %-72s|\n", email);
            System.out.printf("|Nome de usuário: %-63s|\n", nomeUsuario);
            String senhaCensurada = new String(repeteCaracter('*', senha.length()));
            System.out.printf("|Senha: %-73s|\n", senhaCensurada);
            System.out.printf("|Nível Escolar: %-71s|\n", nivel);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "As informações acima estão corretas? (S/N)");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            
            System.out.printf("|%1s", "> ");
            opcao = scan.next();

        } while ((opcao.compareToIgnoreCase("n") == 0) || (opcao.compareToIgnoreCase("não") == 0) || (opcao.compareToIgnoreCase("nao") == 0));

        Aluno a = new Aluno(nomeCompleto, primeiroNome, sobrenome, cpf, telefone, email, nomeUsuario, senha, nivel);
        System.out.println(a.toString());
        return a;

    }

    private Aluno loginAluno () {

        Aluno aluno = null;
        String opcao;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite seu nome de usuário:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            String nomeUsuario = scan.next();
            String complementar = scan.nextLine();
            nomeUsuario = nomeUsuario + complementar;

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite sua senha:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            String senha = scan.next();
            complementar = scan.nextLine();
            senha = senha + complementar;

            try {

                aluno = (Aluno) Aluno.login(nomeUsuario, senha);

            } catch (Exception e) {
                
                System.err.println(e.getMessage());
                aluno = null;

            } finally {

                if (aluno == null) {

                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.printf("|%-80s|\n", "Deseja tentar novamente? (S/N)");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.printf("|%1s", "> ");
                    opcao = scan.next();
                    complementar = scan.nextLine();
                    opcao = opcao + complementar;

                } else {

                    opcao = "N";

                }

            }

        } while ((aluno == null) && (!opcao.equalsIgnoreCase("n")) || (!opcao.equalsIgnoreCase("não") || (!opcao.equalsIgnoreCase("nao"))));
        
        return aluno;

    }

    private Professor loginProfessor () {

        Professor professor = null;
        String opcao;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite seu nome de usuário:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            String nomeUsuario = scan.next();
            String complementar = scan.nextLine();
            nomeUsuario = nomeUsuario + complementar;

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite sua senha:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            String senha = scan.next();
            complementar = scan.nextLine();
            senha = senha + complementar;

            try {

                professor = (Professor) Aluno.login(nomeUsuario, senha);

            } catch (Exception e) {
                
                System.err.println(e.getMessage());
                professor = null;

            } finally {

                if (professor == null) {

                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.printf("|%-80s|\n", "Deseja tentar novamente? (S/N)");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.printf("|%1s", "> ");
                    opcao = scan.next();
                    complementar = scan.nextLine();
                    opcao = opcao + complementar;

                } else {

                    opcao = "N";

                }

            }

        } while ((professor == null) && (!opcao.equalsIgnoreCase("n")) || (!opcao.equalsIgnoreCase("não") || (!opcao.equalsIgnoreCase("nao"))));
        
        return professor;

    }
    
}
