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

public static String montarRelatorio (String nome, String departamento, String cargo, int ano, int mes, int dia) { ... }

