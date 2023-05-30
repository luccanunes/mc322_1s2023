import java.time.LocalDate;
import java.time.Period;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora,
            Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
    }

    public void calcularValor() {
        int idade = Period.between(cliente.getDataFundacao(), LocalDate.now()).getYears();
        double qtdFunc = cliente.getQtdFuncionarios();
        double qtdVeiculos = this.getSeguradora().getSegurosPorCliente(cliente.getNome()).size();
        double qtdSinistrosCliente = this.getSeguradora().getSinistrosPorCliente(cliente.getNome()).size();
        double qtdSinistrosCondutor = 0;
        for (Condutor condutor : this.getListaCondutores())
            qtdSinistrosCondutor += condutor.getListaSinistros().size();
        double valor = CalcSeguro.VALOR_BASE.getx() * (10 + qtdFunc / 10) * (1 + 1 / (qtdVeiculos + 2)) *
                (1 + 1 / (idade + 2)) * (2 + qtdSinistrosCliente / 10) * (5 + qtdSinistrosCondutor / 10);
        this.setValorMensal(valor);
    }

    /* Getters e setters */

    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }
}
