package hamburgueria.mediator;

import hamburgueria.pedido.Pedido;

/**
 * Colega concreto: Entregador/Garçom responsável por servir ou entregar o pedido.
 */
public class Entregador extends Colleague {
    private final String nome;

    public Entregador(String nome, CozinhaMediator mediator) {
        super(mediator);
        this.nome = nome;
    }

    public void receberPedidoParaEntregar(Pedido pedido) {
        System.out.println("[MEDIATOR] Entregador " + nome + " recebeu o pedido e saiu para entrega.");
        mediator.pedidoEntregue(pedido, this);
    }

    public String getNome() {
        return nome;
    }
}
