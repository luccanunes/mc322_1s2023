import java.time.LocalDate;
import java.util.Random;

public class Sinistro {
    private LocalDate data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Condutor condutor;

    private final int id;

    private int gerarId() {
        // Gera um identificador para o sinistro
        return (new Random()).nextInt();
    }

    public Sinistro(LocalDate data, String endereco, Seguradora seguradora, Veiculo veiculo, Condutor condutor) {
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.condutor = condutor;
        this.id = gerarId();
    }

    public String toString() {
        return "-----Sinistro-----\nData: " + this.data
                + "\nEndereço: " + this.endereco
                + "\nSeguradora: " + this.seguradora.getNome()
                + "\nVeiculo: " + this.veiculo
                + "\nCliente: " + this.condutor.getNome()
                + "\n---------------";
    }

    /* Getters e Setters */

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

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
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
}