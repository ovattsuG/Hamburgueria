package hamburgueria.pedido;

public class PedidoRecebidoState implements PedidoState {

    @Override
    public void avancar(Pedido pedido) {
        pedido.setEstadoAtual(new PedidoPreparandoState());
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstadoAtual(new PedidoCanceladoState());
    }

    @Override
    public String getStatus() {
        return "Recebido";
    }
}
