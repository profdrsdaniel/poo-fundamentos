import java.util.Locale;

public class Boletim {

    public static void main(String[] args) {

        String aluno = "Ana Souza";
        double nota1 = 8.0;
        double nota2 = 5.5;
        double nota3 = 4.0;

        double media = (nota1 + nota2 + nota3) / 3.0;

        String situacao;
        if (media >= 6.0) {
            situacao = "Aprovado";
        } else if (media >= 4.0) {
            situacao = "Recuperacao";
        } else {
            situacao = "Reprovado";
        }

        System.out.println("=== BOLETIM ===");
        System.out.println("Aluno: " + aluno);
        System.out.printf(Locale.US, "Media: %.2f%n", media);
        System.out.println("Situacao: " + situacao);
    }
}
