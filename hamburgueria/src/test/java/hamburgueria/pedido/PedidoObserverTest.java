package hamburgueria.pedido;

import hamburgueria.pagamento.PagamentoPix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Observer — Notificação de Eventos")
class PedidoObserverTest {

    @Test
    @DisplayName("deve notificar todos os observers registrados quando o status do pedido mudar")
    void deveNotificarObserversNoStatusChange() {
        Pedido pedido = new PedidoSalao(new PagamentoPix());

        PainelCozinhaObserver cozinha = new PainelCozinhaObserver();
        AppClienteObserver cliente = new AppClienteObserver();

        pedido.registrarObserver(cozinha);
        pedido.registrarObserver(cliente);

        // Avança de Recebido para Em Preparação
        pedido.avancarEstado();

        assertEquals("Em Preparação", cozinha.getUltimoStatus());
        assertEquals("Em Preparação", cliente.getUltimoStatus());

        // Avança de Em Preparação para Pronto
        pedido.avancarEstado();

        assertEquals("Pronto", cozinha.getUltimoStatus());
        assertEquals("Pronto", cliente.getUltimoStatus());
    }

    @Test
    @DisplayName("deve parar de notificar um observer após ele ser removido")
    void devePararDeNotificarObserverRemovido() {
        Pedido pedido = new PedidoSalao(new PagamentoPix());

        PainelCozinhaObserver cozinha = new PainelCozinhaObserver();
        pedido.registrarObserver(cozinha);

        pedido.avancarEstado(); // Em Preparação
        assertEquals("Em Preparação", cozinha.getUltimoStatus());

        pedido.removerObserver(cozinha);
        pedido.avancarEstado(); // Pronto

        // O status no observador removido não deve ter sido atualizado para "Pronto"
        assertEquals("Em Preparação", cozinha.getUltimoStatus());
    }
}
