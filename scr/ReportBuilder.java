class ReportBuilder {

    public static String montarRelatorio(Funcionario funcionario, LocalDate data, List<String> atividades) {
        if (funcionario == null || data == null) {
            return "Erro: dados inválidos.";
        }

        return gerarCabecalho(funcionario) +
               formatarData(data) +
               gerarCorpo(atividades) +
               gerarResumo() +
               adicionarRecursosExtras(funcionario);
    }

    private static String gerarCabecalho(Funcionario f) {
        return "Relatório de " + safe(f.getNome()) + " - " +
               safe(f.getDepartamento()) + " (" +
               safe(f.getCargo()) + ")\n";
    }

    private static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Data: " + data.format(formatter) + "\n";
    }

    private static String gerarCorpo(List<String> atividades) {
        if (atividades == null || atividades.isEmpty()) {
            return "Atividades:\n";
        }

        return "Atividades:\n- " + String.join("\n- ", atividades) + "\n";
    }

    private static String gerarResumo() {
        return "Resumo: OK\n";
    }

    private static String adicionarRecursosExtras(Funcionario f) {
        if (f.isTI()) {
            return "Recursos de TI utilizados: Git, CI/CD\n";
        }
        return "";
    }

    private static String safe(String valor) {
        return valor == null ? "N/A" : valor;
    }
}
