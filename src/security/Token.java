package security;

import java.util.Random;

public final class Token {

    private Integer token;

    public Token () {

        this.token = setToken();
        System.out.println(this.token);

    }

    private Integer setToken () {

        Random aleatorio = new Random();
        String token = "" + aleatorio.nextInt(9) + aleatorio.nextInt(9) + aleatorio.nextInt(9) + aleatorio.nextInt(9) + aleatorio.nextInt(9) + aleatorio.nextInt(9);
        Integer result = Integer.valueOf(token);
        return result;

    }

    public Integer getToken () {

        return token;

    }

    public Boolean verificaToken (Integer token) {

        return this.equals(token);

    }

    @Override
    public boolean equals(Object obj) {
        
        if (!(obj instanceof Integer)) throw new IllegalArgumentException("ERRO: O objeto passado deve ser uma instância de 'Integer'!");

        Integer token = (Integer) obj;
        return this.getToken() == token.intValue();

    }
    
}
