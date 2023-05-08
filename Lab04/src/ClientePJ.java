import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private LocalDate dataFundacao;

    public ClientePJ(String nome, String endereco, LocalDate dataFundacao,
            ArrayList<Veiculo> listaVeiculos, String cnpj) {
        super(nome, endereco, listaVeiculos);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
    }

    public String toString() {
        return "-----ClientePJ-----\nNome: " + this.getNome() + "\nCNPJ: " + this.cnpj
                + "\nData de fundação: " + this.dataFundacao
                + "\nEndereço: " + this.getEndereco()
                + "\n---------------";
    }

    /* Getters e Setters */

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
}
