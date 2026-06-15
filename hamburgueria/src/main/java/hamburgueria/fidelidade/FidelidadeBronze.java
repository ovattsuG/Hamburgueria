package hamburgueria.fidelidade;

/**
 * ConcreteStrategy: Fidelidade Bronze — 5% de desconto, sem frete grátis.
 */
public class FidelidadeBronze implements FidelidadeStrategy {

    private static final double PERCENTUAL_DESCONTO = 5.0;

    @Override
    public double aplicarBeneficio(double valor) {
        return valor * (1.0 - PERCENTUAL_DESCONTO / 100.0);
    }

    @Override
    public boolean isFreteGratis() {
        return false;
    }

    @Override
    public String getNivel() {
        return "Bronze (5% desconto)";
    }
}
