package hamburgueria.produto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Flyweight — Compartilhamento de Ingredientes")
class IngredienteFlyweightTest {

    @BeforeEach
    void setUp() {
        IngredienteFactory.limparPool();
    }

    @Test
    @DisplayName("deve compartilhar instâncias idênticas de ingredientes no pool")
    void deveCompartilharInstanciasNoPool() {
        IngredienteFlyweight queijo1 = IngredienteFactory.getIngrediente("Queijo Cheddar", 110);
        IngredienteFlyweight queijo2 = IngredienteFactory.getIngrediente("Queijo Cheddar", 110);

        // Devem ser exatamente a mesma referência de objeto na memória
        assertSame(queijo1, queijo2);
        assertEquals(1, IngredienteFactory.getQuantidadeNoPool());
    }

    @Test
    @DisplayName("deve criar novas instâncias apenas se o ingrediente não existir no pool")
    void deveCriarNovasInstanciasApenasSeNovo() {
        IngredienteFlyweight queijo = IngredienteFactory.getIngrediente("Queijo Cheddar", 110);
        IngredienteFlyweight bacon = IngredienteFactory.getIngrediente("Bacon Crocante", 150);

        assertNotSame(queijo, bacon);
        assertEquals(2, IngredienteFactory.getQuantidadeNoPool());
    }

    @Test
    @DisplayName("deve calcular calorias totais combinando estado intrínseco (calorias) e extrínseco (quantidade)")
    void deveCalcularCaloriasCombinandoEstados() {
        IngredienteFlyweight baconFlyweight = IngredienteFactory.getIngrediente("Bacon Crocante", 150);

        ItemIngrediente porcao1 = new ItemIngrediente(baconFlyweight, 2); // 2x Bacon (300 kcal)
        ItemIngrediente porcao2 = new ItemIngrediente(baconFlyweight, 3); // 3x Bacon (450 kcal)

        assertEquals(300.0, porcao1.getCaloriasTotais());
        assertEquals("2x Bacon Crocante (300.0 kcal)", porcao1.getDetalhe());

        assertEquals(450.0, porcao2.getCaloriasTotais());
        assertEquals("3x Bacon Crocante (450.0 kcal)", porcao2.getDetalhe());
    }
}
