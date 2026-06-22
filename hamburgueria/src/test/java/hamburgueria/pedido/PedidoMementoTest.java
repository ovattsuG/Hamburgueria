package hamburgueria.pedido;

import hamburgueria.desconto.DescontoPercentual;
import hamburgueria.desconto.SemDesconto;
import hamburgueria.fidelidade.FidelidadeBronze;
import hamburgueria.fidelidade.SemFidelidade;
import hamburgueria.pagamento.FormaPagamento;
import hamburgueria.pagamento.PagamentoCartao;
import hamburgueria.pagamento.PagamentoPix;
import hamburgueria.produto.LancheCarne;
import hamburgueria.produto.Refrigerante;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Memento — Salvar e Restaurar Pedidos")
class PedidoMementoTest {

    @Test
    @DisplayName("deve salvar e restaurar o estado completo de um pedido com sucesso")
    void deveSalvarERestaurarEstadoPedido() {
        FormaPagamento pix = new PagamentoPix();
        Pedido pedido = new PedidoBuilder()
                .paraSalao()
                .comFormaPagamento(pix)
                .comItem(new LancheCarne())
                .build();

        PedidoCaretaker caretaker = new PedidoCaretaker();

        // Estado Inicial
        assertEquals(1, pedido.getItens().size());
        assertEquals(pix, pedido.getFormaPagamento());
        assertTrue(pedido.descontoStrategy instanceof SemDesconto);

        // Salvar Ponto de Restauração 1
        caretaker.salvar(pedido);

        // Modificações 1
        pedido.adicionarItem(new Refrigerante());
        FormaPagamento cartao = new PagamentoCartao();
        pedido.setFormaPagamento(cartao);
        pedido.setDescontoStrategy(new DescontoPercentual(10.0));

        assertEquals(2, pedido.getItens().size());
        assertEquals(cartao, pedido.getFormaPagamento());
        assertTrue(pedido.descontoStrategy instanceof DescontoPercentual);

        // Salvar Ponto de Restauração 2
        caretaker.salvar(pedido);

        // Modificações 2 (mudar fidelidade e adicionar mais um lanche)
        pedido.setFidelidadeStrategy(new FidelidadeBronze());
        pedido.adicionarItem(new LancheCarne());

        assertEquals(3, pedido.getItens().size());
        assertTrue(pedido.fidelidadeStrategy instanceof FidelidadeBronze);

        // Desfazer para o Ponto de Restauração 2 (Modificações 1)
        caretaker.desfazer(pedido);

        assertEquals(2, pedido.getItens().size());
        assertEquals(cartao, pedido.getFormaPagamento());
        assertTrue(pedido.descontoStrategy instanceof DescontoPercentual);
        assertTrue(pedido.fidelidadeStrategy instanceof SemFidelidade);

        // Desfazer para o Ponto de Restauração 1 (Estado Inicial)
        caretaker.desfazer(pedido);

        assertEquals(1, pedido.getItens().size());
        assertEquals(pix, pedido.getFormaPagamento());
        assertTrue(pedido.descontoStrategy instanceof SemDesconto);
    }
}
