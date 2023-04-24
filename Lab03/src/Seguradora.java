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
    }

    public boolean cadastrarCliente(Cliente cliente) {
        return true;
    }

    public boolean removerCliente(Cliente cliente) {
        return true;
    }

    public void listarClientes(String tipoCliente) { // pj ou pf
        for (int i = 0; i < listaClientes.size(); ++i) {
            Cliente cliente = listaClientes.get(i);
            if (cliente.getTipo() == tipoCliente) {
                System.out.println(cliente);
            }
        }
    }

    public boolean gerarSinistro() {
        return true;
    }

    public boolean visualizarSinistro(String cliente) {
        return true;
    }

    public void listarSinistros() {
        for (int i = 0; i < listaSinistros.size(); ++i) {
            Sinistro sinistro = listaSinistros.get(i);
            System.out.println(sinistro);
        }
    }

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