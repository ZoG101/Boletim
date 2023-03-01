package security;

import java.util.Random;

/**
 * A classe {@code Professor} é feita como uma abstração
 * de um professor real que possui seus {@code Alunos} e 
 * contém suas informações básicas e de maior interesse.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.1
 * @see Random
 * @see Integer
 * @see String
 * @see Boolean
 */
public final class Token {

    private Integer token;

    /**
     * Constructor padrão da classe {@code Token} que chama a 
     * função para a criação de um novo {@code token}.
     */
    public Token () {

        this.token = setToken();
        this.enviaToken();

    }

    /**
     * Método privado que cuida de criar um token formatá-lo e executar um cast para 
     * {@code Integer}.
     * 
     * @return {@value resultado}
     * @see Random
     * @see String
     * @see Integer
     */
    private Integer setToken () {

        Random aleatorio = new Random();
        String token = "" + aleatorio.nextInt(9) + aleatorio.nextInt(9) + aleatorio.nextInt(9) + aleatorio.nextInt(9) + aleatorio.nextInt(9) + aleatorio.nextInt(9);
        Integer resultado = Integer.valueOf(token);
        return resultado;

    }

    /**
     * Método auxiliar que envia o token para a pessoa.
     */
    private void enviaToken () {

        System.out.println(this.getToken());

    }

    /**
     * Método auxiliar que recupera o valor do {@code Token} para
     * a própria classe.
     * 
     * @return {@value token}
     * @see Integer
     */
    private Integer getToken () {

        return this.token;

    }

    /**
     * verifica se o {@value token} é nulo e repassa o valor do {@value token}
     * para o método auxiliar sobreescrito {@index equals} que avalia se o {@value token}
     * bate ou não.
     * 
     * @param token
     * @return {@value true} se o {@value token} passado bate com o gerado pela classe;
     *         {@value false} se o {@value token} não bate com o gerado pela classe.
     * @throws nullPointerException
     * @see Boolean
     * @see Integer
     */
    public Boolean verificaToken (Integer token) {

        if (token == null) throw new NullPointerException("ERRO: O token não pode ser nulo!");

        return this.equals(token);

    }

    /**
     * Método que efetivamente avalia a paridade entre o token passado
     * e o gerado pela classe.
     * 
     * @param obj
     * @return {@value true} se o {@value obj} passado bate com o gerado pela classe;
     *         {@value false} se o {@value obj} passado não bate com o gerado pela classe.
     * @throws NullPointerException
     * @throws IllegalArgumentException
     * @see Object
     * @see Integer
     * @see Boolean
     */
    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) throw new NullPointerException("ERRO: O objeto passado não pode ser nulo!");
        if (!(obj instanceof Integer)) throw new IllegalArgumentException("ERRO: O objeto passado deve ser uma instância de 'Integer'!");

        if (this == obj) return true;
        Integer token = (Integer) obj;
        return this.getToken() == token.intValue();

    }
    
}
