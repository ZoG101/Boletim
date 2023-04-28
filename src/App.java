//import java.nio.charset.Charset;

/*
import java.nio.charset.Charset;
import java.util.Scanner;

import models.Aluno;
import models.Boletim;
import models.Professor;
import models.Turma;
import security.Token;
 */

import models.Aluno;
import models.Professor;
import models.Turma;
import ui.MenuCli;

public class App {

    public static void main (String[] args) {

        Professor professor = new Professor("MARCOS FILHO", "MARCOS", "FILHO","28001238938", "(22) 2222-2222", null, "Zinc", "asdfS1", "Matemática");
        Aluno aluno = new Aluno("DAVI CAMPOLINA", "DAVI", "CAMPOLINA", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "Blab", "Zasp23", "PÓS-GRADUAÇÃO");
        Professor.login("Zinc", "asdfS1");
        Turma turmaNova = professor.criaTurma("asdfS1");
        turmaNova.adicionarAluno(aluno);
        professor.criaBoletim(aluno);
        professor.logout();
        

        //nota.setFalta();

        MenuCli i = new MenuCli();

        i.menuInicialVazio();

        /* 
        System.out.println(nota.getFalta());

        nota.setNota(9, 6);

        System.out.println(nota.getNotas());

        nota.gerarBoletim();

        System.out.println(nota.getUltimaNotaAdicionada());

        Token token = new Token();

        Charset charset = Charset.defaultCharset();

        try (Scanner scan = new Scanner(System.in, charset)) {

            System.out.println("\nDigite seu usuário:");
            String logUsuario = scan.next();

            System.out.println("\nDigite sua senha:");
            String logSenha = scan.next();

            System.out.println(professor.autentica(logUsuario, logSenha));

            professor.criaTurma(logSenha);
            professor.criaTurma(logSenha);
            professor.criaTurma(logSenha);
            professor.criaTurma(logSenha);
            professor.criaTurma(logSenha);
            professor.criaTurma(logSenha);
            professor.criaTurma(logSenha);
            professor.criaTurma(logSenha);
            professor.criaTurma(logSenha);
            professor.criaTurma(logSenha);
            professor.criaTurma(logSenha);

            System.out.println("Digite o id da turma:");
            String id = scan.next();

            System.out.println(professor.getTurma(id));
            Turma a = professor.getTurma(id);
            a.adicionarAluno(aluno, aluno2, aluno3, aluno4, aluno5, aluno6, aluno7, aluno8, aluno9, aluno10, aluno11, aluno12, aluno13);

            System.out.println("Digite o id da turma:");
            id = scan.next();
            a = professor.getTurma(id);
            a.adicionarAluno(aluno15, aluno2, aluno3, aluno3, aluno4, aluno5, aluno6, aluno7, aluno8, aluno9, aluno10, aluno11, aluno12, aluno13, aluno14);

            System.out.println("\nDigite o nome do aluno");
            String zeze = scan.next();
            String nome = scan.nextLine();
            System.out.println(professor.procuraAluno(zeze + nome));

            System.out.println("\nDigite o id do aluno");
            String alunoID = scan.next();
            System.out.println(professor.procuraAlunoID(alunoID));

            System.out.println("\nDigite seu novo usuário: ");
            String novoUsuario = scan.next();

            professor.mudarUsuario(novoUsuario);

            Integer tokenDigitado = Integer.valueOf(scan.nextInt());

            professor.solicitaNovaSenha(token, tokenDigitado);

            System.out.println("\nA senha deve conter entre 6 a 12 caracteres, deve conter pelo menos uma letra maiúscula, um número e não deve conter símbolos.");

            String novaSenha = scan.next();

            professor.redefinirSenha(novaSenha);
            
        } catch (Exception e) {
            
            System.err.println(e.getMessage());
            e.printStackTrace();

        }

        */
        
    }

}
