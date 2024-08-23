import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

    public void criarArquivoComNotas() {
        try {
            FileWriter notas = new FileWriter(nome + ".txt");
            for (aluno aluno : alunos) {
                notas.write(aluno.getRespostas() + "    " + aluno.getNome() + "\n");
            }
            notas.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
