package hamburgueria.pedido;

import hamburgueria.produto.ComboComposite;
import hamburgueria.produto.ItemMenu;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Iterator concreto para percorrer os itens de um pedido de forma profunda (deep traversal).
 * Se encontrar um ComboComposite, navega recursivamente nos seus itens internos,
 * retornando apenas os itens folha (Lanche, Bebida, Acompanhamento individuais) para a cozinha.
 */
public class PedidoDeepIterator implements Iterator<ItemMenu> {
    private final Stack<Iterator<ItemMenu>> stack = new Stack<>();

    public PedidoDeepIterator(List<ItemMenu> itens) {
        this.stack.push(itens.iterator());
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        }
        Iterator<ItemMenu> currentIterator = stack.peek();
        if (!currentIterator.hasNext()) {
            stack.pop();
            return hasNext();
        }
        return true;
    }

    @Override
    public ItemMenu next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Iterator<ItemMenu> currentIterator = stack.peek();
        ItemMenu nextItem = currentIterator.next();
        if (nextItem instanceof ComboComposite) {
            ComboComposite combo = (ComboComposite) nextItem;
            stack.push(combo.getItens().iterator());
            // Recurso para entrar no composite e retornar o primeiro item folha dele
            return next();
        }
        return nextItem;
    }
}
