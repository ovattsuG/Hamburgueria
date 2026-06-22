package hamburgueria.produto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Composite — Combos de Itens")
class ComboCompositeTest {

    @Test
    @DisplayName("deve calcular o preço do combo aplicando 15% de desconto")
    void deveCalcularPrecoDoComboComDesconto() {
        // Criar folhas (Leaves)
        Lanche lanche = new LancheCarne(); // R$ 25.00
        Bebida bebida = new Refrigerante(); // R$ 8.00
        Acompanhamento extra = new BatataFrita(); // R$ 10.00

        // Criar composite
        ComboComposite combo = new ComboComposite("Combo Tradicional Completo");
        combo.adicionar(lanche);
        combo.adicionar(bebida);
        combo.adicionar(extra);

        // Preço bruto: 25.00 + 8.00 + 10.00 = 43.00
        // Preço com 15% desc: 43.00 * 0.85 = 36.55
        assertEquals(36.55, combo.getPreco(), 0.01);
        assertEquals("Combo Tradicional Completo (Hambúrguer de Carne Artesanal + Refrigerante Cola + Porção de Batata Frita)", combo.getDescricao());
    }

    @Test
    @DisplayName("deve permitir remover itens do combo")
    void devePermitirRemoverItens() {
        Lanche lanche = new LancheCarne(); // R$ 25.00
        Bebida bebida = new Refrigerante(); // R$ 8.00

        ComboComposite combo = new ComboComposite("Combo Simples");
        combo.adicionar(lanche);
        combo.adicionar(bebida);

        assertEquals(2, combo.getItens().size());

        combo.remover(bebida);

        assertEquals(1, combo.getItens().size());
        // Preço: 25.00 * 0.85 = 21.25
        assertEquals(21.25, combo.getPreco(), 0.01);
    }
}
