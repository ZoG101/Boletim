import models.Aluno;
import models.Boletim;
import models.Professor;

public class App {

    public static void main(String[] args) {

        Professor professor = new Professor("Marcos", "Matemática");
        Aluno aluno = new Aluno("Davi Campolina", "POSGRADUACAO");
        Boletim nota = new Boletim(aluno, professor);

        nota.setFalta();

        System.out.println(nota.getFalta());

        nota.setNota(9, 6);

        System.out.println(nota.getNotas());

        nota.gerarBoletim();

        System.out.println(nota.getUltimaNotaAdicionada());
        
    }

}
