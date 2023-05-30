public enum CalcSeguro {
    VALOR_BASE(10.0),
    FATOR_18_30(1.25),
    FATOR_30_60(1.0),
    FATOR_60_90(1.5);

    public final double x;

    CalcSeguro(double x) {
        this.x = x;
    }

    public double getx() {
        return this.x;
    }
}
