package hamburgueria.mediator;

import hamburgueria.pedido.Pedido;

/**
 * Colega concreto: Atendente que registra novos pedidos.
 */
public class Atendente extends Colleague {
    private final String nome;

    public Atendente(String nome, CozinhaMediator mediator) {
        super(mediator);
        this.nome = nome;
    }

    public void registrarPedido(Pedido pedido) {
        System.out.println("[MEDIATOR] Atendente " + nome + " registrou o pedido.");
        mediator.novoPedidoRegistrado(pedido, this);
    }

    public String getNome() {
        return nome;
    }
}
