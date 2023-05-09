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
        seguradora.cadastrarCliente(lucca);

        for (Cliente cliente : seguradora.getListaClientes()) {
            System.out.println(cliente);
        }

        System.out.println(lucca);
        seguradora.calcularPrecoSeguroCliente(lucca);

        for (Cliente cliente : seguradora.getListaClientes()) {
            System.out.println(cliente);
        }

        System.out.println(lucca);

        lucca.setNome("accul");

        for (Cliente cliente : seguradora.getListaClientes()) {
            System.out.println(cliente);
        }
        // menuInterativo();
    }
}
