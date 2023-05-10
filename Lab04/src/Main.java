import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Seguradora> seguradoras;

    public static Seguradora encontrar_seguradora(String nome) {
        for (Seguradora seguradora : seguradoras) {
            if (seguradora.getNome().equals(nome)) {
                return seguradora;
            }
        }
        return null;
    }

    public static Veiculo ler_veiculo(Scanner scan) {
        String placa, marca, modelo;
        int anoFabricacao;
        System.out.println("Placa:");
        placa = scan.nextLine();
        System.out.println("Marca:");
        marca = scan.nextLine();
        System.out.println("Modelo:");
        modelo = scan.nextLine();
        System.out.println("Ano fabricação:");
        anoFabricacao = Integer.parseInt(scan.nextLine());
        return new Veiculo(placa, marca, modelo, anoFabricacao);
    }

    public static Seguradora ler_seguradora(Scanner scan) {
        String nome, telefone, email, endereco;
        System.out.println("Nome:");
        nome = scan.nextLine();
        System.out.println("Telefone:");
        telefone = scan.nextLine();
        System.out.println("Email:");
        email = scan.nextLine();
        System.out.println("Endereço:");
        endereco = scan.nextLine();
        return new Seguradora(nome, telefone, email, endereco);
    }

    public static ClientePJ ler_cliente_pj(Scanner scan) {
        String nome, endereco, data, cnpj;
        int qtd_funcionarios;
        System.out.println("Nome:");
        nome = scan.nextLine();
        System.out.println("Endereço:");
        endereco = scan.nextLine();
        System.out.println("Data de fundação: ");
        data = scan.nextLine();
        System.out.println("CNPJ: ");
        cnpj = scan.nextLine();
        System.out.println("Quantidade de funcionários: ");
        qtd_funcionarios = Integer.parseInt(scan.nextLine());
        return new ClientePJ(nome, endereco,
                LocalDate.parse(data, DateTimeFormatter.ofPattern("d/MM/yyyy")), null,
                cnpj, qtd_funcionarios);
    }

    public static ClientePF ler_cliente_pf(Scanner scan) {
        String nome, endereco, data_licensa, data_nascimento, educacao, genero, classe_economica, cpf;
        System.out.println("Nome: ");
        nome = scan.nextLine();
        System.out.println("Endereço: ");
        endereco = scan.nextLine();
        System.out.println("Data de licensa: ");
        data_licensa = scan.nextLine();
        System.out.println("Data de nascimento: ");
        data_nascimento = scan.nextLine();
        System.out.println("Educação: ");
        educacao = scan.nextLine();
        System.out.println("Classe econômica: ");
        classe_economica = scan.nextLine();
        System.out.println("Gênero: ");
        genero = scan.nextLine();
        System.out.println("CPF: ");
        cpf = scan.nextLine();
        return new ClientePF(nome, endereco,
                LocalDate.parse(data_licensa, DateTimeFormatter.ofPattern("d/MM/yyyy")),
                educacao,
                genero,
                classe_economica,
                null,
                cpf,
                LocalDate.parse(data_nascimento, DateTimeFormatter.ofPattern("d/MM/yyyy")));
    }

    public static void menuInterativo() {
        /* Menu interativo usando System.in */
        Scanner scan = new Scanner(System.in);

        MenuOperacoes comando = MenuOperacoes.CADASTRAR;
        while (comando != MenuOperacoes.SAIR) {
            for (MenuOperacoes op : MenuOperacoes.values()) {
                System.out.println("(" + op.operacao + ") " + op);
            }
            int entrada = Integer.parseInt(scan.nextLine());
            comando = MenuOperacoes.values()[entrada];
            switch (comando) {
                case CADASTRAR: {
                    System.out.println("(1) Cadastrar cliente PJ");
                    System.out.println("(2) Cadastrar cliente PF");
                    System.out.println("(3) Cadastrar veículo");
                    System.out.println("(4) Cadastrar seguradora");
                    System.out.println("(5) Voltar");
                    entrada = Integer.parseInt(scan.nextLine());
                    switch (entrada) {
                        case 1: {
                            String nome_seguradora;
                            System.out.println("Seguradora:");
                            nome_seguradora = scan.nextLine();
                            ClientePJ cliente = ler_cliente_pj(scan);
                            for (Seguradora seguradora : seguradoras) {
                                if (seguradora.getNome().equals(nome_seguradora)) {
                                    seguradora.cadastrarCliente(cliente);
                                    break;
                                }
                            }
                            break;
                        }
                        case 2: {
                            String nome_seguradora;
                            System.out.println("Seguradora:");
                            nome_seguradora = scan.nextLine();
                            ClientePF cliente = ler_cliente_pf(scan);
                            for (Seguradora seguradora : seguradoras) {
                                if (seguradora.getNome().equals(nome_seguradora)) {
                                    seguradora.cadastrarCliente(cliente);
                                    break;
                                }
                            }
                            break;
                        }
                        case 3: {
                            String nome_cliente, nome_seguradora;
                            System.out.println("Nome do cliente:");
                            nome_cliente = scan.nextLine();
                            System.out.println("Seguradora:");
                            nome_seguradora = scan.nextLine();
                            Veiculo veiculo = ler_veiculo(scan);
                            for (Seguradora seguradora : seguradoras) {
                                if (seguradora.getNome().equals(nome_seguradora)) {
                                    for (Cliente cliente : seguradora.getListaClientes()) {
                                        if (cliente.getNome().equals(nome_cliente)) {
                                            ArrayList<Veiculo> nova_lista = cliente.getListaVeiculos();
                                            nova_lista.add(veiculo);
                                            cliente.setListaVeiculos(nova_lista);
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                        case 4: {
                            seguradoras.add(ler_seguradora(scan));
                            break;
                        }
                    }
                    break;
                }
                case LISTAR: {
                    System.out.println("(1) Listar clientes PJ por seguradora");
                    System.out.println("(2) Listar clientes PF por seguradora");
                    System.out.println("(3) Listar sinistros por cliente");
                    System.out.println("(4) Listar veículos por cliente");
                    System.out.println("(5) Listar veículos por seguradora");
                    System.out.println("(6) Voltar");
                    entrada = Integer.parseInt(scan.nextLine());
                    switch (entrada) {
                        case 1: {
                            String nome_seguradora;
                            System.out.println("Seguradora:");
                            nome_seguradora = scan.nextLine();
                            encontrar_seguradora(nome_seguradora).listarClientes("PJ");
                            break;
                        }
                        case 2: {
                            String nome_seguradora;
                            System.out.println("Seguradora:");
                            nome_seguradora = scan.nextLine();
                            encontrar_seguradora(nome_seguradora).listarClientes("PF");
                            break;
                        }
                        case 3: {
                            String nome_cliente;
                            System.out.println("Nome do cliente:");
                            nome_cliente = scan.nextLine();
                            for (Seguradora seguradora : seguradoras) {
                                seguradora.visualizarSinistro(nome_cliente);
                            }
                            break;
                        }
                        case 4: {
                            String nome_cliente;
                            System.out.println("Nome do cliente:");
                            nome_cliente = scan.nextLine();
                            for (Seguradora seguradora : seguradoras) {
                                for (Cliente cliente : seguradora.getListaClientes()) {
                                    if (cliente.getNome().equals(nome_cliente)) {
                                        for (Veiculo veiculo : cliente.getListaVeiculos()) {
                                            System.out.println(veiculo);
                                        }
                                    }
                                }
                                break;
                            }
                            break;
                        }
                        case 5: {
                            String nome_seguradora;
                            System.out.println("Seguradora:");
                            nome_seguradora = scan.nextLine();
                            Seguradora seguradora = encontrar_seguradora(nome_seguradora);
                            for (Cliente cliente : seguradora.getListaClientes()) {
                                for (Veiculo veiculo : cliente.getListaVeiculos()) {
                                    System.out.println(veiculo);
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
                case EXCLUIR: {
                    System.out.println("(1) Excluir cliente");
                    System.out.println("(2) Excluir veículo");
                    System.out.println("(3) Excluir sinistro");
                    System.out.println("(4) Voltar");
                    entrada = Integer.parseInt(scan.nextLine());
                    switch (entrada) {
                        case 1: {
                            String nome_seguradora, nome_cliente;
                            System.out.println("Nome do cliente:");
                            nome_cliente = scan.nextLine();
                            System.out.println("Seguradora:");
                            nome_seguradora = scan.nextLine();
                            Seguradora seguradora = encontrar_seguradora(nome_seguradora);
                            seguradora.removerCliente(nome_cliente);
                        }
                        case 2: {
                            String placa;
                            System.out.println("Placa do veículo:");
                            placa = scan.nextLine();
                            for (Seguradora seguradora : seguradoras) {
                                ArrayList<Integer> indices_sinistro = new ArrayList<>();
                                for (int i = 0; i < seguradora.getListaSinistros().size(); ++i) {
                                    Sinistro sinistro = seguradora.getListaSinistros().get(i);
                                    if (sinistro.getVeiculo().getPlaca().equals(placa)) {
                                        indices_sinistro.add(i);
                                    }
                                }
                                for (int i : indices_sinistro)
                                    seguradora.removerSinistro(i);
                                for (int i = 0; i < seguradora.getListaClientes().size(); ++i) {
                                    boolean tem_o_veiculo = false;
                                    Cliente cliente = seguradora.getListaClientes().get(i);
                                    for (Veiculo veiculo : cliente.getListaVeiculos()) {
                                        if (veiculo.getPlaca().equals(placa)) {
                                            tem_o_veiculo = true;
                                            break;
                                        }
                                    }
                                    if (tem_o_veiculo)
                                        cliente.removerVeiculo(placa);
                                }
                            }
                        }
                        case 3: {
                            String nome_seguradora;
                            System.out.println("Seguradora:");
                            nome_seguradora = scan.nextLine();
                            Seguradora seguradora = encontrar_seguradora(nome_seguradora);
                            int id_sinistro, indice_sinistro = -1;
                            System.out.println("ID do sinistro:");
                            id_sinistro = Integer.parseInt(scan.nextLine());
                            for (int i = 0; i < seguradora.getListaSinistros().size(); ++i) {
                                if (seguradora.getListaSinistros().get(i).getId() == id_sinistro) {
                                    indice_sinistro = i;
                                    break;
                                }
                            }
                            seguradora.removerSinistro(indice_sinistro);
                        }
                    }
                    break;
                }
                case GERAR_SINISTRO:
                    break;
                case TRANSFERIR_SEGURO:
                    break;
                case CALCULAR_RECEITA: {

                    break;
                }
            }
        }
        scan.close();
    }

    public static void main(String[] args) {
        seguradoras = new ArrayList<>();
        Seguradora seguradora = new Seguradora(
                "SeguradoraSegura",
                "(71) 99999-9999",
                "seguradora@segura.com",
                "Avenida Segura 221");
        seguradoras.add(seguradora);

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

        ArrayList<Veiculo> veiculos_unicamp = new ArrayList<>();
        veiculos_unicamp.add(new Veiculo("123", "BMW", "Gol", 1999));
        veiculos_unicamp.add(new Veiculo("321XXX", "Fiat", "Siena", 2007));
        ClientePJ unicamp = new ClientePJ(
                "Unicamp",
                "Avenida Albert Einstein 314",
                LocalDate.of(1966, Month.OCTOBER, 5),
                veiculos_unicamp,
                "46.068.425/0001-33", 25);
        seguradora.cadastrarCliente(unicamp);

        seguradora.gerarSinistro(
                lucca.getListaVeiculos().get(0),
                lucca,
                "Rua Saturnino de Brito");
        seguradora.gerarSinistro(
                lucca.getListaVeiculos().get(1),
                lucca,
                "Rua Chico Buarque de Holanda");
        seguradora.gerarSinistro(
                unicamp.getListaVeiculos().get(0),
                unicamp,
                "Rua Roxo Moreira");

        menuInterativo();
    }
}