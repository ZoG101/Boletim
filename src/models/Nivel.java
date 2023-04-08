package models;

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

    public abstract String escolherMateria ();

    @Override
    public String toString () {

        return this.escolherMateria();

    }
    
}
