package hamburgueria.fidelidade;

/**
 * ConcreteStrategy: Cliente sem programa de fidelidade.
 * Null Object — sem benefício, sem frete grátis.
 */
public class SemFidelidade implements FidelidadeStrategy {

    @Override
    public double aplicarBeneficio(double valor) {
        return valor;
    }

    @Override
    public boolean isFreteGratis() {
        return false;
    }

    @Override
    public String getNivel() {
        return "Sem fidelidade";
    }
}
