import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String endereco;
    private String tipo;

    private ArrayList<Veiculo> listaVeiculos;

    public Cliente(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String tipo) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = listaVeiculos;
        this.tipo = tipo;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString() {
        return "Nome: " + this.nome
                + "\nEndereço: " + this.endereco;
    }
}
