package hamburgueria.desconto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes TDD para todas as DescontoStrategy.
 * Segue o ciclo RED → GREEN → REFACTOR.
 */
@DisplayName("DescontoStrategy")
class DescontoStrategyTest {

    // =========================================================================
    // SemDesconto
    // =========================================================================
    @Nested
    @DisplayName("SemDesconto")
    class SemDescontoTest {

        private final DescontoStrategy strategy = new SemDesconto();

        @Test
        @DisplayName("deve retornar o valor original sem alteração")
        void deveRetornarValorOriginal() {
            assertEquals(50.0, strategy.aplicar(50.0), 0.01);
        }

        @Test
        @DisplayName("deve retornar zero para valor zero")
        void deveRetornarZeroParaZero() {
            assertEquals(0.0, strategy.aplicar(0.0), 0.01);
        }

        @Test
        @DisplayName("deve ter descrição correta")
        void deveRetornarDescricao() {
            assertEquals("Sem desconto", strategy.getDescricao());
        }
    }

    // =========================================================================
    // DescontoPercentual
    // =========================================================================
    @Nested
    @DisplayName("DescontoPercentual")
    class DescontoPercentualTest {

        @Test
        @DisplayName("10% de desconto em R$ 100 deve retornar R$ 90")
        void deveAplicar10Porcento() {
            DescontoStrategy strategy = new DescontoPercentual(10.0);
            assertEquals(90.0, strategy.aplicar(100.0), 0.01);
        }

        @Test
        @DisplayName("25% de desconto em R$ 80 deve retornar R$ 60")
        void deveAplicar25Porcento() {
            DescontoStrategy strategy = new DescontoPercentual(25.0);
            assertEquals(60.0, strategy.aplicar(80.0), 0.01);
        }

        @Test
        @DisplayName("0% de desconto deve retornar valor original")
        void deveRetornarOriginalComZeroPorcento() {
            DescontoStrategy strategy = new DescontoPercentual(0.0);
            assertEquals(100.0, strategy.aplicar(100.0), 0.01);
        }

        @Test
        @DisplayName("100% de desconto deve retornar zero")
        void deveRetornarZeroCom100Porcento() {
            DescontoStrategy strategy = new DescontoPercentual(100.0);
            assertEquals(0.0, strategy.aplicar(50.0), 0.01);
        }

        @Test
        @DisplayName("deve lançar exceção para percentual negativo")
        void deveLancarExcecaoParaPercentualNegativo() {
            assertThrows(IllegalArgumentException.class, () -> new DescontoPercentual(-5.0));
        }

        @Test
        @DisplayName("deve lançar exceção para percentual acima de 100")
        void deveLancarExcecaoParaPercentualAcima100() {
            assertThrows(IllegalArgumentException.class, () -> new DescontoPercentual(150.0));
        }

        @Test
        @DisplayName("deve ter descrição correta")
        void deveRetornarDescricao() {
            DescontoStrategy strategy = new DescontoPercentual(10.0);
            assertEquals("Desconto de 10.0%", strategy.getDescricao());
        }
    }

    // =========================================================================
    // DescontoValorFixo
    // =========================================================================
    @Nested
    @DisplayName("DescontoValorFixo")
    class DescontoValorFixoTest {

        @Test
        @DisplayName("R$ 5 de desconto em R$ 50 deve retornar R$ 45")
        void deveAplicarDescontoFixo() {
            DescontoStrategy strategy = new DescontoValorFixo(5.0);
            assertEquals(45.0, strategy.aplicar(50.0), 0.01);
        }

        @Test
        @DisplayName("desconto maior que valor deve retornar R$ 0 (floor)")
        void deveRetornarZeroQuandoDescontoExcedeValor() {
            DescontoStrategy strategy = new DescontoValorFixo(100.0);
            assertEquals(0.0, strategy.aplicar(30.0), 0.01);
        }

        @Test
        @DisplayName("desconto igual ao valor deve retornar R$ 0")
        void deveRetornarZeroQuandoDescontoIgualValor() {
            DescontoStrategy strategy = new DescontoValorFixo(50.0);
            assertEquals(0.0, strategy.aplicar(50.0), 0.01);
        }

        @Test
        @DisplayName("deve lançar exceção para valor negativo")
        void deveLancarExcecaoParaValorNegativo() {
            assertThrows(IllegalArgumentException.class, () -> new DescontoValorFixo(-10.0));
        }

        @Test
        @DisplayName("deve ter descrição correta")
        void deveRetornarDescricao() {
            DescontoStrategy strategy = new DescontoValorFixo(5.0);
            assertTrue(strategy.getDescricao().contains("5,00") || strategy.getDescricao().contains("5.00"));
        }
    }

    // =========================================================================
    // DescontoProgressivo
    // =========================================================================
    @Nested
    @DisplayName("DescontoProgressivo")
    class DescontoProgressivoTest {

        private final DescontoStrategy strategy = new DescontoProgressivo();

        @Test
        @DisplayName("valor <= R$ 50 NÃO recebe desconto")
        void semDescontoAteCinquenta() {
            assertEquals(50.0, strategy.aplicar(50.0), 0.01);
            assertEquals(30.0, strategy.aplicar(30.0), 0.01);
        }

        @Test
        @DisplayName("valor > R$ 50 e <= R$ 100 recebe 10% de desconto")
        void dezPorcentoEntre51e100() {
            // R$ 80 * 0.90 = R$ 72
            assertEquals(72.0, strategy.aplicar(80.0), 0.01);
        }

        @Test
        @DisplayName("valor > R$ 100 recebe 15% de desconto")
        void quinzePorcentoAcimaDe100() {
            // R$ 120 * 0.85 = R$ 102
            assertEquals(102.0, strategy.aplicar(120.0), 0.01);
        }

        @Test
        @DisplayName("limites exatos das faixas")
        void limitesExatos() {
            // R$ 50.01 > 50, logo 10% → 50.01 * 0.90 = 45.009
            assertEquals(45.009, strategy.aplicar(50.01), 0.01);
            // R$ 100.01 > 100, logo 15% → 100.01 * 0.85 = 85.0085
            assertEquals(85.0085, strategy.aplicar(100.01), 0.01);
        }

        @Test
        @DisplayName("deve ter descrição correta")
        void deveRetornarDescricao() {
            assertTrue(strategy.getDescricao().contains("progressivo"));
        }
    }
}
