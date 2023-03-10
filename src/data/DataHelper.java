package data;

import java.util.List;
import java.util.NoSuchElementException;

import models.Usuario;

/**
 * A classe {@code DataHelper} é feita para auxiliar
 * a organizar e procurar os dados dos usuários.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.0
 * @see Usuario
 * @see Professor
 * @see Aluno
 * @see Turma
 */
public final class DataHelper {

    /**
     * Método estático genérico de ordenação que 
     * irá ordenar com base na ordem de comparação
     * da {@code List} que ele receber. Ele se baseia
     * no método {@code Comparable.compareTo(Object)} da 
     * classe para ordenar, ou seja, os objetos da 
     * {@code List} que entrar aqui deve implementar 
     * {@code Comparable}. 
     * 
     * @param <T extends Comparable<T>>
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
     * Método auxiliar privado e estático que ajuda o método {@code ordena}
     * a encontrar o pivô ({@value meio}) e posicionar os elementos menores a 
     * esquerda e os elementos maiores a direita.
     * 
     * @param <T extends Comparable<T>>
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
     * Método de procura que retorna o item procurado dentro da {@code List}.
     * 
     * @param <T>
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
     * @param <T>
     * @param lista
     * @param valor
     * @param inicio
     * @param fim
     * @return {@value true} Se o elemento existir dentro da {@code List};
     *         {@value false} Se o elemento não existir dentro da {@code List}.
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
    public static final <T extends Comparable<T>> Boolean procuraBoolean (List<T> lista, T valor, int inicio, int fim) {

        if (inicio > fim || (lista.isEmpty())) return Boolean.FALSE;

        int mid = (inicio + fim) / 2;

        if (lista.get(mid).compareTo(valor) == 0) return Boolean.TRUE;
        if (lista.get(mid).compareTo(valor) < 0) procura(lista, valor, mid + 1, fim);
        if (lista.get(mid).compareTo(valor) > 0) procura(lista, valor, inicio, mid - 1);

        return Boolean.FALSE;

    }

    /* 
    public static final <T extends Usuario> Usuario procuraNome (List<T> lista, String nome, int inicio, int fim) {

        if ((inicio > fim) || (lista.isEmpty())) return null;

        int mid = (inicio + fim) / 2;
        String l = lista.get(mid).getNome();

        if (lista.get(mid).getNome().compareToIgnoreCase(nome) == 0) return lista.get(mid);
        if (lista.get(mid).getNome().compareToIgnoreCase(nome) < 0) procuraNome(lista, nome, mid + 1, fim);
        if (lista.get(mid).getNome().compareToIgnoreCase(nome) > 0) procuraNome(lista, nome, inicio, mid - 1);

        return null;

    }
    */

    public static final <T extends Usuario> Usuario procuraNome (List<T> lista, String nome, int inicio, int fim) {

        if ((inicio > fim) || (lista.isEmpty())) return null;

        int mid = (inicio + fim) / 2;

        if (lista.get(mid).getNome().compareToIgnoreCase(nome) == 0) return lista.get(mid);
        if (lista.get(mid).getNome().compareToIgnoreCase(nome) < 0) return procuraNome(lista, nome, mid + 1, fim);
        if (lista.get(mid).getNome().compareToIgnoreCase(nome) > 0) return procuraNome(lista, nome, inicio, mid - 1);

        return null;

    }

}
