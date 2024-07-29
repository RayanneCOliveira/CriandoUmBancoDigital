import java.util.ArrayList;
import java.util.List;

public class Extrato {

    private List<String> registros = new ArrayList<>();

    public void adicionarRegistro(String registro) {
        registros.add(registro);
    }

    public void imprimirExtrato() {
        registros.forEach(System.out::println);
    }
}
