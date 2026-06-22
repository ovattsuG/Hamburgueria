package hamburgueria.pedido.validador;

import hamburgueria.pedido.Pedido;
import hamburgueria.pedido.PedidoDelivery;

/**
 * Validador concreto que exige um valor mínimo de itens de R$ 15,00 para pedidos de Delivery.
 */
public class ValidadorValorMinimoDelivery extends ValidadorPedido {

    private static final double MINIMO_DELIVERY = 15.0;

    @Override
    public void validar(Pedido pedido) {
        if (pedido instanceof PedidoDelivery) {
            double totalItens = pedido.getValorTotalItens();
            if (totalItens < MINIMO_DELIVERY) {
                throw new ValidationException("Valor mínimo dos itens para entrega é R$ " + MINIMO_DELIVERY + "!");
            }
        }
        validarProximo(pedido);
    }
}
