package hamburgueria.pedido;

import hamburgueria.pagamento.FormaPagamento;
import hamburgueria.pagamento.PagamentoPix;
import hamburgueria.pedido.validador.*;
import hamburgueria.produto.LancheCarne;
import hamburgueria.produto.Refrigerante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Chain of Responsibility — Validação de Pedidos")
class PedidoValidadorTest {

    private ValidadorPedido chain;

    @BeforeEach
    void setUp() {
        // Monta a cadeia de validação: Itens -> Pagamento -> Valor Mínimo Delivery
        chain = new ValidadorItens();
        chain.setProximo(new ValidadorPagamento())
             .setProximo(new ValidadorValorMinimoDelivery());
    }

    @Test
    @DisplayName("deve lançar exceção se o pedido não tiver itens")
    void deveLancarExcecaoSemItens() {
        FormaPagamento pix = new PagamentoPix();
        Pedido pedido = new PedidoBuilder()
                .paraSalao()
                .comFormaPagamento(pix)
                .build(); // Sem itens

        ValidationException ex = assertThrows(ValidationException.class, () -> chain.validar(pedido));
        assertEquals("O pedido deve conter pelo menos um item!", ex.getMessage());
    }

    @Test
    @DisplayName("deve lançar exceção se o pedido não tiver forma de pagamento")
    void deveLancarExcecaoSemPagamento() {
        // Para passar do construtor do PedidoBuilder sem exceção de falta de pagamento,
        // vamos montar instanciando diretamente um PedidoSalao sem FormaPagamento.
        // O PedidoBuilder obriga ter pagamento, o que é ótimo, mas o validador garante isso em nível de pipeline.
        Pedido pedido = new PedidoSalao(null);
        pedido.adicionarItem(new LancheCarne());

        ValidationException ex = assertThrows(ValidationException.class, () -> chain.validar(pedido));
        assertEquals("Forma de pagamento não especificada!", ex.getMessage());
    }

    @Test
    @DisplayName("deve lançar exceção se o delivery não atingir o valor mínimo de R$ 15,00")
    void deveLancarExcecaoMinimoDelivery() {
        FormaPagamento pix = new PagamentoPix();
        // Refrigerante custa R$ 8.00 (menor que R$ 15.00)
        Pedido pedido = new PedidoBuilder()
                .paraDelivery()
                .comFormaPagamento(pix)
                .comItem(new Refrigerante())
                .build();

        ValidationException ex = assertThrows(ValidationException.class, () -> chain.validar(pedido));
        assertEquals("Valor mínimo dos itens para entrega é R$ 15.0!", ex.getMessage());
    }

    @Test
    @DisplayName("deve validar com sucesso se o pedido no salão estiver correto mesmo com valor baixo")
    void deveValidarSalaoValorBaixo() {
        FormaPagamento pix = new PagamentoPix();
        // Refrigerante custa R$ 8.00, mas no Salão não há valor mínimo
        Pedido pedido = new PedidoBuilder()
                .paraSalao()
                .comFormaPagamento(pix)
                .comItem(new Refrigerante())
                .build();

        assertDoesNotThrow(() -> chain.validar(pedido));
    }

    @Test
    @DisplayName("deve validar com sucesso um pedido de delivery válido")
    void deveValidarDeliveryValido() {
        FormaPagamento pix = new PagamentoPix();
        // Lanche custa R$ 25.00 (>= R$ 15.00)
        Pedido pedido = new PedidoBuilder()
                .paraDelivery()
                .comFormaPagamento(pix)
                .comItem(new LancheCarne())
                .build();

        assertDoesNotThrow(() -> chain.validar(pedido));
    }
}
