package hamburgueria.pedido;

import java.util.Stack;

/**
 * Caretaker no padrão Memento (GoF).
 * Armazena o histórico de snapshots (mementos) e executa ações de salvar e desfazer (undo).
 */
public class PedidoCaretaker {
    private final Stack<PedidoMemento> historico = new Stack<>();

    public void salvar(Pedido pedido) {
        historico.push(pedido.salvarMemento());
    }

    public void desfazer(Pedido pedido) {
        if (!historico.isEmpty()) {
            pedido.restaurarMemento(historico.pop());
        }
    }

    public int getHistoricoSize() {
        return historico.size();
    }
}
