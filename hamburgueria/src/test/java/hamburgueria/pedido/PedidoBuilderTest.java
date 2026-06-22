package hamburgueria.pedido;

import hamburgueria.desconto.DescontoPercentual;
import hamburgueria.fidelidade.FidelidadeBronze;
import hamburgueria.pagamento.FormaPagamento;
import hamburgueria.pagamento.PagamentoPix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Builder — Construção de Pedidos")
class PedidoBuilderTest {

    @Test
    @DisplayName("deve construir um Pedido Delivery completo com sucesso")
    void deveConstruirPedidoDeliveryCompleto() {
        FormaPagamento pix = new PagamentoPix();
        Pedido pedido = new PedidoBuilder()
                .paraDelivery()
                .comFormaPagamento(pix)
                .comDesconto(new DescontoPercentual(10.0))
                .comFidelidade(new FidelidadeBronze())
                .build();

        assertNotNull(pedido);
        assertTrue(pedido instanceof PedidoDelivery);
        assertEquals(pix, pedido.formaPagamento);
        assertTrue(pedido.descontoStrategy instanceof DescontoPercentual);
        assertTrue(pedido.fidelidadeStrategy instanceof FidelidadeBronze);
    }

    @Test
    @DisplayName("deve lançar exceção se faltar forma de pagamento")
    void deveLancarExcecaoSemFormaPagamento() {
        assertThrows(IllegalStateException.class, () -> {
            new PedidoBuilder()
                    .paraSalao()
                    .build();
        });
    }

    @Test
    @DisplayName("deve lançar exceção se faltar tipo de pedido")
    void deveLancarExcecaoSemTipoPedido() {
        assertThrows(IllegalStateException.class, () -> {
            new PedidoBuilder()
                    .comFormaPagamento(new PagamentoPix())
                    .build();
        });
    }
}
