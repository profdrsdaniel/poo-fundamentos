public class Laboratorio {

    public static void main(String[] args) {

        System.out.println("=== PARTE 1: primitivo ===");
        int a = 5;
        int b = a;
        b = 10;
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        System.out.println();
        System.out.println("=== PARTE 2: objeto ===");
        Aluno joao = new Aluno("Joao", 7.0);
        Aluno copia = joao;
        copia.setNota(10.0);
        System.out.println("joao  = " + joao);
        System.out.println("copia = " + copia);

        System.out.println();
        System.out.println("=== PARTE 3: dois objetos iguais ===");
        Aluno x = new Aluno("Maria", 8.0);
        Aluno y = new Aluno("Maria", 8.0);
        System.out.println("x == y      -> " + (x == y));
        System.out.println("x.equals(y) -> " + x.equals(y));

        System.out.println();
        System.out.println("=== PARTE 4: String, a pegadinha ===");
        String s1 = "prova";
        String s2 = "prova";
        String s3 = new String("prova");
        System.out.println("s1 == s2      -> " + (s1 == s2));
        System.out.println("s1 == s3      -> " + (s1 == s3));
        System.out.println("s1.equals(s3) -> " + s1.equals(s3));
    }
}
