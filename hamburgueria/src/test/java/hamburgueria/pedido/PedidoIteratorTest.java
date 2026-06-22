package hamburgueria.pedido;

import hamburgueria.desconto.SemDesconto;
import hamburgueria.fidelidade.SemFidelidade;
import hamburgueria.pagamento.FormaPagamento;
import hamburgueria.pagamento.PagamentoPix;
import hamburgueria.produto.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Iterator — Iteração Profunda de Itens")
class PedidoIteratorTest {

    @Test
    @DisplayName("deve iterar sobre itens simples do pedido")
    void deveIterarItensSimples() {
        FormaPagamento pix = new PagamentoPix();
        Pedido pedido = new PedidoBuilder()
                .paraSalao()
                .comFormaPagamento(pix)
                .comItem(new LancheCarne())
                .comItem(new Refrigerante())
                .build();

        Iterator<ItemMenu> iterator = pedido.criarDeepIterator();
        List<ItemMenu> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertEquals(2, result.size());
        assertTrue(result.get(0) instanceof LancheCarne);
        assertTrue(result.get(1) instanceof Refrigerante);
    }

    @Test
    @DisplayName("deve iterar e achatar recursivamente combos compostos (Composite)")
    void deveIterarEAchatarCombos() {
        FormaPagamento pix = new PagamentoPix();

        ComboComposite combo = new ComboComposite("Combo Família");
        combo.adicionar(new LancheCarne());
        combo.adicionar(new Refrigerante());
        combo.adicionar(new BatataFrita());

        Pedido pedido = new PedidoBuilder()
                .paraDelivery()
                .comFormaPagamento(pix)
                .comItem(new LancheVegano()) // item simples
                .comItem(combo)              // combo composto (3 itens)
                .comItem(new SucoNatural())  // item simples
                .build();

        Iterator<ItemMenu> iterator = pedido.criarDeepIterator();
        List<ItemMenu> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        // Deve achatar: LancheVegano, LancheCarne, Refrigerante, BatataFrita, SucoNatural
        assertEquals(5, result.size());
        assertTrue(result.get(0) instanceof LancheVegano);
        assertTrue(result.get(1) instanceof LancheCarne);
        assertTrue(result.get(2) instanceof Refrigerante);
        assertTrue(result.get(3) instanceof BatataFrita);
        assertTrue(result.get(4) instanceof SucoNatural);
    }
}
