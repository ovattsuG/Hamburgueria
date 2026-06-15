package hamburgueria.fidelidade;

/**
 * ConcreteStrategy: Fidelidade Prata — 10% de desconto, sem frete grátis.
 */
public class FidelidadePrata implements FidelidadeStrategy {

    private static final double PERCENTUAL_DESCONTO = 10.0;

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
        return "Prata (10% desconto)";
    }
}
