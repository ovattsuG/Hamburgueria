package hamburgueria.produto;

import hamburgueria.produto.decorator.AdicionalBacon;
import hamburgueria.produto.decorator.AdicionalQueijo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Prototype — Clonagem de Lanches")
class LanchePrototypeTest {

    @Test
    @DisplayName("deve clonar um lanche simples com sucesso")
    void deveClonarLancheSimples() {
        Lanche original = new LancheCarne();
        Lanche clone = original.clonar();

        assertNotNull(clone);
        assertNotSame(original, clone);
        assertEquals(original.getDescricao(), clone.getDescricao());
        assertEquals(original.getPreco(), clone.getPreco());
    }

    @Test
    @DisplayName("deve clonar recursivamente uma cadeia de decorators (cópia profunda)")
    void deveClonarCadeiaDeDecorators() {
        Lanche original = new LancheVegano(); // R$ 28.00
        original = new AdicionalQueijo(original); // R$ 31.50
        original = new AdicionalBacon(original); // R$ 36.50

        Lanche clone = original.clonar();

        assertNotNull(clone);
        assertNotSame(original, clone); // Garante que são instâncias diferentes em memória
        assertEquals(original.getDescricao(), clone.getDescricao());
        assertEquals(original.getPreco(), clone.getPreco());
        assertEquals("Hambúrguer de Grão de Bico + Queijo Cheddar + Bacon Crocante", clone.getDescricao());
        assertEquals(36.50, clone.getPreco(), 0.01);
    }
}
