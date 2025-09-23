# AC1 - Desenvolver feature completa em TDD - Plataforma de Cursos Gamificada

## 📋 User Story

**EU COMO:** Aluno assinante  
**PRECISO/QUERO:** Receber 3 créditos de novos cursos ao concluir curso de média maior ou igual a 7  
**PARA:** Ser recompensado pelo meu desempenho

## 🧪 Behavior Driven Development (BDD)

### Cenário 1: Atribuição de créditos por média suficiente

| Etapa | Descrição |
|-------|-----------|
| **Dado que** | O aluno ao concluir o curso |
| **E** | Sua média final é igual à 7.0 |
| **Quando** | O sistema registrar a conclusão do curso |
| **E** | O sistema confirmar que a nota do curso realizado foi maior ou igual à 7 |
| **Então** | O aluno deve receber exatamente 3 créditos |

### Cenário 2: Não atribuição de créditos por média insuficiente

| Etapa | Descrição |
|-------|-----------|
| **Dado que** | O aluno ao concluir o curso |
| **E** | Sua média final for igual a 6.5 |
| **Quando** | O sistema registrar a conclusão do curso |
| **E** | O sistema confirmar que a nota do curso realizado foi menor do que 7.0 |
| **Então** | O aluno não deve receber créditos |

### Cenário 3: Atribuição de créditos com nota máxima

| Etapa | Descrição |
|-------|-----------|
| **Dado que** | O aluno ao concluir o curso |
| **E** | Sua média final for igual a 10.0 |
| **Quando** | O sistema registrar a conclusão do curso |
| **E** | O sistema confirmar que a nota do curso realizado foi maior ou igual à 7 |
| **Então** | O aluno deve receber exatamente 3 créditos |

### Cenário 4: Acumulação de créditos em aluno com créditos existentes

| Etapa | Descrição |
|-------|-----------|
| **Dado que** | O aluno possui 5 créditos acumulados |
| **E** | Sua média final for igual a 8.0 |
| **Quando** | O sistema registrar a conclusão do curso |
| **E** | O sistema confirmar que a nota do curso realizado foi maior ou igual à 7 |
| **Então** | O aluno deve ter seu total de créditos aumentado para 8 (5 + 3) |

## 🚀 Funcionalidade Implementada

### Regra de Negócio
- **Média ≥ 7.0**: Concede 3 créditos para novos cursos
- **Média < 7.0**: Não concede créditos
- **Nota máxima (10.0)**: Concede 3 créditos (não há bônus adicional)
- **Créditos acumulativos**: Novos créditos somam aos existentes

### Casos de Teste Implementados
1. **BDD#1**: Nota 7.0 concede exatamente 3 créditos
2. **BDD#2**: Nota 6.5 não concede créditos  
3. **BDD#3**: Nota 10.0 concede exatamente 3 créditos
4. **BDD#4**: Aluno com créditos existentes acumula novos créditos
