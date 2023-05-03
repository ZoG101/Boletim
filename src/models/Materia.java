package models;

/**
 * O enum {@code Matéria} é feito para controle das constantes
 * que armazenam as matérias que um {@code Aluno} pode possuir e que um
 * {@code Professor} pode ministrar.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.0
 * @see Aluno
 * @see Professor
 * @see Turma
 * @see String
 */
public enum Materia {
    
    MATEMATICA {
        
        @Override
        public String escolherMateria () {
            
            return new String("MATEMÁTICA");

        }

    }, 
    
    PORTUGUES {

        @Override
        public String escolherMateria () {
            
            return new String("PORTUGUÊS");

        }

    }, 

    QUIMICA {

        @Override
        public String escolherMateria () {
            
            return new String("QUÍMICA");

        }

    },

    FISICA {

        @Override
        public String escolherMateria () {
            
            return new String("FÍSICA");

        }

    },

    HISTORIA {

        @Override
        public String escolherMateria () {
            
            return new String("HISTÓRIA");

        }

    },

    INGLES {

        @Override
        public String escolherMateria () {
            
            return new String("INGLÊS");

        }

    },
    
    GEOGRAFIA {

        @Override
        public String escolherMateria () {
            
            return new String("GEOGRAFIA");

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
