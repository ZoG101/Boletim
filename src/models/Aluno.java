package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.RejectedExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.DataHelper;

/**
 * A classe {@code Aluno} é feita como uma abstração
 * de um aluno real que possui seu {@code Boletim} e 
 * contém suas informações básicas e de maior interesse.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.3
 * @see Usuario
 * @see Professor
 * @see Turma
 * @see Boletim
 */
public final class Aluno extends Usuario implements Comparable<Aluno>{

    private static final long serialVersionUID = 1L;

    private String id;
    private String nivel;
    private Turma turma;
    private static List<String> IDs = new ArrayList<String>();
    private static Map<String, Aluno> alunoMap = new HashMap<String, Aluno>();
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
    public Aluno (String nomeCompleto, String nome, String sobrenome, String cpf, String telefone, String email, String usuario, String senha, String nivel) {

        super(nomeCompleto, nome, sobrenome, cpf, telefone, email, usuario, senha);
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

        Aluno.alunoMap.put(id, this);

        DataHelper.ordena(Aluno.IDs, 0, (Aluno.IDs.size() - 1));

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
     * Retorna o {@code nivel} do {@code Aluno}.
     * 
     * @return {@value nivel}
     * @see String
     */
    public String getNivel () {

        return this.nivel;

    }

    /**
     * Método que retorna a 
     * referência da turma a qual este respectivo
     * {@code Aluno} pertence.
     * 
     * @return {@value turma}
     * @see Turma
     */
    public Turma getTurma () {

        return this.turma;

    }

    /**
     * Método que retorna o {@value ID} da turma que
     * este respectivo {@code Aluno} pertence.
     * 
     * @return {@value ID}
     * @see String
     * @see Turma#getId()
     */
    public String getTurmaID () {

        return this.turma.getId();

    }

    /**
     * Método de busca de {@code Aluno} por ID.
     * 
     * @param id
     * @return {@value Aluno} buscado.
     * @throws IllegalArgumentException
     * @throws NoSuchElementException
     * @see Aluno
     * @see String
     * @see Pattern
     * @see Matcher
     * @see HashMap
     */
    public static Aluno getAlunoID (String id) {

        if ((id == null) || (id.equals(""))) throw new IllegalArgumentException("\nERRO: O ID não pode ser nulo e nem vazio!");

        Pattern formato = Pattern.compile("^([0-9]){1,4}$");
        Matcher matcher = formato.matcher(id);

        if (!matcher.find()) throw new IllegalArgumentException("\nERRO: Formato de id inválido!");

        if (id.length() < 4) {

            for (int i = 0; i <= (4 - (id.length()) + 1); i++) {

                id = String.format("%d%s", 0, id);

            }

        }

        if (!Aluno.IDs.contains(id)) throw new NoSuchElementException("\nERRO: O aluno não existe ou o id foi digitado incorretamente!");

        return Aluno.alunoMap.get(id);

    }

    /**
     * Método pra escrever a turma deste respectivo {@code Aluno}.
     * 
     * @param turma
     * @throws IllegalArgumentException
     * @see Turma
     */
    public void setTurma (Turma turma) {

        if (turma == null) throw new IllegalArgumentException("\nERRO: A turma não pode ser nula!");

        this.turma = turma;

    }

    /**
     * Cria um novo {@code Boletim} para o {@code Aluno}, mas recebe uma 
     * instância de {@code Professor} para que tenha a permissão adequada
     * para criar o {@code Boletim}.
     * 
     * @param professor
     * @throws RejectedExecutionException
     * @see Professor
     * @see Boletim
     */
    public void criarBoletim (Professor professor) {

        Boletim a = new Boletim(this, professor);
        if (!this.verificaBoletim(professor)) throw new RejectedExecutionException("\nERRO: Já existe um boletim da matéria deste professor para este aluno! \n Matéria: " + professor.getMateria()); 
        this.adicionarBoletim(a);

    }

    private Boolean verificaBoletim (Professor professor) {

        return DataHelper.procuraBoolean(this.boletins, new Boletim(this, professor), 0, (this.boletins.size() - 1));

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
        DataHelper.ordena(this.boletins, 0, (this.boletins.size() - 1));

    }

    /**
     * Método de busca de boletim por matéria.
     * 
     * @param materia
     * @param professor
     * @return <p>{@value Boletim} Buscado pela matéria;<br>
     *         {@code Boletim.toString()} e {@value null} se o professor não tiver permissão de alterar o boletim.</p>
     */
    public Boletim getBoletim (String materia, Professor professor) {

        if (professor.getMateria().equalsIgnoreCase(materia)) return DataHelper.procura(this.boletins, new Boletim(this, materia, professor.getNome()), 0, (this.boletins.size() - 1));
        else System.out.println("Você pode visualizar este boletim, mas você não tem permissão de alterá-lo já que ele pertence a outro professor.");

        DataHelper.procura(this.boletins, new Boletim(this, materia, professor.getNome()), 0, (this.boletins.size() - 1)).toString();

        return null;

    }

    public List<Boletim> getBoletins () {

        if (!this.boletins.isEmpty()) return Collections.unmodifiableList(this.boletins);
        return null;

    }

    @Override
    public int compareTo(Aluno o) {
        
        return this.getNomeCompleto().compareToIgnoreCase(o.getNomeCompleto());

    }

    @Override
    public String toString() {
        
        System.out.print(super.toString());
        System.out.printf("|Nível Escolar: %-71s|\n", this.getNivel());
        System.out.printf("|Turma: %-73s|\n", this.getTurma().getId());
        System.out.println("|" + DataHelper.repeteCaracter('-', 80) + "|");
        Integer hashId = Integer.valueOf(this.hashCode());
        System.out.printf("|HashID: %-72s|\n", hashId);
        System.out.println("|" + DataHelper.repeteCaracter('-', 80) + "|");

        return "";

    }
    
}
