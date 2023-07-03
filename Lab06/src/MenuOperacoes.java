public enum MenuOperacoes {
    SAIR(0),
    CADASTRAR(1),
    LISTAR(2),
    EXCLUIR(3),
    ATUALIZAR_FROTA(4),
    CALCULAR_RECEITA(5);

    public final int operacao;

    MenuOperacoes(int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao() {
        return this.operacao;
    }
}