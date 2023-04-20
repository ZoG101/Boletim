package ui;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import models.Aluno;
import models.Boletim;
import models.Materia;
import models.Professor;
import models.Turma;

public abstract class UserCli {

    private Scanner scan;
    Professor usuarioProfessor;
    Aluno usuarioAluno;

    protected void alunoLogado (Aluno aluno) {

        Integer opcao;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String intrudocao = String.format("|%20s", aluno.getNomeCompleto());
            System.out.printf("%-81s|\n", intrudocao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Ver informações de perfil");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%20s", "2.Boletim");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%20s", "3.Sair");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Ver Informações de perfil.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.println(aluno.toString());

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Boletim.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.println(aluno.getBoletins());

                break;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Sair... Que pena, até a póxima.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }

        } while (opcao.intValue() != 3);

    }

    protected void professorLogado (Professor professor) {

        Integer opcao;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String intrudocao = String.format("|%20s", professor.getNomeCompleto());
            System.out.printf("%-81s|\n", intrudocao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Ver informações de perfil");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%20s", "2.Procurar boletim");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%20s", "3.Procurar aluno");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String quartaOpcao = String.format("|%20s", "4.Exibir turmas");
            System.out.printf("%-81s|\n", quartaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String quintaOpcao = String.format("|%20s", "5.Procurar turma por ID");
            System.out.printf("%-81s|\n", quintaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String sextaOpcao = String.format("|%20s", "6.Criar turma");
            System.out.printf("%-81s|\n", sextaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String setimaOpcao = String.format("|%20s", "7.Procurar aluno por nome");
            System.out.printf("%-81s|\n", setimaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String oitavaOpcao = String.format("|%20s", "8.Procurar aluno por ID");
            System.out.printf("%-81s|\n", oitavaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String nonaOpcao = String.format("|%20s", "9.Criar boletim");
            System.out.printf("%-81s|\n", nonaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String decimaOpcao = String.format("|%20s", "10.Sair");
            System.out.printf("%-81s|\n", decimaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Ver Informações de perfil.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    System.out.println(professor.toString());

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar boletim.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    
                    try {

                        this.procuraBoletim(professor);

                    } catch (Exception e) {

                        System.err.println(e.getMessage());

                    }

                break;

                case 4:

                    System.out.printf("|Sua escolha: %-67s|\n", "Exibir turmas.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");
                    Map<String, Turma> turmas = null;

                    try {
                        
                        turmas = professor.getTurmas();

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    if ((turmas == null) || (turmas.isEmpty())) {

                        System.err.println("\nVocê não possui turmas.");
                        break;

                    }

                    Collection<Turma> turmasColecao = turmas.values();

                    for (Turma turma : turmasColecao) {

                        System.out.println(turma.getAlunos());

                    }

                break;

                case 5:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar turma por ID.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        System.out.println(this.retornaTurma(professor));

                    } catch (Exception e) {
                        
                        System.out.println(e.getMessage());

                    }

                break;

                case 6:

                    System.out.printf("|Sua escolha: %-67s|\n", "Criar turma.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        this.criaTurma(professor);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                break;

                case 7:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar aluno por nome.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 8:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar aluno por ID.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 9:

                    System.out.printf("|Sua escolha: %-67s|\n", "Criar boletim.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                case 10:

                    System.out.printf("|Sua escolha: %-67s|\n", "Sair.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }

        } while (opcao.intValue() != 10);

    }

    private void criaTurma (Professor professor) {

        String senha = null;
        Integer count = Integer.valueOf(3);

        do {

            if (count < 3) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Você tem mais " + count + " tentativas!");

            }

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite sua senha:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            senha = new String(System.console().readPassword());
            System.out.println();

            count--;

        } while ((!professor.autentica(senha)) && (count.intValue() > 0));

        try {
            
            professor.criaTurma(senha);

        } catch (Exception e) {

            System.err.println(e.getMessage());

        }

    } 

    private Boletim procuraBoletim (Professor professor) {
        
        Integer opcao = null;
        Aluno aluno = null;
        Boletim boletimAluno = null;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Procurar aluno por nome");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%1s", "2.Procurar aluno por ID");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%1s", "3.Voltar");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar aluno por nome.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        aluno = this.retornaAluno(professor);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }

                    if (aluno == null) {

                        System.err.println("\nNenhum aluno encontrado.");
                        break;

                    }

                    try {
                        
                        boletimAluno = this.retornaBoletim(professor, aluno);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    if (boletimAluno == null) {

                        System.err.println("\nNenhum boletim encontrado.");
                        break;

                    }
                    
                return boletimAluno;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Procurar aluno por ID.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        aluno = this.retornaAlunoID(professor);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }

                    if (aluno == null) {

                        System.err.println("\nNenhum aluno encontrado.");
                        break;

                    }

                    try {
                        
                        boletimAluno = this.retornaBoletim(professor, aluno);

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    }

                    if (boletimAluno == null) {

                        System.err.println("\nNenhum boletim encontrado.");
                        break;

                    }
                    
                return boletimAluno;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Voltando...");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                return null;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }

        } while (opcao.intValue() != 3);

        return null;

    }

    private Boletim retornaBoletim (Professor professor, Aluno aluno) {

        Integer subOpcao = Integer.valueOf(this.subMenuBoletim ());
        Boletim boletimAluno = null;

        switch (subOpcao.intValue()) {

            case 1:
                
                System.out.printf("|Sua escolha: %-67s|\n", "Procurar pelo boletim da própria matéria.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");

                try {
                    
                    boletimAluno = professor.procuraBoletim(professor.getMateria(), aluno);

                } catch (Exception e) {
                    
                    System.err.println(e.getMessage());

                }

                if (boletimAluno != null) return boletimAluno;

            break;

            case 2:

                System.out.printf("|Sua escolha: %-67s|\n", "Procurar pelo boletim de outra matéria.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");

                String materia = this.retornarMateria();

                if (materia == null) break;

                try {
                    
                    boletimAluno = professor.procuraBoletim(materia, aluno);

                } catch (Exception e) {
                    
                    System.out.println(e.getMessage());
                    
                }

                if (boletimAluno != null) return boletimAluno;

            break;

            case 3:

                System.err.printf("|Sua escolha: %-67s|\n", "Cancelar.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");

            return null;
        
            default:

                System.err.println(String.format("|Sua escolha: %-67s|", "Nenhuma das existentes."));
                System.out.println("|" + repeteCaracter('-', 80) + "|");

            break;

        }

        return boletimAluno;

    }

    private Integer subMenuBoletim () {

        System.out.println();
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        String opcao1 = String.format("|%20s", "1.Procurar pelo boletim da própria matéria");
        System.out.printf("%-81s|\n", opcao1);
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        String opcao2 = String.format("|%20s", "2.Procurar pelo boletim de outra matéria");
        System.out.printf("%-81s|\n", opcao2);
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        String opcao3 = String.format("|%20s", "3.Cancelar");
        System.out.printf("%-81s|\n", opcao3);
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%1s", "> ");
        Integer subOpcao = Integer.valueOf(scan.nextInt());
        System.out.println("|" + repeteCaracter('-', 80) + "|");

        return subOpcao;

    }

    private String retornarMateria () {

        Boolean condition = Boolean.TRUE;
        Integer count = Integer.valueOf(0);

        do {

            if (count.intValue() > 0) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Nenhuma matéria com esse nome foi encontrada.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");

            }

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite a matéria (ENTER para voltar):");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            String materia = scan.next();
            String complementar = scan.nextLine();
            materia = materia + complementar;
            Materia[] materias = Materia.values();
            System.out.println();

            if (materia.isEmpty()) break;

            for (Materia m : Materia.values()) {

                System.out.println(m);

            }


            for (Materia m : materias) {

                if (m.toString().equalsIgnoreCase(materia) || m.name().equalsIgnoreCase(materia)) {

                    condition = Boolean.TRUE;
                    return materia = m.toString();

                }

            }

            if (count.intValue() == 0) count++;

        } while (condition != Boolean.TRUE);

        return null;

    }

    private Turma retornaTurma (Professor professor) {

        Boolean condition = Boolean.TRUE;
        Integer count = Integer.valueOf(0);

        do {

            if (count.intValue() > 0) {

                System.out.println("|" + repeteCaracter('-', 80) + "|");
                System.out.printf("|%-80s|\n", "Nenhuma turma com esse ID foi encontrada.");
                System.out.println("|" + repeteCaracter('-', 80) + "|");

            }

            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite o ID (ENTER para voltar):");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            String id = scan.next();
            String complementar = scan.nextLine();
            id = id + complementar;
            System.out.println();

            if (id.isEmpty()) break;

            Turma turma = null;

            try {
                
                turma = professor.getTurma(id);

            } catch (Exception e) {
                
                System.err.println(e.getMessage());
                
            }

            if ((count.intValue() == 0) && (turma == null)) count++;
            else return turma;

        } while (condition != Boolean.TRUE);

        return null;


    }

    private Aluno retornaAluno (Professor professor) {

        Aluno aluno = null;

        System.out.println();
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%-80s|\n", "Digite o nome do aluno:");
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%1s", "> ");
        String alunoNome = scan.next();
        String complementar = scan.nextLine();
        alunoNome = alunoNome + complementar;
        System.out.println("|" + repeteCaracter('-', 80) + "|");

        List<Aluno> alunosRetornados = null;

        try {

            alunosRetornados = professor.procuraAluno(alunoNome);
            
        } catch (Exception e) {
            
            System.err.println(e.getMessage());
            
        }

        if ((!alunosRetornados.isEmpty()) && (alunosRetornados != null)) {

            for (int i = 0; i < alunosRetornados.size(); i++) {

                System.out.println((i + 1) + "." + alunosRetornados.get(i));

            }

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%-80s|\n", "Digite o nome do aluno:");
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            Integer alunoPos = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            aluno = alunosRetornados.get(alunoPos + 1);

            if (aluno != null) return aluno;

        }

        return null;

    }

    private Aluno retornaAlunoID (Professor professor) {

        Aluno aluno = null;

        System.out.println();
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%-80s|\n", "Digite o ID do aluno:");
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%1s", "> ");
        String alunoID = scan.next();
        String complementar = scan.nextLine();
        alunoID = alunoID + complementar;
        System.out.println("|" + repeteCaracter('-', 80) + "|");

        alunoID.replaceAll("^[ !#$%^&*=+-]&", "");

        try {

            aluno = professor.procuraAlunoID(alunoID);
            
        } catch (Exception e) {
            
            System.err.println(e.getMessage());
            
        }

        return aluno;

    }

    protected void registraAluno (Aluno aluno, Professor professor) {

        Integer opcao;

        do {

            System.out.println();
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String primeiraOpcao = String.format("|%20s", "1.Inserir o novo aluno em uma nova turma");
            System.out.printf("%-81s|\n", primeiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String segundaOpcao = String.format("|%20s", "2.Inserir o aluno novo em uma turma existente");
            System.out.printf("%-81s|\n", segundaOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            String terceiraOpcao = String.format("|%20s", "3.Voltar");
            System.out.printf("%-81s|\n", terceiraOpcao);
            System.out.println("|" + repeteCaracter('-', 80) + "|");
            System.out.printf("|%1s", "> ");
            opcao = Integer.valueOf(scan.nextInt());
            System.out.println("|" + repeteCaracter('-', 80) + "|");

            switch (opcao.intValue()) {

                case 1:

                    System.out.printf("|Sua escolha: %-67s|\n", "Criar um novo aluno e uma nova turma.");

                    try {

                        for (int i = 3; i > 0; i--) {
                        
                            System.out.println("|" + repeteCaracter('-', 80) + "|");
                            System.out.printf("|%-80s|\n", "Digite sua senha:");
                            System.out.println("|" + repeteCaracter('-', 80) + "|");
                            System.out.printf("|%1s", "> ");
                            String senha = scan.next();
                            String complementar = scan.nextLine();
                            senha = senha + complementar;
                            System.out.println("|" + repeteCaracter('-', 80) + "|");

                            try {
                                
                                Turma turmaNova = professor.criaTurma(senha);
                                turmaNova.adicionarAluno(aluno);

                            } catch (Exception e) {
                                
                                System.err.println(e.getMessage());
                                System.out.println("\nNúmeros restantes de tentativas: " + i);

                            }

                        }

                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    } finally {

                        opcao = Integer.valueOf(3);

                    }

                break;

                case 2:

                    System.out.printf("|Sua escolha: %-67s|\n", "Criar um aluno e inseri-lo em uma turma existente.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                    try {

                        String id = this.getTurmaId(professor);
                        System.out.println("|" + repeteCaracter('-', 80) + "|");
                        Turma turma = professor.getTurma(id);
                        turma.adicionarAluno(aluno);
                        
                    } catch (Exception e) {
                        
                        System.err.println(e.getMessage());

                    } finally {

                        opcao = Integer.valueOf(3);

                    }

                break;

                case 3:

                    System.out.printf("|Sua escolha: %-67s|\n", "Voltar.");
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

                default:

                    System.err.println(String.format("|Sua escolha: %-67s|", "Nenhuma das existentes."));
                    System.out.println("|" + repeteCaracter('-', 80) + "|");

                break;

            }

        } while (opcao.intValue() != 3);

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
    protected String repeteCaracter (char c, Integer tamanho) {

        char[] caracteres = new char[tamanho.intValue()];
        Arrays.fill(caracteres, c);
        return new String(caracteres);

    }

    private String getTurmaId (Professor professor) {

        System.out.println();
        System.out.println(professor.getTurmas());
        System.out.println();
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%-80s|\n", "Digite o ID da turma:");
        System.out.println("|" + repeteCaracter('-', 80) + "|");
        System.out.printf("|%1s", "> ");
        String id = scan.next();
        String complementar = scan.nextLine();
        return id = id + complementar;

    }

}
