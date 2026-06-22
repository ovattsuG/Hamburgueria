package hamburgueria.pedido;

public class PedidoProntoState implements PedidoState {

    @Override
    public void avancar(Pedido pedido) {
        pedido.setEstadoAtual(new PedidoEntregueState());
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("Não é possível cancelar um pedido que já está pronto para entrega!");
    }

    @Override
    public String getStatus() {
        return "Pronto";
    }
}
