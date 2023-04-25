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

    public boolean validarCNPJ() {
        String temp = this.cnpj;
        temp = temp.replaceAll("[\\.-]", ""); // Remove os caracteres separadores
        temp = temp.replaceAll("[/]", "");
        for (int i = 0; i < temp.length(); ++i)
            if (!Character.isDigit(temp.charAt(i))) // Checa se todos os caracteres são dígitos
                return false;
        if (temp.length() != 14) // Checa se o tamanho está correto
            return false;
        boolean todosIguais = true;
        for (int i = 1; i < 11; ++i)
            if (temp.charAt(i) != temp.charAt(0)) // Checa se todos os dígitos são iguais
                todosIguais = false;
        if (todosIguais)
            return false;

        // Calcula o primeiro dígito verificador
        int primeiros_multiplicadores[] = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        int primeiroDigito = 0;
        for (int i = 0; i < 12; ++i) {
            primeiroDigito += primeiros_multiplicadores[i] * Character.getNumericValue(temp.charAt(i));
        }
        primeiroDigito %= 11;
        if (primeiroDigito > 1)
            primeiroDigito = 11 - primeiroDigito;
        else
            primeiroDigito = 0;

        if (primeiroDigito != Character.getNumericValue(temp.charAt(12)))
            return false;

        // Calcula o segundo dígito verificador
        int segundos_multiplicadores[] = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        int segundoDigito = 0;
        for (int i = 0; i < 13; ++i) {
            segundoDigito += segundos_multiplicadores[i] * Character.getNumericValue(temp.charAt(i));
        }
        segundoDigito %= 11;
        if (segundoDigito > 1)
            segundoDigito = 11 - segundoDigito;
        else
            segundoDigito = 0;

        return segundoDigito == Character.getNumericValue(temp.charAt(13));
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
