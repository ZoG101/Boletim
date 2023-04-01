package models;

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

    public abstract String escolherMateria ();

    @Override
    public String toString () {

        return this.escolherMateria();

    }

}
