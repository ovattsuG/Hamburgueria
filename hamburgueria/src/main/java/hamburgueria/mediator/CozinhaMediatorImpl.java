package hamburgueria.mediator;

import hamburgueria.pedido.Pedido;

/**
 * Mediador concreto que gerencia as interações entre Atendente, Cozinheiro e Entregador,
 * alterando coordenadamente o estado do pedido.
 */
public class CozinhaMediatorImpl implements CozinhaMediator {
    private Atendente atendente;
    private Cozinheiro cozinheiro;
    private Entregador entregador;

    private String ultimoLog;

    @Override
    public void registrarAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    @Override
    public void registrarCozinheiro(Cozinheiro cozinheiro) {
        this.cozinheiro = cozinheiro;
    }

    @Override
    public void registrarEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    @Override
    public void novoPedidoRegistrado(Pedido pedido, Atendente atendente) {
        ultimoLog = "Atendente " + atendente.getNome() + " registrou. Encaminhando ao cozinheiro " + cozinheiro.getNome();
        System.out.println("[MEDIATOR_LOG] " + ultimoLog);
        pedido.avancarEstado(); // Recebido -> Em Preparação
        cozinheiro.receberPedidoParaPreparar(pedido);
    }

    @Override
    public void pedidoProntoParaEntrega(Pedido pedido, Cozinheiro cozinheiro) {
        ultimoLog = "Cozinheiro " + cozinheiro.getNome() + " finalizou. Encaminhando ao entregador " + entregador.getNome();
        System.out.println("[MEDIATOR_LOG] " + ultimoLog);
        pedido.avancarEstado(); // Em Preparação -> Pronto
        entregador.receberPedidoParaEntregar(pedido);
    }

    @Override
    public void pedidoEntregue(Pedido pedido, Entregador entregador) {
        ultimoLog = "Entregador " + entregador.getNome() + " entregou.";
        System.out.println("[MEDIATOR_LOG] " + ultimoLog);
        pedido.avancarEstado(); // Pronto -> Entregue
    }

    public String getUltimoLog() {
        return ultimoLog;
    }
}
