public class Investigacao {

    public static void main(String[] args) {

        System.out.println("=== O problema ===");
        Aluno joao = new Aluno("Joao", 7.0);
        Aluno apelido = joao;
        apelido.setNota(10.0);
        System.out.println("joao    = " + joao);
        System.out.println("apelido = " + apelido);
        System.out.println("joao == apelido -> " + (joao == apelido));

        System.out.println();
        System.out.println("=== A correcao: construtor de copia ===");
        Aluno maria = new Aluno("Maria", 7.0);
        Aluno copiaReal = new Aluno(maria);
        copiaReal.setNota(10.0);
        System.out.println("maria     = " + maria);
        System.out.println("copiaReal = " + copiaReal);
        System.out.println("maria == copiaReal -> " + (maria == copiaReal));

        System.out.println();
        System.out.println("=== Passando objeto para metodo ===");
        Aluno ana = new Aluno("Ana", 6.0);
        System.out.println("antes  : " + ana);
        aprovar(ana);
        System.out.println("depois : " + ana);

        System.out.println();
        System.out.println("=== Passando primitivo para metodo ===");
        double nota = 6.0;
        System.out.println("antes  : " + nota);
        aprovar(nota);
        System.out.println("depois : " + nota);
    }

    static void aprovar(Aluno aluno) {
        aluno.setNota(10.0);
    }

    static void aprovar(double nota) {
        nota = 10.0;
    }
}
