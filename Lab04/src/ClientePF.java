import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class ClientePF extends Cliente {
    private final String cpf;
    private String classeEconomica;
    private String educacao;
    private String genero;
    private LocalDate dataLicenca;
    private LocalDate dataNascimento;
    private int idade;

    public ClientePF(String nome, String endereco, LocalDate dataLicenca,
            String educacao, String genero, String classeEconomica,
            ArrayList<Veiculo> listaVeiculos, String cpf, LocalDate dataNascimento) {
        super(nome, endereco, listaVeiculos);
        this.cpf = cpf;
        this.dataLicenca = dataLicenca;
        this.classeEconomica = classeEconomica;
        this.educacao = educacao;
        this.genero = genero;
        this.dataNascimento = dataLicenca;
        this.setIdade(Period.between(dataNascimento, LocalDate.now()).getYears());
    }

    public String toString() {
        return "-----ClientePF-----\nNome: " + this.getNome() + "\nCPF: " + this.cpf
                + "\nData de nascimento: " + this.dataNascimento
                + "\nData da licença: " + this.dataLicenca
                + "\nEndereço: " + this.getEndereco()
                + "\nIdade: " + this.idade
                + "\nClasse econômica: " + this.classeEconomica
                + "\nEducação: " + this.educacao
                + "\nGênero: " + this.genero
                + "\n---------------";
    }

    public double calculaScore() {
        double FATOR_IDADE = 0;
        int idade = this.getIdade();
        if (18 <= idade && idade <= 30) {
            FATOR_IDADE = CalcSeguro.FATOR_18_30.getx();
        } else if (30 < idade && idade < 60) {
            FATOR_IDADE = CalcSeguro.FATOR_30_60.getx();
        } else if (60 <= idade && idade <= 90) {
            FATOR_IDADE = CalcSeguro.FATOR_60_90.getx();
        }
        return CalcSeguro.VALOR_BASE.getx() * FATOR_IDADE * this.getListaVeiculos().size();
    }

    /* Getters e Setters */

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
