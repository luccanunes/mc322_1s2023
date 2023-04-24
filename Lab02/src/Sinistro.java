import java.util.Random;

public class Sinistro {
    private String data;
    private String endereco;
    private int id;

    private int gerarId() {
        return (new Random()).nextInt();
    }

    public Sinistro(String data, String endereco) {
        this.data = data;
        this.endereco = endereco;
        this.id = gerarId();
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
}