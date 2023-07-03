import java.time.LocalDate;
import java.util.ArrayList;
import java.util.SplittableRandom;

public class Seguradora {
    private final String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Seguro> listaSeguros;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Condutor> listaCondutores;
    private ArquivoClientePF arquivoClientePF;
	private ArquivoClientePJ arquivoClientePJ;
	private ArquivoCondutor arquivoCondutor;
	private ArquivoFrota arquivoFrota;
	private ArquivoVeiculo arquivoVeiculo;
	private ArquivoSeguro arquivoSeguro;
	private ArquivoSinistro arquivoSinistro;


    public Seguradora(String cnpj, String nome, String telefone, String email, String endereco) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaClientes = new ArrayList<>();
        this.listaSeguros = new ArrayList<>();
        this.listaCondutores = new ArrayList<>();
        this.arquivoClientePF = new ArquivoClientePF();
		this.arquivoClientePJ = new ArquivoClientePJ();
		this.arquivoCondutor = new ArquivoCondutor();
		this.arquivoFrota = new ArquivoFrota();
		this.arquivoVeiculo = new ArquivoVeiculo();
		this.arquivoSeguro = new ArquivoSeguro();
		this.arquivoSinistro = new ArquivoSinistro();
    }

    public String toString() {
        return "\n-----Seguradora-----\nNome: " + this.nome
                + "\nEndereço: " + this.endereco
                + "\nCNPJ: " + this.cnpj
                + "\nEmail: " + this.email
                + "\nTelefone: " + this.telefone
                + "\n---------------";
    }

    public void gerarSeguro(Seguro seguro) {
        // Adiciona o seguro na lista de seguros
        listaSeguros.add(seguro);
    }

    public boolean cancelarSeguro(int id) {
        // Remove o seguro da lista de seguros
        // Retorna true se o seguro estava na lista
        // e false do contrario
        for (int i = 0; i < listaSeguros.size(); ++i) {
            Seguro seguro = listaSeguros.get(i);
            if (seguro.getId() == id) {
                listaSeguros.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Seguro> getSegurosPorCliente(String nome_cliente) {
        // Retorna uma lista com todos os seguros associados
        // ao cliente em questão
        ArrayList<Seguro> lista = new ArrayList<>();
        for (Seguro seguro : this.getListaSeguros())
            if (seguro.getCliente().getNome().equals(nome_cliente))
                lista.add(seguro);
        return lista;
    }

    public ArrayList<Sinistro> getSinistrosPorCliente(String nome_cliente) {
        // Retorna uma lista com todos os sinistros associados
        // ao cliente em questão
        ArrayList<Sinistro> lista = new ArrayList<>();
        for (Seguro seguro : this.getListaSeguros())
            if (seguro.getCliente().getNome().equals(nome_cliente))
                for (Sinistro sinistro : seguro.getListaSinistros())
                    lista.add(sinistro);
        return lista;
    }

    public boolean cadastrarCliente(Cliente cliente) {
        // Retorna false se o cliente já está registrado
        // Do contrário, retorna true
        for (int i = 0; i < listaClientes.size(); ++i) {
            if (listaClientes.get(i) == cliente) {
                return true;
            }
        }
        listaClientes.add(cliente);
        // this.calcularPrecoSeguroCliente(cliente);
        return false;
    }

    public Cliente encontrarCliente(String nome_cliente) {
        /*
         * Encontra e retorna o cliente de acordo com o nome
         * Se o cliente não estiver cadastrado, retorna null
         */
        for (Cliente cliente : listaClientes) {
            if (cliente.getNome().equals(nome_cliente)) {
                return cliente;
            }
        }
        return null;
    }

    public boolean removerCliente(String cliente) {
        // Retorna false se o cliente não está registrado
        // Do contrário, retorna true
        for (int i = 0; i < listaClientes.size(); ++i) {
            if (listaClientes.get(i).getNome().equals(cliente)) {
                listaClientes.remove(i);
                return true;
            }
        }
        return false;
    }

    public void listarClientes(String tipoCliente) {
        // tipoCliente: "PJ" ou "PF"
        for (int i = 0; i < listaClientes.size(); ++i) {
            Cliente cliente = listaClientes.get(i);
            if ((tipoCliente == "PJ" && cliente instanceof ClientePJ)
                    ||
                    (tipoCliente == "PF" && cliente instanceof ClientePF)) {
                System.out.println(cliente);
            }
        }
    }

    public double calcularReceita() {
        // Retorna a soma dos valores do valor mensal de cada seguro
        double receita = 0;
        for (Seguro seguro : listaSeguros) {
            receita += seguro.getValorMensal();
        }
        return receita;
    }

    public void lerDados(){
		// Leitura dos Veiculo
		ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

		for(String s : arquivoVeiculo.lerArquivo()){
			String[] splitted = new String[] {};
            splitted = s.split(",");
			listaVeiculos.add(new Veiculo(splitted[0], splitted[1], splitted[2], Integer.parseInt(splitted[3])));
		}

		// Leitura das Frota
		ArrayList<Frota> listaFrotas = new ArrayList<Frota>();
		for(String s : arquivoFrota.lerArquivo()){
            String[] splitted = new String[] {};
            splitted = s.split(",");
            Frota frota = new Frota(splitted[0]);
			for(int i = 1; i < splitted.length; ++i){
                String placa = splitted[i];
				for(Veiculo v : listaVeiculos)
					if(v.getPlaca().equals(placa))
						frota.cadastrarVeiculo(v);
			}
			listaFrotas.add(frota);
		}

		// Leitura dos ClientePF
		for(String s : arquivoClientePF.lerArquivo()){
            String[] splitted = new String[] {};
            splitted = s.split(",");
			ClientePF cliente = new ClientePF(
                splitted[1], splitted[3], null, splitted[6], 
                splitted[5], splitted[2], splitted[0], LocalDate.parse(splitted[7]), splitted[4]);

			for(Veiculo v : listaVeiculos)
				if(v.getPlaca().equals(splitted[8]))
					cliente.cadastrarVeiculo(v);
			this.cadastrarCliente(cliente);
		}

		// Leitura dos ClientePJ
		for(String s : arquivoClientePJ.lerArquivo()){
            String[] splitted = new String[] {};
            splitted = s.split(",");
            ClientePJ cliente = new ClientePJ(splitted[1], splitted[3], 
            LocalDate.parse(splitted[5]), splitted[2], splitted[0], 0, splitted[4]);
			for (Frota frota : listaFrotas)
				if (frota.getCodigo().equals(splitted[6]))
					cliente.cadastrarFrota(frota);
		}

		// Leitura dos Condutor
		for(String s : arquivoCondutor.lerArquivo()){
			String[] splitted = new String[] {};
            splitted = s.split(",");
			listaCondutores.add(new Condutor(splitted[0], 
            splitted[3], splitted[4], splitted[2], splitted[0], 
            LocalDate.parse(splitted[5])));
		}
	}
	
	// Grava os dados de seguros e sinistros
	public void gravarDados(){
        // Grava os dados dos seguros
		for(Seguro seguro : listaSeguros){
			arquivoSeguro.gravarArquivo(seguro);
			// Grava os dados dos sinistros
			for(Sinistro sinistro : seguro.getListaSinistros())
				arquivoSinistro.gravarArquivo(sinistro);
		}
	}

    /* Getters e Setters */

    public String getCnpj() {
        return cnpj;
    }

    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}