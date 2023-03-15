package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import security.Autenticavel;
import security.Token;

/**
 * A classe {@code Usuario} é abstrata e feita para gerenciar
 * todos os aspectos de usuário que uma conta pode ter, verificá-los,
 * autentica-lo, administra-los e modifica-los.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.2
 * @see Professor
 * @see Aluno
 * @see Turma
 * @see Serializable
 * @see Autenticavel
 */
public abstract class Usuario implements Serializable, Autenticavel {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String sobrenome;
    private String senha;
    private String usuario;
    private String cpf;
    private String telefone;
    private String email;
    private Boolean novaSenha;
    private Boolean novoUsuario;
    private Boolean autenticado;
    private LocalDate ultimaMudanca;
    private static Map<String, Usuario> usuarios = new HashMap<>();

    /**
     * Construtor da classe para instânciar o objeto {@code Professor}
     * da forma adequada recebendo {@code nome} {@code materia}.
     * 
     * @param nome
     * @param cpf
     * @param usuario
     * @param senha
     * @see String
     * @see Boolean
     */
    public Usuario (String nome, String sobrenome, String cpf, String telefone, String email, String usuario, String senha) {

        if (!(this instanceof Aluno) && ((cpf == null) ||(cpf.equals("")))) throw new RejectedExecutionException("\nERRO: Somente um aluno pode não ter um CPF!");
        if (((telefone == null) || (telefone.equals(""))) && ((email == null) || (email.equals("")))) throw new RejectedExecutionException("\nERRO: Você deve adicionar pelo menos um meio de contato!");
        if ((email != null) && (!email.equals(""))) if ((!this.verificaEmail(email))) throw new IllegalArgumentException("\nERRO: E-mail inválido!");
        if ((cpf != null) && (!cpf.equals(""))) if (!this.verificaCPF(cpf)) throw new IllegalArgumentException("\nERRO: CPF inválido!");

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = new String();
        this.novaSenha = Boolean.TRUE;
        this.novoUsuario = Boolean.TRUE;
        this.autenticado = Boolean.FALSE;
        setUsuario(usuario);
        setSenha(senha);

    }

    /**
     * Retorna uma {@code String} contendo o nome do {@code Usuario}.
     * 
     * @return {@value nome}
     * @see String
     */
    public String getNome () {

        return this.nome;

    }

    /**
     * Retorna o {@code sobrenome} do {@code Usuario}.
     * 
     * @return {@value sobrenome}
     * @see String
     */
    public String getSobrenome () {

        return this.sobrenome;

    }

    /**
     * retorna o nome completo do {@code Usuario}.
     * 
     * @return {@value nomeCompleto}
     * @see String
     */
    public String getNomeCompleto () {

        return this.getNome() + " " + this.getSobrenome();

    }

    /**
     * Retorna a senha gravada.
     * 
     * @return {@value senha}
     * @see String
     */
    private String getSenha () {

        return this.senha;

    }

    /**
     * Método privado auxiliar para retornar o
     * nome de {@code Usuario}.
     * 
     * @return {@value usuario}
     * @see String
     */
    private String getUsuario () {

        return this.usuario;

    }

    /**
     * retorna o cpf do {@code Usuario}.
     * 
     * @return {@value cpf}
     * @throws IllegalStateException
     * @throws RejectedExecutionException
     * @see String
     */
    public String getCpf (String senha) {

        if (!(this.getAutenticacao())) throw new IllegalStateException("\nERRO: Você deve estar autenticado para acessar essa informação!");
        if (!(this.autentica(senha))) throw new RejectedExecutionException("\nERRO: Senha incorreta!");
        return this.cpf;

    }

    /**
     * Retorna o número de telefone do {@code Usuario}.
     * 
     * @return {@value telefone}
     * @throws IllegalStateException
     * @see String
     */
    public String getTelefone () {

        if (!(this.getAutenticacao())) throw new IllegalStateException("\nERRO: Você deve estar autenticado para acessar essa informação!");
        return this.telefone;

    }

    /**
     * Retorna o email do {@code Usuario}.
     * 
     * @return {@value email}
     * @throws IllegalStateException
     * @see String
     */
    public String getEmail () {

        if (!(this.getAutenticacao())) throw new IllegalStateException("\nERRO: Você deve estar autenticado para acessar essa informação!");
        return this.email;

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

    /**
     * Método auxiliar para retornar a
     * permissão de troca de nome de
     * usuário.
     * 
     * @return {@value true} Se tiver permissão para trocar de nome de usuário;
     *         {@value false} Se não tiver permissão para trocar de nome de usuário.
     * @see Boolean
     */
    private Boolean getNovoUsuario() {

        return this.novoUsuario.booleanValue();

    }

    /**
     * Retorna o estado de autenticação de um 
     * usuário.
     * 
     * @return {@value true} Se o usuário estiver autenticado;
     *         {@value false} Se não estiver autenticado.
     * @see Boolean
     */
    public Boolean getAutenticacao () {

        return this.autenticado.booleanValue();

    }

    /**
     * Método auxiliar que retorna a última mudança 
     * de nome de usuário através de um {@code LocalDate}.
     * 
     * @return {@value ultimaMudanca}
     * @see LocalDate
     */
    private LocalDate getUltimaMudanca() {

        return ultimaMudanca;

    }

    /**
     * Retorna os usuários em um {@code Map} não modificável.
     * 
     * @return {@value usuarios}
     * @see Map
     * @see Collections
     */
    public static Map<String, Usuario> getUsuarios () {

        return Collections.unmodifiableMap(Usuario.usuarios);

    }

    /**
     * Método auxiliar para marcar a data de uma nova mudança
     * no nome de usuário.
     * 
     * @see LocalDate
     */
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

    /**
     * Método auxiliar para dar permissão de troca de nome 
     * de usuário.
     * 
     * @see Boolean
     */
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

    /**
     * Método auxiliar para retirar a permissão de troca de nome 
     * de usuário.
     * 
     * @see Boolean
     */
    private void unsetPermissaoNovoUsuario () {

        this.novoUsuario = Boolean.FALSE;

    }

    /**
     * Método auxiliar para gravar um novo nome de usuário no sistema
     * garantindo todas as verificações necessárias.
     * 
     * @param usuario
     * @throws NullPointerException
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     * @see String
     */
    private void setUsuario (String usuario) {

        if (usuario == null) throw new NullPointerException("\nERRO: O usuário não pode ser nulo!");
        if (Usuario.usuarios.containsKey(usuario)) throw new IllegalArgumentException("\nERRO: O nome de usuário inseriodo já existe!");
        if (!(getNovoUsuario())) throw new IllegalStateException("\nERRO: Você não tem permissão para auterar o nome de usuário!");
        if (!(this.verificaUsuário(usuario))) throw new IllegalArgumentException("\nERRO: O nome de usuário não pode conter símbolos e deve ter pelo menos 4 caracteres!");

        this.addUsuario(usuario);
        this.usuario = usuario;
        this.unsetPermissaoNovoUsuario();

    }

    /**
     * Método para trocar o nome de usuário garantindo as verificações necessárias.
     * 
     * @param usuario
     * @throws NullPointerException
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     * @throws RejectedExecutionException
     * @see String
     * @see Period
     * @see LocalDate
     */
    public void mudarUsuario (String usuario) {

        if ((usuario == null) || (usuario.compareToIgnoreCase("") == 0)) throw new NullPointerException("\nERRO: O usuário não pode ser nulo!");
        if (!(getAutenticacao())) throw new IllegalStateException("\nERRO: O usuário precisa estar autenticado para se alterar o nome de usuário!");
        if (this.getUsuario().equals(usuario)) throw new IllegalArgumentException("\nERRO: O nome de usuário inserido deve ser diferente do atual!");
        if (Usuario.usuarios.containsKey(usuario)) throw new IllegalArgumentException("\nERRO: O nome de usuário inseriodo já existe!");

        if (this.getUltimaMudanca() != null) {

            Period periodo = Period.between(this.getUltimaMudanca(), LocalDate.now());

            if (periodo.getDays() < 30) throw new RejectedExecutionException("\nERRO: Não é possivel trocar de nome de usuário pois fazem menos de 30 dias que desde que você alterou seu nome de usuário pela última vez.");

        }

        this.setPermissaoNovoUsuario();
        this.setUsuario(usuario);
        this.setUltimaMudanca();

        System.out.println("\nSeu nome de usuário foi redefinido com sucesso!");

    }

    /**
     * Método auxiliar para adicionar um nome de usuário ao {@code Map}
     * junto com seu usuário e execuatar as verificações necessárias antes
     * das midificações.
     * 
     * @param usuario
     * @throws NullPointerException
     * @throws IllegalArgumentException
     * @see Map
     */
    private void addUsuario (String usuario) {

        if (usuario == null) throw new NullPointerException("\nERRO: O usuário não pode ser nulo!");
        if (Usuario.usuarios.containsKey(usuario)) throw new IllegalArgumentException("\nERRO: O nome de usuário inseriodo já existe!");

        Usuario.usuarios.put(usuario, this);

    }

    /**
     * Método auxiliar para gravar a senha do usuário.
     * 
     * @throws NullPointerException
     * @throws IllegalArgumentException
     * @see Scanner
     * @see String
     */
    private void setSenha (String senha) {

        if (senha == null) throw new NullPointerException("\nERRO: A senha não pôde ser definida!");
        if (this.senha.equals(senha)) throw new IllegalArgumentException("\nERRO: A senha não pode ser igual a atual!");
        if (!(this.verificaSenha(senha))) throw new IllegalArgumentException("\nERRO: A senha deve conter entre 6 a 20 caracteres, deve conter pelo menos uma letra maiúscula, um número e não deve conter símbolos.");
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

    /**
     * Método para redefinir a senha com o usuário autenticado.
     * 
     * @param senha
     * @throws RejectedExecutionException
     * @throws NullPointerException
     * @see String
     */
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
     * @see Boolean
     * @see String
     * @see Pattern
     * @see Matcher
     */
    private Boolean verificaSenha (String s) {

        Pattern formato = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?!.*[ !@#$%^&*_=+-]).{6,20}$");
        Matcher confirma = formato.matcher(s);

        if(confirma.matches()) return Boolean.TRUE;
        return Boolean.FALSE;

    }

    /**
     * Método auxiliar que verifica se o nome de usuário inserido para
     * cadastro ou troca está de acordo com as regras da instituição.
     * 
     * @param u
     * @return {@value true} se o nome de usuário estiver de acordo com as regras da instituição;
     *         {@value false} se o nome de usuário não estiver de acordo com as regras da instituição.
     * @see Boolean
     * @see String
     * @see Pattern
     * @see Matcher
     */
    private Boolean verificaUsuário (String u) {

        Pattern formato = Pattern.compile("^(?!.*[ !@#$%^&*_=+-]).{4,20}$");
        Matcher confirma = formato.matcher(u);

        if (confirma.matches()) return Boolean.TRUE;
        return Boolean.FALSE;

    }

    /**
     * Método privado auxiliar para verificar formatos de {@code email} que
     * podem ser válidos.
     * 
     * @param email
     * @return {@value true} Se o fomato de {@code email} inserido for válido;
     *         {@value false} Se o fomato de {@code email} não for válido.
     * @see Boolean
     * @see String
     * @see Pattern
     * @see Matcher
     */
    private Boolean verificaEmail (String email) {

        Pattern formato = Pattern.compile("^([a-zA-Z0-9._]){2,64}([@]){1,1}([a-zA-Z0-9._]){2,64}(?=.*[.])(?!.*[ !#$%^&*=+-]).{6,64}$");
        Matcher confirma = formato.matcher(email);

        if (confirma.matches()) return Boolean.TRUE; 
        return Boolean.FALSE;

    }

    /**
     * Método auxiliar para verificar o CPF por meio do cálculo para confirmar os
     * dois dígitos verificadores.
     * 
     * @param cpf
     * @return {@value true} Se o cpf for válido;
     *         {@value false} Se o cpf for inválido.
     * @see Boolean
     * @see String
     * @see Pattern
     * @see Matcher
     * @see Integer
     * @implNote <p>Para o primeiro dígito ({@code dig1}) os nove primeiros algarismos são 
     * ordenadamente multiplicados pela sequência {@value10, 9, 8, 7, 6,
     * 5, 4, 3, 2} (o primeiro por {@value 10}, o segundo por {@value 9}, e
     * assim sucessivamente). Em seguida, calcula-se o resto da divisão
     * ({@code sobra}) da soma dos resultados das multiplicações por {@value 11}.
     * Se esse resto ({@code sobra}) for {@value 0} ou {@value 1}, o primeiro dígito
     * verificador é {@value 0}.</p>
     * 
     * <p>Para o segundo dígito verificador {@code dig2} é calculado pela mesma
     * regra, na qual os números a serem multiplicados pela sequência {@value 10,
     * 9, 8, 7, 6, 5, 4, 3, 2} são contados a partir do segundo algarismo, sendo 
     * {@code dig1} o último algarismo. Se {@code sobra} for {@value 0} ou {@value 1},
     * {@code dig2} é igual a {@value 0}.</p>
     */
    private Boolean verificaCPF (String cpf) {

        Pattern formato1 = Pattern.compile("^([0-9]){3}.([0-9]){3}.([0-9]){3}-([0-9]){2}$");
        Matcher matcher1 = formato1.matcher(cpf);

        Pattern formato2 = Pattern.compile("^([0-9]){3}([0-9]){3}([0-9]){3}([0-9]){2}$");
        Matcher matcher2 = formato2.matcher(cpf);

        if (matcher1.matches()) {

            int count1 = 10;
            int count2 = 10;
            int dig1 = 0;
            int dig2 = 0;
            Integer cpfPartido;

            for (int i = 0; i < cpf.length() - 1; i++) {

                if ((i != 3) && (i != 7) && (i != 11)) {

                    cpfPartido = Integer.parseInt(cpf.substring(i, i + 1));

                    if (count1 >= 2) { 

                        dig1 += cpfPartido * count1; 

                        count1--;

                    } else {

                        int sobra = (dig1 % 11);

                        if ((sobra == 0) || (sobra == 1)) {

                            dig1 = 0;

                        } else {

                            dig1 = 11 - sobra;

                        }

                    }

                    if ((i >= 1) && (count2 >= 2)) {

                        dig2 += cpfPartido * count2;

                        count2--;

                        if (i == 12) {

                            int sobra = (dig2 % 11);

                            if ((sobra == 0) || (sobra == 1)) {

                                dig2 = 0;
    
                            } else {
    
                                dig2 = 11 - sobra;
    
                            }
    
                        }

                    }

                }

            }

            if ((dig1 == (Integer.parseInt(cpf.substring(cpf.length() - 2, cpf.length() - 1)))) && (dig2 == Integer.parseInt(cpf.substring(cpf.length() - 1, cpf.length())))) return Boolean.TRUE;

        } else if (matcher2.matches()) {

            int count1 = 10;
            int count2 = 10;
            int dig1 = 0;
            int dig2 = 0;
            Integer cpfPartido;

            for (int i = 0; i < cpf.length()-1; i++) {

                cpfPartido = Integer.parseInt(cpf.substring(i, i + 1));

                if (count1 >= 2) { 

                    dig1 += cpfPartido * count1; 

                    count1--;

                } else {

                    int sobra = (dig1 % 11);
                    dig1 = 11 - sobra;

                }

                if ((i >= 1) && (count2 >= 2)) {

                    dig2 += cpfPartido * count2;

                    count2--;

                    if (i == 9) {

                        int sobra = (dig2 % 11);
                        dig2 = 11 - sobra;

                    }

                }

            }

            if ((dig1 == (Integer.parseInt(cpf.substring(cpf.length() - 2, cpf.length() - 1)))) && (dig2 == Integer.parseInt(cpf.substring(cpf.length() - 1, cpf.length())))) return Boolean.TRUE;

        }

        return Boolean.FALSE;

    }

    private Usuario recuperaUsuario (String usuario) {

        if (!Usuario.usuarios.containsKey(usuario)) throw new IllegalArgumentException("\nERRO: Usuário não existe!");

        return Usuario.usuarios.get(usuario);

    }

    /**
     * Autentica o usuário verificando se a senha e usuário inserido 
     * está conforme a senha e usuário gravados.
     * 
     * @param u
     * @param s
     * @return {@value true} Se a senha e usuário estiverem corretos.
     * @throws NullPointerException
     * @throws RejectedExecutionException
     * @see Boolean
     * @see String
     */
    @Override
    public Boolean autentica (String u, String s) {
        
        if ((s == null) || (u == null)) throw new NullPointerException("\nERRO: A senha e usuário não podem ser nulos!");

        Usuario user = (Usuario) recuperaUsuario(u);

        if (!(user.getSenha().equals(s)) || (!user.getUsuario().equals(u))) throw new RejectedExecutionException("\nERRO: A senha ou o usuário estão incorretos!");

        this.autenticado = Boolean.TRUE;
        System.out.println("\nAutenticado com sucesso!");
        return Boolean.TRUE;

    }

    /**
     * Autentica o usuário verificando se a senha inserida 
     * está conforme a senha gravados. É um método sobrecarregado para
     * caso o usuário já esteja autenticado porém a senha precisa ser 
     * confirmada para utilizar alguma funcionalidade interna do sistema.
     * 
     * @param s
     * @return {@value true} Se a senha estiver correta.
     * @throws NullPointerException
     * @throws RejectedExecutionException
     * @see Boolean
     * @see String
     */
    @Override
    public Boolean autentica (String s) {

        if (s == null) throw new NullPointerException("\nERRO: A senha não pode ser nula!");
        if (!(getAutenticacao())) throw new IllegalStateException("\nERRO: Usuário tem que estar autenticado para acessar esta área!");
        if (!(this.getSenha().equals(s))) throw new RejectedExecutionException("\nERRO: A senha está incorreta!");

        return Boolean.TRUE;

    }
    
}
