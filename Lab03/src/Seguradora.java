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

    public boolean removerCliente(String cliente) {
        // Retorna false se o cliente não está registrado
        // Do contrário, retorna true
        for (int i = 0; i < listaClientes.size(); ++i) {
            if (listaClientes.get(i).getNome() == cliente) {
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

    public boolean gerarSinistro(Sinistro sinistro) {
        return true;
    }

    public boolean visualizarSinistro(String cliente) {
        // cliente: nome do cliente
        for (int i = 0; i < listaSinistros.size(); ++i) {
            Sinistro sinistro = listaSinistros.get(i);
            if (sinistro.getCliente().getNome() == cliente) {
                System.out.println(sinistro);
                return true;
            }
        }
        return false;
    }

    public void listarSinistros() {
        // Lista os sinistros
        for (int i = 0; i < listaSinistros.size(); ++i) {
            Sinistro sinistro = listaSinistros.get(i);
            System.out.println(sinistro);
        }
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