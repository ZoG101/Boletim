package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.RejectedExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A classe {@code Professor} é feita como uma abstração
 * de um professor real que possui seus {@code Alunos} e 
 * contém suas informações básicas e de maior interesse.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.5
 * @see Usuario
 * @see Aluno
 * @see Turma
 */
public final class Professor extends Usuario {

    private static final long serialVersionUID = 1L;

    private String materia;
    private Map<String, Turma> turmas = new HashMap<String, Turma>();

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
     * Retorna um {@code Map} contendo as turmas do {@code professor}.
     * 
     * @return {@value turmas}
     * @throws IllegalStateException
     * @throws RejectedExecutionException
     * @see ArrayList
     * @see Map
     * @see Collections
     */
    public Map<String, Turma> getTurmas () {

        if (!this.getAutenticacao()) throw new IllegalStateException("\nERRO: Autenticação necessária para executar tais ações!");
        if(this.turmas.isEmpty()) throw new RejectedExecutionException("\nERRO: Você não tem turmas!");

        return Collections.unmodifiableMap(this.turmas);

    }

    /**
     * Retorna um {@code List} contendo a turma buscada pelo {@code Professor}.
     * 
     * @param id
     * @return {@value turmas}
     * @throws IllegalStateException
     * @throws RejectedExecutionException
     * @throws IllegalArgumentException
     * @see List
     * @see String
     * @see Pattern
     * @see Matcher
     */
    public Turma getTurma (String id) {

        if (!this.getAutenticacao()) throw new IllegalStateException("\nERRO: Autenticação necessária para executar tais ações!");
        if (this.turmas.isEmpty()) throw new RejectedExecutionException("\nERRO: Você não tem turmas!");
        if ((id == null) || (id.equals("")) || (id.length() > 4)) throw new IllegalArgumentException("\nERRO: Formato de id inválido!");

        Pattern formato = Pattern.compile("^([0-9]){1,4}$");
        Matcher matcher = formato.matcher(id);

        if (!matcher.find()) throw new IllegalArgumentException("\nERRO: Formato de id inválido!");

        if (id.length() < 4) {

            for (int i = 0; i <= (4 - (id.length() + 1)); i++) {

                id = String.format("%d%s", 0, id);

            }

        }

        if (!this.turmas.containsKey(id)) throw new IllegalArgumentException("\nERRO: Essa turma que você está procurando não existe!");

        return this.turmas.get(id);

    }

    /**
     * Método auxiliar privado que adiciona uma nova turma no {@code Map}.
     * 
     * @param t
     * @throws IllegalArgumentException
     * @see Turma
     */
    private void addTurma (Turma t) {

        if (t == null) throw new IllegalArgumentException("\nERRO: Turma não pode ser nula!");

        this.turmas.put(t.getId(), t);

    }

    /**
     * Método para criar uma nova {@code Turma}.
     * 
     * @param senha
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     * @see String
     * @see Turma
     */
    public void criaTurma (String senha) {

        if (!this.getAutenticacao()) throw new IllegalStateException("\nERRO: Autenticação necessária para executar tais ações!");
        if ((senha == null) || (senha.equals(""))) throw new IllegalArgumentException("\nERRO: Senha não pode estar vazia!");

        if (this.autentica(senha)) {

            Turma t = new Turma();

            this.addTurma(t);

        }

    }

    /** 
     * Função para pesquisar por um aluno específico dentro de todas as turmas do {@code Professor} 
     * e retorna uma {@code List} auxiliar com os alunos selecionados.
     * 
     * @param nome
     * @return {@value alunos}
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     * @throws NoSuchElementException
     * @see List
     * @see Aluno
     * @see String
     * @see Set
     */
    public List<Aluno> procuraAluno (String nome) {

        if (!this.getAutenticacao()) throw new IllegalStateException("\nERRO: Autenticação necessária para executar tais ações!");
        if ((nome == null) || (nome.equals(""))) throw new IllegalArgumentException("\nERRO: O nome não pode estar vazio!");

        List<Aluno> alunos = new ArrayList<Aluno>();

        Set<String> k = this.turmas.keySet();

        k.forEach((chave) -> {

            turmas.get(chave).procuraAluno(nome).forEach((aluno) -> {
                
                if ((aluno != null) && (!alunos.contains(aluno))) alunos.add(aluno);
            
            });

        });

        if (alunos.isEmpty()) throw new NoSuchElementException("\nERRO: Nenhum elemento com esse nome foi encontrado!");

        return alunos;
    
    }
    
}
