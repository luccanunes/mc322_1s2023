import java.time.LocalDate;
import java.time.Period;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora,
            Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public void calcularValor() {
        double FATOR_IDADE = 0;
        int idade = Period.between(cliente.getDataNascimento(), LocalDate.now()).getYears();
        if (18 <= idade && idade <= 30) {
            FATOR_IDADE = CalcSeguro.FATOR_18_30.getx();
        } else if (30 < idade && idade < 60) {
            FATOR_IDADE = CalcSeguro.FATOR_30_60.getx();
        } else if (60 <= idade && idade <= 90) {
            FATOR_IDADE = CalcSeguro.FATOR_60_90.getx();
        }
        double qtdVeiculos = this.getSeguradora().getSegurosPorCliente(cliente.getNome()).size();
        double qtdSinistrosCliente = this.getSeguradora().getSinistrosPorCliente(cliente.getNome()).size();
        double qtdSinistrosCondutor = 0;
        for (Condutor condutor : this.getListaCondutores())
            qtdSinistrosCondutor += condutor.getListaSinistros().size();
        double valor = CalcSeguro.VALOR_BASE.getx() * FATOR_IDADE * (1 + 1 / (qtdVeiculos + 2)) *
                (2 + qtdSinistrosCliente / 10) * (5 + qtdSinistrosCondutor / 10);
        this.setValorMensal(valor);
    }

    /* Getters e setters */

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }
}
