import java.util.Scanner;

import models.Aluno;
import models.Boletim;
import models.Professor;
import security.Token;

public class App {

    public static void main(String[] args) {

        Professor professor = new Professor("Marcos", "Matemática", "asdfS1");
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

            professor.redefinirSenha(token, tokenDigitado, "Agdfer2");
            
        } catch (Exception e) {
            
            System.err.println(e.getMessage());
            e.printStackTrace();

        }
        
    }

}
