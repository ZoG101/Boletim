package security;

/**
 * Interface cujo objetivo é garantir a autenticação daqueles
 * que a implementão.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.0
 */
public interface Autenticavel {
    
    /**
     * Recebe um parametro em forma de {@code String} e 
     * deve autenticá-la.
     * 
     * @param s
     * @return {@value Boolean}
     */
    public Boolean autentica (String s);

}
