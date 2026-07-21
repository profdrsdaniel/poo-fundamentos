# Aula 01 — Primeiro contato com Java: boletim

Aula de nivelamento. Você já programa em alguma linguagem; o objetivo aqui não é aprender a programar,
é aprender **como Java escreve** aquilo que você já sabe fazer — declaração de tipos, condicional e
saída formatada.

## A tarefa

Abra `Boletim.java`. Ele já vem com os dados de entrada prontos:

```java
String aluno = "Ana Souza";
double nota1 = 8.0;
double nota2 = 5.5;
double nota3 = 4.0;
```

O resto está marcado com comentários `// TODO`. Você deve:

1. **Calcular a média** das três notas.
2. **Decidir a situação** com `if / else if / else`:
   - média maior ou igual a `6.0` → `"Aprovado"`
   - média entre `4.0` e `6.0` → `"Recuperacao"`
   - média menor que `4.0` → `"Reprovado"`
3. **Imprimir o boletim**, com a média em duas casas decimais.

Com os dados que vieram no arquivo, a saída correta é exatamente esta:

```
=== BOLETIM ===
Aluno: Ana Souza
Media: 5.83
Situacao: Recuperacao
```

## O que você entrega

`Boletim.java` compilando e produzindo essa saída. Um arquivo só — não precisa criar mais nada.

## A armadilha da divisão inteira

Este é o erro que mais pega quem vem de linguagem dinâmica, então vale o aviso antecipado.

Em Java, o operador `/` olha para o **tipo dos operandos**, não para o tipo da variável que vai receber
o resultado. Se os dois lados forem inteiros, a divisão é inteira e a parte fracionária é **descartada**
— não arredondada, descartada. E isso acontece *antes* de o resultado ser guardado, então declarar a
variável como `double` não salva:

```java
int a = 7, b = 2;
double resultado = a / b;   // 3.0, e nao 3.5 — o estrago ja aconteceu
```

Nesta aula suas notas são `double`, então `(nota1 + nota2 + nota3) / 3` funcionaria por sorte: basta um
operando ser `double` para a divisão inteira toda virar `double`. Mesmo assim, **escreva `3.0`**. É o
hábito que te protege no dia em que as notas chegarem como `int` — e esse dia chega.

## A vírgula que aparece do nada

Se você imprimir a média com `System.out.printf("Media: %.2f%n", media)` e vier `5,83` em vez de
`5.83`, não é bug e você não errou a conta. O `printf` formata números segundo o **locale do sistema**,
e num computador configurado em português o separador decimal é a vírgula — como manda a norma
brasileira.

Só que aqui a saída faz parte do enunciado, e ela precisa ser a mesma na máquina de todo mundo. Então
fixe o formato passando `Locale.US` como primeiro argumento:

```java
import java.util.Locale;   // no topo do arquivo, antes da classe

System.out.printf(Locale.US, "Media: %.2f%n", media);
```

Guarde a ideia: sempre que a saída de um programa for lida por outro programa — ou comparada com um
gabarito — você fixa o locale em vez de depender de como a máquina está configurada.

## Como rodar

### Pelo IntelliJ IDEA

1. `File → Open...` e selecione a pasta raiz do repositório (`poo-fundamentos`).
2. Na primeira vez, configure o SDK: `File → Project Structure → Project → SDK`, escolhendo um
   **JDK 21 ou superior**.
3. Clique com o botão direito na pasta `aula01` e escolha `Mark Directory as → Sources Root`.
4. Abra `Boletim.java` e clique no ▶ verde ao lado da declaração do `main`.

### Pelo terminal

A partir da raiz do repositório:

```bash
cd aula01
javac -d out Boletim.java
java -cp out Boletim
```

O `javac -d out` coloca o `.class` gerado numa pasta `out/` (que está no `.gitignore` e não vai para o
commit). O `java -cp out` manda o Java procurar a classe ali dentro.

> Rodando o arquivo como ele veio, com os `TODO` ainda por fazer, ele **compila e não imprime nada** —
> o `main` só declara variáveis. Isso é esperado: é o seu ponto de partida, não um erro.

A resolução comentada fica na branch `task/boletim`. Tente antes de olhar.
