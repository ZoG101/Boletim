package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe {@code Aluno} é feita como uma abstração
 * de um aluno real que possui seu {@code Boletim} e 
 * contém suas informações básicas e de maior interesse.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.0
 * @see Professor
 * @see Turma
 * @see Serializable
 */
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Boletim> boletins = new ArrayList<Boletim>();
    private String nome;
    private String nivel;

    /**
     * Construtor do aluno que inicializa o aluno de forma adequada
     * com {@code nome} e {@code nivel} de estudo para uso adequada
     * de outras classes.
     * 
     * @param nome
     * @param nivel
     * @see String
     */
    public Aluno (String nome, String nivel) {

        this.nome = nome;
        this.nivel = nivel;

    }

    /**
     * Retorna o {@code nome} do {@code Aluno}.
     * 
     * @return {@value nome}
     */
    public String getNome () {

        return this.nome;

    }

    /**
     * Retorna o {@code nivel} do {@code Aluno}.
     * 
     * @return {@value nivel}
     */
    public String getNivel () {

        return this.nivel;

    }

    /**
     * Cria um novo {@code Boletim} para o {@code Aluno}, mas recebe uma 
     * instância de {@code Professor} para que tenha a permissão adequada
     * para criar o {@code Boletim}.
     * 
     * @param professor
     * @see Professor
     * @see Boletim
     */
    public void criarBoletim (Professor professor) {

        Boletim a = new Boletim(this, professor);
        this.adicionarBoletim(a);

    }

    /**
     * Método auxiliar privado que adiciona o {@code Boletim} criado
     * na coleção de boletins do {@code Aluno}.
     * 
     * @param boletim
     * @see Boletim
     */
    private void adicionarBoletim (Boletim boletim) {

        boletins.add(boletim);

    }
    
}
