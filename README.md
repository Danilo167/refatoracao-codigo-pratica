<h1 align="center">  
ATIVIDADE PRÁTICA - REFATORAÇÃO:
Identificação de Smells
</h1>

<h2>
1. Código Utilizado:
</h2>
<br>
<p>
  Relatórios de Funcionários: geração de relatórios textuais de atividades de
colaboradores:
</p>
<br>

```java
class Grupo6_ReportBuilder {

    public static String montarRelatorio(String nome, String departamento, String cargo,
                                         int ano, int mes, int dia) {

        String cabecalho = "Relatório de " + nome + " - " + departamento + " (" + cargo + ")\n";
        String data = "Data: " + dia + "/" + mes + "/" + ano + "\n";

        String corpo = "Atividades:\n";
        corpo += "- Reuniões com equipe\n";
        corpo += "- Entregas do sprint\n";
        corpo += "- Pendências\n";

        String resumo = "Resumo: OK\n";

        String relatorio = cabecalho + data + corpo + resumo;

        if (departamento != null && departamento.toUpperCase().contains("TI")) {
            String extra = "Recursos de TI utilizados: Git, CI/CD\n";
            relatorio += extra;
        }

        return relatorio;
    }

    public static void main(String[] args) {
        System.out.println(
            montarRelatorio("Carla", "TI", "Analista", 2025, 9, 18)
        );
    }
}
```

<h2>
2. Identificação de Smells:
</h2>
<p>
A análise de code smells serve para identificar problemas de design que dificultam a manutenção, evolução e reutilização do software. No código fornecido, é possível identificar pelo menos 4 smells claros:
</p>

<h3>1. 
Long Method - Método Muito Longo:
</h3>
<p>
Onde ocorre:
</p>

```java
public static String montarRelatorio(String nome, String departamento,
String cargo, int ano, int mes, int dia) {
//Esse método cria cabeçalho, formata data, define atividades, cria resumo, verifica departamento, monta o relatório final. Ou seja, faz muitas coisas ao mesmo tempo.
```
<p>
Por que é um smell?
</p>
<p>Segundo boas práticas de POO, um método deve ter uma única responsabilidade. 
<br><br>
Aqui, o método:
<br><br>
- formatar dados
<br>
- contém regras de negócio
<br>
- constrói texto
<br><br>
Problemas gerados:
<br><br>
- difícil de testar em partes
<br>
- difícil de modificar sem quebrar algo
<br>
- difícil de entender rapidamente
</p>
<h2>
2. Primitive Obsession - Obsessão por Tipos Primitivos:
</h2>
<p>
Onde ocorre:
</p>

```java
String nome, String departamento, String cargo,
int ano, int mes, int dia
//O método recebe muitos dados primitivos ao invés de objetos mais significativos.
```
<p>
Por que é um smell?
<br><br>
Em POO, deveria existir uma classe:
<br>
class Funcionario
<br>
class Data
<br><br>
Mas o código usa 3 strings e 3 inteiros.
<br><br>
Problemas gerados:
<br><br>
- aumenta risco de passar dados errados
<br>
- reduz legibilidade
<br>
- dificulta validação
</p>
<h2>
3. Data Clumps - Agrupamento de Dados:
</h2>
<p>
Onde ocorre:
</p>

```java
int ano, int mes, int dia
//Esses três valores sempre aparecem juntos, representando uma única coisa: uma data.
```
<p>
Por que é um smell?
<br><br>
Quando vários parâmetros aparecem juntos repetidamente, significa que eles deveriam ser um objeto.
<br><br>
Exemplo correto:
<br><br>
LocalDate data;
</p>
Problemas gerados:
<br><br>
- poluição de parâmetros
<br>
- aumento de erros humanos
<br>
- assinatura do método fica grande e confusa

<br><br>
<h2>
<strong>
Integrantes
</strong>
</h2>
<h3>
- Aryanny Galvao da Silva Nascimento
- Daniel Goncalves da Silva
- Danilo Scheidt Caxias do Rêgo
- Jamilly Santos de Lima
- Maria Rafaela Nunes da Silva
- Raika de Souza Silva
- Renato Felix da Silva Neto
</h3>
