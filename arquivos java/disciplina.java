import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;

public class disciplina {
    private String nome;
    private ArrayList<Aluno> alunos = new ArrayList<>();

    public disciplina(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void criarArquivoComNotas() {
        try (FileWriter notas = new FileWriter(nome + ".txt")) {
            for (Aluno aluno : alunos) {
                notas.write(aluno.getRespostas() + " " + aluno.getNome() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void criarArquivoComGabaritos() {
        Scanner teclado1 = new Scanner(System.in);
        try {
            FileWriter gabarito = new FileWriter(nome + "gabarito.txt");
            System.out.println("Digite o gabarito da disciplina: ");
            String gabaritoDaProva = teclado1.nextLine();
            gabarito.write(gabaritoDaProva);
            gabarito.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gerarNota(String nomeDaDisciplina, Aluno aluno) {
        List<String> respostas = null;
        List<String> gabarito = null;

        try {
            Path caminho = Path.of(nomeDaDisciplina + ".txt");
            respostas = Files.readAllLines(caminho);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Path caminho1 = Path.of(nomeDaDisciplina + "gabarito.txt");
            gabarito = Files.readAllLines(caminho1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (respostas != null && gabarito != null && !gabarito.isEmpty()) {
            String gabaritoCorreto = gabarito.get(0); 

           
            for (String linha : respostas) {
                String[] partes = linha.split(" ", 2); 
                if (partes.length == 2) {
                    String respostasAluno = partes[0].trim(); 
                    String nomeAluno = partes[1].trim(); 

                 
                    aluno.setAcertos(0);

                    contarAcertos(gabaritoCorreto, respostasAluno, aluno);

                    System.out.println("Aluno: " + nomeAluno + " - Acertos: " + aluno.getAcertos() + "/10");

                    
                    for (Aluno a : alunos) {
                        if (a.getNome().equals(nomeAluno)) {
                            a.setAcertos(aluno.getAcertos());
                        }
                    }
                }
            }
        }
    }

    private void contarAcertos(String gabarito, String respostasAluno, Aluno aluno) {
    // Verifica se as respostas do aluno são homogêneas (ex: "vvvvvvvvvv" ou "ffffffffff")
    if (respostasAluno.chars().distinct().count() == 1) {
        aluno.setAcertos(0);
        return;
    }

    // Conta os acertos
    int acertos = 0;
    for (int i = 0; i < gabarito.length() && i < respostasAluno.length(); i++) {
        if (gabarito.charAt(i) == respostasAluno.charAt(i)) {
            acertos++;
        }
    }
    aluno.setAcertos(acertos);
}

    public void criarArquivoComNotasAlunos() {
        try (FileWriter arquivoNotas = new FileWriter(this.getNome() + "notas.txt")) {
            
            List<Aluno> alunosOrdenados = new ArrayList<>(this.getAlunos());
            alunosOrdenados.sort((a1, a2) -> Integer.compare(a2.getAcertos(), a1.getAcertos()));
    
            
            for (Aluno a : alunosOrdenados) {
                arquivoNotas.write(a.getNome() + " " + a.getAcertos() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   public void criarArquivoComNotasAlunosAlfabeticos() {
    try (FileWriter arquivoNotasAlfabeticas = new FileWriter(this.getNome() + "notascomordemalfabetica.txt")) {
        // Ordena os alunos em ordem alfabética
        List<Aluno> alunosOrdenados = new ArrayList<>(this.getAlunos());
        alunosOrdenados.sort((a1, a2) -> a1.getNome().compareToIgnoreCase(a2.getNome())); 

        int somaNotas = 0;

        // Escreve as notas dos alunos no arquivo
        for (Aluno a : alunosOrdenados) {
            arquivoNotasAlfabeticas.write(a.getNome() + " " + a.getAcertos() + "\n");
            somaNotas += a.getAcertos(); // Acumula a soma das notas
        }

        // Calcula a média das notas
        double mediaNotas = alunosOrdenados.isEmpty() ? 0 : (double) somaNotas / alunosOrdenados.size();

        // Escreve a média das notas no arquivo
        arquivoNotasAlfabeticas.write("\nMedia das notas: " + String.format("%.2f", mediaNotas) + "\n");

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    




}