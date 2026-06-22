package hamburgueria.pedido.validador;

import hamburgueria.pedido.Pedido;

/**
 * Validador concreto que garante que o pedido contém pelo menos um item.
 */
public class ValidadorItens extends ValidadorPedido {

    @Override
    public void validar(Pedido pedido) {
        if (pedido.getItens().isEmpty()) {
            throw new ValidationException("O pedido deve conter pelo menos um item!");
        }
        validarProximo(pedido);
    }
}
