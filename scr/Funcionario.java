import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class Funcionario {
    private String nome;
    private String departamento;
    private String cargo;

    public Funcionario(String nome, String departamento, String cargo) {
        this.nome = nome;
        this.departamento = departamento;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public boolean isTI() {
        return departamento != null && departamento.toUpperCase().contains("TI");
    }
}
