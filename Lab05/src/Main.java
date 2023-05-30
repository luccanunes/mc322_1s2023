import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static ArrayList<Seguradora> seguradoras;

    public static Seguradora encontrar_seguradora(String nome) {
        /*
         * Encontra e retorna uma seguradora na lista
         * de acordo com o nome
         */
        for (Seguradora seguradora : seguradoras) {
            if (seguradora.getNome().equals(nome)) {
                return seguradora;
            }
        }
        return null;
    }

    public static Seguradora perguntar_seguradora(Scanner scan) {
        /*
         * Lê o nome de uma seguradora e retorna a seguradora
         * correspondente
         */
        String nome_seguradora;
        System.out.println("Nome da seguradora:");
        nome_seguradora = scan.nextLine();
        return encontrar_seguradora(nome_seguradora);
    }

    public static Veiculo ler_veiculo(Scanner scan) {
        /*
         * Lê os dados de um veículo e retorna
         * um objeto Veiculo correspondente
         */
        String placa, marca, modelo;
        int anoFabricacao;
        System.out.println("Placa do veículo:");
        placa = scan.nextLine();
        System.out.println("Marca do veículo:");
        marca = scan.nextLine();
        System.out.println("Modelo do veículo:");
        modelo = scan.nextLine();
        System.out.println("Ano fabricação do veículo:");
        anoFabricacao = Integer.parseInt(scan.nextLine());
        return new Veiculo(placa, marca, modelo, anoFabricacao);
    }

    public static Seguradora ler_seguradora(Scanner scan) {
        /*
         * Lê os dados de uma seguradora e retorna
         * um objeto Seguradora correspondente
         */
        String nome, telefone, email, endereco, cnpj;
        System.out.println("CNPJ da seguradora:");
        cnpj = scan.nextLine();
        System.out.println("Nome da seguradora:");
        nome = scan.nextLine();
        System.out.println("Telefone da seguradora:");
        telefone = scan.nextLine();
        System.out.println("Email da seguradora:");
        email = scan.nextLine();
        System.out.println("Endereço da seguradora:");
        endereco = scan.nextLine();
        return new Seguradora(cnpj, nome, telefone, email, endereco);
    }

    public static ClientePJ ler_cliente_pj(Scanner scan) {
        /*
         * Lê os dados de um cliente PJ e retorna
         * um objeto ClientePJ correspondente
         */
        String nome, endereco, data, cnpj;
        int qtd_funcionarios;
        System.out.println("Nome do cliente:");
        nome = scan.nextLine();
        if (!Validacao.validaNome(nome)) {
            System.out.println("Nome inválido!");
            System.exit(0);
        }
        System.out.println("Endereço do cliente:");
        endereco = scan.nextLine();
        System.out.println("Data de fundação do cliente:");
        data = scan.nextLine();
        System.out.println("CNPJ: ");
        cnpj = scan.nextLine();
        if (!Validacao.validarCNPJ(cnpj)) {
            System.out.println("CNPJ inválido!");
            System.exit(0);
        }
        System.out.println("Quantidade de funcionários do cliente:");
        qtd_funcionarios = Integer.parseInt(scan.nextLine());
        return new ClientePJ(nome, endereco,
                LocalDate.parse(data, DateTimeFormatter.ofPattern("d/MM/yyyy")), null,
                cnpj, qtd_funcionarios);
    }

    public static ClientePF ler_cliente_pf(Scanner scan) {
        /*
         * Lê os dados de um cliente PF e retorna
         * um objeto ClientePF correspondente
         */
        String nome, endereco, data_licenca, data_nascimento, educacao, genero, telefone, cpf;
        System.out.println("Nome do cliente:");
        nome = scan.nextLine();
        if (!Validacao.validaNome(nome)) {
            System.out.println("Nome inválido!");
            System.exit(0);
        }
        System.out.println("Endereço do cliente:");
        endereco = scan.nextLine();
        System.out.println("Data da licença do cliente:");
        data_licenca = scan.nextLine();
        System.out.println("Data de nascimento do cliente:");
        data_nascimento = scan.nextLine();
        System.out.println("Educação: ");
        educacao = scan.nextLine();
        System.out.println("Telefone:");
        telefone = scan.nextLine();
        System.out.println("Gênero do cliente:");
        genero = scan.nextLine();
        System.out.println("CPF do cliente:");
        cpf = scan.nextLine();
        if (!Validacao.validarCPF(cpf)) {
            System.out.println("CPF inválido!");
            System.exit(0);
        }
        return new ClientePF(nome, endereco,
                LocalDate.parse(data_licenca, DateTimeFormatter.ofPattern("d/MM/yyyy")),
                educacao,
                genero,
                telefone,
                cpf,
                LocalDate.parse(data_nascimento, DateTimeFormatter.ofPattern("d/MM/yyyy")));
    }

    public static void menuInterativo() {
        /* Menu interativo usando System.in */
        Scanner scan = new Scanner(System.in);

        MenuOperacoes comando = MenuOperacoes.CADASTRAR;
        while (comando != MenuOperacoes.SAIR) {
            System.out.println("(1) Cadastrar");
            System.out.println("(2) Listar");
            System.out.println("(3) Excluir");
            System.out.println("(4) Gerar sinistros");
            System.out.println("(5) Transferir seguro");
            System.out.println("(6) Calcular receita");
            System.out.println("(0) Sair");
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
                        case 1: { // Cadastrar cliente PJ
                            Seguradora seguradora = perguntar_seguradora(scan);
                            ClientePJ cliente = ler_cliente_pj(scan);
                            seguradora.cadastrarCliente(cliente);
                            break;
                        }
                        case 2: { // Cadastrar cliente PF
                            Seguradora seguradora = perguntar_seguradora(scan);
                            ClientePF cliente = ler_cliente_pf(scan);
                            seguradora.cadastrarCliente(cliente);
                            break;
                        }
                        case 3: { // Cadastrar veículo
                            String nome_cliente;
                            System.out.println("Nome do cliente:");
                            nome_cliente = scan.nextLine();
                            Seguradora seguradora = perguntar_seguradora(scan);
                            Veiculo veiculo = ler_veiculo(scan);
                            seguradora.encontrarCliente(nome_cliente).adicionarVeiculo(veiculo);
                            break;
                        }
                        case 4: { // Cadastrar seguradora
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
                        case 1: { // Listar clientes PJ
                            perguntar_seguradora(scan).listarClientes("PJ");
                            break;
                        }
                        case 2: { // Listar clientes PJ
                            perguntar_seguradora(scan).listarClientes("PF");
                            break;
                        }
                        case 3: { // Listar sinistros por cliente
                            String nome_cliente;
                            System.out.println("Nome do cliente:");
                            nome_cliente = scan.nextLine();
                            for (Seguradora seguradora : seguradoras) {
                                seguradora.visualizarSinistro(nome_cliente);
                            }
                            break;
                        }
                        case 4: { // Listar veículos por cliente
                            String nome_cliente;
                            System.out.println("Nome do cliente:");
                            nome_cliente = scan.nextLine();
                            for (Seguradora seguradora : seguradoras) {
                                Cliente cliente = seguradora.encontrarCliente(nome_cliente);
                                if (Objects.nonNull(cliente)) {
                                    for (Veiculo veiculo : cliente.getListaVeiculos()) {
                                        System.out.println(veiculo);
                                    }
                                }
                                break;
                            }
                            break;
                        }
                        case 5: { // Listar veículos por seguradora
                            Seguradora seguradora = perguntar_seguradora(scan);
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
                        case 1: { // Excluir cliente
                            String nome_cliente;
                            System.out.println("Nome do cliente:");
                            nome_cliente = scan.nextLine();
                            perguntar_seguradora(scan).removerCliente(nome_cliente);
                        }
                        case 2: { // Excluir veículo
                            String placa;
                            System.out.println("Placa do veículo:");
                            placa = scan.nextLine();
                            for (Seguradora seguradora : seguradoras) { // Para toda seguradora
                                ArrayList<Integer> indices_sinistro = new ArrayList<>();
                                // Procura os sinistros associados ao veículo
                                for (int i = 0; i < seguradora.getListaSinistros().size(); ++i) {
                                    Sinistro sinistro = seguradora.getListaSinistros().get(i);
                                    if (sinistro.getVeiculo().getPlaca().equals(placa)) {
                                        indices_sinistro.add(i);
                                    }
                                }
                                // Remove os sinistros
                                for (int i : indices_sinistro)
                                    seguradora.removerSinistro(i);
                                // Procura os clientes que têm esse veículo
                                for (int i = 0; i < seguradora.getListaClientes().size(); ++i) {
                                    boolean tem_o_veiculo = false;
                                    Cliente cliente = seguradora.getListaClientes().get(i);
                                    for (Veiculo veiculo : cliente.getListaVeiculos()) {
                                        if (veiculo.getPlaca().equals(placa)) {
                                            tem_o_veiculo = true;
                                            break;
                                        }
                                    }
                                    // Remove o veículo da lista
                                    if (tem_o_veiculo)
                                        cliente.removerVeiculo(placa);
                                }
                            }
                        }
                        case 3: { // Excluir sinistro
                            Seguradora seguradora = perguntar_seguradora(scan);
                            int id_sinistro, indice_sinistro = -1;
                            System.out.println("ID do sinistro:");
                            id_sinistro = Integer.parseInt(scan.nextLine());
                            for (int i = 0; i < seguradora.getListaSinistros().size(); ++i) {
                                // Procura o sinistro nas seguradoras
                                if (seguradora.getListaSinistros().get(i).getId() == id_sinistro) {
                                    indice_sinistro = i; // Guarda o índice para apagar
                                    break;
                                }
                            }
                            seguradora.removerSinistro(indice_sinistro); // E apaga
                        }
                    }
                    break;
                }
                case GERAR_SINISTRO: {
                    Seguradora seguradora = perguntar_seguradora(scan);
                    Veiculo veiculo = ler_veiculo(scan);
                    String endereco, nome_cliente;
                    System.out.println("Endereço do sinistro:");
                    endereco = scan.nextLine();
                    System.out.println("Nome do cliente:");
                    nome_cliente = scan.nextLine();
                    Cliente cliente = seguradora.encontrarCliente(nome_cliente);
                    if (!Objects.nonNull(cliente)) {
                        System.out.println("Cliente não cadastrado!");
                    } else {
                        seguradora.gerarSinistro(veiculo, cliente, endereco);
                        seguradora.calcularPrecoSeguroCliente(cliente);
                    }
                    break;
                }
                case TRANSFERIR_SEGURO: {
                    Seguradora seguradora = perguntar_seguradora(scan);
                    String nome_doador, nome_receptor;
                    System.out.println("Nome do cliente que fará a transferência:");
                    nome_doador = scan.nextLine();
                    System.out.println("Nome do cliente que receberá a transferência:");
                    nome_receptor = scan.nextLine();
                    Cliente doador = seguradora.encontrarCliente(nome_doador);
                    Cliente receptor = seguradora.encontrarCliente(nome_receptor);
                    for (Veiculo veiculo : doador.getListaVeiculos()) {
                        receptor.adicionarVeiculo(veiculo);
                    }
                    doador.setListaVeiculos(new ArrayList<>());
                    seguradora.calcularPrecoSeguroCliente(doador);
                    seguradora.calcularPrecoSeguroCliente(receptor);
                    break;
                }
                case CALCULAR_RECEITA: {
                    Seguradora seguradora = perguntar_seguradora(scan);
                    System.out.println("Receita seguradora: " + seguradora.calcularReceita());
                    break;
                }
            }
        }
        scan.close();
    }

    public static void main(String[] args) {
        seguradoras = new ArrayList<>();
        // Instancia seguradora
        Seguradora seguradora = new Seguradora(
                "46.068.425/0001-33",
                "SeguradoraSegura",
                "(71) 99999-9999",
                "seguradora@segura.com",
                "Avenida Segura 221");
        seguradoras.add(seguradora);

        // Instancia clientes teste
        ArrayList<Veiculo> veiculos_lucca = new ArrayList<>();
        veiculos_lucca.add(new Veiculo("666", "Ferrari", "Corsa", 2018));
        veiculos_lucca.add(new Veiculo("777", "Fiat", "Palio", 2002));
        ClientePF lucca = new ClientePF(
                "Lucca",
                "Rua Lindo 221B",
                LocalDate.of(2022, Month.JULY, 8),
                "Muito educado",
                "Suspense",
                "(71) 9 9999-999",
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
                "(19) 9 8888-8888",
                "46.068.425/0001-33", 25);
        seguradora.cadastrarCliente(unicamp);

        // Gera sinistros
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

        // Chamar os métodos

        seguradora.listarClientes("PF");
        seguradora.listarClientes("PJ");
        seguradora.visualizarSinistro("Lucca");
        seguradora.listarSinistros();
        for (Cliente cliente : seguradora.getListaClientes()) {
            seguradora.calcularPrecoSeguroCliente(cliente);
        }
        System.out.println("Receita: " + seguradora.calcularReceita());

        menuInterativo();
    }
}