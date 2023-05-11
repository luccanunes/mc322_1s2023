import java.time.LocalDate;
import java.util.ArrayList;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;

    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaClientes = new ArrayList<>();
        this.listaSinistros = new ArrayList<>();
    }

    public String toString() {
        return "\n-----Seguradora-----\nNome: " + this.nome
                + "\nEndereço: " + this.endereco
                + "\nEmail: " + this.email
                + "\nTelefone: " + this.telefone
                + "\n---------------";
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
        return false;
    }

    public boolean removerSinistro(int indice) {
        if (0 <= indice && indice < listaSinistros.size()) {
            listaSinistros.remove(indice);
            return true;
        }
        return false;
    }

    public Cliente encontrarCliente(String nome_cliente) {
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
            if (listaClientes.get(i).getNome() == cliente) {
                ArrayList<Integer> indices_para_remover = new ArrayList<>(); // Guarda os índices dos sinistros desse
                                                                             // cliente
                for (int j = 0; j < listaSinistros.size(); ++j) {
                    Sinistro sinistro = listaSinistros.get(j);
                    if (sinistro.getCliente().getNome() == cliente) {
                        indices_para_remover.add(j);
                    }
                }
                // Remove os sinistros associados ao cliente
                for (int indice : indices_para_remover) {
                    listaSinistros.remove(indice);
                }
                listaClientes.remove(i);
                return false;
            }
        }
        return true;
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

    public boolean gerarSinistro(Veiculo veiculo, Cliente cliente, String endereco) {
        // Gera um sinistro com o veiculo e cliente passados e a data atual
        cadastrarCliente(cliente);
        listaSinistros.add(new Sinistro(LocalDate.now(), endereco, this, veiculo, cliente));
        return true;
    }

    public boolean visualizarSinistro(String cliente) {
        // cliente: nome do cliente
        // Mostra na tela os sinistros associados a esse cliente
        // Retona true se existe algum sinistro associado a esse cliente e false do
        // contrário
        boolean existe = false;
        for (int i = 0; i < listaSinistros.size(); ++i) {
            Sinistro sinistro = listaSinistros.get(i);
            if (sinistro.getCliente().getNome().trim().equals(cliente)) {
                System.out.println(sinistro);
                existe = true;
            }
        }
        return existe;
    }

    public void listarSinistros() {
        // Lista os sinistros
        for (int i = 0; i < listaSinistros.size(); ++i) {
            Sinistro sinistro = listaSinistros.get(i);
            System.out.println(sinistro);
        }
    }

    public double calcularPrecoSeguroCliente(Cliente cliente) {
        // Retorna o preço do seguro do cliente
        int qtd_sinistros = 0;
        for (int i = 0; i < listaSinistros.size(); ++i) {
            if (listaSinistros.get(i).getCliente().equals(cliente)) {
                // Calcula quantidade de sinistros associados a esse cliente
                qtd_sinistros++;
            }
        }
        cliente.setValorSeguro(cliente.calculaScore() * (1 + qtd_sinistros));
        return cliente.getValorSeguro();
    }

    public double calcularReceita() {
        // Retorna a soma dos valores do seguro de cada cliente
        double receita = 0;
        for (Cliente cliente : listaClientes) {
            receita += cliente.getValorSeguro();
        }
        return receita;
    }

    /* Getters e Setters */

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
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