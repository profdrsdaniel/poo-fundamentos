# Fundamentos de Programação Orientada a Objetos

Repositório oficial da disciplina. Aqui ficam os projetos base de cada aula — o código que você recebe
para trabalhar em sala — e, em branches separadas, as resoluções comentadas dos exercícios.

Você vai clonar este repositório **uma vez** e usá-lo o semestre inteiro.

## Como o repositório está organizado

Cada pasta na raiz é uma aula:

```
poo-fundamentos/
├── aula01/     Primeiro contato com Java: tipos, condicional e saída formatada
├── aula04/     Objetos na memória: referência, aliasing e construtor de cópia
└── ...         (as próximas aulas aparecem aqui ao longo do semestre)
```

Cada pasta de aula tem seu próprio `README.md` explicando o que fazer, como rodar e o que entregar.
**Leia o README da aula antes de abrir o código.**

## Branches: onde está cada coisa

- A branch **`main`** contém **apenas os projetos base**. É o ponto de partida limpo, sem respostas.
  É nela que você trabalha.
- As **resoluções** ficam em branches separadas, com o padrão `task/<assunto>`.

### Resoluções disponíveis

| Aula | Branch | Assunto | Link |
| --- | --- | --- | --- |
| 01 | `task/boletim` | Média, condicional encadeada e saída formatada | [ver branch](https://github.com/profdrsdaniel/poo-fundamentos/tree/task/boletim) |
| 04 | `task/objetos-na-memoria` | Aliasing, construtor de cópia e passagem de parâmetros | [ver branch](https://github.com/profdrsdaniel/poo-fundamentos/tree/task/objetos-na-memoria) |

> Uma resolução só faz sentido depois que você tentou. Consultar antes de tentar transforma um exercício
> de raciocínio em exercício de leitura.

## Como clonar

Abra o terminal, vá até a pasta onde você guarda seus projetos e rode:

```bash
git clone https://github.com/profdrsdaniel/poo-fundamentos.git
cd poo-fundamentos
```

Isso cria a pasta `poo-fundamentos` já na branch `main`.

## Como trocar de branch

Se você nunca usou git, pense numa branch como uma versão paralela da mesma pasta. Trocar de branch
muda o conteúdo dos arquivos **na sua pasta**, sem criar cópias.

Ver em qual branch você está:

```bash
git branch
```

Baixar a lista de branches que existem no servidor:

```bash
git fetch --all
```

Ir para a branch de resolução da Aula 04:

```bash
git switch task/objetos-na-memoria
```

Voltar para o projeto base:

```bash
git switch main
```

Se o seu git for antigo e não reconhecer o `switch`, use `git checkout` no lugar dele
(`git checkout main`) — faz a mesma coisa neste caso.

> **Antes de trocar de branch, salve o seu trabalho.** Se você tiver alterações não commitadas, o git
> pode recusar a troca. O caminho mais simples é commitar o que fez:
> `git add . && git commit -m "minhas anotacoes da aula 04"`.

## Requisitos

- **JDK 21 ou superior** — confira com `java -version` no terminal.
- **IntelliJ IDEA** (Community Edition serve).
- **git** instalado.
