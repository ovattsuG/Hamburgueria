package hamburgueria.fidelidade;

/**
 * ConcreteStrategy: Fidelidade Ouro — 15% de desconto + frete grátis.
 * Nível máximo de fidelidade, demonstra que o flag isFreteGratis()
 * influencia diretamente a lógica do PedidoDelivery via Strategy.
 */
public class FidelidadeOuro implements FidelidadeStrategy {

    private static final double PERCENTUAL_DESCONTO = 15.0;

    @Override
    public double aplicarBeneficio(double valor) {
        return valor * (1.0 - PERCENTUAL_DESCONTO / 100.0);
    }

    @Override
    public boolean isFreteGratis() {
        return true;
    }

    @Override
    public String getNivel() {
        return "Ouro (15% desconto + frete grátis)";
    }
}
