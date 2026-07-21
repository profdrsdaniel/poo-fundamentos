# Aula 01 — Boletim (resolução comentada)

> Esta é a branch `task/boletim`. Se você ainda não tentou resolver sozinho, volte para a `main`
> (`git switch main`) e tente primeiro — o texto abaixo entrega a resposta inteira.

Para rodar, a partir da raiz do repositório:

```bash
cd aula01
javac -d out Boletim.java
java -cp out Boletim
```

---

## Por que dividir por `3.0` e não por `3`

Neste programa específico, `(nota1 + nota2 + nota3) / 3` daria o mesmo resultado. As notas são `double`,
a soma é `double`, e em Java basta **um** dos operandos ser `double` para o outro ser promovido: o `3`
inteiro viraria `3.0` sozinho e a divisão sairia certa. Então por que insistir no `3.0`?

Porque o dia em que as notas chegarem como `int` — vindas de um banco, de um formulário, de um arquivo
CSV — a linha continua compilando, continua sem aviso nenhum, e passa a dar a resposta errada. Com
`int nota1 = 8, nota2 = 5, nota3 = 4`, a expressão `(8 + 5 + 4) / 3` é uma divisão **entre inteiros**:
o resultado é `5`, não `5.666...`. A parte fracionária não é arredondada, é descartada. E declarar
`double media` não corrige nada, porque o estrago acontece na conta, antes de o valor ser guardado —
o que se guarda é o `5` que já perdeu a informação.

O `3.0` custa um caractere e remove a classe inteira desse bug. É por isso que ele é hábito, e não
preciosismo: você não escreve `3.0` porque o código de hoje precisa, escreve porque o código de amanhã
vai precisar e ninguém vai lembrar de revisar essa linha.

## Por que a condicional testa `>= 6.0` primeiro

Repare que o segundo teste é só `media >= 4.0`, sem nenhuma menção a `< 6.0`:

```java
if (media >= 6.0) {
    situacao = "Aprovado";
} else if (media >= 4.0) {
    situacao = "Recuperacao";
} else {
    situacao = "Reprovado";
}
```

Isso funciona porque um `else if` só é avaliado quando **todos os testes anteriores falharam**. Quando
o programa chega no `media >= 4.0`, já é fato consumado que `media < 6.0` — senão teria parado no
primeiro ramo. A faixa "entre 4.0 e 6.0" está expressa pela *posição* do teste na cadeia, não por uma
condição composta. Daí a cadeia ler como uma peneira: cada ramo pega o que sobrou do anterior.

Agora inverta a ordem e veja o desastre:

```java
if (media >= 4.0) {          // ERRADO
    situacao = "Recuperacao";
} else if (media >= 6.0) {
    situacao = "Aprovado";
}
```

Uma média `9.0` é maior ou igual a `4.0`, então ela entra no primeiro ramo e vira `"Recuperacao"`. O
ramo `"Aprovado"` fica **inalcançável**: nenhum valor no mundo chega até ele, porque tudo que passaria
no teste `>= 6.0` já foi capturado antes. Todo aluno aprovado da turma vai para recuperação, e o
compilador não reclama — a sintaxe está perfeita, a lógica é que está de cabeça para baixo.

A regra que evita isso: em cadeia de faixas, **teste sempre da condição mais restritiva para a mais
frouxa**. Do topo da escala para a base, ou o contrário — mas em ordem, nunca embaralhado.

## Por que `printf("%.2f")` no lugar de `println`

A média aqui é `17.5 / 3.0`, uma dízima. Com `System.out.println("Media: " + media)` a saída seria:

```
Media: 5.833333333333333
```

Treze casas decimais, porque o `println` de um `double` imprime o valor com a precisão necessária para
distinguí-lo de qualquer outro `double` — que é uma decisão excelente para depurar e péssima para
mostrar a alguém. Boletim não tem treze casas; boletim tem duas.

O `printf` separa as duas coisas: o `double` continua guardando o valor cheio na memória, e o `%.2f`
decide apenas **como escrevê-lo**, arredondando na exibição. O `%n` no fim é a quebra de linha (prefira-o
ao `\n`: ele usa a quebra de linha do sistema operacional). E o `printf` não pula linha sozinho, ao
contrário do `println` — esquecer o `%n` é o motivo mais comum de duas linhas saírem grudadas.

### E o `Locale.US`?

Este é o detalhe que morde numa máquina brasileira. O `printf` formata números segundo o locale do
sistema, e em `pt_BR` o separador decimal é a **vírgula**. Sem o `Locale.US`, a saída seria:

```
Media: 5,83
```

Não é bug, é a norma brasileira sendo respeitada. Mas a saída deste exercício é comparada com um
gabarito, e gabarito não pode depender de como cada máquina está configurada. Por isso o primeiro
argumento fixa o formato:

```java
System.out.printf(Locale.US, "Media: %.2f%n", media);
```

A lição vale muito além da aula: **texto que será lido por outro programa** — gabarito, arquivo CSV,
JSON, log que vai para um parser — tem o locale fixado explicitamente. Texto que será lido por uma
pessoa usa o locale dela. Confundir os dois é como um sistema exporta relatório que quebra ao ser
reimportado noutro computador.

---

## Saída real da execução

Colada do terminal, sem edição:

```
=== BOLETIM ===
Aluno: Ana Souza
Media: 5.83
Situacao: Recuperacao
```

Confira o resultado contra as regras: a média de `8.0`, `5.5` e `4.0` é `5.8333...`, que é menor que
`6.0` e maior ou igual a `4.0` — cai no segundo ramo da cadeia, `"Recuperacao"`. E aparece como `5.83`,
com ponto e duas casas, em qualquer máquina.
