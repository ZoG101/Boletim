package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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
 * @version 1.1
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
    private Boolean novaSenha;
    private List<Turma> turmas = new ArrayList<Turma>();

    /**
     * Construtor da classe para instânciar o objeto {@code Professor}
     * da forma adequada recebendo {@code nome} {@code materia}.
     * 
     * @param nome
     * @param materia
     * @see String
     * @see Boolean
     */
    public Professor (String nome, String materia, String senha) {

        this.materia = materia;
        this.nome = nome;
        this.senha = new String();
        this.novaSenha = Boolean.TRUE;
        setSenha(senha);

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
    private Boolean getNovaSenha() {

        return novaSenha.booleanValue();

    }

    /**
     * Método privado auxiliar para manipular e dar adequadamente a
     * permissão para o usuário trocar de senha.
     * 
     * @see Boolean
     */
    private void setPermissao () {

        this.novaSenha = Boolean.TRUE;

    }

    /**
     * Método privado auxiliar para manipular e retirar adequadamente a
     * permissão para o usuário não trocar de senha.
     * 
     * @see Boolean
     */
    private void unsetPermissao () {

        this.novaSenha = Boolean.FALSE;

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
        this.unsetPermissao();

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
        this.setPermissao();

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

        if(confirma.matches()) return true;
        return false;

    }

    /**
     * Autentica o usuário verificando se a senha inserida está conforme a senha gravada.
     * 
     */
    @Override
    public Boolean autentica(String s) {
        
        return this.getSenha().equals(s);

    }
    
}
