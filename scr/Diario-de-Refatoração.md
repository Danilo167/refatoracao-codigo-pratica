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
<br>
- Antes:
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



