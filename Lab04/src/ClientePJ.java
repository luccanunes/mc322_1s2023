import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private LocalDate dataFundacao;
    private int qtdFuncionarios;

    public ClientePJ(String nome, String endereco, LocalDate dataFundacao,
            ArrayList<Veiculo> listaVeiculos, String cnpj, int qtdFuncionarios) {
        super(nome, endereco, listaVeiculos);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.qtdFuncionarios = qtdFuncionarios;
    }

    public String toString() {
        return "-----ClientePJ-----\nNome: " + this.getNome() + "\nCNPJ: " + this.cnpj
                + "\nData de fundação: " + this.dataFundacao
                + "\nEndereço: " + this.getEndereco()
                + "\n---------------";
    }

    public double calculaScore() {
        return CalcSeguro.VALOR_BASE.getx() * (1 + qtdFuncionarios / 100.0) * this.getListaVeiculos().size();
    }

    /* Getters e Setters */

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdFuncionarios() {
        return qtdFuncionarios;
    }

    public void setQtdFuncionarios(int qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }
}
