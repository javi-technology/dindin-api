# Regras Gerais do Copilot

- Idioma: pt-BR. Respostas objetivas e técnicas, como um analista sênior.

## TDD (Red → Green → Refactor)

Siga rigorosamente o ciclo TDD em toda implementação:

1. **Red**: Escreva o teste primeiro. Ele deve falhar.
2. **Green**: Implemente apenas o mínimo para o teste passar.
3. **Refactor**: Refatore mantendo todos os testes verdes.

### Restrições

- NUNCA escreva código de produção sem um teste que o justifique.
- NUNCA escreva mais código do que o necessário para o teste passar.
- Antes de criar ou alterar código, verifique se existe teste correspondente. Se não, crie-o primeiro.
- Para bugs: teste que reproduz o defeito → correção.
- Testes devem ser pequenos, isolados e com responsabilidade única.

## Formatação de Código

- Formate todo código gerado seguindo as boas práticas da linguagem (indentação, espaçamento, quebras de linha).
- Mantenha consistência com o estilo já existente no projeto.
- Após formatar, remova imports e variáveis não utilizados.

---

# Instruções para Mensagens de Commit

## IMPORTANTE: IDIOMA OBRIGATÓRIO

**TODAS as mensagens de commit DEVEM ser escritas em PORTUGUÊS BRASILEIRO. NUNCA use inglês.**

## IMPORTANTE: NÚMERO DA ISSUE É OBRIGATÓRIO

**ANTES de gerar qualquer mensagem de commit, SEMPRE verifique o nome da branch atual.**

**O número da issue é OBRIGATÓRIO em TODAS as branches, EXCETO a branch `main`.**

- Se a branch for `issue-XX` (ex: `issue-25`), você DEVE incluir `#25` na mensagem
- Se a branch for `develop`, você DEVE usar `#44`
- Se a branch for `main`, NÃO inclua número de issue

**NUNCA gere uma mensagem de commit sem o número da issue (exceto para branch main).**

## Padrão de Commits (Conventional Commits)

Ao gerar mensagens de commit, SEMPRE utilize o padrão Conventional Commits com os seguintes prefixos:

### Prefixos Obrigatórios

| Prefixo     | Descrição                           | Exemplo                                               |
| ----------- | ----------------------------------- | ----------------------------------------------------- |
| `feat:`     | Nova funcionalidade                 | `feat: adicionar filtro de busca por autor`           |
| `fix:`      | Correção de bug                     | `fix: corrigir erro ao salvar livro sem capítulos`    |
| `bugfix:`   | Correção de bug (alternativo)       | `bugfix: resolver problema de validação de data`      |
| `hotfix:`   | Correção urgente em produção        | `hotfix: corrigir falha crítica no login`             |
| `docs:`     | Alterações na documentação          | `docs: atualizar README com instruções de instalação` |
| `style:`    | Formatação, sem alteração de código | `style: ajustar indentação do componente`             |
| `refactor:` | Refatoração de código               | `refactor: simplificar lógica de addChapter`          |
| `perf:`     | Melhorias de performance            | `perf: otimizar carregamento de imagens`              |
| `test:`     | Adição ou correção de testes        | `test: adicionar testes para BookAdminForms`          |
| `chore:`    | Tarefas de manutenção               | `chore: atualizar dependências do projeto`            |
| `build:`    | Alterações no build                 | `build: configurar variáveis de ambiente`             |
| `ci:`       | Alterações no CI/CD                 | `ci: adicionar pipeline de deploy`                    |
| `revert:`   | Reverter commit anterior            | `revert: reverter feat de filtro de busca`            |

### Formato da Mensagem

**Para branches `issue-XX` e `develop` (OBRIGATÓRIO incluir número):**

```
<tipo>(#<numero>): <descrição curta>
<tipo>(<escopo>)(#<numero>): <descrição curta>  // quando houver escopo
```

**Para branch `main` (NÃO incluir número):**

```
<tipo>: <descrição curta>
<tipo>(<escopo>): <descrição curta>  // quando houver escopo
```

**Corpo e rodapé (opcionais em ambos os casos):**

```
[corpo opcional]

[rodapé opcional]
```

### Extração do Número da Issue da Branch

**PASSO OBRIGATÓRIO: Primeiro, identifique o nome da branch atual.**

**SEMPRE extraia o número da issue do nome da branch e inclua na mensagem de commit:**

1. **Branches no formato `issue-XX`**: Extrair o número após "issue-"
   - **OBRIGATÓRIO**: Se a branch for `issue-25`, você DEVE usar `#25` na mensagem
   - Exemplo: Branch `issue-25` → formato obrigatório: `feat(#25): descrição` ou `feat(books)(#25): descrição`
   - **ERRO CRÍTICO**: Gerar mensagem sem o número quando a branch é `issue-XX`

2. **Branch `develop`**: SEMPRE usar `#44`
   - **OBRIGATÓRIO**: Se a branch for `develop`, você DEVE usar `#44` na mensagem
   - Formato obrigatório: `feat(#44): descrição` ou `feat(books)(#44): descrição`
   - **ERRO CRÍTICO**: Gerar mensagem sem o número quando a branch é `develop`

3. **Branch `main`**: NÃO incluir número de issue (formato normal sem parênteses)
   - Apenas para branch `main`: Formato `feat: descrição` ou `feat(books): descrição`
   - **ATENÇÃO**: Se a branch não for `main`, você DEVE incluir o número da issue

### Regras OBRIGATÓRIAS

1. **Idioma**: TODA mensagem DEVE ser em PORTUGUÊS BRASILEIRO. Proibido usar inglês.
2. **Número da Issue (OBRIGATÓRIO)**:
   - **PRIMEIRO**: Verifique o nome da branch atual
   - **OBRIGATÓRIO**: Para branches `issue-XX`, extrair o número após "issue-" e incluir como `#XX` no formato
   - **OBRIGATÓRIO**: Para branch `develop`, sempre usar `#44`
   - **EXCEÇÃO**: Apenas branch `main` não inclui número de issue
   - **Formato obrigatório** (exceto main): `tipo(#numero):` ou `tipo(escopo)(#numero):` quando houver escopo
   - **ERRO CRÍTICO**: Gerar mensagem sem número da issue quando não estiver na branch `main`
3. **Tipo**: Sempre em minúsculo
4. **Descrição**: SEMPRE iniciar com verbo no infinitivo em PORTUGUÊS BRASILEIRO (ex: "adicionar", "corrigir", "atualizar", "remover", "refatorar")
5. **Limite**: Máximo de 72 caracteres na primeira linha
6. **Escopo**: Opcional, indica o módulo afetado (ex: `feat(books)(#25):`)

### Exemplos Práticos CORRETOS

#### Branches com formato `issue-XX`

```
feat(#25): adicionar formulário de cadastro de livros

fix(auth)(#30): corrigir validação de token expirado

refactor(forms)(#25): extrair lógica de upload para service

docs(readme)(#18): adicionar seção de contribuição

chore(#42): atualizar Angular para versão 19
```

#### Branch `develop` (sempre #44)

```
feat(#44): implementar nova funcionalidade de busca

fix(books)(#44): corrigir erro ao salvar livro
```

#### Branch `main` (sem número de issue)

```
feat: atualizar dependências do projeto

refactor(forms): simplificar lógica de validação

chore: atualizar instruções de commit
```

### Exemplos INCORRETOS (NÃO FAÇA ISSO)

❌ `refactor: update commit message instructions` (inglês - ERRADO)
❌ `feat: add new feature` (inglês - ERRADO)
❌ `fix: fix bug` (inglês - ERRADO)
❌ `refactor: reorganizar instruções de commit e remover arquivos obsoletos` (FALTANDO número da issue na branch `issue-XX` - ERRADO CRÍTICO, deveria ser `refactor(#XX): ...`)
❌ `feat(books): adicionar funcionalidade` (faltando número da issue na branch `issue-25` - ERRADO CRÍTICO, deveria ser `feat(books)(#25):`)
❌ `feat: adicionar funcionalidade` (faltando número da issue na branch `issue-25` - ERRADO CRÍTICO, deveria ser `feat(#25):`)
❌ `feat(#44): implementar feature` (usando #44 em branch diferente de `develop` - ERRADO)
❌ `feat(#25): add new feature` (misturando inglês com número - ERRADO, deveria ser português)

**ATENÇÃO**: Mensagens sem número da issue são ERRADAS, exceto quando a branch for `main`.

### Breaking Changes

Para mudanças que quebram compatibilidade:

```
feat(api)(#25)!: alterar estrutura de resposta do endpoint de livros

BREAKING CHANGE: o campo 'chapters' agora retorna array de objetos
```

**Nota**: O número da issue ainda deve ser incluído mesmo em breaking changes, seguindo as mesmas regras de branches (`issue-XX` → `#XX`, `develop` → `#44`, `main` → sem número).

## Lembrete Final

**SEMPRE escreva em PORTUGUÊS BRASILEIRO. Use verbos no infinitivo.**
