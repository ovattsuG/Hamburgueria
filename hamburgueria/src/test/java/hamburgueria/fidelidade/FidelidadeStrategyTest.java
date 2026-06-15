package hamburgueria.fidelidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes TDD para todas as FidelidadeStrategy.
 */
@DisplayName("FidelidadeStrategy")
class FidelidadeStrategyTest {

    // =========================================================================
    // SemFidelidade
    // =========================================================================
    @Nested
    @DisplayName("SemFidelidade")
    class SemFidelidadeTest {

        private final FidelidadeStrategy strategy = new SemFidelidade();

        @Test
        @DisplayName("deve retornar valor sem alteração")
        void deveRetornarValorOriginal() {
            assertEquals(100.0, strategy.aplicarBeneficio(100.0), 0.01);
        }

        @Test
        @DisplayName("frete NÃO é grátis")
        void freteNaoGratis() {
            assertFalse(strategy.isFreteGratis());
        }

        @Test
        @DisplayName("deve ter nível correto")
        void deveRetornarNivel() {
            assertEquals("Sem fidelidade", strategy.getNivel());
        }
    }

    // =========================================================================
    // FidelidadeBronze
    // =========================================================================
    @Nested
    @DisplayName("FidelidadeBronze")
    class FidelidadeBronzeTest {

        private final FidelidadeStrategy strategy = new FidelidadeBronze();

        @Test
        @DisplayName("deve aplicar 5% de desconto")
        void deveAplicar5Porcento() {
            // R$ 100 * 0.95 = R$ 95
            assertEquals(95.0, strategy.aplicarBeneficio(100.0), 0.01);
        }

        @Test
        @DisplayName("frete NÃO é grátis")
        void freteNaoGratis() {
            assertFalse(strategy.isFreteGratis());
        }

        @Test
        @DisplayName("deve ter nível Bronze")
        void deveRetornarNivel() {
            assertTrue(strategy.getNivel().contains("Bronze"));
        }
    }

    // =========================================================================
    // FidelidadePrata
    // =========================================================================
    @Nested
    @DisplayName("FidelidadePrata")
    class FidelidadePrataTest {

        private final FidelidadeStrategy strategy = new FidelidadePrata();

        @Test
        @DisplayName("deve aplicar 10% de desconto")
        void deveAplicar10Porcento() {
            // R$ 100 * 0.90 = R$ 90
            assertEquals(90.0, strategy.aplicarBeneficio(100.0), 0.01);
        }

        @Test
        @DisplayName("frete NÃO é grátis")
        void freteNaoGratis() {
            assertFalse(strategy.isFreteGratis());
        }

        @Test
        @DisplayName("deve ter nível Prata")
        void deveRetornarNivel() {
            assertTrue(strategy.getNivel().contains("Prata"));
        }
    }

    // =========================================================================
    // FidelidadeOuro
    // =========================================================================
    @Nested
    @DisplayName("FidelidadeOuro")
    class FidelidadeOuroTest {

        private final FidelidadeStrategy strategy = new FidelidadeOuro();

        @Test
        @DisplayName("deve aplicar 15% de desconto")
        void deveAplicar15Porcento() {
            // R$ 100 * 0.85 = R$ 85
            assertEquals(85.0, strategy.aplicarBeneficio(100.0), 0.01);
        }

        @Test
        @DisplayName("frete É grátis")
        void freteGratis() {
            assertTrue(strategy.isFreteGratis());
        }

        @Test
        @DisplayName("deve ter nível Ouro")
        void deveRetornarNivel() {
            assertTrue(strategy.getNivel().contains("Ouro"));
        }
    }
}
