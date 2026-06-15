package hamburgueria.desconto;

/**
 * ConcreteStrategy: Desconto Percentual configurável.
 * Exemplo: 10% de desconto → new DescontoPercentual(10.0)
 */
public class DescontoPercentual implements DescontoStrategy {

    private final double percentual;

    /**
     * @param percentual valor percentual do desconto (ex: 10.0 para 10%)
     * @throws IllegalArgumentException se percentual < 0 ou > 100
     */
    public DescontoPercentual(double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException(
                "Percentual de desconto deve estar entre 0 e 100. Recebido: " + percentual
            );
        }
        this.percentual = percentual;
    }

    @Override
    public double aplicar(double valorOriginal) {
        return valorOriginal * (1.0 - percentual / 100.0);
    }

    @Override
    public String getDescricao() {
        return "Desconto de " + percentual + "%";
    }
}
