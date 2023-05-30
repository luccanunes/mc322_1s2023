import java.util.ArrayList;
import java.util.Random;
import java.time.LocalDate;

public abstract class Seguro {
    private final int id;
    private double valorMensal;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;

    public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = new ArrayList<>();
        this.listaCondutores = new ArrayList<>();
        this.id = gerarId();
        calcularValor();
    }

    public String toString() {
        return "-----Seguro " + this.getId() + "-----\n" +
                "Seguradora: " + this.getSeguradora().getNome() +
                "\nData de início: " + this.getDataInicio() +
                "\nData de fim: " + this.getDataFim();
    }

    private int gerarId() {
        // Gera um identificador para o sinistro
        return (new Random()).nextInt();
    }

    public boolean autorizarCondutor(Condutor condutor) {
        for (Condutor c : listaCondutores)
            if (c.equals(condutor))
                return false;
        listaCondutores.add(condutor);
        return true;
    }

    public boolean desautorizarCondutor(Condutor condutor) {
        for (Condutor c : listaCondutores)
            if (c.equals(condutor)) {
                listaCondutores.remove(condutor);
                return true;
            }
        return false;
    }

    public void gerarSinistro(Sinistro sinistro) {
        listaSinistros.add(sinistro);
    }

    public abstract void calcularValor();

    /* Getters e setters */

    public abstract Cliente getCliente();

    public int getId() {
        return id;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }
}
