package hamburgueria.pagamento;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Adapter — Pagamentos Externos")
class MercadoPagoAdapterTest {

    @Test
    @DisplayName("deve adaptar a chamada de cobrança para a API do Mercado Pago")
    void deveAdaptarPagamentoMercadoPago() {
        LegacyMercadoPagoAPI apiExterna = new LegacyMercadoPagoAPI();
        FormaPagamento adapter = new MercadoPagoAdapter(apiExterna, "MP-TOKEN-1234");

        double valorCobrado = adapter.cobrar(50.0);

        assertEquals(50.0, valorCobrado, 0.01);
        assertEquals(50.0, apiExterna.getUltimoValorProcessado(), 0.01);
        assertEquals("MP-TOKEN-1234", apiExterna.getUltimoTokenUsado());
    }
}
