package hamburgueria.pedido;

import hamburgueria.pagamento.PagamentoPix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão State — Ciclo de Vida do Pedido")
class PedidoStateTest {

    @Test
    @DisplayName("deve avançar no ciclo de vida correto: Recebido -> Em Preparação -> Pronto -> Entregue")
    void deveAvancarCicloDeVidaCorreto() {
        Pedido pedido = new PedidoSalao(new PagamentoPix());

        assertEquals("Recebido", pedido.getDescricaoStatus());

        pedido.avancarEstado();
        assertEquals("Em Preparação", pedido.getDescricaoStatus());

        pedido.avancarEstado();
        assertEquals("Pronto", pedido.getDescricaoStatus());

        pedido.avancarEstado();
        assertEquals("Entregue", pedido.getDescricaoStatus());

        // Avançar um pedido entregue deve falhar
        assertThrows(IllegalStateException.class, pedido::avancarEstado);
    }

    @Test
    @DisplayName("deve permitir cancelar um pedido que está no estado Recebido")
    void devePermitirCancelarPedidoRecebido() {
        Pedido pedido = new PedidoSalao(new PagamentoPix());
        pedido.cancelarPedidoState();

        assertEquals("Cancelado", pedido.getDescricaoStatus());
        assertThrows(IllegalStateException.class, pedido::avancarEstado);
    }

    @Test
    @DisplayName("deve impedir o cancelamento de um pedido que já está no estado Em Preparação")
    void deveImpedirCancelarPedidoEmPreparacao() {
        Pedido pedido = new PedidoSalao(new PagamentoPix());
        pedido.avancarEstado(); // Em Preparação

        assertThrows(IllegalStateException.class, pedido::cancelarPedidoState);
        assertEquals("Em Preparação", pedido.getDescricaoStatus());
    }
}
