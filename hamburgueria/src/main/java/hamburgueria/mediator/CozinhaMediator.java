package hamburgueria.mediator;

import hamburgueria.pedido.Pedido;

/**
 * Interface Mediator no padrão Mediator (GoF).
 * Centraliza e coordena a comunicação entre Atendente, Cozinheiro e Entregador,
 * reduzindo o acoplamento direto entre esses atores.
 */
public interface CozinhaMediator {
    void registrarAtendente(Atendente atendente);
    void registrarCozinheiro(Cozinheiro cozinheiro);
    void registrarEntregador(Entregador entregador);

    void novoPedidoRegistrado(Pedido pedido, Atendente atendente);
    void pedidoProntoParaEntrega(Pedido pedido, Cozinheiro cozinheiro);
    void pedidoEntregue(Pedido pedido, Entregador entregador);
}
