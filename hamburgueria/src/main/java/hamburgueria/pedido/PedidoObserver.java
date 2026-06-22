package hamburgueria.pedido;

/**
 * Observer: Interface que define o contrato para receber notificações
 * quando o status de um Pedido for alterado.
 */
public interface PedidoObserver {
    void onStatusChanged(String novoStatus);
}
