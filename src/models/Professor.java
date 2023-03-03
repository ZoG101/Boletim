package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A classe {@code Professor} é feita como uma abstração
 * de um professor real que possui seus {@code Alunos} e 
 * contém suas informações básicas e de maior interesse.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.3
 * @see Usuario
 * @see Aluno
 * @see Turma
 */
public class Professor extends Usuario {

    private static final long serialVersionUID = 1L;

    private String materia;
    private List<Turma> turmas = new ArrayList<Turma>();

    /**
     * Construtor da classe para instânciar o objeto {@code Professor}
     * da forma adequada recebendo {@code nome} {@code materia}.
     * 
     * @param nome
     * @param materia
     * @param senha
     * @param usuario
     * @see String
     * @see Boolean
     */
    public Professor (String nome, String sobrenome, String cpf, String telefone, String email, String usuario, String senha, String materia) {

        super(nome, sobrenome, cpf, telefone, email, usuario, senha);
        this.materia = materia;

    }

    /**
     * Retorna uma {@code String} contendo a matéria de atuação
     * do respectivo professor.
     * 
     * @return {@value materia}
     * @see String
     */
    public String getMateria () {

        return materia;

    }

    /**
     * Retorna uma {@code List} contendo as turmas do professor.
     * 
     * @return {@value turmas}
     * @see List
     * @see Collections
     */
    public List<Turma> getTurmas () {

        return Collections.unmodifiableList(turmas);

    }
    
}
