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
<p>Onde ocorre:</p>

```java
public static String montarRelatorio(String nome, String departamento,
String cargo, int ano, int mes, int dia) {
//Esse método cria cabeçalho, formata data, define atividades, cria resumo, verifica departamento, monta o relatório final. Ou seja, faz muitas coisas ao mesmo tempo.
```
