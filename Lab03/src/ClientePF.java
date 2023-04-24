import java.time.LocalDate;
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
    }

    public boolean validarCPF() {
        String temp = this.cpf;
        temp = temp.replaceAll("[\\.-]", ""); // Remove os caracteres separadores
        for (int i = 0; i < temp.length(); ++i)
            if (!Character.isDigit(temp.charAt(i))) // Checa se todos os caracteres são dígitos
                return false;
        if (temp.length() != 11) // Checa se o tamanho está correto
            return false;
        boolean todosIguais = true;
        for (int i = 1; i < 11; ++i)
            if (temp.charAt(i) != temp.charAt(0)) // Checa se todos os dígitos são iguais
                todosIguais = false;
        if (todosIguais)
            return false;

        // Calcula o primeiro dígito verificador
        int primeiroDigito = 0;
        for (int i = 0; i < 9; ++i) {
            primeiroDigito += (10 - i) * Character.getNumericValue(temp.charAt(i));
        }
        primeiroDigito = 11 - (primeiroDigito % 11);
        if (primeiroDigito >= 10)
            primeiroDigito = 0;

        // Calcula o segundo dígito verificador
        int segundoDigito = 0;
        for (int i = 0; i < 10; ++i) {
            segundoDigito += (11 - i) * Character.getNumericValue(temp.charAt(i));
        }
        segundoDigito = 11 - (segundoDigito % 11);
        if (segundoDigito >= 10)
            segundoDigito = 0;

        return primeiroDigito == Character.getNumericValue(temp.charAt(9)) &&
                segundoDigito == Character.getNumericValue(temp.charAt(10));
    }

    public String toString() {
        return "Nome: " + this.getNome() + "\nCPF: " + this.cpf
                + "\nData de nascimento: " + this.dataNascimento
                + "\nData da licença: " + this.dataLicenca
                + "\nEndereço: " + this.getEndereco()
                + "\nIdade: " + this.idade
                + "\nClasse econômica: " + this.classeEconomica
                + "\nEducação: " + this.educacao
                + "\nGênero: " + this.genero
                + "\nEducação: " + this.educacao;
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
