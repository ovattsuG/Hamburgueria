package hamburgueria.pedido;

public class PedidoCanceladoState implements PedidoState {

    @Override
    public void avancar(Pedido pedido) {
        throw new IllegalStateException("Não é possível avançar um pedido cancelado!");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("O pedido já está cancelado!");
    }

    @Override
    public String getStatus() {
        return "Cancelado";
    }
}
