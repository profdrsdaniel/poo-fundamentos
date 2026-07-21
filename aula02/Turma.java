import java.util.Locale;

public class Turma {

    public static void main(String[] args) {

        double[] notas = {8.0, 5.5, 4.0, 9.5, 6.0, 3.0, 7.5, 10.0, 2.5, 6.0};

        System.out.println("=== ESTATISTICAS DA TURMA ===");
        System.out.printf(Locale.US, "Alunos:     %d%n", notas.length);
        System.out.printf(Locale.US, "Media:      %.2f%n", media(notas));
        System.out.printf(Locale.US, "Maior nota: %.1f%n", maior(notas));
        System.out.printf(Locale.US, "Menor nota: %.1f%n", menor(notas));
        System.out.printf(Locale.US, "Aprovados:  %d de %d%n", aprovados(notas, 6.0), notas.length);
    }

    static double media(double[] valores) {
        double soma = 0.0;
        for (double v : valores) {
            soma += v;
        }
        return soma / valores.length;
    }

    static double maior(double[] valores) {
        double max = valores[0];
        for (int i = 1; i < valores.length; i++) {
            if (valores[i] > max) {
                max = valores[i];
            }
        }
        return max;
    }

    static double menor(double[] valores) {
        double min = valores[0];
        for (int i = 1; i < valores.length; i++) {
            if (valores[i] < min) {
                min = valores[i];
            }
        }
        return min;
    }

    static int aprovados(double[] valores, double corte) {
        int total = 0;
        for (double v : valores) {
            if (v >= corte) {
                total++;
            }
        }
        return total;
    }
}
