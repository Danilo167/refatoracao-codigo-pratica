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
<h1 align="center">Atividade Prática - Refatoração: Identificação de Code Smells</h1>

## Exemplo de Código

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
