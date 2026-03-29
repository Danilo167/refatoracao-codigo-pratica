<h1 align="center">
  Diário de Refatoração
</h1>
<p>
Este documento registra o processo de melhoria de qualidade do sistema Relatórios de Funcionários. Partimos de um código procedural e rígido para uma arquitetura baseada em objetos, visando facilitar a manutenção e a extensibilidade do software conforme os princípios de Engenharia de Software.
</p>
<h2>Registro de Mudanças</h2>
<p>
Alteração 1: Encapsulamento de Entidades
<br><br>
- Smell identificado: Primitive Obsession (Obsessão por Primitivos) e Data Clumps (Agrupamento de Dados). O código tratava informações de funcionários e datas como variáveis soltas (String nome, int dia, etc.).
<br>
- Refatoração aplicada: Extract Class (Extração de Classe) e Introduce Parameter Object.
<br><br>
Antes:
</p>

```java
public static String montarRelatorio (String nome, String departamento, String cargo, int ano, int mes, int dia) { ... }
```
<p>
Depois:
</p>

```java
            class Funcionario {
            private String nome, departamento, cargo;
            // Métodos de acesso e lógica de TI encapsulados
            }
            public static String montarRelatorio (Funcionario funcionario, LocalDate data,  List<String>     atividades) { ... }
```
<p>
Impacto: Melhora na manutenção e legibilidade. A assinatura do método ficou limpa e o risco de passar dados errados (como trocar ano por dia) foi eliminado pelo uso de tipos fortes como Funcionario e LocalDate.
</p>

<h2>Alteração 2: Decomposição do Método Monolítico</h2>
<p>
Smell identificado: Long Method (Método Muito Longo). O método montarRelatorio fazia tudo: formatava o cabeçalho, processava a data, criava a lista de atividades e aplicava regras de TI.
<br><br>
Refatoração aplicada: Extract Method (Extração de Método).
<br><br>
Antes:
</p>

```java
public static String montarRelatorio (...) {
           String cabecalho = "Relatório de " + nome + "-" + departamento + ...;
           String dataStr = "Data: " + dia + "/" + mes + "/" + ano + "\n";
           String corpo = "Atividades:\n- Reuniões...\n";
           // ... mais 15 linhas de lógica misturada ...
          return relatorio;
          }
```
<p>
Depois:
</p>

```java
public static String montarRelatorio (Funcionario f, LocalDate d, List<String> a) {
    return gerarCabecalho(f) + 
           formatarData(d) + 
           gerarCorpo(a) + 
           gerarResumo() + 
           adicionarRecursosExtras(f);
}
```
<p>
Impacto: Melhora na testabilidade e reuso. Cada parte do relatório agora é um método privado independente. Se precisarmos mudar apenas o formato da data, não corremos o risco de quebrar a lógica do cabeçalho.
</p>
<h2>
Alteração 3: Dinamização de Conteúdo
</h2>
<p>
Smell identificado: Hardcoded Strings (Textos Fixos). As atividades estavam escritas diretamente no código, impedindo que o relatório fosse personalizado para diferentes funcionários.
<br><br>
Refatoração aplicada: Parameterize Method (Parametrizar Método).
<br><br>
Antes:
</p>

```java
corpo += "- Reuniões com equipe\n";
corpo += "- Entregas do sprint\n";
corpo += "- Pendências\n";
```
<p>
Depois:
</p>

```java
private static String gerarCorpo(List<String> atividades) {
    if (atividades == null || atividades.isEmpty()) return "Atividades:\n";
    return "Atividades:\n-" + String.join("\n- ", atividades) + "\n";
}
```
<p>
Impacto: Aumento drástico no reuso. O sistema agora segue o Princípio Aberto/Fechado (OCP): podemos gerar relatórios com qualquer lista de atividades sem precisar abrir e modificar a classe ReportBuilder.
</p>
<h2>
Alteração 4: Centralização de Regras de Negócio
</h2>
<p>
Smell identificado: Feature Envy (Inveja de Dados). O construtor do relatório ficava "analisando" as strings do funcionário para saber se ele era de TI.
<br><br>
Refatoração aplicada: Move Method (Mover Método).
<br><br>
Antes:
</p>

```java
if (departamento != null && departamento.toUpperCase().contains("TI")) { 
    extra = "Recursos de TI utilizados..."; 
}
```
<p>
Depois:
</p>

```java
// Na classe Funcionario:
           public boolean isTI() {
                       return departamento != null && departamento.toUpperCase().contains("TI");
            }

            // No ReportBuilder:
            if (funcionario.isTI()) { ... }
```
<p>
Impacto: Melhor coesão. A responsabilidade de saber se um funcionário é de TI pertence ao objeto Funcionario. Isso evita duplicação de lógica caso outro sistema precise dessa mesma informação.
</p>
<h2>
Alternativa de Refatoração Não Utilizada
</h2>
<p>
Para o tratamento da regra de negócio de TI (Alteração 4), cogitamos utilizar Replace Conditional with Polymorphism (Substituir Condicional por Polimorfismo).
<br><br>
Descrição: Criaríamos uma subclasse FuncionarioTI que herdaria de Funcionario e sobrescreveria um método getRecursosExtras().
<br><br>
Por que não foi escolhida: Embora o polimorfismo seja uma solução elegante para sistemas complexos, no contexto atual do código (que possui apenas uma condicional simples), essa abordagem adicionaria uma complexidade desnecessária (sobrecarga de classes). Optamos pelo Move Method e Encapsulamento por serem soluções mais simples e diretas para o tamanho do problema proposto, mantendo o código limpo sem criar uma hierarquia de classes prematura.
<br><br>
A refatoração permitiu que o código deixasse de ser um "texto formatado manualmente" para se tornar um processo estruturado. A maior lição aprendida pelo grupo foi que código limpo não é apenas sobre estética, mas sobre viabilidade econômica: um código refatorado é mais barato de manter e mais rápido de evoluir.
</p>
