package hamburgueria.mediator;

import hamburgueria.pagamento.FormaPagamento;
import hamburgueria.pagamento.PagamentoPix;
import hamburgueria.pedido.Pedido;
import hamburgueria.pedido.PedidoBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Mediator — Coordenação de Fluxos na Cozinha")
class CozinhaMediatorTest {

    @Test
    @DisplayName("deve coordenar fluxo de atendimento e produção com alterações de estado corretas")
    void deveCoordenarFluxoAtendimentoEProducao() {
        CozinhaMediatorImpl mediator = new CozinhaMediatorImpl();

        Atendente atendente = new Atendente("João", mediator);
        Cozinheiro cozinheiro = new Cozinheiro("Chef Jacquin", mediator);
        Entregador entregador = new Entregador("Vini", mediator);

        mediator.registrarAtendente(atendente);
        mediator.registrarCozinheiro(cozinheiro);
        mediator.registrarEntregador(entregador);

        FormaPagamento pix = new PagamentoPix();
        Pedido pedido = new PedidoBuilder()
                .paraDelivery()
                .comFormaPagamento(pix)
                .build();

        // Estado inicial
        assertEquals("Recebido", pedido.getDescricaoStatus());

        // 1. Atendente registra o pedido
        atendente.registrarPedido(pedido);
        assertEquals("Em Preparação", pedido.getDescricaoStatus());
        assertTrue(mediator.getUltimoLog().contains("João registrou"));

        // 2. Cozinheiro termina o preparo
        cozinheiro.finalizarPreparo();
        assertEquals("Entregue", pedido.getDescricaoStatus()); // Cozinheiro envia ao entregador, que entrega na hora!
        assertTrue(mediator.getUltimoLog().contains("Vini entregou"));
    }
}
