package hamburgueria.pedido;

import hamburgueria.caixa.Caixa;
import hamburgueria.combo.AcompanhamentoFactory;
import hamburgueria.combo.ComboFactory;
import hamburgueria.combo.ComboTradicionalFactory;
import hamburgueria.combo.ComboVeganoFactory;
import hamburgueria.desconto.*;
import hamburgueria.fidelidade.*;
import hamburgueria.pagamento.FormaPagamento;
import hamburgueria.pagamento.PagamentoCartao;
import hamburgueria.pagamento.PagamentoPix;
import hamburgueria.produto.Acompanhamento;
import hamburgueria.produto.Bebida;
import hamburgueria.produto.Lanche;
import hamburgueria.produto.decorator.AdicionalBacon;
import hamburgueria.produto.decorator.AdicionalQueijo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste de integração completo da Hamburgueria.
 * Combina todos os padrões: Abstract Factory, Decorator, Factory Method,
 * Bridge, Singleton, e os novos Strategy (Desconto + Fidelidade).
 */
@DisplayName("Integração — Pedido com todos os padrões")
class PedidoIntegracaoTest {

    @BeforeEach
    void setUp() {
        Caixa.getInstancia().zerarCaixa();
    }

    // =========================================================================
    // Teste legado (retrocompatibilidade)
    // =========================================================================
    @Test
    @DisplayName("deve manter retrocompatibilidade com construtores antigos")
    void deveFazerPedidoCompletoRetrocompativel() {
        // 1. ABSTRACT FACTORY
        ComboFactory comboTradicional = new ComboTradicionalFactory();
        Lanche meuLanche = comboTradicional.criarLanche();
        Bebida minhaBebida = comboTradicional.criarBebida();

        assertEquals("Refrigerante Cola", minhaBebida.getDescricao());

        // 2. DECORATOR
        meuLanche = new AdicionalQueijo(meuLanche);
        meuLanche = new AdicionalBacon(meuLanche);

        assertEquals("Hambúrguer de Carne Artesanal + Queijo Cheddar + Bacon Crocante",
                     meuLanche.getDescricao());
        assertEquals(33.50, meuLanche.getPreco(), 0.01);

        // 3. FACTORY METHOD
        Acompanhamento extra = AcompanhamentoFactory.criar("Batata");
        assertEquals("Porção de Batata Frita", extra.emitir());

        // 4. BRIDGE + SINGLETON (construtor retrocompatível, sem Strategy)
        FormaPagamento pix = new PagamentoPix();
        Pedido meuPedido = new PedidoDelivery(pix);
        double valorPago = meuPedido.finalizarPedido(meuLanche.getPreco());

        // 33.50 + 10.00 (frete) = 43.50 * 0.90 (Pix) = 39.15
        assertEquals(39.15, valorPago, 0.01);

        // 5. SINGLETON
        assertEquals(39.15, Caixa.getInstancia().getSaldoTotal(), 0.01);
    }

    // =========================================================================
    // Strategy + Open/Closed Principle
    // =========================================================================
    @Nested
    @DisplayName("Prova do Open/Closed Principle (OCP)")
    class OpenClosedPrincipleTest {

        @Test
        @DisplayName("trocar DescontoStrategy NÃO modifica Pedido — OCP demonstrado")
        void devePermitirTrocarDescontoSemModificarPedido() {
            FormaPagamento cartao = new PagamentoCartao();

            // Cenário 1: SemDesconto
            Pedido pedido1 = new PedidoSalao(cartao, new SemDesconto(), new SemFidelidade());
            // R$ 100 * 1.05 (taxa cartão) = R$ 105
            assertEquals(105.0, pedido1.finalizarPedido(100.0), 0.01);

            Caixa.getInstancia().zerarCaixa();

            // Cenário 2: 10% desconto — mesma classe Pedido, outra Strategy
            Pedido pedido2 = new PedidoSalao(cartao, new DescontoPercentual(10.0), new SemFidelidade());
            // R$ 100 * 0.90 (desconto) = R$ 90 * 1.05 (cartão) = R$ 94.50
            assertEquals(94.50, pedido2.finalizarPedido(100.0), 0.01);

            Caixa.getInstancia().zerarCaixa();

            // Cenário 3: Desconto Progressivo — mesma classe Pedido, outra Strategy
            Pedido pedido3 = new PedidoSalao(cartao, new DescontoProgressivo(), new SemFidelidade());
            // R$ 120 > 100, logo 15% desconto: 120 * 0.85 = 102 * 1.05 = 107.10
            assertEquals(107.10, pedido3.finalizarPedido(120.0), 0.01);
        }

        @Test
        @DisplayName("trocar FidelidadeStrategy NÃO modifica Pedido — OCP demonstrado")
        void devePermitirTrocarFidelidadeSemModificarPedido() {
            FormaPagamento pix = new PagamentoPix();

            // Cenário: Bronze no Salão
            Pedido pedidoBronze = new PedidoSalao(pix, new SemDesconto(), new FidelidadeBronze());
            // R$ 100 * 0.95 (fidelidade) = R$ 95 * 0.90 (Pix) = R$ 85.50
            assertEquals(85.50, pedidoBronze.finalizarPedido(100.0), 0.01);

            Caixa.getInstancia().zerarCaixa();

            // Cenário: Ouro no Salão
            Pedido pedidoOuro = new PedidoSalao(pix, new SemDesconto(), new FidelidadeOuro());
            // R$ 100 * 0.85 (fidelidade) = R$ 85 * 0.90 (Pix) = R$ 76.50
            assertEquals(76.50, pedidoOuro.finalizarPedido(100.0), 0.01);
        }
    }

    // =========================================================================
    // Pipeline completo: Desconto + Fidelidade + Entrega + Pagamento
    // =========================================================================
    @Nested
    @DisplayName("Pipeline completo de cálculo")
    class PipelineCompletoTest {

        @Test
        @DisplayName("Delivery com Ouro: desconto + fidelidade + frete grátis")
        void deliveryOuroComDescontoProgressivo() {
            FormaPagamento pix = new PagamentoPix();
            DescontoStrategy desconto = new DescontoProgressivo();
            FidelidadeStrategy fidelidade = new FidelidadeOuro();

            Pedido pedido = new PedidoDelivery(pix, desconto, fidelidade);

            // R$ 120 → progressivo >100: 120*0.85=102
            //        → ouro 15%: 102*0.85=86.70
            //        → frete grátis (Ouro): +0
            //        → Pix 10%: 86.70*0.90=78.03
            assertEquals(78.03, pedido.finalizarPedido(120.0), 0.01);
        }

        @Test
        @DisplayName("Delivery com Bronze: desconto fixo + fidelidade + frete cobrado")
        void deliveryBronzeComDescontoFixo() {
            Caixa.getInstancia().zerarCaixa();
            FormaPagamento cartao = new PagamentoCartao();
            DescontoStrategy desconto = new DescontoValorFixo(10.0);
            FidelidadeStrategy fidelidade = new FidelidadeBronze();

            Pedido pedido = new PedidoDelivery(cartao, desconto, fidelidade);

            // R$ 80 → fixo -10: R$ 70
            //       → bronze 5%: 70*0.95=66.50
            //       → frete: +10 = 76.50
            //       → cartão +5%: 76.50*1.05=80.325
            assertEquals(80.325, pedido.finalizarPedido(80.0), 0.01);
        }

        @Test
        @DisplayName("Salão com Prata: desconto percentual + fidelidade")
        void salaoComPrataEDescontoPercentual() {
            Caixa.getInstancia().zerarCaixa();
            FormaPagamento pix = new PagamentoPix();
            DescontoStrategy desconto = new DescontoPercentual(20.0);
            FidelidadeStrategy fidelidade = new FidelidadePrata();

            Pedido pedido = new PedidoSalao(pix, desconto, fidelidade);

            // R$ 100 → 20% desconto: 80
            //        → prata 10%: 80*0.90=72
            //        → Pix 10%: 72*0.90=64.80
            assertEquals(64.80, pedido.finalizarPedido(100.0), 0.01);
        }
    }

    // =========================================================================
    // Combo Vegano com Strategy
    // =========================================================================
    @Test
    @DisplayName("Combo Vegano + Decorator + Strategy completa")
    void comboVeganoComStrategyCompleta() {
        Caixa.getInstancia().zerarCaixa();

        // Abstract Factory: Combo Vegano
        ComboFactory combo = new ComboVeganoFactory();
        Lanche lanche = combo.criarLanche();
        Bebida bebida = combo.criarBebida();

        assertEquals("Suco Natural de Laranja", bebida.getDescricao());

        // Decorator: adiciona queijo
        lanche = new AdicionalQueijo(lanche);
        assertEquals(31.50, lanche.getPreco(), 0.01); // 28 + 3.50

        // Strategy Pipeline: Ouro + Progressivo + Pix + Salão
        Pedido pedido = new PedidoSalao(
            new PagamentoPix(),
            new DescontoPercentual(5.0),
            new FidelidadeOuro()
        );

        // R$ 31.50 → 5% desc: 29.925
        //          → Ouro 15%: 29.925*0.85=25.43625
        //          → Pix 10%: 25.43625*0.90=22.892625
        double valor = pedido.finalizarPedido(lanche.getPreco());
        assertEquals(22.89, valor, 0.01);
    }
}
