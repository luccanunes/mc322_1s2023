import java.util.ArrayList;

public class Frota {
    private String codigo;
    ArrayList<Veiculo> listaVeiculos;

    public Frota(String codigo) {
        this.codigo = codigo;
        listaVeiculos = new ArrayList<>();
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

    public String toString() {
        String res = "-----Frota " + codigo + "-----\nVeículos:\n";
        for (Veiculo veiculo : listaVeiculos)
            res += veiculo.toString();
        return res;
    }

    /* Getters e setters */

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
}
