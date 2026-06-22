package hamburgueria.pedido;

public class PedidoPreparandoState implements PedidoState {

    @Override
    public void avancar(Pedido pedido) {
        pedido.setEstadoAtual(new PedidoProntoState());
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("Não é possível cancelar um pedido que já está em preparação na cozinha!");
    }

    @Override
    public String getStatus() {
        return "Em Preparação";
    }
}
