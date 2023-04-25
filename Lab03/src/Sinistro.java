import java.util.Random;

public class Sinistro {
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    private final int id;

    private int gerarId() {
        // Gera um identificador para o sinistro
        return (new Random()).nextInt();
    }

    public Sinistro(String data, String endereco) {
        this.data = data;
        this.endereco = endereco;
        this.id = gerarId();
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Data: " + this.data
                + "\nEndereço: " + this.endereco
                + "\nSeguradora: " + this.seguradora
                + "\nVeiculo: " + this.veiculo
                + "\nCliente: " + this.cliente;
    }
}