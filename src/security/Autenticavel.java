package security;

/**
 * Interface cujo objetivo é garantir a autenticação daqueles
 * que a implementão.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.1
 */
public interface Autenticavel {
    
    /**
     * Recebe dois parametros em forma de {@code String} e 
     * deve autenticá-los.
     * 
     * @param u
     * @param s
     * @return {@value Boolean}
     */
    public Boolean autentica (String u, String s);

    /**
     * Recebe um parametro em forma de {@code String} e 
     * deve autenticá-la.
     * 
     * @param s
     * @return {@value Boolean}
     */
    public Boolean autentica (String s);

}
