package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A classe {@code Professor} é feita como uma abstração
 * de um professor real que possui seus {@code Alunos} e 
 * contém suas informações básicas e de maior interesse.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.0
 * @see Aluno
 * @see Turma
 * @see Serializable
 */
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String materia;
    private String nome;
    private List<Turma> turmas = new ArrayList<Turma>();

    /**
     * Construtor da classe para instânciar o objeto {@code Professor}
     * da forma adequada recebendo {@code nome} {@code materia}.
     * 
     * @param nome
     * @param materia
     * @see String
     */
    public Professor (String nome, String materia) {

        this.materia = materia;
        this.nome = nome;

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
     * Retorna uma {@code String} contendo o nome do professor.
     * 
     * @return {@value nome}
     * @see String
     */
    public String getNome () {

        return nome;

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
