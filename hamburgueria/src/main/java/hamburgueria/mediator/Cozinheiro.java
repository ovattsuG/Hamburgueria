package hamburgueria.mediator;

import hamburgueria.pedido.Pedido;

/**
 * Colega concreto: Cozinheiro que prepara os pratos na cozinha.
 */
public class Cozinheiro extends Colleague {
    private final String nome;
    private Pedido pedidoAtual;

    public Cozinheiro(String nome, CozinhaMediator mediator) {
        super(mediator);
        this.nome = nome;
    }

    public void receberPedidoParaPreparar(Pedido pedido) {
        this.pedidoAtual = pedido;
        System.out.println("[MEDIATOR] Cozinheiro " + nome + " está preparando o pedido.");
    }

    public void finalizarPreparo() {
        if (pedidoAtual != null) {
            System.out.println("[MEDIATOR] Cozinheiro " + nome + " finalizou o preparo.");
            mediator.pedidoProntoParaEntrega(pedidoAtual, this);
            pedidoAtual = null;
        }
    }

    public String getNome() {
        return nome;
    }
}
