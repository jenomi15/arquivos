import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<disciplina> disciplinas = new ArrayList<>();

        System.out.println("Digite a quantidade de disciplinas:");
        int quantidadeDisciplinas = teclado.nextInt();
        teclado.nextLine();

        // Coletando informações das disciplinas e dos alunos
        for (int i = 0; i < quantidadeDisciplinas; i++) {
            System.out.println("Digite o nome da disciplina " + (i + 1) + ":");
            String nomeDisciplina = teclado.nextLine();
            disciplina disciplina = new disciplina(nomeDisciplina);
            disciplinas.add(disciplina);

            System.out.println("Digite a quantidade de alunos para a disciplina " + nomeDisciplina + ":");
            int quantidadeAlunos = teclado.nextInt();
            teclado.nextLine();

            for (int j = 0; j < quantidadeAlunos; j++) {
                System.out.println("Digite o nome do aluno " + (j + 1) + ":");
                String nomeAluno = teclado.nextLine();
                System.out.println("Digite as respostas do aluno " + (j + 1) + ":");
                String respostas = teclado.nextLine();
                aluno aluno = new aluno(respostas, nomeAluno);
                disciplina.adicionarAluno(aluno);
            }

            // Criando o arquivo com as respostas dos alunos logo após a inserção dos dados
            disciplina.criarArquivoComNotas(); 
        }

        // Coletando o gabarito para cada disciplina
        for (disciplina d : disciplinas) {
            System.out.println("Digite o gabarito da disciplina " + d.getNome() + ":");
            d.criarArquivoComGabaritos();
        }

        // Gerando as notas conforme solicitado
        int continuar;
        do {
            System.out.println("Digite a disciplina para gerar as notas:");
            String nomeDisciplina = teclado.nextLine();

            // Busca da disciplina pelo nome
            disciplina disciplinaEncontrada = null;
            for (disciplina d : disciplinas) {
                if (d.getNome().equalsIgnoreCase(nomeDisciplina)) {
                    disciplinaEncontrada = d;
                    break;
                }
            }

            if (disciplinaEncontrada != null) {
                for (aluno a : disciplinaEncontrada.getAlunos()) {
                    disciplinaEncontrada.gerarNota(nomeDisciplina, a);
                }
                disciplinaEncontrada.criarArquivoComNotasAlunos(); // Gerando o arquivo com as notas atualizadas
            } else {
                System.out.println("Disciplina não encontrada.");
            }

            System.out.println("Deseja gerar notas para outra disciplina? (1 - Sim / 0 - Não)");
            continuar = teclado.nextInt();
            teclado.nextLine();

        } while (continuar == 1);

        teclado.close();
    }
}
