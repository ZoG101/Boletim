package models;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * A classe {@code Boletim} é feita para gerenciar
 * as coleções de notas dos alunos, professor responsável e
 * suas operações.
 * 
 * @author Davi Campolina Leite Morato
 * @version 1.0
 * @see Aluno
 * @see Professor
 * @see Turma
 * @see Serializable
 */
public class Boletim implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Double> notas;
    private double ultimaNotaAdicionada;
    private double falta;
    private double media;
    private boolean aprovacao;
    private String materia;
    private String nivel;
    private String professorNome;
    private String alunoNome;
    private LocalDate hoje;

    /**
     * Construtor padrão da classe precisa ter pelo menos um {@code aluno}
     * e um {@code professor} recebidos para a construção do objeto já que a 
     * gerencia das notas e reponsabilidades atribuidas pelo mesmo 
     * precisam ser reconhecidas.
     * 
     * @param aluno
     * @param professor
     * @see Aluno
     * @see Professor
     * @see Turma
     */
    public Boletim (Aluno aluno, Professor professor) {

        if (aluno == null || professor == null) throw new NullPointerException("\nERRO: Para criar um boletim nem o aluno ou professor podem ser nulos!");

        this.materia = professor.getMateria();
        this.aprovacao = false;
        this.nivel = aluno.getNivel();
        this.alunoNome = aluno.getNomeCompleto();
        this.professorNome = professor.getNomeCompleto();
        notas = new ArrayList<Double>(0);

    }

    /**
     * Returna a porcentagem da quantidade de faltas basendo-se na quantidade
     * padrão de um ano {@value 365} dias.
     * 
     * @return {@value falta}
     * @exception IllegalArgumentException
     * @see Double
     */
    public Double getFalta () {

        if (this.falta == 0) return 0.;

        return (365/100) * this.falta;

    }

    /**
     * Retorna uma {@code String} formatada da média do aluno.
     * 
     * @return {@value media}
     * @see String
     */
    public String getMediaFormatada () {

        return String.format("%.1f", this.media);

    }

    /**
     * Retorna a ultima nota adicionada na orde de adição.
     * 
     * @return {@value ultimaNotaAdicionada}
     * @see Double
     */
    public Double getUltimaNotaAdicionada () {

        return this.ultimaNotaAdicionada;

    }

    /**
     * Retona uma versão formatada em {@code String} da
     * coleção de notas.
     * 
     * @return {@value notas}
     * @see String#toString()
     */
    public String getNotas () {

        return notas.toString();

    }

    /**
     * Retorna o estado de aprovação do aluno.
     * 
     * @return {@value aprovacao}
     * @See Boolean
     */
    public Boolean getAprovado () {

        return this.aprovacao;

    }

    /**
     * Retorna uma {@code String} matéria relacionada a este boletim.
     * 
     * @return {@value materia}
     * @see String
     */
    public String getMateria () {

        return this.materia;

    }

    /**
     * 
     * Retorna uma {@code String} nivel de estudo do aluno.
     * 
     * @return {@value nivel}
     * @see String
     */
    public String getNivel () {

        return this.nivel;

    }

    /**
     * Retorna uma {@code String} contendo o nome do {@code professor} responsável por este
     * boletim.
     * 
     * @return {@value professorNome}
     * @see String
     */
    public String getProfessorNome () {

        return professorNome;

    }

    /**
     * Retorna uma {@code String} contendo o nome do {@code aluno} deste respectivo 
     * boletim.
     * 
     * @return {@value alunoNome}
     * @see String
     */
    public String getAlunoNome () {

        return alunoNome;

    }

    /**
     * Verifica e executa o cálculo da média do aluno de
     * {@code GRADUACAO}.
     * 
     * @param AM
     * @param AC
     * @param AS
     * @return {@value media}
     * @see Aluno
     * @see Double
     */
    private Double verificarMediaGraduacao (double AM, double AC, double AS) {

		this.setMedia(AM * 0.3 + AC * 0.2 + AS * 0.5);
		return media;

    }

    /**
     * Executa o cálculo da média do aluno de
     * {@code POSGRADUACAO}.
     * 
     * @param PM
     * @param AS
     * @return {@value media}
     * @see Aluno
     * @see Double
     */
    private Double verificarMediaPosGraduacao (double PM, double AS) {

        this.setMedia(PM * 0.4 + AS * 0.6);
		return media;

    }

    /**
     * Verifica a aprovação do aluno executando algumas verificações
     * preventivas com base na quantidade de notas adicionadas nacoleção para 
     * evitar exceções de {@code Collections} e também já chama a verificação da
     * média com base no nível do aluno. 
     * 
     * Além disso jogará uma exceção em caso de tamnho indequado
     * da coleção de notas.
     * 
     * @exception IllegalArgumentException
     * @see Collections
     * @see Double
     */
    private void verificarAprovacao () {

        Double a = Double.valueOf(0);
        Double b = Double.valueOf(0);
        Double c = Double.valueOf(0);

        if (this.notas.size() > 0 && this.notas.size() <= 3) {

            switch (this.notas.size()){

                case 1:

                    a = Double.valueOf(this.notas.get(0));

                break;

                case 2: 

                    a = Double.valueOf(this.notas.get(0));
                    b = Double.valueOf(this.notas.get(1));

                break;

                case 3:

                    a = Double.valueOf(this.notas.get(0));
                    b = Double.valueOf(this.notas.get(1));
                    c = Double.valueOf(this.notas.get(2));

                break;

            }

        } else {

            throw new IllegalStateException("ERRO: Tamanho inválido de conjunto de notas!");

        }

        switch (this.nivel) {

            case "GRADUACAO": 

                if (((verificarMediaGraduacao(a, b, c) >= 7)) && ((getFalta()) < 15)) this.aprovacao = true;

            break;

            case "POSGRADUACAO": 
            
                if (((verificarMediaPosGraduacao(a, b)) >= 7) && ((getFalta()) < 15)) this.aprovacao = true;
            
            break;

        }

    }

    /**
     * Método polimórfico estático para a adição de uma {@code falta} única.
     */
    public void setFalta () {

        this.falta++;

    }

    /**
     * Método polimórfico estático para a adição de um número de {@code faltas} maior.
     * 
     * @param faltas
     */
    public void setFalta (int faltas) {

        this.falta += faltas;

    }

    /**
     * Método para especificar a média do aluno em uma variável
     * específica e gravado no objeto da classe.
     * 
     * @param m
     */
    private void setMedia (Double m) {

        this.media = m;

    }

    /**
     * Método polimórfico estático para a adição de uma número personalizado de
     * notas, recebendo {@code Double}, dentro do limite permitido para cada 
     * tipo de {@code Aluno} dependendo do seu nível.
     * 
     * Este método também trata as notas adequadamente e, recursivamente, repassa
     * cada nota para um segundo método especializado para adicionar as notas 
     * dentro da coleção do boletim.
     * 
     * @param notas
     * @see Aluno
     * @see Double
     */
    public void setNota (Double... notas) {

        if ((this.getNivel().equals("GRADUACAO")) && ((this.notas.size() == 3) || (notas.length > 3))) throw new ArrayIndexOutOfBoundsException("ERRO: Todas as notas de graduação foram adicionadas neste boletim!");
        if ((this.getNivel().equals("POSGRADUACAO")) && ((this.notas.size() == 2))) throw new ArrayIndexOutOfBoundsException("ERRO: Todas as notas de pós-graduação foram adicionadas neste boletim!");

        int c = 1;

        for (Double nota : notas) {
            
            if (nota == null) throw new NullPointerException("ERRO: A nota" + c + " deve ser inserida para ser adicionada!");
            if ((nota > 10) || (nota < 0)) throw new IllegalArgumentException("ERRO: A escala das notas é de zero (0) a dez (10)!");

            this.setNotas(nota);

            c++;

        }

    }

    /**
     * Método polimórfico estático para a adição de uma número personalizado de
     * notas, recebendo {@code Integer}, dentro do limite permitido para cada 
     * tipo de {@code Aluno} dependendo do seu nível. 
     * 
     * Este método também trata as notas adequadamente e, recursivamente, repassa
     * cada nota para um segundo método auxiliar especializado para adicionar as notas 
     * dentro da coleção do boletim.
     * 
     * @param notas
     * @see Aluno
     * @see Integer
     * @see Double
     */
    public void setNota (Integer... notas) {

        if ((this.getNivel().equals("GRADUACAO")) && ((this.notas.size() == 3) || (notas.length > 3))) throw new ArrayIndexOutOfBoundsException("ERRO: Todas as notas de graduação foram adicionadas neste boletim!");
        if ((this.getNivel().equals("POSGRADUACAO")) && ((this.notas.size() == 2) || (notas.length > 2))) throw new ArrayIndexOutOfBoundsException("ERRO: Todas as notas de pós-graduação foram adicionadas neste boletim!");

        ArrayList<Double> notasCast = new ArrayList<Double>();

        int c = 1;

        for (Integer nota : notas) {
            
            if ((nota > 10) || (nota < 0)) throw new IllegalArgumentException("ERRO: A escala das notas é de zero (0) a dez (10)!");
            Double n = Double.valueOf(nota);
            notasCast.add(n);

            c++;

        }

        c = 0;

        for (Double nota : notasCast) {
            
            if (nota == null) throw new NullPointerException("ERRO: A nota" + c + " deve ser inserida para ser adicionada!");

            this.setNotas(nota);

            c++;

        }

    }

    /**
     * Método auxiliar privado que adiciona propriamente as 
     * notas dentro da coleção do boletim
     * 
     * @param nota
     * @see Double
     * @see List
     * @see ArrayList
     */
    private void setNotas (Double nota) {

        this.notas.add(nota);

        this.verificarAprovacao();

        this.ultimaNotaAdicionada = nota;

    }

    /**
     * Método para especificar a matéria a qual se referencia
     * este respectivo objeto de {@code Boletim}.
     * 
     * @param materia
     * @see String
     */
    public void setMateria (String materia) {

        this.materia = materia;

    }

    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do boletim. Este método recebe um caracter e um {@code Iteger}
     * e, com base nesse parametro, o mesmo irá repetir o caracter inserido
     * o número de vezes que lhe for informado no parâmetro de {@code tamanho}.
     * 
     * @param c
     * @param tamanho
     * @return String
     * @see String
     * @see Integer
     * @see Arrays
     */
    private String repeteCaracter (char c, Integer tamanho) {

        char[] caracteres = new char[tamanho.intValue()];
        Arrays.fill(caracteres, c);
        return new String(caracteres);

    }

    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do boletim. Este método irá imprimir o título/cabeçalho do {@code Boletim}.
     * 
     * @see LocalDate
     * @see String
     * @see TextStyle
     * @see Locale
     */
    private void printTitulo () {

        hoje = LocalDate.now();
        String titulo = String.format("Boletim do Aluno - Mês: %s Ano: %-44d|\n| Nome do Aluno: %-64s|\n| Nome do Professor: %-60s|\n| Matéria: %-70s|",
        hoje.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")),
        hoje.getYear(), this.getAlunoNome(), this.getProfessorNome(), this.getMateria());
        System.out.printf("| %-79s\n", titulo);

    }

    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do boletim. Este método imprimio cabeçalho de notas.
     * 
     * @see String
     */
    private void printCabecalhoNotas () {

        String cabecalho = String.format("|%40.5s", "Notas");
        System.out.printf("%-81s|\n", cabecalho);

    }

    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do boletim. Imprimi cebçalho para graduação.
     * 
     * @see String
     */
    private void printCabecalhoGraduacao () {

        String am = String.format("|%13s", "AM");
        System.out.printf("%-26s|", am);

        String ac = String.format("%13s", "AC");
        System.out.printf("%-26s|", ac);

        String as = String.format("%14s", "AS");
        System.out.printf("%-27s|\n", as);

    }

    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do boletim da graduação. Este método formata corretamento as notas para tratar
     * as notas dentro da coleção para evitar exceção de tamanho de coleção.
     * Também imprimi os valores para a tabela junto com sua formatação.
     * 
     * @see Double
     * @see String
     * @throws IllegalStateException
     */
    private void printNotasGraduacao () {

        Double a = Double.valueOf(0);
        Double b = Double.valueOf(0);
        Double c = Double.valueOf(0);

        if (this.notas.size() > 0 && this.notas.size() <= 3) {

            switch (this.notas.size()){

                case 1:

                    a = Double.valueOf(this.notas.get(0));

                break;

                case 2: 

                    a = Double.valueOf(this.notas.get(0));
                    b = Double.valueOf(this.notas.get(1));

                break;

                case 3:

                    a = Double.valueOf(this.notas.get(0));
                    b = Double.valueOf(this.notas.get(1));
                    c = Double.valueOf(this.notas.get(2));

                break;

            }

        } else {

            throw new IllegalStateException("ERRO: Tamanho inválido de conjunto de notas!");

        }

        String am = String.format("|%13.5s", a);
        System.out.printf("%-26s|", am);

        String ac = String.format("%13.5s", b);
        System.out.printf("%-26s|", ac);

        String as = String.format("%14.5s", c);
        System.out.printf("%-27s|\n", as);

    }

    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do boletim. Imprimi cebçalho para pós-graduação.
     * 
     * @see String
     */
    private void printCabecalhoPosGraduacao () {

        String pm = String.format("|%20s", "PM");
        System.out.printf("%-40s|", pm);

        String as = String.format("%20s", "AS");
        System.out.printf("%-40s|\n", as);

    }

    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do boletim da pós-graduação. Este método formata corretamento as notas para tratar
     * as notas dentro da coleção para evitar exceção de tamanho de coleção.
     * Também imprimi os valores para a tabela junto com sua formatação.
     * 
     * @see Double
     * @see String
     * @throws IllegalStateException
     */
    private void printNotasPosGraduacao () {

        Double a = Double.valueOf(0);
        Double b = Double.valueOf(0);

        if (this.notas.size() > 0 && this.notas.size() <= 2) {

            switch (this.notas.size()){

                case 1:

                    a = Double.valueOf(this.notas.get(0));

                break;

                case 2: 

                    a = Double.valueOf(this.notas.get(0));
                    b = Double.valueOf(this.notas.get(1));

                break;

            }

        } else {

            throw new IllegalStateException("ERRO: Tamanho inválido de conjunto de notas!");

        }

        String am = String.format("|%20s", a);
        System.out.printf("%-40s|", am);

        String ac = String.format("%20s", b);
        System.out.printf("%-40s|\n", ac);

    }

    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do boletim. Imprimi o cebçalho da média.
     * 
     * @see String
     */
    private void printMediaCabecalho() {

        String cabecalho = String.format("|%40.5s", "Média");
        System.out.printf("%-81s|\n", cabecalho);

    }

    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do boletim. Imprimi a média do {@code Aluno}.
     * 
     * @see String
     */
    private void printMedia () {

        String media = String.format("|%39s", this.getMediaFormatada());
        System.out.printf("%-81s|\n", media);

    }

    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do boletim. Imprimi "APROVADO" no {@code boletim} caso o 
     * {@code Aluno} tenha sido aprovado.
     * 
     * @see String
     */
    private void printAprovado () {

        String aprovado = String.format("|%40.5s", "APROVADO");
        System.out.printf("%-81s|\n", aprovado);

    }

    /**
     * Método auxiliar privado de formatação para a formação e exibição
     * do boletim. Imprimi "REPROVADO" no {@code boletim} caso o 
     * {@code Aluno} tenha sido reprovado.
     * 
     * @see String
     */
    private void printReprovado () {

        String reprovado = String.format("|%40.5s", "REPROVADO");
        System.out.printf("%-81s|\n", reprovado);

    }

    /**
     * Método que gera o boletim formatado adequadamente chamando todos os
     * outros métodos auxiliares de formatação de forma adequada dependendo
     * do nível do {@code Aluno}.
     * 
     * @see String
     */
    public void gerarBoletim () {

        System.out.println("|" + repeteCaracter('-', 80) + "|");
        this.printTitulo();
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        this.printCabecalhoNotas();
        System.out.println("|" + repeteCaracter('-', 80) + "|");

        switch (this.getNivel()) {

            case "GRADUACAO": 

                printCabecalhoGraduacao();
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                printNotasGraduacao();
                System.out.println("|" + repeteCaracter('-', 80) + "|");

            break;

            case "POSGRADUACAO": 
            
                printCabecalhoPosGraduacao();
                System.out.println("|" + repeteCaracter('-', 80) + "|");
                printNotasPosGraduacao ();
                System.out.println("|" + repeteCaracter('-', 80) + "|");

            
            break;

        }


        printMediaCabecalho();
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        printMedia();
        System.out.println("|" + repeteCaracter('-', 80) + "|");

        if (this.getAprovado()) printAprovado();
        else printReprovado();

        System.out.println("|" + repeteCaracter('-', 80) + "|");
        
    }
    
}
