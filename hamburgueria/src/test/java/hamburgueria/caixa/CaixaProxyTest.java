package hamburgueria.caixa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Proxy — Segurança e Auditoria do Caixa")
class CaixaProxyTest {

    private Caixa caixaReal;

    @BeforeEach
    void setUp() {
        caixaReal = Caixa.getInstancia();
        caixaReal.zerarCaixa();
    }

    @Test
    @DisplayName("deve permitir registrar vendas e consultar saldo através de qualquer cargo")
    void devePermitirRegistrarVendasQualquerCargo() {
        ICaixa caixaAtendente = new CaixaProxy("ATENDENTE");
        caixaAtendente.registrarVenda(100.0);

        assertEquals(100.0, caixaAtendente.getSaldoTotal(), 0.01);
    }

    @Test
    @DisplayName("deve autorizar zerar o caixa se o cargo for GERENTE")
    void deveAutorizarZerarCaixaParaGerente() {
        ICaixa caixaAtendente = new CaixaProxy("ATENDENTE");
        caixaAtendente.registrarVenda(100.0);

        ICaixa caixaGerente = new CaixaProxy("GERENTE");
        caixaGerente.zerarCaixa();

        assertEquals(0.0, caixaGerente.getSaldoTotal(), 0.01);
    }

    @Test
    @DisplayName("deve lançar exceção e negar fechamento de caixa se o cargo não for GERENTE")
    void deveNegarZerarCaixaParaOutrosCargos() {
        ICaixa caixaAtendente = new CaixaProxy("ATENDENTE");
        caixaAtendente.registrarVenda(100.0);

        assertThrows(SecurityException.class, () -> {
            caixaAtendente.zerarCaixa();
        });

        // O saldo deve permanecer intocado
        assertEquals(100.0, caixaAtendente.getSaldoTotal(), 0.01);
    }
}
