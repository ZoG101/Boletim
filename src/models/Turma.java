package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A classe {@code Turma} é feita para gerenciar
 * as coleções de {@code Aluno}, e é gerenciado pelo
 * professor que possui estas turmas.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.0
 * @see Aluno
 * @see Professor
 * @see Boletim
 * @see Serializable
 */
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private List<Aluno> alunos = new ArrayList<Aluno>();

    /**
     * Construtor da classe {@code Turma} que recebe um parâmetro
     * com o número da turma que a identifica.
     * 
     * @param id
     */
    public Turma (String id) {

        this.id = id;

    }

    /**
     * Construtor da classe {@code Turma} que recebe um parâmetro
     * com o número que a identifica e uma lista pronta de {@code Aluno} 
     * para aquela turma.
     * 
     * @param id
     * @param alunos
     * @see List
     */
    public Turma (String id, List<Aluno> alunos) {

        this.id = id;
        this.alunos = alunos;

    }

    public Turma () {



    }

    /**
     * Retorna uma lista de alunos pertencentes aquela
     * determinada turma.
     * 
     * @return {@value Alunos}
     */
    public List<Aluno> getAlunos () {

        return Collections.unmodifiableList(alunos);

    }

    /**
     * Retorna o número que identifica a turma.
     * 
     * @return {@value num}
     */
    public String getId () {

        return this.id;

    }

    /**
     * Recebe um {@code Aluno} e o adiciona a 
     * coleção de alunos da determinada turma.
     * 
     * @param aluno
     */
    public void adicionarAluno (Aluno aluno) {

        this.alunos.add(aluno);

    }
    
}
