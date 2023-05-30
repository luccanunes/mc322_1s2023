import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private LocalDate dataFundacao;
    private int qtdFuncionarios;
    private ArrayList<Frota> listaFrotas;

    public ClientePJ(String nome, String endereco, LocalDate dataFundacao,
            String telefone, String cnpj, int qtdFuncionarios) {
        super(nome, endereco, telefone);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.qtdFuncionarios = qtdFuncionarios;
        listaFrotas = new ArrayList<>();
    }

    public String toString() {
        return "-----ClientePJ-----\nNome: " + this.getNome() + "\nCNPJ: " + this.cnpj
                + "\nData de fundação: " + this.dataFundacao
                + "\nEndereço: " + this.getEndereco()
                + "\nTelefone: " + this.getTelefone()
                + "\n---------------";
    }

    public boolean cadastrarFrota(Frota frota) {
        for (Frota f : listaFrotas)
            if (f.getCodigo().equals(frota.getCodigo()))
                return false;
        listaFrotas.add(frota);
        return true;
    }

    public boolean atualizarFrota(String codigo, ArrayList<Veiculo> novaListaVeiculos) {
        for (Frota frota : listaFrotas) {
            if (frota.getCodigo().equals(codigo)) {
                if (novaListaVeiculos.size() == 0)
                    listaFrotas.remove(frota);
                else
                    frota.setListaVeiculos(novaListaVeiculos);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Veiculo> getVeiculosPorFrota(String codigo) {
        for (Frota frota : listaFrotas)
            if (frota.getCodigo().equals(codigo))
                return frota.getListaVeiculos();
        return new ArrayList<>();
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

    public ArrayList<Frota> getListaFrotas() {
        return listaFrotas;
    }

    public void setListaFrotas(ArrayList<Frota> listaFrotas) {
        this.listaFrotas = listaFrotas;
    }
}
