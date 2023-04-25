import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Seguradora seguradora = new Seguradora(
                "SeguradoraSegura",
                "(71) 99999-9999",
                "seguradora@segura.com",
                "Avenida Segura 221");

        ArrayList<Veiculo> veiculos_lucca = new ArrayList<>();
        veiculos_lucca.add(new Veiculo("666", "Ferrari", "Corsa"));
        veiculos_lucca.add(new Veiculo("777", "Fiat", "Palio"));
        ClientePF lucca = new ClientePF(
                "Lucca Mito",
                "Rua Lindo 221B",
                LocalDate.of(2022, Month.JULY, 8),
                "Muito educado",
                "Suspense",
                "Quebrado",
                veiculos_lucca,
                "070.680.938-68",
                LocalDate.of(2004, Month.JULY, 7));
        System.out.println("CPF Válido:" + lucca.validarCPF());

        seguradora.cadastrarCliente(lucca);

        ArrayList<Veiculo> veiculos_unicamp = new ArrayList<>();
        veiculos_unicamp.add(new Veiculo("123", "BMW", "Gol"));
        veiculos_unicamp.add(new Veiculo("321XXX", "Fiat", "Siena"));
        ClientePJ unicamp = new ClientePJ(
                "Unicamp",
                "Avenida Albert Einstein 314",
                LocalDate.of(1966, Month.OCTOBER, 5),
                veiculos_unicamp,
                "46.068.425/0001-33");
        System.out.println("CNPJ Válido:" + unicamp.validarCNPJ());

        seguradora.cadastrarCliente(unicamp);

        System.out.println("CLIENTES PF:");
        seguradora.listarClientes("PF");
        System.out.println("CLIENTES PJ:");
        seguradora.listarClientes("PJ");

    }
}
