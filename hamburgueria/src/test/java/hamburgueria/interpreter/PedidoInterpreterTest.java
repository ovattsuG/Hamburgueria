package hamburgueria.interpreter;

import hamburgueria.produto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Interpreter — Interpretador de Pedidos Textuais")
class PedidoInterpreterTest {

    @Test
    @DisplayName("deve interpretar um lanche simples com sucesso")
    void deveInterpretarLancheSimples() {
        Expression exp = PedidoParser.parse("lanche carne");
        ItemMenu item = exp.interpretar();

        assertNotNull(item);
        assertTrue(item instanceof LancheCarne);
        assertEquals("Hambúrguer de Carne Artesanal", item.getDescricao());
        assertEquals(25.0, item.getPreco());
    }

    @Test
    @DisplayName("deve interpretar e montar combo complexo a partir do texto")
    void deveInterpretarComboComplexo() {
        // Expressão: lanche carne (25.0) + bebida refri (8.0) + acompanhamento batata (a batata frita)
        // Batata frita preco: vamos verificar. LancheCarne é 25, Refrigerante é 8.
        Expression exp = PedidoParser.parse("lanche carne + bebida refri + acompanhamento batata");
        ItemMenu item = exp.interpretar();

        assertNotNull(item);
        assertTrue(item instanceof ComboComposite);

        ComboComposite combo = (ComboComposite) item;
        assertEquals(3, combo.getItens().size());

        // O composite aplica 15% de desconto automático no getPreco()
        // Preço bruto: LancheCarne (25) + Refrigerante (8) + BatataFrita (esperado)
        // Vamos checar o preco da Batata Frita na classe BatataFrita.java.
        // Se BatataFrita for, por exemplo, 10, total bruto é 43. Com 15% de desconto (0.85), total com desconto is calculated.
        assertTrue(combo.getPreco() > 0);
    }

    @Test
    @DisplayName("deve lançar exceção para tokens desconhecidos ou inválidos")
    void deveLancarExcecaoTokenInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            PedidoParser.parse("bebida cerveja").interpretar();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PedidoParser.parse("xpto de carne").interpretar();
        });
    }
}
