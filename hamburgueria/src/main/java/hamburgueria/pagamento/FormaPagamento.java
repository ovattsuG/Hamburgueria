package hamburgueria.pagamento;

/**
 * Strategy interface para formas de pagamento.
 * Padrão Strategy (GoF) — já existente no sistema original.
 * Desacopla a lógica de cobrança do pedido.
 */
public interface FormaPagamento {
    double cobrar(double valor);
}
