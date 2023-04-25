import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Seguradora seguradora;

    public static void menuInterativo() {
        /* Menu interativo usando System.in */
        Scanner scan = new Scanner(System.in);

        int comando = 0;
        while (comando != 4) {
            System.out.println("Bem vindo(a) à SeguradoraSegura! Como posso te ajudar?");
            System.out.println("(1) Listar clientes");
            System.out.println("(2) Visualizar sinistro");
            System.out.println("(3) Listar sinistros");
            System.out.println("(4) Sair");
            comando = scan.nextInt();
            if (comando == 1) {
                System.out.println("Gostaria de listar clientes PJ (1) ou PF? (2)");
                int tipo = scan.nextInt();
                if (tipo == 1) {
                    seguradora.listarClientes("PJ");
                } else {
                    seguradora.listarClientes("PF");
                }
            } else if (comando == 2) {
                System.out.println("Insira o nome do cliente desejado: ");
                String cliente = scan.next();
                cliente = cliente.trim();
                seguradora.visualizarSinistro(cliente);
            } else if (comando == 3) {
                seguradora.listarSinistros();
            }
        }
        scan.close();
    }

    public static void main(String[] args) {
        /* Cria uma seguradora e instância todos os métodos necessários */
        seguradora = new Seguradora(
                "SeguradoraSegura",
                "(71) 99999-9999",
                "seguradora@segura.com",
                "Avenida Segura 221");

        ArrayList<Veiculo> veiculos_lucca = new ArrayList<>();
        veiculos_lucca.add(new Veiculo("666", "Ferrari", "Corsa", 2018));
        veiculos_lucca.add(new Veiculo("777", "Fiat", "Palio", 2002));
        ClientePF lucca = new ClientePF(
                "Lucca",
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
        veiculos_unicamp.add(new Veiculo("123", "BMW", "Gol", 1999));
        veiculos_unicamp.add(new Veiculo("321XXX", "Fiat", "Siena", 2007));
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

        seguradora.gerarSinistro(
                new Veiculo("SSA123", "Volquisquiabo", "Creta", 2000),
                lucca,
                "Rua Saturnino de Brito");
        seguradora.gerarSinistro(
                new Veiculo("BSB727", "Rocket", "League", 2018),
                lucca,
                "Rua Chico Buarque de Holanda");
        seguradora.gerarSinistro(
                new Veiculo("SAO2022", "Magnus", "Carlsen", 1988),
                unicamp,
                "Rua Roxo Moreira");

        System.out.println("SINISTROS DE 'Lucca':");
        seguradora.visualizarSinistro("Lucca");
        System.out.println("SINISTROS DE 'Unicamp':");
        seguradora.visualizarSinistro("Unicamp");
        System.out.println("SINISTROS:");
        seguradora.listarSinistros();

        menuInterativo();
    }
}
