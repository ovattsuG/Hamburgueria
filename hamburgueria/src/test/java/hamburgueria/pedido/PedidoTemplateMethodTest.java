package hamburgueria.pedido;

import hamburgueria.desconto.SemDesconto;
import hamburgueria.fidelidade.SemFidelidade;
import hamburgueria.pagamento.PagamentoPix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Template Method — Pipeline de Finalização")
class PedidoTemplateMethodTest {

    @Test
    @DisplayName("deve executar o pipeline chamando o gancho de taxas adicionais para Delivery")
    void deveExecutarTemplateMethodParaDelivery() {
        Pedido pedido = new PedidoDelivery(new PagamentoPix(), new SemDesconto(), new SemFidelidade());
        // Preço: 100.0 (itens) + 10.0 (frete gancho) = 110.0 -> Pix 10% -> 99.00
        double valorFinal = pedido.finalizarPedido(100.0);
        assertEquals(99.00, valorFinal, 0.01);
    }

    @Test
    @DisplayName("deve executar o pipeline chamando o gancho de taxas adicionais para Salão")
    void deveExecutarTemplateMethodParaSalao() {
        Pedido pedido = new PedidoSalao(new PagamentoPix(), new SemDesconto(), new SemFidelidade());
        // Preço: 100.0 (itens) + 0.0 (taxa gancho) = 100.0 -> Pix 10% -> 90.00
        double valorFinal = pedido.finalizarPedido(100.0);
        assertEquals(90.00, valorFinal, 0.01);
    }
}
