import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Seguradora> seguradoras;

    public static Seguro encontrar_seguro(int id) {
        /*
         * Encontra e retorna um seguro
         * de acordo com o id
         */
        for (Seguradora seguradora : seguradoras) {
            for (Seguro seguro : seguradora.getListaSeguros()) {
                if (seguro.getId() == id) {
                    return seguro;
                }
            }
        }
        return null;
    }

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

    public static Frota encontrar_frota(String codigo) {
        /*
         * Encontra e retorna uma frota
         * de acordo com o codigo
         */
        for (Seguradora seguradora : seguradoras)
            for (Cliente cliente : seguradora.getListaClientes())
                if (cliente instanceof ClientePJ)
                    for (Frota frota : ((ClientePJ) cliente).getListaFrotas())
                        if (frota.getCodigo().equals(codigo))
                            return frota;
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

    public static Cliente perguntar_cliente(Scanner scan) {
        /*
         * Lê o nome de um cliente e sua seguradora e
         * retorna o cliente correspondente
         */
        String nome_cliente;
        System.out.println("Nome do cliente:");
        nome_cliente = scan.nextLine();
        return perguntar_seguradora(scan).encontrarCliente(nome_cliente);
    }

    public static Frota perguntar_frota(Scanner scan) {
        /*
         * Lê o codigo de uma frota e retorna a Frota
         * correspondente
         */
        String codigo;
        System.out.println("Codigo da frota:");
        codigo = scan.nextLine();
        return encontrar_frota(codigo);
    }

    public static Seguro perguntar_seguro(Scanner scan) {
        /*
         * Lê o id de um seguro e retorna o seguro
         * correspondente
         */
        int id;
        System.out.println("ID do seguro:");
        id = Integer.parseInt(scan.nextLine());
        return encontrar_seguro(id);
    }

    public static Condutor perguntar_condutor(Scanner scan) {
        /*
         * Lê o nome de um condutor e retorna o
         * Condutor correspondente
         */
        String nome;
        System.out.println("Nome do condutor:");
        nome = scan.nextLine();
        if (!Validacao.validaNome(nome)) {
            System.out.println("Nome inválido!");
            System.exit(0);
        }
        Seguro seguro = perguntar_seguro(scan);
        for (Condutor condutor : seguro.getListaCondutores())
            if (condutor.getNome().equals(nome))
                return condutor;
        return null;
    }

    public static Condutor ler_condutor(Scanner scan) {
        /*
         * Lê os dados de um condutor e retorna
         * um objeto Condutor correspondente
         */
        String nome, endereco, telefone, cpf, email, data_nascimento;
        System.out.println("Nome do condutor:");
        nome = scan.nextLine();
        if (!Validacao.validaNome(nome)) {
            System.out.println("Nome inválido!");
            System.exit(0);
        }
        System.out.println("Endereço do condutor:");
        endereco = scan.nextLine();
        System.out.println("Data de nascimento do condutor:");
        data_nascimento = scan.nextLine();
        System.out.println("Telefone:");
        telefone = scan.nextLine();
        System.out.println("Email do condutor:");
        email = scan.nextLine();
        System.out.println("CPF do condutor:");
        cpf = scan.nextLine();
        if (!Validacao.validarCPF(cpf)) {
            System.out.println("CPF inválido!");
            System.exit(0);
        }
        return new Condutor(
                nome, endereco, email,
                telefone, cpf,
                LocalDate.parse(data_nascimento, DateTimeFormatter.ofPattern("d/MM/yyyy")));
    }

    public static Frota ler_frota(Scanner scan) {
        /*
         * Lê os dados de uma frota e retorna
         * um objeto Frota correspondente
         */
        String codigo;
        System.out.println("Código da frota:");
        codigo = scan.nextLine();
        return new Frota(codigo);
    }

    public static SeguroPF ler_seguro_pf(Scanner scan, Seguradora seguradora) {
        /*
         * Lê os dados de um seguro PF e retorna
         * um objeto Seguro correspondente
         */
        String data_inicio, data_fim, nome_cliente;
        System.out.println("Data de início do seguro:");
        data_inicio = scan.nextLine();
        System.out.println("Data do fim do seguro:");
        data_fim = scan.nextLine();
        System.out.println("Nome do cliente:");
        nome_cliente = scan.nextLine();
        Veiculo veiculo = ler_veiculo(scan);
        ClientePF cliente = (ClientePF) seguradora.encontrarCliente(nome_cliente);
        return new SeguroPF(
                LocalDate.parse(data_inicio, DateTimeFormatter.ofPattern("d/MM/yyyy")),
                LocalDate.parse(data_fim, DateTimeFormatter.ofPattern("d/MM/yyyy")),
                seguradora, veiculo, cliente);
    }

    public static SeguroPJ ler_seguro_pj(Scanner scan, Seguradora seguradora) {
        /*
         * Lê os dados de um seguro PJ e retorna
         * um objeto Seguro correspondente
         */
        String data_inicio, data_fim, nome_cliente;
        System.out.println("Data de início do seguro:");
        data_inicio = scan.nextLine();
        System.out.println("Data do fim do seguro:");
        data_fim = scan.nextLine();
        System.out.println("Nome do cliente:");
        nome_cliente = scan.nextLine();
        Frota frota = perguntar_frota(scan);
        ClientePJ cliente = (ClientePJ) seguradora.encontrarCliente(nome_cliente);
        return new SeguroPJ(
                LocalDate.parse(data_inicio, DateTimeFormatter.ofPattern("d/MM/yyyy")),
                LocalDate.parse(data_fim, DateTimeFormatter.ofPattern("d/MM/yyyy")),
                seguradora, frota, cliente);
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
        String nome, endereco, data, cnpj, email;
        int qtd_funcionarios;
        System.out.println("Nome do cliente:");
        nome = scan.nextLine();
        if (!Validacao.validaNome(nome)) {
            System.out.println("Nome inválido!");
            System.exit(0);
        }
        System.out.println("Endereço do cliente:");
        endereco = scan.nextLine();
        System.out.println("Email do cliente:");
        email = scan.nextLine();
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
                cnpj, qtd_funcionarios, email);
    }

    public static ClientePF ler_cliente_pf(Scanner scan) {
        /*
         * Lê os dados de um cliente PF e retorna
         * um objeto ClientePF correspondente
         */
        String nome, endereco, data_licenca, data_nascimento, educacao, genero, telefone, cpf, email;
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
        System.out.println("Email do cliente:");
        email = scan.nextLine();
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
                LocalDate.parse(data_nascimento, DateTimeFormatter.ofPattern("d/MM/yyyy")),
                email);
    }

    public static void menuInterativo() {
        /* Menu interativo usando System.in */
        Scanner scan = new Scanner(System.in);

        MenuOperacoes comando = MenuOperacoes.CADASTRAR;
        while (comando != MenuOperacoes.SAIR) {
            System.out.println("(1) Cadastrar");
            System.out.println("(2) Listar");
            System.out.println("(3) Excluir");
            System.out.println("(4) Atualizar frota");
            System.out.println("(5) Calcular receita");
            System.out.println("(0) Sair");
            int entrada = Integer.parseInt(scan.nextLine());
            comando = MenuOperacoes.values()[entrada];
            switch (comando) {
                case CADASTRAR: {
                    System.out.println("(1) Cadastrar seguradora");
                    System.out.println("(2) Cadastrar cliente PJ");
                    System.out.println("(3) Cadastrar cliente PF");
                    System.out.println("(4) Cadastrar condutor");
                    System.out.println("(5) Cadastrar sinistro");
                    System.out.println("(6) Cadastrar seguro PJ");
                    System.out.println("(7) Cadastrar seguro PF");
                    System.out.println("(8) Cadastrar frota");
                    System.out.println("(9) Cadastrar veículo");
                    System.out.println("(10) Voltar");
                    entrada = Integer.parseInt(scan.nextLine());
                    switch (entrada) {
                        case 1: { // Cadastrar seguradora
                            seguradoras.add(ler_seguradora(scan));
                            break;
                        }
                        case 3: { // Cadastrar cliente PJ
                            Seguradora seguradora = perguntar_seguradora(scan);
                            ClientePJ cliente = ler_cliente_pj(scan);
                            seguradora.cadastrarCliente(cliente);
                            break;
                        }
                        case 4: { // Cadastrar cliente PF
                            Seguradora seguradora = perguntar_seguradora(scan);
                            ClientePF cliente = ler_cliente_pf(scan);
                            seguradora.cadastrarCliente(cliente);
                            break;
                        }
                        case 5: { // Cadastrar condutor
                            Seguro seguro = perguntar_seguro(scan);
                            Condutor condutor = ler_condutor(scan);
                            seguro.autorizarCondutor(condutor);
                            break;
                        }
                        case 6: { // Cadastrar seguro PJ
                            Seguradora seguradora = perguntar_seguradora(scan);
                            Seguro seguro = ler_seguro_pj(scan, seguradora);
                            seguradora.gerarSeguro(seguro);
                            break;
                        }
                        case 7: { // Cadastrar seguro PF
                            Seguradora seguradora = perguntar_seguradora(scan);
                            Seguro seguro = ler_seguro_pf(scan, seguradora);
                            seguradora.gerarSeguro(seguro);
                            break;
                        }
                        case 8: { // Cadastrar frota
                            ClientePJ cliente = ler_cliente_pj(scan);
                            Frota frota = ler_frota(scan);
                            cliente.cadastrarFrota(frota);
                            break;
                        }
                        case 9: { // Cadastrar veículo
                            Frota frota = perguntar_frota(scan);
                            Veiculo veiculo = ler_veiculo(scan);
                            frota.cadastrarVeiculo(veiculo);
                            break;
                        }
                    }
                    break;
                }
                case LISTAR: {
                    System.out.println("(1) Listar clientes por seguradora");
                    System.out.println("(2) Listar veículos por cliente PF");
                    System.out.println("(3) Listar frotas por cliente PJ");
                    System.out.println("(4) Listar sinistros por condutor");
                    System.out.println("(5) Listar sinistros por seguro");
                    System.out.println("(6) Listar condutores por seguro");
                    System.out.println("(7) Listar veículos por frota");
                    System.out.println("(8) Voltar");
                    entrada = Integer.parseInt(scan.nextLine());
                    switch (entrada) {
                        case 1: { // Listar clientes por seguradora
                            Seguradora seguradora = perguntar_seguradora(scan);
                            System.out.println("CLIENTES PJ:");
                            seguradora.listarClientes("PJ");
                            System.out.println("CLIENTES PF:");
                            seguradora.listarClientes("PF");
                            break;
                        }
                        case 2: { // Listar veículos por cliente PF
                            ClientePF cliente = (ClientePF) perguntar_cliente(scan);
                            System.out.println("Veículos de " + cliente.getNome() + ":");
                            System.out.println(cliente.getListaVeiculos());
                            break;
                        }
                        case 3: { // Listar frotas por cliente PJ
                            ClientePJ cliente = (ClientePJ) perguntar_cliente(scan);
                            System.out.println("Frotas de " + cliente.getNome() + ":");
                            System.out.println(cliente.getListaFrotas());
                            break;
                        }
                        case 4: { // Listar sinistros por condutor
                            Seguro seguro = perguntar_seguro(scan);
                            for (Condutor condutor : seguro.getListaCondutores()) {
                                System.out.println("Sinistros de " + condutor.getNome() + ":");
                                System.out.println(condutor.getListaSinistros());
                            }
                            break;
                        }
                        case 5: { // Listar sinistros por seguro
                            System.out.println(perguntar_seguro(scan).getListaSinistros());
                        }
                        case 6: { // Listar condutores por seguro
                            System.out.println(perguntar_seguro(scan).getListaCondutores());
                        }
                        case 7: { // Listar veículos por frota
                            System.out.println(perguntar_frota(scan).getListaVeiculos());
                        }
                    }
                    break;
                }
                case EXCLUIR: {
                    System.out.println("(1) Excluir seguro");
                    System.out.println("(2) Excluir cliente");
                    System.out.println("(3) Excluir veiculo");
                    System.out.println("(4) Excluir condutor de um seguro");
                    System.out.println("(5) Voltar");
                    entrada = Integer.parseInt(scan.nextLine());
                    switch (entrada) {
                        case 1: { // Excluir seguro
                            perguntar_seguradora(scan).cancelarSeguro(perguntar_seguro(scan).getId());
                            break;
                        }
                        case 2: { // Excluir cliente
                            String nome_cliente;
                            System.out.println("Nome do cliente:");
                            nome_cliente = scan.nextLine();
                            perguntar_seguradora(scan).removerCliente(nome_cliente);
                            break;
                        }
                        case 3: { // Excluir veículo
                            String placa;
                            System.out.println("Placa do veículo:");
                            placa = scan.nextLine();
                            for (Seguradora seguradora : seguradoras) { // Para toda seguradora
                                for (Seguro seguro : seguradora.getListaSeguros())
                                    if (seguro instanceof SeguroPJ)
                                        ((SeguroPJ) seguro).getFrota().removerVeiculo(placa);
                                for (Cliente cliente : seguradora.getListaClientes())
                                    if (cliente instanceof ClientePF)
                                        ((ClientePF) cliente).removerVeiculo(placa);
                            }
                            break;
                        }
                        case 4: { // Excluir condutor de um seguro
                            perguntar_seguro(scan).desautorizarCondutor(perguntar_condutor(scan));
                            break;
                        }
                    }
                    break;
                }
                case ATUALIZAR_FROTA: {
                    ClientePJ cliente = (ClientePJ) perguntar_cliente(scan);
                    String codigo;
                    int qtd_veiculos;
                    ArrayList<Veiculo> nova_lista_veiculos = new ArrayList<>();
                    System.out.println("Código da frota:");
                    codigo = scan.nextLine();
                    System.out.println("Nova quantidade de veículos na frota:");
                    qtd_veiculos = Integer.parseInt(scan.nextLine());
                    for (int i = 0; i < qtd_veiculos; ++i)
                        nova_lista_veiculos.add(ler_veiculo(scan));
                    cliente.atualizarFrota(codigo, nova_lista_veiculos);
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
                LocalDate.of(2004, Month.JULY, 7),
                "lucca@lucca.lucca");
        seguradora.cadastrarCliente(lucca);
        for (Veiculo veiculo : veiculos_lucca)
            lucca.cadastrarVeiculo(veiculo);
        seguradora.gerarSeguro(new SeguroPF(
                LocalDate.of(1997, Month.OCTOBER, 10),
                LocalDate.of(2010, Month.NOVEMBER, 21),
                seguradora,
                lucca.getListaVeiculos().get(0),
                lucca));

        ArrayList<Veiculo> veiculos_unicamp = new ArrayList<>();
        veiculos_unicamp.add(new Veiculo("123", "BMW", "Gol", 1999));
        veiculos_unicamp.add(new Veiculo("321XXX", "Fiat", "Siena", 2007));
        ClientePJ unicamp = new ClientePJ(
                "Unicamp",
                "Avenida Albert Einstein 314",
                LocalDate.of(1966, Month.OCTOBER, 5),
                "(19) 9 8888-8888",
                "46.068.425/0001-33", 25, "unicamp@unicamp.unicamp");
        seguradora.cadastrarCliente(unicamp);
        unicamp.cadastrarFrota(new Frota("A"));
        unicamp.atualizarFrota("A", veiculos_unicamp);
        seguradora.gerarSeguro(new SeguroPJ(
                LocalDate.of(2004, Month.FEBRUARY, 2),
                LocalDate.of(2010, Month.DECEMBER, 12),
                seguradora, unicamp.getListaFrotas().get(0),
                unicamp));

        Condutor lucca_condutor = new Condutor(
                "lucca", "salvador",
                "lucca@lucca", "71 99999",
                "070.680.938-68", LocalDate.of(2004, Month.JULY, 7));
        Condutor vini_condutor = new Condutor(
                "vinícius damasceno", "fortaleza",
                "vini@vini", "85 99999",
                "070.680.938-68", LocalDate.of(2003, Month.JANUARY, 31));

        // Gera sinistros

        seguradora.getSegurosPorCliente("Lucca").get(0).gerarSinistro(
                new Sinistro(LocalDate.of(2023, Month.JUNE, 6), "Rua Saturnino de Brito 573",
                        seguradora, lucca.getListaVeiculos().get(0), lucca_condutor));
        seguradora.getSegurosPorCliente("Lucca").get(0).gerarSinistro(
                new Sinistro(LocalDate.of(2023, Month.JUNE, 4), "Rua Roxo Moreira 666",
                        seguradora, lucca.getListaVeiculos().get(1), lucca_condutor));
        seguradora.getSegurosPorCliente("Unicamp").get(0).gerarSinistro(
                new Sinistro(LocalDate.of(2023, Month.JUNE, 3), "Rua Chico Buarque de Holanda",
                        seguradora, unicamp.getListaFrotas().get(0).getListaVeiculos().get(0),
                        vini_condutor));

        // Chamar os métodos

        seguradora.listarClientes("PF");
        seguradora.listarClientes("PJ");
        seguradora.cancelarSeguro(7);
        System.out.println(seguradora.getSegurosPorCliente("lucca"));
        System.out.println(seguradora.getSinistrosPorCliente("lucca"));
        for (Seguro seguro : seguradora.getListaSeguros()) {
            seguro.calcularValor();
        }
        System.out.println("Receita: " + seguradora.calcularReceita());
        System.out.println(lucca);
        System.out.println(lucca.getListaVeiculos().get(0));
        System.out.println(unicamp.getListaFrotas().get(0));
        System.out.println(seguradora.getListaSeguros().get(0));

        System.out.println("-------FIM DA MAIN-------");
        menuInterativo();
    }
}