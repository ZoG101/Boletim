package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import security.Autenticavel;
import security.Token;

/**
 * A classe {@code Professor} é feita como uma abstração
 * de um professor real que possui seus {@code Alunos} e 
 * contém suas informações básicas e de maior interesse.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.2
 * @see Aluno
 * @see Turma
 * @see Serializable
 * @see Autenticavel
 */
public class Professor implements Serializable, Autenticavel {

    private static final long serialVersionUID = 1L;

    private String materia;
    private String nome;
    private String senha;
    private String usuario;
    private Boolean novaSenha;
    private Boolean novoUsuario;
    private Boolean autenticado;
    private LocalDate ultimaMudanca;
    private List<Turma> turmas = new ArrayList<Turma>();
    private static Map<String, Professor> usuarios = new HashMap<>();

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
    public Professor (String nome, String materia,String usuario, String senha) {

        this.materia = materia;
        this.nome = nome;
        this.senha = new String();
        this.novaSenha = Boolean.TRUE;
        this.novoUsuario = Boolean.TRUE;
        this.autenticado = Boolean.FALSE;
        setSenha(senha);
        setUsuario(usuario);

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
     * Retorna a senha gravada.
     * 
     * @return {@value senha}
     */
    private String getSenha () {

        return this.senha;

    }

    private String getUsuario () {

        return this.usuario;

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

    /**
     * Retorna o estado de permissão do usuário para uma nova senha ser 
     * gravada ou não.
     * 
     * @return {@value true} se o usuário tiver permissão;
     *         {@value false} se o usuário não tiver permissão.
     * @see Boolean
     */
    private Boolean getNovaSenha () {

        return novaSenha.booleanValue();

    }

    private Boolean getNovoUsuario() {

        return this.novoUsuario.booleanValue();

    }

    public Boolean getAutenticacao () {

        return this.autenticado.booleanValue();

    }

    private LocalDate getUltimaMudanca() {

        return ultimaMudanca;

    }

    private void setUltimaMudanca () {

        this.ultimaMudanca = LocalDate.now();

    }

    /**
     * Método privado auxiliar para manipular e dar adequadamente a
     * permissão para o usuário trocar de senha.
     * 
     * @see Boolean
     */
    private void setPermissaoNovaSenha () {

        this.novaSenha = Boolean.TRUE;

    }

    private void setPermissaoNovoUsuario () {

        this.novoUsuario = Boolean.TRUE;

    }

    /**
     * Método privado auxiliar para manipular e retirar adequadamente a
     * permissão para o usuário não trocar de senha.
     * 
     * @see Boolean
     */
    private void unsetPermissaoNovaSenha () {

        this.novaSenha = Boolean.FALSE;

    }

    private void unsetPermissaoNovoUsuario () {

        this.novoUsuario = Boolean.FALSE;

    }

    private void setUsuario (String usuario) {

        if (usuario == null) throw new NullPointerException("\nERRO: O usuário não pode ser nulo!");
        if (Professor.usuarios.containsKey(usuario)) throw new IllegalArgumentException("\nERRO: O nome de usuário inseriodo já existe!");
        if (!(getNovoUsuario())) throw new IllegalStateException("\nERRO: Você não tem permissão para auterar o nome de usuário!");
        if (!(this.verificaUsuário(usuario))) throw new IllegalArgumentException("\nERRO: O nome de usuário não pode conter símbolos e deve ter pelo menos 4 caracteres!");

        this.addUsuario(usuario);
        this.usuario = usuario;
        this.unsetPermissaoNovoUsuario();

    }

    public void mudarUsuario (String usuario) {

        if (usuario == null) throw new NullPointerException("\nERRO: O usuário não pode ser nulo!");
        if (!(getAutenticacao())) throw new IllegalStateException("\nERRO: O usuário precisa estar autenticado para se alterar o nome de usuário!");
        if (this.getUsuario().equals(usuario)) throw new IllegalArgumentException("\nERRO: O nome de usuário inserido deve ser diferente do atual!");
        if (Professor.usuarios.containsKey(usuario)) throw new IllegalArgumentException("\nERRO: O nome de usuário inseriodo já existe!");

        if (this.getUltimaMudanca() != null) {

            Period periodo = Period.between(this.getUltimaMudanca(), LocalDate.now());

            if (periodo.getDays() < 30) throw new RejectedExecutionException("\nERRO: Não é possivel trocar de nome de usuário pois fazem menos de 30 dias que desde que você alterou seu nome de usuário pela última vez.");

        }

        this.setPermissaoNovoUsuario();
        this.setUsuario(usuario);
        this.setUltimaMudanca();

        System.out.println("\nSeu nome de usuário foi redefinido com sucesso!");

    }

    private void addUsuario (String usuario) {

        if (usuario == null) throw new NullPointerException("\nERRO: O usuário não pode ser nulo!");
        if (Professor.usuarios.containsKey(usuario)) throw new IllegalArgumentException("\nERRO: O nome de usuário inseriodo já existe!");

        Professor.usuarios.put(usuario, this);

    }

    /**
     * Grava a senha do usuário.
     * 
     * @throws NullPointerException
     * @throws IllegalArgumentException
     * @see Scanner
     * @see String
     */
    private void setSenha (String senha) {

        if (senha == null) throw new NullPointerException("\nERRO: A senha não pôde ser definida!");
        if (this.senha.equals(senha)) throw new IllegalArgumentException("\nERRO: A senha não pode ser igual a atual!");
        if (!(this.verificaSenha(senha))) throw new IllegalArgumentException("\nERRO: A senha deve conter entre 6 a 12 caracteres, deve conter pelo menos uma letra maiúscula, um número e não deve conter símbolos.");
        this.senha = senha;
        this.unsetPermissaoNovaSenha();

    }

    /**
     * Método de redefiniçãode senha que recebe um {@code Token}, um {@code Integer} que
     * se refere ao token digitado pelo usuário para conferir se o usuário tem a permissão
     * para redefinir a senha.
     * 
     * @param token
     * @param tokenPassado
     * @param senha
     * @throws NullPointerException
     * @throws RejectedExecutionException
     * @see Token
     * @see Integer
     */
    public void solicitaNovaSenha (Token token, Integer tokenPassado) {

        if (token == null || tokenPassado == null) throw new NullPointerException("\nERRO: Nem o token e nem o token passado podem ser nulos!");
        if (!(token.verificaToken(tokenPassado))) throw new RejectedExecutionException("\nERRO: Você errou o token de redefinição de 6 dígitos!");
        this.setPermissaoNovaSenha();

    }

    /**
     * Método que redefine a {@code senha} do usuário caso ele tenha a permissão para tal.
     * 
     * @param senha
     * @throws RejectedExecutionException
     * @throws NullPointerException
     * @see String
     */
    public void redefinirSenha (String senha) {

        if (!getNovaSenha()) throw new RejectedExecutionException("\nERRO: Você não tem permissão para alterar a senha!");
        if (senha == null) throw new NullPointerException("\nERRO: A senha não pode ser nula!");
        setSenha(senha);
        System.out.println("\nSua senha foi redefinida com sucesso!");

    }

    public void redefinirSenhaAutenticado (String senha) {

        if (!getAutenticacao()) throw new RejectedExecutionException("\nERRO: Você não tem permissão para alterar a senha!");
        if (senha == null) throw new NullPointerException("\nERRO: A senha não pode ser nula!");
        this.setPermissaoNovaSenha();
        setSenha(senha);
        System.out.println("Sua senha foi redefinida com sucesso!");

    }

    /**
     * Método auxiliar para verificar se a senha está conforme o 
     * padrão de sengurança de senha permitido.
     * 
     * @param s
     * @return {@value true} se a senha estiver conforme o padrão de sengurança de senha;
     *         {@value false} se a senha estiver fora do padrão de sengurança de senha.
     */
    private Boolean verificaSenha (String s) {

        Pattern formato = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?!.*[ !@#$%^&*_=+-]).{6,12}$");
        Matcher confirma = formato.matcher(s);

        if(confirma.matches()) return Boolean.TRUE;
        return Boolean.FALSE;

    }

    private Boolean verificaUsuário (String u) {

        Pattern formato = Pattern.compile("^(?!.*[ !@#$%^&*_=+-]).{4,14}$");
        Matcher confirma = formato.matcher(u);

        if (confirma.matches()) return Boolean.TRUE;
        return Boolean.FALSE;

    }

    /**
     * Autentica o usuário verificando se a senha e usuário inserido 
     * está conforme a senha e usuário gravados.
     * 
     */
    @Override
    public Boolean autentica (String u, String s) {
        
        if ((s == null) || (u == null)) throw new NullPointerException("\nERRO: A senha e usuário não podem ser nulos!");
        if (!(this.getSenha().equals(s)) && (this.getUsuario().equals(u))) throw new RejectedExecutionException("\nERRO: A senha ou o usuário estão incorretos!");

        this.autenticado = Boolean.TRUE;
        System.out.println("\nAutenticado com sucesso!");
        return Boolean.TRUE;

    }

    @Override
    public Boolean autentica (String s) {

        if (s == null) throw new NullPointerException("\nERRO: A senha não pode ser nula!");
        if (!(getAutenticacao())) throw new IllegalStateException("\nERRO: Usuário tem que estar autenticado para acessar esta área!");
        if (!(this.getSenha().equals(s))) throw new RejectedExecutionException("\nERRO: A senha está incorreta!");

        return Boolean.TRUE;

    }
    
}
