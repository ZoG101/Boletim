package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Professor;
import models.Usuario;

/**
 * A classe {@code DataHelper} é feita para auxiliar
 * a organizar e procurar os dados dos usuários.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.3
 * @see Usuario
 * @see Professor
 * @see Aluno
 * @see Turma
 */
public final class DataHelper {

    /**
     * Método estático e final de ordenação que 
     * irá ordenar com base na ordem de comparação
     * da {@code List} que ele receber. Ele se baseia
     * no método {@code Comparable.compareTo(Object)} da 
     * classe para ordenar, ou seja, os objetos da 
     * {@code List} que entrar aqui devem implementar 
     * {@code Comparable}. 
     * 
     * @param <T> Comparable<T>
     * @param dados
     * @param inicio
     * @param fim
     * @see Comparable#compareTo(Object)
     * @see List
     * @implNote<p>Este é um algorítmo {@code quickSort} que funciona em 
     * ordem da divisão e conquista através de chamadas recursivas. Nele é 
     * definido um {@value Pivô}, como não é recomendado um {@value pivô} fixo 
     * (pois sua posição pode não mais existir durante o processo), ele 
     * será determinado de forma relativa. Depois do {@value pivô} ser determinado 
     * a {@code List} é "quebrada" em outros três tipos: </p>
     * 1. Com elementos menores que o pivô.<br>
     * 2. Com elementos iguais ao pivô.<br>
     * 3. Com elementos maiores que o pivô.
     * <p>Assim teremos os elementos já sendo ordenados durante sua "quebra".
     * Por fim ele retorna todos os elementos quando chega em seu caso base que, 
     * nesse caso, seria quando não houvrem mais elementos para serem divididos.
     * Dessa forma temos uma ordenação muito eficiente. O tempo médio do {@code quickSort} 
     * é {@value c(n) = 0(n log n)}, bem parecido com o {@code mergeSort}, porém com uma leve
     * vantagem por conseguir realizar o processo de retorno mais rápido já
     * que quase todo o trabalho de divisão e ordenação é feito durante a 
     * primeira parte do processo em conjunto. Assim, quando precisar retorna, será
     * quase como um processo de concatenação simples. O {@code quickSort} pode
     * ter um tempo quadrático ({@value c(n) = n^2}) em seu pior caso que seria uma 
     * {@code List} já ordenada com elementos completamente diferentes (de diferentes tipo).
     * Por isso ele considerado um algoritmo instável e, o seu "concorrente", o {@code mergeSort} 
     * já é bem mais estável e possui um tempo de {@value c(n) = 0(n log n)} em quase todos os seus
     * casos.</p>
     */
    public static final <T extends Comparable<T>> void ordena (List<T> dados, int inicio, int fim) {

        if (inicio < fim) {

            int pivot = DataHelper.divide(dados, inicio, fim);

            ordena(dados, inicio, pivot - 1);
            ordena(dados, pivot + 1, fim);

        }

    }

    /**
     * Método auxiliar privado, estático e final que ajuda o método {@code ordena}
     * a encontrar o pivô ({@value meio}) e posicionar os elementos menores a 
     * esquerda e os elementos maiores a direita.
     * 
     * @param <T> Comparable<T>
     * @param x
     * @param inicio
     * @param fim
     * @return {@value postPivot}
     * @see Comparable#compareTo(Object)
     * @see List
     * @implNote<p>O método divide posiciona o pivô ao centro do vetor e 
     * coloca nas posições anteriores a ele os menores elementos, 
     * enquanto os maiores são colocados nas posteriores (Lembrando que
     * o que é considerado maior ou menor que outro elemento dentro de uma
     * {@code List} nesta implementação depende do que foi implementado
     * no método {@code compareTo} da classe dos elementos dentro da {@code List}). 
     * Este é um trabalho realizado pela referência de posição do vetor, 
     * o que facilita o uso de memória de armazenamento. O desempenho é bem 
     * próximo ao de se criar vetores novos, mas, ao criar vetores, 
     * utiliza-se mais espaço de memória.</p>
     */
    private static final <T extends Comparable<T>> int divide (List<T> x, int inicio, int fim) {

        T pivot = x.get(inicio);
        int postPivot = inicio;

        for (int i = inicio + 1; i <= fim; i++) {

            if (x.get(i).compareTo(pivot) < 0) {

                x.set(postPivot, x.get(i));
                x.set(i, x.get(postPivot + 1));

                postPivot++;

            }

        }

        x.set(postPivot, pivot);

        return postPivot;

    }

    /**
     * Método auxiliar estático, privado e final de ordenação que 
     * irá ajudar a ordenar com base no sobrenome de 
     * um {@code Usuario} da {@code List} que ele receber. 
     * Ele se baseia no método {@code Comparable.compareTo(Object)} da 
     * classe para ordenar, ou seja, os objetos da 
     * {@code List} que entrar aqui devem implementar 
     * {@code Usuario}. 
     * 
     * @param <T> Usuario
     * @param dados
     * @param inicio
     * @param fim
     * @see Comparable#compareTo(Object)
     * @see List
     * @implNote<p>Este é um algorítmo {@code quickSort} que funciona em 
     * ordem da divisão e conquista através de chamadas recursivas. Nele é 
     * definido um {@value Pivô}, como não é recomendado um {@value pivô} fixo 
     * (pois sua posição pode não mais existir durante o processo), ele 
     * será determinado de forma relativa. Depois do {@value pivô} ser determinado 
     * a {@code List} é "quebrada" em outros três tipos: </p>
     * 1. Com elementos menores que o pivô.<br>
     * 2. Com elementos iguais ao pivô.<br>
     * 3. Com elementos maiores que o pivô.
     * <p>Assim teremos os elementos já sendo ordenados durante sua "quebra".
     * Por fim ele retorna todos os elementos quando chega em seu caso base que, 
     * nesse caso, seria quando não houvrem mais elementos para serem divididos.
     * Dessa forma temos uma ordenação muito eficiente. O tempo médio do {@code quickSort} 
     * é {@value c(n) = 0(n log n)}, bem parecido com o {@code mergeSort}, porém com uma leve
     * vantagem por conseguir realizar o processo de retorno mais rápido já
     * que quase todo o trabalho de divisão e ordenação é feito durante a 
     * primeira parte do processo em conjunto. Assim, quando precisar retorna, será
     * quase como um processo de concatenação simples. O {@code quickSort} pode
     * ter um tempo quadrático ({@value c(n) = n^2}) em seu pior caso que seria uma 
     * {@code List} já ordenada com elementos completamente diferentes (de diferentes tipo).
     * Por isso ele considerado um algoritmo instável e, o seu "concorrente", o {@code mergeSort} 
     * já é bem mais estável e possui um tempo de {@value c(n) = 0(n log n)} em quase todos os seus
     * casos.</p>
     */
    private static final <T extends Usuario> void ordenaSobrenome (List<T> dados, int inicio, int fim) {

        if (inicio < fim) {

            int pivot = DataHelper.divideSobrenome(dados, inicio, fim);

            ordenaSobrenome(dados, inicio, pivot - 1);
            ordenaSobrenome(dados, pivot + 1, fim);

        }

    }

    /**
     * Método auxiliar privado, estático e final que ajuda o método {@code ordenaSobrenome}
     * a encontrar o pivô ({@value meio}) e posicionar os elementos menores a 
     * esquerda e os elementos maiores a direita com base no sobrenome do {@code Usuario}.
     * 
     * @param <T> Usuario
     * @param x
     * @param inicio
     * @param fim
     * @return {@value postPivot}
     * @see Comparable#compareTo(Object)
     * @see List
     * @implNote<p>O método divide posiciona o pivô ao centro do vetor e 
     * coloca nas posições anteriores a ele os menores elementos, 
     * enquanto os maiores são colocados nas posteriores (Lembrando que
     * o que é considerado maior ou menor que outro elemento dentro de uma
     * {@code List} nesta implementação depende do que foi implementado
     * no método {@code compareTo} da classe dos elementos dentro da {@code List}). 
     * Este é um trabalho realizado pela referência de posição do vetor, 
     * o que facilita o uso de memória de armazenamento. O desempenho é bem 
     * próximo ao de se criar vetores novos, mas, ao criar vetores, 
     * utiliza-se mais espaço de memória.</p>
     */
    private static final <T extends Usuario> int divideSobrenome (List<T> x, int inicio, int fim) {

        T pivot = x.get(inicio);
        int postPivot = inicio;

        for (int i = inicio + 1; i <= fim; i++) {

            if (x.get(i).getSobrenome().compareToIgnoreCase(pivot.getSobrenome()) < 0) {

                x.set(postPivot, x.get(i));
                x.set(i, x.get(postPivot + 1));

                postPivot++;

            }

        }

        x.set(postPivot, pivot);

        return postPivot;

    }

    /**
     * Método de procura que retorna o item procurado dentro da {@code List}.
     * 
     * @param <T> Comparable<T>
     * @param lista
     * @param valor
     * @param inicio
     * @param fim
     * @return {@value T}
     * @throws NoSuchElementException
     * @see Comparable#compareTo(Object)
     * @see List
     * @implNote<p>Executa um algoritmo de busca binária que tem o tempo de execução
     * {@value c(O) = log n}. O algoritmo se baseia em dividir a {@code List} em duas. 
     * Caso o item do ponto de divisão seja menor que o procurado ele procurará a partir do ponto 
     * de divisão para frente (direita), assim, eliminando já boa parte da busca. Caso o elmento
     * do ponto de divisão seja maior que o procurado ele deve ir do ponto de divisão para
     * trás (esquerda). E ele repetirá isso até encontrar o elemento buscado ou determinar que o 
     * elemento buscado não existe.</p> 
     * <p>O ponto de divisão neste tipo de algoritmo pode ser aleatório, 
     * fixo, ou variável. Neste caso foi escolhido um variável que tenta sempre recuperar a posição
     * central da {@code List} ou o mais próximo dela. E, neste algoritmo, essa é sua implementação
     * ideal.</p> 
     * <p>O que vai determinar se um elemento dentro da {@code List} é considerado maior ou menor
     * que o outro durante sua comparação vai ser a implementação individual de cada classe em seu 
     * método {@code compareTo}. Todas as classes que entram neste método deve implementar o 
     * {@code Comparable} para garantir sua comparação de forma adequada.</p>
     */
    public static final <T extends Comparable<T>> T procura (List<T> lista, T valor, int inicio, int fim) {

        if (inicio > fim || (lista.isEmpty())) return null;

        int mid = (inicio + fim) / 2;

        if (lista.get(mid).compareTo(valor) == 0) return lista.get(mid);
        if (lista.get(mid).compareTo(valor) < 0) return procura(lista, valor, mid + 1, fim);
        if (lista.get(mid).compareTo(valor) > 0) return procura(lista, valor, inicio, mid - 1);

        throw new NoSuchElementException("\nERRO: Elemento não encontrado!");

    }

    /**
     * Método de procura que retorna se item procurado existe dentro da {@code List}.
     * 
     * @param <T> Comparable<T>
     * @param lista
     * @param valor
     * @param inicio
     * @param fim
     * @return {@value true} Se o elemento existir dentro da {@code List};
     *         {@value false} Se o elemento não existir dentro da {@code List}.
     * @see Comparable#compareTo(Object)
     * @see Boolean
     * @see List
     * @implNote<p>Executa um algoritmo de busca binária que tem o tempo de execução
     * {@value c(O) = log n}. O algoritmo se baseia em dividir a {@code List} em duas. 
     * Caso o item do ponto de divisão seja menor que o procurado ele procurará a partir do ponto 
     * de divisão para frente (direita), assim, eliminando já boa parte da busca. Caso o elmento
     * do ponto de divisão seja maior que o procurado ele deve ir do ponto de divisão para
     * trás (esquerda). E ele repetirá isso até encontrar o elemento buscado ou determinar que o 
     * elemento buscado não existe.</p> 
     * <p>O ponto de divisão neste tipo de algoritmo pode ser aleatório, 
     * fixo, ou variável. Neste caso foi escolhido um variável que tenta sempre recuperar a posição
     * central da {@code List} ou o mais próximo dela. E, neste algoritmo, essa é sua implementação
     * ideal.</p> 
     * <p>O que vai determinar se um elemento dentro da {@code List} é considerado maior ou menor
     * que o outro durante sua comparação vai ser a implementação individual de cada classe em seu 
     * método {@code compareTo}. Todas as classes que entram neste método deve implementar o 
     * {@code Comparable} para garantir sua comparação de forma adequada.</p>
     */
    public static final <T extends Comparable<T>> Boolean procuraBoolean (List<T> lista, T valor, int inicio, int fim) {

        if (inicio > fim || (lista.isEmpty())) return Boolean.FALSE;

        int mid = (inicio + fim) / 2;

        if (lista.get(mid).compareTo(valor) == 0) return Boolean.TRUE;
        if (lista.get(mid).compareTo(valor) < 0) return procuraBoolean(lista, valor, mid + 1, fim);
        if (lista.get(mid).compareTo(valor) > 0) return procuraBoolean(lista, valor, inicio, mid - 1);

        return Boolean.FALSE;

    }

    /**
     * Método que procura pelo primeiro nome de um {@code Usuario} dentro de uma {@code List} 
     * e retorna o {@code Usuario}.
     * 
     * @param <T> Usuario
     * @param lista
     * @param valor
     * @param inicio
     * @param fim
     * @return <p>{@value Usuario} se o {@code Usuario} com esse primeiro nome existir dentro da {@code List};</p>
     *         <p>{@value null} se o {@code Usuario} com esse primeiro nome não existir dentro da {@code List} 
     *         ou a {@code List} estiver vazia ou não tiver mais como dividir.</p>
     * @see Comparable#compareTo(Object)
     * @see Usuario
     * @see String
     * @see List
     * @implNote<p>Executa um algoritmo de busca binária que tem o tempo de execução
     * {@value c(O) = log n}. O algoritmo se baseia em dividir a {@code List} em duas. 
     * Caso o item do ponto de divisão seja menor que o procurado ele procurará a partir do ponto 
     * de divisão para frente (direita), assim, eliminando já boa parte da busca. Caso o elmento
     * do ponto de divisão seja maior que o procurado ele deve ir do ponto de divisão para
     * trás (esquerda). E ele repetirá isso até encontrar o elemento buscado ou determinar que o 
     * elemento buscado não existe.</p> 
     * <p>O ponto de divisão neste tipo de algoritmo pode ser aleatório, 
     * fixo, ou variável. Neste caso foi escolhido um ponto variável que tenta sempre recuperar a posição
     * central da {@code List} ou o mais próximo dela. E, neste algoritmo, essa é sua implementação
     * ideal.</p> 
     * <p>O que vai determinar se um elemento dentro da {@code List} é considerado maior ou menor
     * que o outro durante sua comparação vai ser a implementação individual de cada classe em seu 
     * método {@code compareTo}. Todas as classes que entram neste método deve estender o 
     * {@code Usuario} para garantir sua comparação de forma adequada.</p>
     */
    public static final <T extends Usuario> Usuario procuraNome (List<T> lista, String nome, int inicio, int fim) {

        if ((inicio >= fim) || (lista.isEmpty())) return null;

        int mid = (inicio + fim) / 2;

        if (lista.get(mid).getNome().compareToIgnoreCase(nome) == 0) return lista.get(mid);
        if (lista.get(mid).getNome().compareToIgnoreCase(nome) < 0) return procuraNome(lista, nome, mid + 1, fim);
        if (lista.get(mid).getNome().compareToIgnoreCase(nome) > 0) return procuraNome(lista, nome, inicio, mid - 1);

        return null;

    }


    /**
     * Método que procura pelo sobrenome de um {@code Usuario} dentro de uma {@code List} 
     * e retorna o {@code Usuario}.
     * 
     * @param <T> Usuario
     * @param lista
     * @param valor
     * @param inicio
     * @param fim
     * @return <p>{@value Usuario} se o {@code Usuario} com esse sobrenome existir dentro da {@code List};</p>
     *         <p>{@value null} se o {@code Usuario} com esse sobrenome não existir dentro da {@code List} 
     *         ou a {@code List} estiver vazia ou não tiver mais como dividir.</p>
     * @see Comparable#compareTo(Object)
     * @see Usuario
     * @see String
     * @see List
     * @implNote<p>Executa um algoritmo de busca binária que tem o tempo de execução
     * {@value c(O) = log n}. O algoritmo se baseia em dividir a {@code List} em duas. 
     * Caso o item do ponto de divisão seja menor que o procurado ele procurará a partir do ponto 
     * de divisão para frente (direita), assim, eliminando já boa parte da busca. Caso o elmento
     * do ponto de divisão seja maior que o procurado ele deve ir do ponto de divisão para
     * trás (esquerda). E ele repetirá isso até encontrar o elemento buscado ou determinar que o 
     * elemento buscado não existe.</p> 
     * <p>O ponto de divisão neste tipo de algoritmo pode ser aleatório, 
     * fixo, ou variável. Neste caso foi escolhido um ponto variável que tenta sempre recuperar a posição
     * central da {@code List} ou o mais próximo dela. E, neste algoritmo, essa é sua implementação
     * ideal.</p> 
     * <p>O que vai determinar se um elemento dentro da {@code List} é considerado maior ou menor
     * que o outro durante sua comparação vai ser a implementação individual de cada classe em seu 
     * método {@code compareTo}. Todas as classes que entram neste método deve estender o 
     * {@code Usuario} para garantir sua comparação de forma adequada.</p>
     */
    public static final <T extends Usuario> Usuario procuraSobrenome (List<T> lista, String nome, int inicio, int fim) {

        List<T> copia = new ArrayList<T>(lista);

        if ((inicio >= fim) || (lista.isEmpty()) || (copia.isEmpty())) return null;

        DataHelper.ordenaSobrenome(copia, inicio, (fim - 1));

        int mid = (inicio + fim) / 2;

        if (copia.get(mid).getSobrenome().compareToIgnoreCase(nome) == 0) return copia.get(mid);
        if (copia.get(mid).getSobrenome().compareToIgnoreCase(nome) < 0) return procuraSobrenome(copia, nome, mid + 1, fim);
        if (copia.get(mid).getSobrenome().compareToIgnoreCase(nome) > 0) return procuraSobrenome(copia, nome, inicio, mid - 1);

        return null;

    }


    /**
     * Método que procura pelo nome completo de um {@code Usuario} dentro de uma {@code List} 
     * e retorna o {@code Usuario}.
     * 
     * @param <T> Usuario
     * @param lista
     * @param valor
     * @param inicio
     * @param fim
     * @return <p>{@value Usuario} se o {@code Usuario} com esse nome completo existir dentro da {@code List};</p>
     *         <p>{@value null} se o {@code Usuario} com esse nome completo não existir dentro da {@code List} 
     *         ou a {@code List} estiver vazia ou não tiver mais como dividir.</p>
     * @see Comparable#compareTo(Object)
     * @see Usuario
     * @see String
     * @see List
     * @implNote<p>Executa um algoritmo de busca binária que tem o tempo de execução
     * {@value c(O) = log n}. O algoritmo se baseia em dividir a {@code List} em duas. 
     * Caso o item do ponto de divisão seja menor que o procurado ele procurará a partir do ponto 
     * de divisão para frente (direita), assim, eliminando já boa parte da busca. Caso o elmento
     * do ponto de divisão seja maior que o procurado ele deve ir do ponto de divisão para
     * trás (esquerda). E ele repetirá isso até encontrar o elemento buscado ou determinar que o 
     * elemento buscado não existe.</p> 
     * <p>O ponto de divisão neste tipo de algoritmo pode ser aleatório, 
     * fixo, ou variável. Neste caso foi escolhido um ponto variável que tenta sempre recuperar a posição
     * central da {@code List} ou o mais próximo dela. E, neste algoritmo, essa é sua implementação
     * ideal.</p> 
     * <p>O que vai determinar se um elemento dentro da {@code List} é considerado maior ou menor
     * que o outro durante sua comparação vai ser a implementação individual de cada classe em seu 
     * método {@code compareTo}. Todas as classes que entram neste método deve estender o 
     * {@code Usuario} para garantir sua comparação de forma adequada.</p>
     */
    public static final <T extends Usuario> Usuario procuraNomeCompleto (List<T> lista, String nome, int inicio, int fim) {

        if ((inicio >= fim) || (lista.isEmpty())) return null;

        int mid = (inicio + fim) / 2;

        if (lista.get(mid).getNomeCompleto().compareToIgnoreCase(nome) == 0) return lista.get(mid);
        if (lista.get(mid).getNomeCompleto().compareToIgnoreCase(nome) < 0) return procuraNomeCompleto(lista, nome, mid + 1, fim);
        if (lista.get(mid).getNomeCompleto().compareToIgnoreCase(nome) > 0) return procuraNomeCompleto(lista, nome, inicio, mid - 1);

        return null;

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
    public static Boolean verificaSenha (String s) {

        Pattern formato = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?!.*[ !@#$%^&*_=+-]).{6,20}$");
        Matcher confirma = formato.matcher(s);

        if(confirma.matches()) return Boolean.TRUE;
        return Boolean.FALSE;

    }

    /**
     * Método auxiliar que verifica se o nome de usuário inserido para
     * cadastro está de acordo com as regras da instituição.
     * 
     * @param u
     * @return {@value true} se o nome de usuário estiver de acordo com as regras da instituição;
     *         {@value false} se o nome de usuário não estiver de acordo com as regras da instituição.
     * @see Boolean
     * @see String
     * @see Pattern
     * @see Matcher
     */
    public static Boolean verificaUsuário (String u) {

        Pattern formato = Pattern.compile("^(?!.*[ !@#$%^&*_=+-]).{4,20}$");
        Matcher confirma = formato.matcher(u);

        if ((Professor.getUsuarios().containsKey(u))) throw new IllegalArgumentException("\nERRO: Nome de usuário já está em uso, tente outro.");
        if ((!confirma.matches())) return Boolean.FALSE;
        return Boolean.TRUE;

    }

    public static Boolean exiteUsuario (String nomeUsuario) {

        if ((nomeUsuario == null) || (nomeUsuario.compareToIgnoreCase("") == 0)) return Boolean.FALSE;
        if (Usuario.getUsuarios().containsKey(nomeUsuario)) return Boolean.TRUE;
        return Boolean.FALSE;

    }

    /**
     * Método auxiliar para verificar formatos de {@code email} que
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
    public static Boolean verificaEmail (String email) {

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
    public static Boolean verificaCPF (String cpf) {

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

    public static String reformataCPF(String cpf) {

        Pattern formato = Pattern.compile("^([0-9]){3}([0-9]){3}([0-9]){3}([0-9]){2}$");
        Matcher confirma = formato.matcher(cpf);

        if (confirma.matches()) {
 
            String cpfReformatado = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
            return cpfReformatado;

        }

        return cpf;

    }

    public static String formataCPF(String cpf) {

        Pattern formato = Pattern.compile("^([0-9]){3}.([0-9]){3}.([0-9]){3}-([0-9]){2}$");
        Matcher confirma = formato.matcher(cpf);

        if (confirma.matches()) cpf = cpf.replaceAll("[.-]", "");
        return cpf;

    }

    public static Boolean verificaTelefone (String telefone) {

        Pattern formato = Pattern.compile("^(?=.*[0-9])(?!.*[a-z])(?!.*[A-Z])(?!.*[!@#$%^&*_=+.]).{12,15}$");
        Matcher matcher = formato.matcher(telefone);

        if (!matcher.matches()) return Boolean.FALSE;
        return Boolean.TRUE;

    }

    public static String formataTelefone (String telefone) {

        Pattern formato = Pattern.compile("^(?=.*[0-9])(?=.*[)(])(?!.*[a-z])(?!.*[A-Z])(?!.*[!@#$%^&*_=+.]).{15}$");
        Matcher matcher = formato.matcher(telefone);
        
        if (matcher.matches()) return telefone = "0" + telefone.replaceAll("[ )(-]", "");
        return telefone;

    }

    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do {@code Cli}. Este método recebe um caracter e um {@code Iteger}
     * e, com base nesse parametro, o mesmo irá repetir o caracter inserido
     * o número de vezes que lhe for informado no parâmetro de {@code tamanho}.
     * 
     * @param c
     * @param tamanho
     * @return {@value String} com um caractere repetido quantas vezes
     *         for escolhido.
     * @see String
     * @see Integer
     * @see Arrays
     */
    public static String repeteCaracter (char c, Integer tamanho) {

        char[] caracteres = new char[tamanho.intValue()];
        Arrays.fill(caracteres, c);
        return new String(caracteres);

    }

}
