public class Aluno {

    private String nome;
    private double nota;

    public Aluno(String nome, double nota) {
        this.nome = nome;
        this.nota = nota;
    }

    // Construtor de copia: constroi um objeto novo no heap com o mesmo conteudo do original.
    public Aluno(Aluno original) {
        this.nome = original.nome;
        this.nota = original.nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return nome + " (" + nota + ")";
    }
}
