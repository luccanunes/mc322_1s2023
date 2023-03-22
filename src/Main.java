public class Main {
    public static void testaCliente(Cliente cliente) {
        System.out.println(cliente); // toString
        System.out.println(cliente.getCpf());
        System.out.println(cliente.validarCPF());
        cliente.setCpf("carlinhos brow");
        System.out.println(cliente.validarCPF());
        System.out.println(cliente.getCpf());
    }

    public static void testaSeguradora(Seguradora seguradora) {
        System.out.println(seguradora.getNome());
        seguradora.setNome("seguradora não segura");
        System.out.println(seguradora.getNome());
    }

    public static void testaSinistro(Sinistro sinistro) {
        System.out.println(sinistro.getData());
        sinistro.setData("07/07/2023");
        System.out.println(sinistro.getData());
    }

    public static void testaVeiculo(Veiculo veiculo) {
        System.out.println(veiculo.getMarca());
        veiculo.setMarca("Fiat");
        System.out.println(veiculo.getMarca());
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente("lula", "070.680.938-68", "06/10/1945", "brasilia", 25);
        testaCliente(cliente);

        Seguradora seguradora = new Seguradora("Seguradora segura", "(71) 99999-9999", "seguradora@segura.com",
                "avenida 3");
        testaSeguradora(seguradora);

        Sinistro sinistro = new Sinistro("14/03/2023", "avenida 7");
        testaSinistro(sinistro);

        Veiculo veiculo = new Veiculo("KING-23", "Ferrari", "Vermelho");
        testaVeiculo(veiculo);
    }
}
