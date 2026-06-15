package hamburgueria.desconto;

/**
 * Strategy interface para cálculo de descontos.
 * Padrão Strategy (GoF) — demonstra o Princípio Open/Closed (OCP do SOLID).
 *
 * Novas políticas de desconto podem ser adicionadas criando novas classes
 * que implementam esta interface, SEM modificar o código existente do Pedido.
 */
public interface DescontoStrategy {

    /**
     * Aplica a política de desconto sobre o valor original.
     *
     * @param valorOriginal valor bruto do pedido
     * @return valor após aplicação do desconto (nunca negativo)
     */
    double aplicar(double valorOriginal);

    /**
     * Retorna descrição legível da política de desconto.
     *
     * @return nome/descrição da strategy
     */
    String getDescricao();
}
