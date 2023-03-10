package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import data.DataHelper;

/**
 * A classe {@code Aluno} é feita como uma abstração
 * de um aluno real que possui seu {@code Boletim} e 
 * contém suas informações básicas e de maior interesse.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.1
 * @see Usuario
 * @see Professor
 * @see Turma
 * @see Boletim
 */
public final class Aluno extends Usuario implements Comparable<Aluno>{

    private static final long serialVersionUID = 1L;

    private String id;
    private String nivel;
    private static List<String> IDs = new ArrayList<String>();
    private List<Boletim> boletins;

    /**
     * Construtor do aluno que inicializa o aluno de forma adequada
     * com {@code nome} e {@code nivel} de estudo para uso adequada
     * de outras classes.
     * 
     * @param nome
     * @param nivel
     * @see String
     */
    public Aluno (String nome, String sobrenome, String cpf, String telefone, String email, String usuario, String senha, String nivel) {

        super(nome, sobrenome, cpf, telefone, email, usuario, senha);
        this.id = this.setID();
        this.nivel = nivel;
        this.boletins = new ArrayList<Boletim>();

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

        if (Aluno.IDs.isEmpty()) return Boolean.FALSE; 

        return DataHelper.procuraBoolean(Aluno.IDs, id, 0, (Aluno.IDs.size() - 1));

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

        Aluno.IDs.add(id);

        DataHelper.ordena(Aluno.IDs, 0, (Aluno.IDs.size() - 1));

        System.out.println("\n" + Aluno.IDs);

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
     * Retorna uma {@code String} contendo o nome do professor.
     * 
     * @return {@value nome}
     * @see String
     * @see Usuario
     */
    @Override
    public String getNome () {

        return super.getNome();

    }

    /**
     * Retorna o {@code sobrenome} do usuário.
     * 
     * @return {@value sobrenome}
     * @see String
     * @see Usuario
     */
    @Override
    public String getSobrenome () {

        return super.getSobrenome();

    }

    /**
     * retorna o nome completo do usuário.
     * 
     * @return {@value nomeCompleto}
     * @see String
     * @see Usuario
     */
    @Override
    public String getNomeCompleto () {

        return super.getNome() + " " + super.getSobrenome();

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

    @Override
    public int compareTo(Aluno o) {
        
        return this.getNomeCompleto().compareTo(o.getNomeCompleto());

    }

    @Override
    public String toString() {
        
        String formato = String.format("Nome: %s", this.getNomeCompleto());
        return formato;

    }
    
}
