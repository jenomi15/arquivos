import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<aluno> alunos = new ArrayList<>();
        
        System.out.println("Digite a quantidade de alunos que você quer criar:");
        int d = teclado.nextInt();
        teclado.nextLine(); // Consome a nova linha após o nextInt()

        for (int j = 0; j < d; j++) {
            System.out.println("Digite as respostas do aluno " +(j + 1 ) + ":");
            String y = teclado.nextLine();
            System.out.println("Digite o nome do aluno "+ (j +1) +  ":");
            String l = teclado.nextLine();
            aluno aluno = new aluno(y, l);
            alunos.add(aluno);
        }

        // Adiciona um aluno específico
        aluno joao = new aluno("VVFFVVFVFF", "JOÃO ANTÔNIO");
        alunos.add(joao);
        System.out.println("digite o nome da disciplina ");
        String disciplina = teclado.nextLine();
        try {
            FileWriter notas = new FileWriter(disciplina+".txt");
            for (aluno aluno : alunos) {
                notas.write(aluno.getRespostas() + "    " + aluno.getNome() + "\n");
            }
            notas.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    

