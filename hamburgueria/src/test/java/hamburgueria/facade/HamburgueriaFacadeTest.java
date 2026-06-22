package hamburgueria.facade;

import hamburgueria.caixa.Caixa;
import hamburgueria.desconto.DescontoPercentual;
import hamburgueria.fidelidade.FidelidadeOuro;
import hamburgueria.pagamento.FormaPagamento;
import hamburgueria.pagamento.PagamentoPix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Façade — Simplificação de Pedidos")
class HamburgueriaFacadeTest {

    @BeforeEach
    void setUp() {
        Caixa.getInstancia().zerarCaixa();
    }

    @Test
    @DisplayName("deve realizar um pedido completo via Facade com sucesso")
    void deveRealizarPedidoViaFacade() {
        HamburgueriaFacade facade = new HamburgueriaFacade();
        FormaPagamento pix = new PagamentoPix();

        // Combo Tradicional: LancheCarne (R$ 25.00) + Refrigerante (R$ 8.00) = R$ 33.00
        // Adicionais: Queijo (+ R$ 3.50), Bacon (+ R$ 5.00) = R$ 41.50
        // Pedido Delivery (+ R$ 10 de frete, mas Ouro zera o frete) -> R$ 41.50
        // DescontoPercentual (10%) -> R$ 41.50 * 0.90 = R$ 37.35
        // FidelidadeOuro (15%) -> R$ 37.35 * 0.85 = R$ 31.7475
        // Pix (10%) -> R$ 31.7475 * 0.90 = R$ 28.57275
        double totalPago = facade.realizarPedidoCompleto(
                "Tradicional",
                "Delivery",
                pix,
                new DescontoPercentual(10.0),
                new FidelidadeOuro(),
                true, // com queijo
                true  // com bacon
        );

        assertEquals(28.57, totalPago, 0.01);
        assertEquals(28.57, Caixa.getInstancia().getSaldoTotal(), 0.01);
    }
}
