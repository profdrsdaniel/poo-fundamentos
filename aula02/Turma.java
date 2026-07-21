public class Turma {

    public static void main(String[] args) {

        double[] notas = {8.0, 5.5, 4.0, 9.5, 6.0, 3.0, 7.5, 10.0, 2.5, 6.0};

        // TODO: chame os quatro metodos e imprima no formato:
        //   === ESTATISTICAS DA TURMA ===
        //   Alunos:     10
        //   Media:      6.20
        //   Maior nota: 10.0
        //   Menor nota: 2.5
        //   Aprovados:  6 de 10
        // Use notas.length para a quantidade, nunca o numero 10 cravado.
        // Como na aula 01, passe Locale.US no printf para o separador decimal
        // sair ponto e nao virgula. Exige import java.util.Locale; no topo.
    }

    // TODO: media(double[] valores) -> soma tudo e divide por valores.length
    // TODO: maior(double[] valores) -> comeca em valores[0] e percorre trocando
    // TODO: menor(double[] valores) -> igual ao maior, invertendo a comparacao
    // TODO: aprovados(double[] valores, double corte) -> conta quantos v >= corte
}
