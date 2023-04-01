//import java.nio.charset.Charset;
import java.nio.charset.Charset;
import java.util.Scanner;

import models.Aluno;
import models.Boletim;
import models.Professor;
import models.Turma;
import security.Token;
import ui.Cli;

public class App {

    public static void main (String[] args) {

        Professor professor = new Professor("Marcos", "Filho","28001238938", "(22) 2222-2222", null, "Zinc", "asdfS1", "Matemática");
        Aluno aluno = new Aluno("Davi", "Campolina", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "Blab", "Zasp23", "POSGRADUACAO");
        Aluno aluno2 = new Aluno("zezé", "Bota", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "fdsfs", "dfdS2f", "POSGRADUACAO");
        Aluno aluno3 = new Aluno("Lucas", "Morato", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "Bvcxczvlab", "xcD4vs", "POSGRADUACAO");
        Aluno aluno4 = new Aluno("Augusto", "Pinto", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "vcxvz", "Zasp23", "POSGRADUACAO");
        Aluno aluno5 = new Aluno("Lara", "Moraes", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "ascvav", "Zasp23", "POSGRADUACAO");
        Aluno aluno6 = new Aluno("Santiago", "Matos", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "Blascvasab", "Zasp23", "POSGRADUACAO");
        Aluno aluno7 = new Aluno("Léo", "Soares", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "savsvavas", "Zasp23", "POSGRADUACAO");
        Aluno aluno8 = new Aluno("Vitor", "Santos", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "Basvvaslab", "Zasp23", "POSGRADUACAO");
        Aluno aluno9 = new Aluno("fulano", "de Tal", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "asvav", "Zasp23", "POSGRADUACAO");
        Aluno aluno10 = new Aluno("Vitória", "Luiza", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "wVCw", "Zasp23", "POSGRADUACAO");
        Aluno aluno11 = new Aluno("Marcos", "Paulo", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "Bavavalab", "Zasp23", "POSGRADUACAO");
        Aluno aluno12 = new Aluno("Trevor", "Silas", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "Bawfvtrblab", "Zasp23", "POSGRADUACAO");
        Aluno aluno13 = new Aluno("Link", "Linkado", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "Blsacvavrvab", "Zasp23", "POSGRADUACAO");
        Aluno aluno14 = new Aluno("Davi", "Campolina", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "Blabdfgsfg", "Zasp23", "POSGRADUACAO");
        Aluno aluno15 = new Aluno("Davi", "zé", "280.012.389-38", "(11) 1111-1111", "blavla@gamil.com", "Blaasdjfncb", "Zasp23", "POSGRADUACAO");
        Boletim nota = new Boletim(aluno, professor);

        nota.setFalta();

        Cli i = new Cli();

        i.menuInicialVazio();
        i.menuIniciaComLogin();

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
        
    }

}
