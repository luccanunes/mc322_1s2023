import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    private double valorSeguro;

    public Cliente(String nome, String endereco, ArrayList<Veiculo> listaVeiculos) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = listaVeiculos;
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

    public void adicionarVeiculo(Veiculo veiculo) {
        /*
         * Adiciona um veículo à lista de veículos
         */
        listaVeiculos.add(veiculo);
    }

    public double calculaScore() {
        /*
         * Vai ser sobrescrito pelas classes filhas
         */
        return 1.0;
    }

    /* Getters e Setters */

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public String toString() {
        return "\n-----Cliente-----\nNome: " + this.nome
                + "\nEndereço: " + this.endereco
                + "\nValor seguro: " + this.valorSeguro
                + "\n---------------";
    }
}
