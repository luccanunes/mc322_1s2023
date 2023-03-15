public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String endereco;
    private int idade;

    public Cliente(String nome, String cpf, String dataNascimento, String endereco, int idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean validarCPF() {
        String temp = this.cpf;
        temp = temp.replaceAll("[\\.-]", "");
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
        return this.nome;
    }
}
