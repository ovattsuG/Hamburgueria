package hamburgueria.pedido;

/**
 * State: Interface base para representar os estados de um Pedido.
 */
public interface PedidoState {
    void avancar(Pedido pedido);
    void cancelar(Pedido pedido);
    String getStatus();
}
