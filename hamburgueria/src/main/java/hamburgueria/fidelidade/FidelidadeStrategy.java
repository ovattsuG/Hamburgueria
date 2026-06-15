package hamburgueria.fidelidade;

/**
 * Strategy interface para programas de fidelidade.
 * Padrão Strategy (GoF) — demonstra o Princípio Open/Closed (OCP do SOLID).
 *
 * Novos níveis de fidelidade podem ser adicionados sem modificar Pedido.
 */
public interface FidelidadeStrategy {

    /**
     * Aplica o benefício de fidelidade sobre o valor.
     *
     * @param valor valor do pedido após desconto
     * @return valor após benefício de fidelidade
     */
    double aplicarBeneficio(double valor);

    /**
     * Indica se o nível de fidelidade concede frete grátis.
     *
     * @return true se o frete é isento
     */
    boolean isFreteGratis();

    /**
     * Retorna descrição do nível de fidelidade.
     *
     * @return nome do nível
     */
    String getNivel();
}
