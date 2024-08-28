public class Aluno {
    private String respostas;
    private String nome;
    private int acertos;


    public Aluno(String respostas, String nome) {
        this.respostas = respostas;
        this.nome = nome;
        this.acertos = 0;  
    }


    public String getRespostas() {
        return respostas;
    }

    public void setRespostas(String respostas) {
        this.respostas = respostas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }
}

