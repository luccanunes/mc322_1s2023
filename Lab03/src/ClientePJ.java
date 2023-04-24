import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private String cnpj;
    private LocalDate dataFundacao;

    public ClientePJ(String nome, String endereco, LocalDate dataFundacao,
            ArrayList<Veiculo> listaVeiculos, String cnpj) {
        super(nome, endereco, listaVeiculos, "PJ");
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
    }

    public boolean validarCNPJ() {
        String temp = this.cnpj;
        temp = temp.replaceAll("[\\.-]", "");
        for (int i = 0; i < temp.length(); ++i)
            if (!Character.isDigit(temp.charAt(i)))
                return false;
        if (temp.length() != 11)
            return false;
        boolean todosIguais = true;
        for (int i = 1; i < 11; ++i)
            if (temp.charAt(i) != temp.charAt(0))
                todosIguais = false;
        if (todosIguais)
            return false;

        int primeiroDigito = 0;
        for (int i = 0; i < 9; ++i) {
            primeiroDigito += (10 - i) * Character.getNumericValue(temp.charAt(i));
        }
        primeiroDigito = 11 - (primeiroDigito % 11);
        if (primeiroDigito >= 10)
            primeiroDigito = 0;

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
        return "Nome: " + this.getNome() + "\nCNPJ: " + this.cnpj
                + "\nData de fundação: " + this.dataFundacao
                + "\nEndereço: " + this.getEndereco();
    }
}
