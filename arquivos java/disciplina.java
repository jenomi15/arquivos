import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;

public class disciplina {
    private String nome;
    private ArrayList<aluno> alunos = new ArrayList<>();

    public disciplina(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarAluno(aluno aluno) {
        alunos.add(aluno);
    }

    public ArrayList<aluno> getAlunos() {
        return alunos;
    }

    public void criarArquivoComNotas() {
        try (FileWriter notas = new FileWriter(nome + ".txt")) {
            for (aluno aluno : alunos) {
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

    public void gerarNota(String nomeDaDisciplina, aluno aluno) {
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

                    
                    for (aluno a : alunos) {
                        if (a.getNome().equals(nomeAluno)) {
                            a.setAcertos(aluno.getAcertos());
                        }
                    }
                }
            }
        }
    }

    private void contarAcertos(String gabarito, String respostasAluno, aluno aluno) {
        
        for (int i = 0; i < gabarito.length() && i < respostasAluno.length(); i++) {
            if (gabarito.charAt(i) == respostasAluno.charAt(i)) {
                aluno.setAcertos(aluno.getAcertos() + 1);
            }
        }
    }

    public void criarArquivoComNotasAlunos() {
        try (FileWriter arquivoNotas = new FileWriter(this.getNome() + "notas.txt")) {
            for (aluno a : this.getAlunos()) {
                arquivoNotas.write(a.getNome() + " " + a.getAcertos() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
