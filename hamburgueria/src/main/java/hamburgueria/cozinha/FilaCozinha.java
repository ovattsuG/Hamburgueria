package hamburgueria.cozinha;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Invoker/Caretaker do padrão Command.
 * Gerencia a fila de preparos e o histórico de execuções para possibilitar undo (desfazer).
 */
public class FilaCozinha {
    private final List<CozinhaCommand> fila = new ArrayList<>();
    private final Stack<CozinhaCommand> historico = new Stack<>();

    public void adicionarCommand(CozinhaCommand command) {
        fila.add(command);
    }

    public void processarProximo() {
        if (!fila.isEmpty()) {
            CozinhaCommand command = fila.remove(0);
            command.executar();
            historico.push(command);
        }
    }

    public void desfazerUltimo() {
        if (!historico.isEmpty()) {
            CozinhaCommand command = historico.pop();
            command.desfazer();
            // Coloca de volta no início da fila para re-processamento
            fila.add(0, command);
        }
    }

    public List<CozinhaCommand> getFila() {
        return fila;
    }

    public Stack<CozinhaCommand> getHistorico() {
        return historico;
    }
}
