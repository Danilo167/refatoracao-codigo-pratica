import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Funcionario carla = new Funcionario("Carla", "TI", "Analista");

        LocalDate dataRelatorio = LocalDate.of(2025, 9, 18);

        List<String> atividadesCarla = new ArrayList<>();
        atividadesCarla.add("Reuniões com equipe");
        atividadesCarla.add("Entregas do sprint");
        atividadesCarla.add("Pendências");

        String relatorio = ReportBuilder.montarRelatorio(carla, dataRelatorio, atividadesCarla);

        System.out.println(relatorio);
    }
}
