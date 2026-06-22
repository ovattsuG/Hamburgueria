package hamburgueria.pedido.validador;

import hamburgueria.pedido.Pedido;

/**
 * Validador concreto que garante que o pedido possui uma forma de pagamento definida.
 */
public class ValidadorPagamento extends ValidadorPedido {

    @Override
    public void validar(Pedido pedido) {
        if (pedido.getFormaPagamento() == null) {
            throw new ValidationException("Forma de pagamento não especificada!");
        }
        validarProximo(pedido);
    }
}
