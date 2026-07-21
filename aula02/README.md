# Aula 02 — Estatísticas da turma (resolução comentada)

> Esta é a branch `task/estatisticas`. Se você ainda não tentou resolver sozinho, volte para a `main`
> (`git switch main`) e tente primeiro — o texto abaixo entrega a resposta inteira.

Para rodar, a partir da raiz do repositório:

```bash
cd aula02
javac -d out Turma.java
java -cp out Turma
```

---

## Por que `media` usa for-each e `maior`/`menor` usam for com índice

Os quatro métodos percorrem o mesmo array, mas não da mesma maneira. Isso não é inconsistência de
estilo: cada laço foi escolhido pelo que o cálculo precisa saber.

Somar precisa apenas dos **valores**. Não importa se o `8.0` estava na posição 0 ou na 7 — a soma dá o
mesmo resultado em qualquer ordem, e o índice nunca é usado. Quando o índice não faz falta, o for-each
diz exatamente isso:

```java
for (double v : valores) {
    soma += v;
}
```

Lê-se "para cada valor `v` em `valores`". Não há contador para inicializar errado, não há condição de
parada para escrever `<=` no lugar de `<`, e é impossível estourar o array. A classe inteira de bugs de
índice simplesmente não existe nesse laço, porque não há índice.

Já `maior` precisa de algo que o for-each não oferece: **começar do segundo elemento**.

```java
double max = valores[0];
for (int i = 1; i < valores.length; i++) {
    if (valores[i] > max) {
        max = valores[i];
    }
}
```

A ideia é "assuma que o primeiro é o maior, e compare com todos os outros". Como `valores[0]` já foi
usado como palpite inicial, compará-lo consigo mesmo seria trabalho jogado fora — daí o `i = 1`. O
for-each sempre começa do primeiro e não tem como pular; para expressar "do segundo em diante" é preciso
o for com índice.

Vale dizer que um for-each aqui **também funcionaria** — comparar `valores[0]` com ele mesmo é inútil,
não é errado. A versão com índice foi escolhida porque torna a intenção visível: o `i = 1` é a única
pista, no código, de que o primeiro elemento tem um papel diferente dos demais. E porque você precisa
saber escrever as duas formas: for-each quando só interessa o valor, for com índice quando interessa a
**posição** — para começar do meio, andar de dois em dois, ir de trás para frente ou comparar `valores[i]`
com `valores[i - 1]`.

A regra prática: **use for-each por padrão, e for com índice quando o índice for realmente necessário.**
O laço mais simples que resolve é o que menos erra.

## Por que `maior`/`menor` iniciam com `valores[0]` e não com `0`

Inicializar com zero é a tentação natural, e funciona — com estes dados:

```java
double max = 0.0;                 // parece inofensivo
for (double v : valores) {
    if (v > max) max = v;
}
```

Com notas, nunca dá problema: nota não é negativa, então algum valor sempre supera o zero e `max`
acaba correto. O bug fica dormindo até o método ser reaproveitado — e ele *vai* ser, porque o
parâmetro se chama `valores`, não `notas`, e nada nele diz "só sirvo para notas".

No dia em que alguém passar temperaturas de inverno, saldos bancários ou variações percentuais:

```java
double[] temperaturas = {-3.0, -7.5, -1.2};
```

O maior valor real é `-1.2`. Mas nenhum deles é maior que `0.0`, então o `if` nunca dispara, `max`
continua `0.0`, e o método devolve **um número que não está no array**. Pior: não há exceção, não há
aviso, não há nada. Só uma resposta errada com cara de certa.

Inicializar com `valores[0]` remove a suposição. O palpite inicial passa a ser um elemento que
comprovadamente existe no array, e o resultado é necessariamente um dos valores recebidos — quaisquer que
sejam eles, negativos ou não. O método deixa de depender do domínio dos dados.

Um cuidado que fica em aberto: `valores[0]` estoura com `ArrayIndexOutOfBoundsException` se o array
vier **vazio**. Isso é discutível melhora — a versão com zero devolveria `0.0` silenciosamente para um
array vazio, o que é pior, porque esconde o problema. Ainda assim, código de produção trataria o caso
explicitamente, lançando uma exceção com mensagem clara ou devolvendo um `OptionalDouble`. Fica para
quando o assunto for tratamento de erros. (O mesmo vale para `media`, que devolve `NaN` ao dividir
`0.0` por `0` num array vazio.)

## Por que `aprovados` recebe o corte como parâmetro

Compare as duas assinaturas possíveis:

```java
static int aprovados(double[] valores)               // corte 6.0 cravado dentro
static int aprovados(double[] valores, double corte) // corte vem de fora
```

A primeira responde a uma pergunta só: "quantos tiraram 6.0 ou mais?". A segunda responde a uma família
inteira de perguntas, e a mesma linha de código serve para todas:

```java
aprovados(notas, 6.0)   // quantos passaram
aprovados(notas, 7.0)   // e se a media de corte subisse?
aprovados(notas, 9.0)   // quantos tiraram nota de excelencia
```

O padrão por trás disso: **o que varia entre um uso e outro vira parâmetro; o que é sempre igual fica
dentro do método.** O algoritmo — percorrer, comparar, contar — é sempre o mesmo, e por isso mora no
corpo. O limiar muda conforme a pergunta, o curso, o semestre, a decisão do colegiado. Cravá-lo lá
dentro obrigaria a editar o método, ou a duplicá-lo com outro nome, toda vez que a pergunta mudasse.

Repare que essa é a mesma ideia por trás de `valores.length`, discutida no projeto base, e por trás de
`double[] valores` em vez de mexer direto na variável `notas`: o método não decide nada que quem chama
deveria decidir. Ele recebe os dados e o critério, e devolve a resposta. É isso que o torna
reaproveitável — e é o embrião da ideia de **responsabilidade** que a disciplina vai perseguir quando
começarem as classes de verdade.

---

## Saída real da execução

Colada do terminal, sem edição:

```
=== ESTATISTICAS DA TURMA ===
Alunos:     10
Media:      6.20
Maior nota: 10.0
Menor nota: 2.5
Aprovados:  6 de 10
```

Confira à mão: as dez notas somam `62.0`, e `62.0 / 10` dá `6.2` — exibido como `6.20` por causa do
`%.2f`. As notas maiores ou iguais a `6.0` são `8.0`, `9.5`, `6.0`, `7.5`, `10.0` e `6.0`: seis
aprovados. E note que os dois `6.0` entraram na conta — o critério é `>=`, não `>`. Trocar um pelo
outro derrubaria dois alunos que passaram raspando, e é o tipo de erro que a saída não denuncia
sozinha; só conferindo é que aparece.
