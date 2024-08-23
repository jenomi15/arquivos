import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<disciplina> disciplinas = new ArrayList<>();
        ArrayList<aluno> alunos = new ArrayList<>();


        System.out.println("Digite a quantidade de disciplinas:");
        int quantidadeDisciplinas = teclado.nextInt();
        teclado.nextLine(); // Consome a nova linha após o nextInt()

        // Criação das disciplinas
        for (int i = 0; i < quantidadeDisciplinas; i++) {
            System.out.println("Digite o nome da disciplina "+ (i+1)+ ":");
            String nomeDisciplina = teclado.nextLine();
            disciplina disciplina = new disciplina(nomeDisciplina);
            disciplinas.add(disciplina);

            System.out.println("Digite a quantidade de alunos para a disciplina " + nomeDisciplina + ":");
            int quantidadeAlunos = teclado.nextInt();
            teclado.nextLine(); // Consome a nova linha após o nextInt()

            for (int j = 0; j < quantidadeAlunos; j++) {
                System.out.println("Digite as respostas do aluno " + (j + 1) + ":");
                String respostas = teclado.nextLine();
                System.out.println("Digite o nome do aluno " + (j + 1) + ":");
                String nomeAluno = teclado.nextLine();
                aluno aluno = new aluno(respostas, nomeAluno);
                disciplina.adicionarAluno(aluno);
            }

            // Gera o arquivo .txt para a disciplina
            disciplina.criarArquivoComNotas();
        }
        for ( int i = 0 ; i < disciplinas.size() ; i ++){
            System.out.println("digite o gabarito da disciplina : " + disciplinas.get(i).getNome() );
            disciplinas.get(i).criarArquivoComGabaritos();
        }

        teclado.close();
    }
}


       
    

       



   

