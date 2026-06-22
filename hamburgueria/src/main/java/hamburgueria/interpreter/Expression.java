package hamburgueria.interpreter;

import hamburgueria.produto.ItemMenu;

/**
 * Interface Expression do padrão Interpreter (GoF).
 * Define a operação de interpretação para a nossa DSL de pedidos textuais.
 */
public interface Expression {
    ItemMenu interpretar();
}
