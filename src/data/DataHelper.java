package data;

import java.util.List;
import java.util.NoSuchElementException;

public final class DataHelper {

    public static final <T extends Comparable<T>> void ordena (List<T> dados, int inicio, int fim) {

        if (inicio < fim) {

            int pivot = DataHelper.divide(dados, inicio, fim);

            ordena(dados, inicio, pivot - 1);
            ordena(dados, pivot + 1, fim);

        }

    }

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

    public static final <T extends Comparable<T>> T procura (List<T> lista, T valor, int inicio, int fim) {

        if (inicio > fim) return null;

        int mid = (inicio + fim) / 2;

        if (lista.get(mid).compareTo(valor) == 0) return lista.get(mid);
        if (lista.get(mid).compareTo(valor) < 0) return procura(lista, valor, mid + 1, fim);
        if (lista.get(mid).compareTo(valor) > 0) return procura(lista, valor, inicio, mid - 1);

        throw new NoSuchElementException("\nERRO: Elemento não encontrado!");

    }

    public static final <T extends Comparable<T>> Boolean procuraBoolean (List<T> lista, T valor, int inicio, int fim) {

        if (inicio > fim) return Boolean.FALSE;

        int mid = (inicio + fim) / 2;

        if (lista.get(mid).compareTo(valor) == 0) return Boolean.TRUE;
        if (lista.get(mid).compareTo(valor) < 0) procura(lista, valor, mid + 1, fim);
        if (lista.get(mid).compareTo(valor) > 0) procura(lista, valor, inicio, mid - 1);

        return Boolean.FALSE;

    }
    
}
