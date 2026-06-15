package hamburgueria.desconto;

/**
 * ConcreteStrategy: Desconto de valor fixo em reais.
 * Exemplo: R$ 5,00 de desconto → new DescontoValorFixo(5.0)
 *
 * O valor final nunca fica negativo (floor em R$ 0,00).
 */
public class DescontoValorFixo implements DescontoStrategy {

    private final double valorDesconto;

    /**
     * @param valorDesconto valor fixo a ser descontado (em R$)
     * @throws IllegalArgumentException se valorDesconto < 0
     */
    public DescontoValorFixo(double valorDesconto) {
        if (valorDesconto < 0) {
            throw new IllegalArgumentException(
                "Valor do desconto fixo não pode ser negativo. Recebido: " + valorDesconto
            );
        }
        this.valorDesconto = valorDesconto;
    }

    @Override
    public double aplicar(double valorOriginal) {
        return Math.max(0.0, valorOriginal - valorDesconto);
    }

    @Override
    public String getDescricao() {
        return "Desconto fixo de R$ " + String.format("%.2f", valorDesconto);
    }
}
