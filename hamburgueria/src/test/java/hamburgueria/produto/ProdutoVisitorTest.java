package hamburgueria.produto;

import hamburgueria.produto.decorator.AdicionalQueijo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Visitor — Operações Acessórias sobre Produtos")
class ProdutoVisitorTest {

    @Test
    @DisplayName("deve calcular calorias totais com sucesso")
    void deveCalcularCalorias() {
        Lanche lanche = new LancheCarne(); // 600 kcal
        lanche = new AdicionalQueijo(lanche); // +100 kcal = 700 kcal
        Bebida bebida = new Refrigerante(); // 150 kcal

        ComboComposite combo = new ComboComposite("Combo 1");
        combo.adicionar(lanche);
        combo.adicionar(bebida);

        CalculadorCaloriasVisitor visitor = new CalculadorCaloriasVisitor();
        combo.aceitar(visitor);

        // 700 (Lanche + Queijo) + 150 (Refrigerante) = 850
        assertEquals(850, visitor.getTotalCalorias());
    }

    @Test
    @DisplayName("deve gerar cupom fiscal formatado com sucesso")
    void deveGerarCupom() {
        Lanche lanche = new LancheCarne();
        Bebida bebida = new Refrigerante();

        ComboComposite combo = new ComboComposite("Combo 1");
        combo.adicionar(lanche);
        combo.adicionar(bebida);

        ImpressorCupomVisitor visitor = new ImpressorCupomVisitor();
        combo.aceitar(visitor);

        String cupom = visitor.getCupom();

        assertTrue(cupom.contains("Combo:  Combo 1"));
        assertTrue(cupom.contains("* Lanche: Hambúrguer de Carne Artesanal"));
        assertTrue(cupom.contains("* Bebida: Refrigerante Cola"));
    }
}
