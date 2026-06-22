package hamburgueria.cozinha;

import hamburgueria.produto.LancheCarne;
import hamburgueria.produto.Refrigerante;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Command — Comandos da Cozinha")
class CozinhaCommandTest {

    @Test
    @DisplayName("deve executar e desfazer o preparo de um lanche com sucesso")
    void deveExecutarEDesfazerLanche() {
        LancheCarne lanche = new LancheCarne();
        PrepararLancheCommand cmd = new PrepararLancheCommand(lanche);

        assertEquals("Pendente", cmd.getStatus());

        cmd.executar();
        assertEquals("Preparado", cmd.getStatus());

        cmd.desfazer();
        assertEquals("Pendente", cmd.getStatus());
    }

    @Test
    @DisplayName("deve executar e desfazer o preparo de uma bebida com sucesso")
    void deveExecutarEDesfazerBebida() {
        Refrigerante refrigerante = new Refrigerante();
        PrepararBebidaCommand cmd = new PrepararBebidaCommand(refrigerante);

        assertEquals("Pendente", cmd.getStatus());

        cmd.executar();
        assertEquals("Preparado", cmd.getStatus());

        cmd.desfazer();
        assertEquals("Pendente", cmd.getStatus());
    }

    @Test
    @DisplayName("deve gerenciar fila e historico (undo) na FilaCozinha")
    void deveGerenciarFilaEHistorico() {
        FilaCozinha fila = new FilaCozinha();
        LancheCarne lanche = new LancheCarne();
        Refrigerante refrigerante = new Refrigerante();

        CozinhaCommand cmd1 = new PrepararLancheCommand(lanche);
        CozinhaCommand cmd2 = new PrepararBebidaCommand(refrigerante);

        fila.adicionarCommand(cmd1);
        fila.adicionarCommand(cmd2);

        assertEquals(2, fila.getFila().size());
        assertEquals(0, fila.getHistorico().size());

        // Processar lanche
        fila.processarProximo();
        assertEquals(1, fila.getFila().size());
        assertEquals(1, fila.getHistorico().size());
        assertEquals("Preparado", cmd1.getStatus());
        assertEquals("Pendente", cmd2.getStatus());

        // Processar bebida
        fila.processarProximo();
        assertEquals(0, fila.getFila().size());
        assertEquals(2, fila.getHistorico().size());
        assertEquals("Preparado", cmd2.getStatus());

        // Desfazer bebida (ultimo)
        fila.desfazerUltimo();
        assertEquals(1, fila.getFila().size());
        assertEquals(1, fila.getHistorico().size());
        assertEquals("Pendente", cmd2.getStatus());
        assertEquals("Preparado", cmd1.getStatus());

        // Desfazer lanche (primeiro)
        fila.desfazerUltimo();
        assertEquals(2, fila.getFila().size());
        assertEquals(0, fila.getHistorico().size());
        assertEquals("Pendente", cmd1.getStatus());
    }
}
