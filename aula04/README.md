# Aula 04 — Objetos na memória (resolução comentada)

> Esta é a branch `task/objetos-na-memoria`. Se você chegou aqui **antes** de tentar resolver o
> laboratório sozinho, volte para a `main` (`git switch main`) e tente primeiro. O texto abaixo entrega
> as respostas.

## O que tem nesta pasta

| Arquivo | Para que serve |
| --- | --- |
| `Aluno.java` | A mesma classe do projeto base, agora com um **construtor de cópia**. |
| `Investigacao.java` | Classe com o `main` que demonstra os quatro casos da aula. |
| `Laboratorio.java` | O programa original do laboratório, mantido para comparação. |

Para rodar, a partir da raiz do repositório:

```bash
cd aula04
javac -d out *.java
java -cp out Investigacao
```

---

## A ideia central

Uma variável de tipo primitivo guarda o valor. Uma variável de tipo objeto **não guarda o objeto** —
guarda o endereço de onde o objeto está no heap. Quando você escreve `Aluno apelido = joao;`, você não
copiou nenhum aluno: copiou um endereço. Passaram a existir duas variáveis apontando para o mesmo e
único objeto. Alterar por uma é alterar por ambas, porque não há "ambas" — há um objeto só, com dois
nomes. É isso que se chama *aliasing*, e é por isso que `joao == apelido` dá `true`: o `==` entre
objetos compara endereços, não conteúdo.

O construtor de cópia é a saída: ele existe para produzir um segundo objeto, de verdade, com o mesmo
conteúdo do primeiro.

---

## Por que o construtor de cópia não tem `new` dentro dele

Esta é a dúvida mais comum, e ela é razoável: se o construtor serve para criar um objeto novo, por que
não aparece nenhum `new` no corpo dele?

Porque o `new` não é trabalho do construtor — é trabalho de quem chama. Quando você escreve
`new Aluno(maria)`, quem aloca a memória no heap é o operador `new`. Ele reserva o espaço, cria o
objeto vazio, e **só então** chama o construtor para preencher esse objeto recém-criado. Ou seja: quando
o corpo do construtor começa a executar, o objeto novo **já existe**. O `this` dentro dele já aponta para
esse endereço fresco. O construtor não cria nada; ele apenas responde à pergunta "e agora, com que
valores eu preencho isto aqui?".

Por isso o corpo é só atribuição:

```java
public Aluno(Aluno original) {
    this.nome = original.nome;
    this.nota = original.nota;
}
```

`this` é o objeto novo, que o `new` acabou de alocar. `original` é o objeto que veio como argumento.
Um `new` dentro do construtor criaria um *terceiro* objeto, que não teria para onde ir.

## Por que ele consegue ler `original.nome`, sendo `nome` um campo privado

À primeira vista parece uma violação: `nome` é `private`, e o código está lendo `original.nome`
diretamente, sem passar por `getNome()`.

Não é violação, porque em Java **`private` é por classe, não por objeto**. O modificador diz "este campo
só é acessível de dentro do código da classe `Aluno`" — e não "só é acessível de dentro deste `Aluno`
específico". O construtor de cópia é código escrito dentro da classe `Aluno`; portanto ele enxerga os
campos privados de **qualquer** instância de `Aluno` que tenha em mãos, inclusive a que chegou por
parâmetro.

É por isso também que métodos como `equals` conseguem comparar campo a campo sem depender de getters.
Se `private` fosse por objeto, escrever qualquer operação binária entre dois objetos da mesma classe
seria um inferno.

## Por que `aprovar(double)` não altera nada

Compare os dois métodos:

```java
static void aprovar(Aluno aluno) { aluno.setNota(10.0); }
static void aprovar(double nota) { nota = 10.0; }
```

O segundo parece que deveria funcionar, e não funciona. Isso é intencional — **não "conserte"**.

Java passa parâmetros **sempre por valor**. Quando você chama `aprovar(nota)` com um `double`, o que
entra no método é uma *cópia* do valor `6.0`. O parâmetro `nota` de dentro do método é uma variável
nova, local, que vive só durante a execução da chamada. A linha `nota = 10.0;` altera essa variável
local. Quando o método termina, ela desaparece junto com a pilha da chamada, e a variável `nota` do
`main` — que nunca foi tocada — continua valendo `6.0`.

E por que o primeiro funciona, se a regra é a mesma? Porque ali o que é copiado é o **endereço**. O
parâmetro `aluno` é uma variável nova, sim, mas ela contém o mesmo endereço que `ana` contém — as duas
apontam para o mesmo objeto no heap. `aluno.setNota(10.0)` não altera o parâmetro; ele vai até o objeto
apontado e altera o objeto. E o objeto continua existindo depois que o método termina, com a alteração
feita.

O teste que separa os dois casos é este: **reatribuir o parâmetro nunca tem efeito fora do método;
alterar o objeto apontado pelo parâmetro sempre tem.** Se dentro de `aprovar(Aluno)` você tivesse
escrito `aluno = new Aluno("Outro", 10.0);`, o `main` não veria diferença nenhuma — pelo mesmo motivo
que `aprovar(double)` não vê.

## O limite desta solução: a cópia é rasa

O construtor de cópia escrito aqui faz uma **cópia rasa** (*shallow copy*): copia o conteúdo de cada
campo, um a um, exatamente como ele está.

Nesta classe isso é suficiente, mas por sorte — pela natureza dos dois campos. `nota` é um `double`, um
primitivo: copiar o campo copia o valor, e as duas cópias ficam de fato independentes. `nome` é uma
`String`, e aí sim o campo copiado é um endereço — as duas instâncias de `Aluno` passam a apontar para
o mesmo objeto `String` na memória. Só que `String` em Java é **imutável**: não existe operação que
altere uma `String` existente. Quem fizer `copia.setNome("Outro")` não modifica a `String` compartilhada,
faz o campo apontar para outra `String`. O compartilhamento existe, mas é inofensivo, porque nada pode
mudar por baixo.

O problema apareceria no instante em que `Aluno` ganhasse um atributo que fosse um **objeto mutável** —
digamos um `List<Double> notas`, ou um `Endereco endereco`. Aí a cópia rasa copiaria o endereço da
lista, e as duas instâncias voltariam a compartilhar o mesmo objeto: adicionar uma nota no "clone"
apareceria no original. Seria exatamente o bug do aliasing de novo, só que escondido um nível mais
fundo, e mais difícil de enxergar justamente porque a linha `new Aluno(maria)` dá a impressão de que
tudo foi duplicado.

A correção, quando esse caso aparecer, é a **cópia profunda** (*deep copy*): copiar também os objetos
apontados, e não só os apontadores — `this.notas = new ArrayList<>(original.notas);`. Fica para uma
aula adiante. Por ora, o que importa é reconhecer a fronteira: cópia rasa resolve enquanto os campos
forem primitivos ou imutáveis.

---

## Saída real da execução

Colada do terminal, sem edição:

```
=== O problema ===
joao    = Joao (10.0)
apelido = Joao (10.0)
joao == apelido -> true

=== A correcao: construtor de copia ===
maria     = Maria (7.0)
copiaReal = Maria (10.0)
maria == copiaReal -> false

=== Passando objeto para metodo ===
antes  : Ana (6.0)
depois : Ana (10.0)

=== Passando primitivo para metodo ===
antes  : 6.0
depois : 6.0
```

Leia a saída na ordem em que ela ataca a questão. No primeiro bloco, `joao` foi alterado por uma linha
que só mencionava `apelido` — e o `true` do `==` explica por quê. No segundo, o construtor de cópia
faz a alteração ficar contida na cópia: `maria` continua com 7.0, e o `false` do `==` confirma que agora
são dois objetos. No terceiro e no quarto, o mesmo método com o mesmo nome produz efeitos opostos —
não porque as regras de passagem sejam diferentes, mas porque o que está sendo copiado é diferente:
lá um endereço, aqui um valor.
