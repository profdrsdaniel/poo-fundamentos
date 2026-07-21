# Aula 02 — Arrays, laços e métodos

Segunda aula de nivelamento. Na Aula 01 você trabalhou com três notas em três variáveis separadas.
Isso não escala: uma turma tem dez, quarenta, duzentos alunos, e ninguém declara `nota1` até `nota200`.
Aqui entram o **array**, que guarda muitos valores sob um nome só, o **laço**, que percorre esses
valores, e o **método**, que dá nome a um cálculo para você poder repeti-lo sem repetir o código.

## A tarefa

Abra `Turma.java`. O array já vem preenchido:

```java
double[] notas = {8.0, 5.5, 4.0, 9.5, 6.0, 3.0, 7.5, 10.0, 2.5, 6.0};
```

Você deve escrever **quatro métodos**, cada um com uma responsabilidade só:

| Método | O que faz |
| --- | --- |
| `media(double[] valores)` | Soma todos os valores e divide pela quantidade. |
| `maior(double[] valores)` | Devolve o maior valor do array. |
| `menor(double[] valores)` | Devolve o menor valor do array. |
| `aprovados(double[] valores, double corte)` | Conta quantos valores são maiores ou iguais ao corte. |

E, no `main`, chamar os quatro e imprimir exatamente neste formato:

```
=== ESTATISTICAS DA TURMA ===
Alunos:     10
Media:      6.20
Maior nota: 10.0
Menor nota: 2.5
Aprovados:  6 de 10
```

Use corte `6.0` para os aprovados.

## O que você entrega

`Turma.java` compilando, com os quatro métodos implementados e produzindo a saída acima. Um arquivo só.

## Use `notas.length`, nunca `10` cravado

O array tem dez notas hoje. Escrever `10` direto no código funciona hoje e passa a mentir no minuto em
que alguém acrescentar um aluno — e não há erro de compilação para avisar: o programa continua rodando
e apenas responde errado, dividindo por dez o que deveria ser dividido por onze.

Em Java o array sabe o próprio tamanho, e você pergunta com `notas.length` (sem parênteses — é um campo,
não um método; em `String` é `length()`, com parênteses, e a inconsistência é uma pegadinha clássica da
linguagem). Prefira sempre perguntar ao array:

```java
for (int i = 0; i < valores.length; i++) { ... }   // adapta-se sozinho
for (int i = 0; i < 10; i++) { ... }               // quebra em silencio
```

Isso vale dentro dos métodos também: eles recebem `double[] valores` sem saber de onde veio nem quantos
são, e é justamente por perguntarem `valores.length` que servem para qualquer array — inclusive o da
próxima turma, com trinta alunos.

## O detalhe do separador decimal

Como na Aula 01: `printf` formata números segundo o locale do sistema, e em português o separador
decimal é a vírgula (`6,20`). Para a saída bater com o enunciado em qualquer máquina, passe `Locale.US`
como primeiro argumento:

```java
import java.util.Locale;   // no topo do arquivo

System.out.printf(Locale.US, "Media:      %.2f%n", media(notas));
```

## Como rodar

### Pelo IntelliJ IDEA

1. `File → Open...` e selecione a pasta raiz do repositório (`poo-fundamentos`).
2. Na primeira vez, configure o SDK em `File → Project Structure → Project → SDK` com um
   **JDK 21 ou superior**.
3. Clique com o botão direito na pasta `aula02` e escolha `Mark Directory as → Sources Root`.
4. Abra `Turma.java` e clique no ▶ verde ao lado do `main`.

### Pelo terminal

A partir da raiz do repositório:

```bash
cd aula02
javac -d out Turma.java
java -cp out Turma
```

> Rodando o arquivo como ele veio, ele **compila e não imprime nada** — o `main` só declara o array e o
> resto são comentários. É o ponto de partida, não um erro.

A resolução comentada fica na branch `task/estatisticas`. Tente antes de olhar.
