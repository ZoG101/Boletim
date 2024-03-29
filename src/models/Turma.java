package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.RejectedExecutionException;

import data.DataHelper;

/**
 * A classe {@code Turma} é feita para gerenciar
 * as coleções de {@code Aluno}, e é gerenciado pelo
 * professor que possui estas turmas.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.3
 * @see Usuario
 * @see Aluno
 * @see Professor
 * @see Boletim
 * @see Serializable
 */
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private List<Aluno> alunos;
    private static List<String> IDs = new ArrayList<String>();

    /**
     * Construtor da classe {@code Turma} que chama um método que gera 
     * automaticamente um parâmetro com o número da turma que a identifica.
     * 
     */
    public Turma () {
        
        alunos = new ArrayList<Aluno>();
        this.id = this.setID();

    }

    /**
     * Construtor da classe {@code Turma} que chama um método que gera 
     * automaticamente um parâmetro com o número da turma que a identifica 
     * e uma lista pronta de {@code Aluno} para aquela turma.
     * 
     * @param alunos
     * @see ArrayList
     */
    public Turma (ArrayList<Aluno> alunos) {

        DataHelper.ordena((this.alunos = alunos), 0, (this.alunos.size() - 1));
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

    /**
     * Método auxiliar privado que verifica se o ID já existe ou não.
     * 
     * @param id
     * @return {@value true} Se o valor for encontrado e já exister na lista;
     *         {@value false} Se a {@code List} estiver vazia ou se o ID ainda não existir.
     * @see Boolean
     * @see String
     * @see Turma
     * @see DataHelper#procuraBoolean(List, Comparable, int, int)
     * @implNote <p>Chama o método estático {@code DataHelper.procuraBoolean(List, Comparable, int, int)} 
     * para fazer a verificação utilizando uma busca binária para tal.</p>
     */
    private Boolean verificaID (String id) {

        if (Turma.IDs.isEmpty()) return Boolean.FALSE; 

        return DataHelper.procuraBoolean(Turma.IDs, id, 0, (Turma.IDs.size() - 1));

    }

    /**
     * Método auxiliar privado que adiciona o ID dentro da lista e já a ordena.
     * 
     * @param id
     * @see String
     * @see Turma
     * @see DataHelper#ordena(List, int, int)
     * @implNote <p>Chama o método estático {@code DataHelper.ordena(List, int, int)}
     * que executa um {@code QuickSort} para ordernar a lista.</p>
     */
    private void adicionaID (String id) {

        Turma.IDs.add(id);

        DataHelper.ordena(Turma.IDs, 0, (Turma.IDs.size() - 1));

    }

    /**
     * Retorna uma lista de alunos pertencentes aquela
     * determinada turma.
     * 
     * @return {@value Alunos}
     * @see List
     * @see Aluno
     * @see Collections
     */
    public List<Aluno> getAlunos () {

        return Collections.unmodifiableList(alunos);

    }

    /**
     * Retorna o número que identifica a turma.
     * 
     * @return {@value num}
     * @see String
     */
    public String getId () {

        return this.id;

    }

    /**
     * Recebe um {@code Aluno} e o adiciona a 
     * coleção de alunos da determinada turma
     * e já a ordena.
     * 
     * @param aluno
     * @see Aluno
     * @see DataHelper#ordena(List, int, int)
     */
    public void adicionarAluno (Aluno... alunos) {

        if (alunos.length < 0 || alunos.length > 30) throw new IllegalArgumentException("\nERRO: O limite de alunos por turma é TRINTA(30)!");
        if (this.alunos.size() == 30) throw new RejectedExecutionException("\nERRO: O limite de alunos para esta turma foi atingido!");

        for (Aluno aluno : alunos) {
            
            if ((aluno != null) && (!this.alunos.contains(aluno))) { 
                
                this.alunos.add(aluno);
                aluno.setTurma(this);

            }

        }

        DataHelper.ordena(this.alunos, 0, (this.alunos.size() - 1));

    }

    /**
     * Método auxiliar que executa a busca de um ou mais alunos 
     * dentro da {@code Turma}.
     * 
     * @param nome
     * @return {@value alunos}
     * @see List
     * @see Aluno
     * @see String
     * @see Boolean
     * @see DataHelper#procuraNome(List, String, int, int)
     * @see DataHelper#procuraSobrenome(List, String, int, int)
     * @see DataHelper#procuraNomeCompleto(List, String, int, int)
     */
    public List<Aluno> procuraAluno(String nome) {

        List<Aluno> alunos = new ArrayList<Aluno>();
        Aluno aluno = null;
        Boolean permissao = Boolean.FALSE;

        for (int i = 0; (((aluno != null) && (permissao)) || (i < (this.alunos.size() - i))); i++) {

            aluno = (Aluno) DataHelper.procuraNome(this.alunos, nome, (0 + i), (this.alunos.size() - i));
            alunos.add(aluno);
            permissao = Boolean.TRUE;

        }

        permissao = Boolean.FALSE;

        for (int i = 0; (((aluno != null) && (permissao)) || (i < (this.alunos.size() - i))); i++) {

            aluno = (Aluno) DataHelper.procuraSobrenome(this.alunos, nome, (0 + i), (this.alunos.size() - i));
            alunos.add(aluno);
            permissao = Boolean.TRUE;

        }

        permissao = Boolean.FALSE;

        for (int i = 0; (((aluno != null) && (permissao)) || (i < (this.alunos.size() - i))); i++) {

            aluno = (Aluno) DataHelper.procuraNomeCompleto(this.alunos, nome, (0 + i), (this.alunos.size() - i));
            alunos.add(aluno);
            permissao = Boolean.TRUE;

        }

        return alunos;

    }

    @Override
    public String toString() {
        
        System.out.println();
        System.out.println("|" + DataHelper.repeteCaracter('-', 80) + "|");
        System.out.printf("|ID DA TURMA %-68s|\n", this.getId());
        System.out.println("|" + DataHelper.repeteCaracter('-', 80) + "|");
        System.out.println("|" + DataHelper.repeteCaracter('-', 80) + "|");

        List<Aluno> alunos = this.getAlunos();

        alunos.forEach((aluno) -> {

            System.out.printf("|Aluno: %-73s|\n", aluno.getNomeCompleto());
            System.out.printf("|ID: %-76s|\n", aluno.getId());
            System.out.println("|" + DataHelper.repeteCaracter('-', 80) + "|");

        });

        System.out.println("|" + DataHelper.repeteCaracter('-', 80) + "|");
        Integer hashId = Integer.valueOf(this.hashCode());
        System.out.printf("|HashID: %-72s|\n", hashId);
        System.out.println("|" + DataHelper.repeteCaracter('-', 80) + "|");

        return "";
        
    }
    
}
