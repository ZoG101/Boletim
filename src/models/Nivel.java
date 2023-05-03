package models;

/**
 * O enum {@code Nivel} é feito para controle das constantes
 * que armazenam os níveis escolares que um {@code Aluno} pode possuir e que um
 * {@code Professor} pode ministrar.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.0
 * @see Aluno
 * @see Professor
 * @see Turma
 * @see String
 */
public enum Nivel {

    GRADUACAO {

        @Override
        public String escolherMateria () {
            
            return new String("GRADUAÇÃO");

        }

    },
    
    POSGRADUACAO {

        @Override
        public String escolherMateria () {
            
            return new String("PÓS-GRADUAÇÃO");

        }
        
    };
    
    /**
     * Método abstrado que retornará a matéria
     * determinada na instância do enum.
     * 
     * @return {@value Matéria} determinada.
     */
    public abstract String escolherMateria ();

    @Override
    public String toString () {

        return this.escolherMateria();

    }
    
}
