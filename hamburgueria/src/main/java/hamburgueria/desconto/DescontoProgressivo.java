package hamburgueria.desconto;

/**
 * ConcreteStrategy: Desconto Progressivo por faixas de valor.
 *
 * Regras de negócio:
 * - Pedidos acima de R$ 100 → 15% de desconto
 * - Pedidos acima de R$ 50  → 10% de desconto
 * - Pedidos até R$ 50       → sem desconto
 *
 * Demonstra que a lógica de faixas fica encapsulada na Strategy,
 * sem poluir a classe Pedido com if/else.
 */
public class DescontoProgressivo implements DescontoStrategy {

    private static final double FAIXA_ALTA = 100.0;
    private static final double FAIXA_MEDIA = 50.0;
    private static final double PERCENTUAL_ALTA = 15.0;
    private static final double PERCENTUAL_MEDIA = 10.0;

    @Override
    public double aplicar(double valorOriginal) {
        if (valorOriginal > FAIXA_ALTA) {
            return valorOriginal * (1.0 - PERCENTUAL_ALTA / 100.0);
        } else if (valorOriginal > FAIXA_MEDIA) {
            return valorOriginal * (1.0 - PERCENTUAL_MEDIA / 100.0);
        }
        return valorOriginal;
    }

    @Override
    public String getDescricao() {
        return "Desconto progressivo (>R$50=10%, >R$100=15%)";
    }
}
