import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePF extends Cliente {
    private final String cpf;
    private String educacao;
    private String genero;
    private LocalDate dataLicenca;
    private LocalDate dataNascimento;
    private ArrayList<Veiculo> listaVeiculos;

    public ClientePF(String nome, String endereco, LocalDate dataLicenca,
            String educacao, String genero, String telefone,
            String cpf, LocalDate dataNascimento, String email) {
        super(nome, endereco, telefone, email);
        this.cpf = cpf;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.listaVeiculos = new ArrayList<>();
    }

    public String toString() {
        return "-----ClientePF-----\nNome: " + this.getNome() + "\nCPF: " + this.cpf
                + "\nData de nascimento: " + this.dataNascimento
                + "\nData da licença: " + this.dataLicenca
                + "\nEndereço: " + this.getEndereco()
                + "\nEducação: " + this.educacao
                + "\nGênero: " + this.genero
                + "\nTelefone: " + this.getTelefone()
                + "\n---------------";
    }

    public void removerVeiculo(String placa) {
        /*
         * Remove um veículo da lista de veículos
         * de acordo com a placa
         */
        int indice = -1;
        for (int i = 0; i < listaVeiculos.size(); ++i)
            if (listaVeiculos.get(i).getPlaca().equals(placa))
                indice = i;
        if (indice != -1)
            listaVeiculos.remove(indice);
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        /*
         * Adiciona um veículo à lista de veículos
         */
        listaVeiculos.add(veiculo);
    }

    /* Getters e Setters */

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
