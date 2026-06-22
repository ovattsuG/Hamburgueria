package hamburgueria.pedido;

public class PedidoEntregueState implements PedidoState {

    @Override
    public void avancar(Pedido pedido) {
        throw new IllegalStateException("O pedido já foi entregue e finalizado!");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("Não é possível cancelar um pedido que já foi entregue!");
    }

    @Override
    public String getStatus() {
        return "Entregue";
    }
}
