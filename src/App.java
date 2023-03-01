import java.util.Scanner;

import models.Aluno;
import models.Boletim;
import models.Professor;
import security.Token;

public class App {

    public static void main(String[] args) {

        Professor professor = new Professor("Marcos", "Matemática", "asdfS1", "Zinc");
        Aluno aluno = new Aluno("Davi Campolina", "POSGRADUACAO");
        Boletim nota = new Boletim(aluno, professor);

        nota.setFalta();

        System.out.println(nota.getFalta());

        nota.setNota(9, 6);

        System.out.println(nota.getNotas());

        nota.gerarBoletim();

        System.out.println(nota.getUltimaNotaAdicionada());

        Token token = new Token();

        try (Scanner scan = new Scanner(System.in)) {

            Integer tokenDigitado = Integer.valueOf(scan.nextInt());

            professor.solicitaNovaSenha(token, tokenDigitado);

            System.out.println("A senha deve conter entre 6 a 12 caracteres, deve conter pelo menos uma letra maiúscula, um número e não deve conter símbolos.");

            String novaSenha = scan.next();

            professor.redefinirSenha(novaSenha);
            
        } catch (Exception e) {
            
            System.err.println(e.getMessage());
            e.printStackTrace();

        }
        
    }

}
