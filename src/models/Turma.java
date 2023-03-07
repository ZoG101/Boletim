package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import data.DataHelper;

/**
 * A classe {@code Turma} é feita para gerenciar
 * as coleções de {@code Aluno}, e é gerenciado pelo
 * professor que possui estas turmas.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.1
 * @see Aluno
 * @see Professor
 * @see Boletim
 * @see Serializable
 */
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private List<Aluno> alunos = new ArrayList<Aluno>();
    private static List<String> IDs = new ArrayList<String>();

    /**
     * Construtor da classe {@code Turma} que chama um método que gera 
     * automaticamente um parâmetro com o número da turma que a identifica.
     * 
     */
    public Turma () {

        this.id = this.setID();

    }

    /**
     * Construtor da classe {@code Turma} que chama um método que gera 
     * automaticamente um parâmetro com o número da turma que a identifica 
     * e uma lista pronta de {@code Aluno} para aquela turma.
     * 
     * @param alunos
     * @see List
     */
    public Turma (List<Aluno> alunos) {

        this.alunos = alunos;
        this.id = this.setID();

    }

    /**
     * Método privado que cuida de criar um {@code id}, formatá-lo e adicionar 
     * na lista de turmas.
     * 
     * @return {@value resultado}
     * @see Random
     * @see String
     */
    private String setID () {

        Random aleatorio = new Random();
        String ID = "" + aleatorio.nextInt(9) + aleatorio.nextInt(9) + aleatorio.nextInt(9) + aleatorio.nextInt(9);

        while (verificaID(ID)) {

            ID = "" + aleatorio.nextInt(9) + aleatorio.nextInt(9) + aleatorio.nextInt(9) + aleatorio.nextInt(9);

        }

        this.adicionaID(ID);

        return ID;

    }

    private Boolean verificaID (String id) {

        if (Turma.IDs.isEmpty()) return Boolean.FALSE; 

        return DataHelper.procuraBoolean(Turma.IDs, id, 0, Turma.IDs.size());

    }

    private void adicionaID (String id) {

        Turma.IDs.add(id);

        DataHelper.ordena(Turma.IDs, 0, (Turma.IDs.size() - 1));

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
