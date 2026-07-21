# Aula 04 — Objetos na memória

> ⚠️ **PARE ANTES DE EXECUTAR.**
> O enunciado da aula (no Notion) pede que você **escreva a saída que espera** de cada parte do
> `Laboratorio.java` **antes** de rodar o programa. O exercício inteiro depende disso: o valor dele
> está em você comparar a sua previsão com o que a máquina realmente faz, e descobrir onde as duas
> discordam. Se rodar primeiro e prever depois, você não descobre nada — só confirma o que já viu na
> tela, e o assunto da aula passa batido. Preveja primeiro. Depois execute.

## O que tem nesta pasta

| Arquivo | Para que serve |
| --- | --- |
| `Aluno.java` | Classe de domínio bem simples: `nome`, `nota`, construtor, getters, setters e `toString()`. É o material sobre o qual o laboratório trabalha. |
| `Laboratorio.java` | Classe com o `main`. Imprime quatro blocos, cada um com um comportamento diferente de atribuição e comparação. É o programa que você deve prever e depois executar. |

## Como rodar

### Pelo IntelliJ IDEA

1. `File → Open...` e selecione a pasta raiz do repositório (`poo-fundamentos`).
2. Se for a primeira vez, o IntelliJ pode pedir para configurar o SDK: escolha um **JDK 21 ou superior**
   em `File → Project Structure → Project → SDK`.
3. Marque a pasta `aula04` como pasta de fontes: clique com o botão direito nela e escolha
   `Mark Directory as → Sources Root`.
4. Abra `Laboratorio.java` e clique no ▶ verde ao lado da declaração do `main`.

### Pelo terminal

A partir da raiz do repositório:

```bash
cd aula04
javac -d out *.java
java -cp out Laboratorio
```

O `javac -d out` joga os `.class` gerados numa pasta `out/` (que está no `.gitignore`, então não vai
parar no commit). O `java -cp out` diz ao Java para procurar as classes ali dentro.

## O que você deve entregar

Ao final da aula, sua pasta `aula04` deve conter:

1. **`Aluno.java`** com um **construtor de cópia** — um construtor que recebe outro `Aluno` e produz um
   objeto novo com o mesmo conteúdo.
2. **`Investigacao.java`** — uma classe com `main` demonstrando os quatro casos discutidos em aula:
   - o problema do aliasing (duas variáveis, um objeto só);
   - a correção usando o construtor de cópia;
   - o que acontece ao passar um **objeto** para um método;
   - o que acontece ao passar um **primitivo** para um método.

A resolução comentada fica na branch `task/objetos-na-memoria` — mas só olhe **depois** de tentar.
